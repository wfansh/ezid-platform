package cn.ezid.cert.app.executor.machine;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ezid.cert.app.executor.ExecutorException;
import cn.ezid.cert.core.oss.OSSService;
import cn.ezid.cert.core.util.CommandUtils;
import cn.ezid.cert.core.util.EZUtils;
import cn.ezid.cert.core.util.EZUtils.OSPlatform;

@Service
public class MachineServiceImpl implements MachineService {
	private static final Logger log = LoggerFactory.getLogger(MachineServiceImpl.class);

	private static final String DETECT_CMD = "br -algorithm FaceDetection -enrollAll -enroll";
	private static final String COMPARE_CMD = "br -algorithm FaceRecognition -compare";
	private static final String TARGET_PATH = "C:\\images\\";

	@Autowired
	private OSSService ossService;

	public MachineServiceImpl() {
		if(EZUtils.getOSPlatform() == OSPlatform.Windows){
			new File(TARGET_PATH).mkdirs();
		}
	}

	@Override
	public boolean detectFace(String srcPhotoUri) throws ExecutorException {
		File srcFile = null;
		try {
			log.info("Start to download photo from aliyun。");
			srcFile = getTempFile(srcPhotoUri, null);
			srcFile.createNewFile();
			ossService.downloadFile(srcPhotoUri, srcFile);

			File csvFile = getTempFile(srcPhotoUri, ".csv");
			CommandUtils.executeCommand(DETECT_CMD + " " + srcFile.getAbsolutePath() + " " + csvFile.getAbsolutePath());

			if (csvFile.exists()) {
				csvFile.delete();
				return true;
			}

			return false;
		} catch (Exception e) {
			log.error("Caught exception when detecting face.", e);
			throw new ExecutorException(e);
		} finally {
			if (srcFile != null)
				srcFile.delete();
		}
	}

	@Override
	public long compareFace(String srcPhotoUri, String targetPhotoUri) throws ExecutorException {
		File srcFile = null;
		File targetFile = null;
		try {
			// Maybe already downloaded in checkFace
			srcFile = getTempFile(srcPhotoUri, null);

			if (!srcFile.exists()) {
				srcFile.createNewFile();
				ossService.downloadFile(srcPhotoUri, srcFile);
			}

			targetFile = getTempFile(targetPhotoUri, null);
			targetFile.createNewFile();
			ossService.downloadFile(targetPhotoUri, targetFile);

			log.debug("Executing command {}.", COMPARE_CMD);
			// Loop 5 times for br exceptional result
			for (int i = 0; i < 5; i++) {
				String result = CommandUtils.executeCommand(COMPARE_CMD + " " + srcFile.getAbsolutePath() + " "
						+ targetFile.getAbsolutePath());

				if (EZUtils.isValidString(result))
					return (long) (100 * Double.parseDouble(result));

				Thread.sleep(1000);
			}

			return 0;
		} catch (Exception e) {
			log.error("Caught exception when comparing face.", e);
			throw new ExecutorException(e);
		} finally {
			if (srcFile != null)
				srcFile.delete();

			if (targetFile != null)
				targetFile.delete();
		}
	}

	private File getTempFile(String uri, String suffix) {
		String[] split = uri.split("\\\\|/");
		String fileName = split[split.length - 1];

		if (suffix != null) {
			int index = fileName.lastIndexOf(".");
			fileName = fileName.substring(0, index) + suffix;
		}

		File file = new File(TARGET_PATH + fileName);
		return file;
	}
}
