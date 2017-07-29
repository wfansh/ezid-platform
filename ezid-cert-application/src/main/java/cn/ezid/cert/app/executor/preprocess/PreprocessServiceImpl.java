package cn.ezid.cert.app.executor.preprocess;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ezid.cert.app.executor.ExecutorException;
import cn.ezid.cert.core.oss.OSSService;
import cn.ezid.cert.core.util.CommandUtils;
import cn.ezid.cert.core.util.EZUtils;

/**
 *  ffmpeg语法：
 * -ab bitrate 设置音频码率
 * -ar freq 设置音频采样率
 * -f fmt 强迫采用格式fmt
 * -i filename 输入文件 
 * -r fps 设置帧频 缺省25
 * -y 覆盖输出文件
 * -vf transpose=1顺时针旋转90度
 * -vf transpose=2逆时针旋转90度
 * 
 * @author Fan
 *
 */
@Service
public class PreprocessServiceImpl implements PreprocessService {
	private static final Logger log = LoggerFactory.getLogger(PreprocessServiceImpl.class);

	private static final String CMD_CONVERT_IMAGE = "convert -sample 400x300 %s %s";
	private static final String CMD_CONVERT_ROTATE_VIDEO = "ffmpeg -y -i %s -vf transpose=1 -strict experimental -ar 44100 -ab 96 -f flv %s";
	private static final String CMD_CONVERT_ROTATE_180_VIDEO = "ffmpeg -y -i %s -vf transpose=2 -strict experimental -ar 44100 -ab 96 -f flv %s";
	private static final String CMD_GET_VIDEO_FIRST_FRAME = "ffmpeg -y -i %s -vframes 1 -f image2 %s";

	@Autowired
	private OSSService ossService;

	@Override
	public void preprocessPhoto(String photo) throws ExecutorException {
		log.info("Start preprocess photo {}.", photo);

		String fileExtension = getFileExtension(photo, "jpg");
		File tempFile = null;
		File tempTargetFile = null;

		try {
			tempFile = File.createTempFile("photo", "." + fileExtension);
			ossService.downloadFile(photo, tempFile);

			tempTargetFile = File.createTempFile("targetphoto", "." + fileExtension);
			String cmd = String.format(CMD_CONVERT_IMAGE, tempFile.getAbsoluteFile(), tempTargetFile.getAbsoluteFile());
			log.debug("Executing command {}.", cmd);
			String output = CommandUtils.executeCommand(cmd);
			log.debug("Execute command result: {}", output);

			if (tempTargetFile.exists() && EZUtils.getFileSize(tempTargetFile) > 0) {
				ossService.uploadFile(photo + "_s." + fileExtension, tempTargetFile);
			} else {
				log.warn("Convert photo file failed, use original file as the thumbnail.");
				ossService.uploadFile(photo + "_s." + fileExtension, tempFile);
			}
		} catch (Exception e) {
			log.warn("Convert photo file failed.", e);
			throw new ExecutorException(e);
		} finally {
			if (tempFile != null)
				tempFile.delete();

			if (tempTargetFile != null)
				tempTargetFile.delete();
		}
	}

	@Override
	public void preprocessVideo(String video) throws ExecutorException {
		log.info("Start preprocess person video {}.", video);

		String fileExtension = getFileExtension(video, "mp4");
		File tempFile = null;
		File tempRotateFile = null;
		File tempRotate180File = null;
		File tempImgTargetFile = null;
		File tempFlvInputFile = null;

		try {
			tempFile = File.createTempFile("video", "." + fileExtension);
			ossService.downloadFile(video, tempFile);

			// 1. Rotate
			tempRotateFile = File.createTempFile("rotatedvideo", ".flv");
			String rotateCmd = String.format(CMD_CONVERT_ROTATE_VIDEO, tempFile.getAbsoluteFile(),
					tempRotateFile.getAbsoluteFile());
			log.info("Executing command {}.", rotateCmd);
			String rotateOutput = CommandUtils.executeCommand(rotateCmd);
			log.info("Execute command result: {}.", rotateOutput);
			if (tempRotateFile.exists() && EZUtils.getFileSize(tempRotateFile) > 0) {
				tempFlvInputFile = tempRotateFile;
				ossService.uploadFile(video + ".flv", tempRotateFile);
			} else {
				log.warn("Convert video file failed: {}.", rotateCmd);
				tempFlvInputFile = tempFile;
				ossService.uploadFile(video + ".flv", tempFile);
			}

			// 2. Rotate 180
			tempRotate180File = File.createTempFile("rotated180video", ".flv");
			String r180Cmd = String.format(CMD_CONVERT_ROTATE_180_VIDEO, tempFile.getAbsoluteFile(),
					tempRotate180File.getAbsoluteFile());
			log.info("Executing command {}", r180Cmd);
			String r180Output = CommandUtils.executeCommand(r180Cmd);
			log.info("Execute command result: {}.", r180Output);
			if (tempRotate180File.exists() && EZUtils.getFileSize(tempRotate180File) > 0) {
				ossService.uploadFile(video + "_180.flv", tempRotate180File);
			} else {
				log.warn("Convert video file 180 failed: {}", r180Cmd);
			}

			// 3. First Frame
			tempImgTargetFile = File.createTempFile("targetImg", ".jpg");
			String firstFrameCmd = String.format(CMD_GET_VIDEO_FIRST_FRAME, tempFile.getAbsoluteFile(),
					tempImgTargetFile.getAbsoluteFile());
			log.info("Executing command {}", firstFrameCmd);
			String firstFrameOutput = CommandUtils.executeCommand(firstFrameCmd);
			log.info("Execute command result: {}.", firstFrameOutput);
			if (tempImgTargetFile.exists() && EZUtils.getFileSize(tempImgTargetFile) > 0) {
				ossService.uploadFile(video + ".jpg", tempImgTargetFile);
			} else {
				log.warn("Convert video file to img failed: {}.", firstFrameOutput);
			}

		} catch (Exception e) {
			log.warn("Convert video file failed", e);
			throw new ExecutorException(e);
		} finally {
			if (tempFile != null)
				tempFile.delete();

			if (tempRotate180File != null)
				tempRotate180File.delete();

			if (tempRotateFile != null)
				tempRotateFile.delete();

			if (tempImgTargetFile != null)
				tempImgTargetFile.delete();

			if (tempFlvInputFile != null)
				tempImgTargetFile.delete();
		}
	}

	private String getFileExtension(String fileName, String defaultValue) {
		if (fileName == null)
			return defaultValue;

		String[] tokens = fileName.split("\\.(?=[^\\.]+$)");
		if (tokens.length < 2)
			return defaultValue;

		return tokens[1];
	}

}
