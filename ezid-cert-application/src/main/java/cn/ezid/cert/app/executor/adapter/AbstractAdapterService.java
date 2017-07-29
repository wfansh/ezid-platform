package cn.ezid.cert.app.executor.adapter;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.ezid.cert.app.executor.ExecutorException;
import cn.ezid.cert.core.history.HistoryService;
import cn.ezid.cert.core.history.PhotoEntity;
import cn.ezid.cert.core.oss.OSSService;

public abstract class AbstractAdapterService implements AdapterService {

	private static final Logger log = LoggerFactory.getLogger(AbstractAdapterService.class);

	@Autowired
	private OSSService ossService;

	@Autowired
	private HistoryService historyService;

	protected abstract VendorService getVendorService();
	
	@Override
	public String getPhoto(String idcardNum, String name, boolean photoIgnored, boolean debugMode) {
		// get photo from database
		PhotoEntity photoEntity = historyService.getPhoto(idcardNum, name);
		if (photoEntity != null)
			return photoEntity.getPhotoUri();

		// get photo
		String uri = null;
		if (!debugMode) {
			uri = getPhotoFromVendor(idcardNum, name, photoIgnored);
			// save the photo to database
			if (uri != null) {
				historyService.insertPhoto(idcardNum, name, uri);
			}
		} else {
			uri = getPhotoFromLocal(idcardNum, name);
		}
		
		return uri;
	}


	private String getPhotoFromVendor(String idcardNum, String name, boolean photoIgnored) {
		try {
			if (!ossService.getStatus())
				throw new ExecutorException("Ali data store service unreachable!");

			Image image = this.getVendorService().getPhoto(idcardNum, name, photoIgnored);

			if (image == null) {
				return null;
			}
			
			File temp = File.createTempFile(idcardNum, ".jpg");
			ImageIO.write((BufferedImage) image, "jpg", temp);
			log.info("Write image {}.", temp);

			String path = ossService.getIdcardPhotoFilePath(idcardNum, "jpg");
			ossService.uploadFile(path, temp);

			temp.delete();

			return path;
		} catch (IOException e) {
			// Set DataStoreService status=false, adapter will not load photos then
			ossService.setStatus(false);

			log.error("Failed to upload image to aliyun for {} {}.", idcardNum, name);
			log.error("", e);

			throw new ExecutorException(e);
		} finally {
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {

			}
		}
	}
	
	private String getPhotoFromLocal(String idcardNum, String name) {
		try {
			// TODO hardcode the file
			if (!ossService.getStatus())
				throw new ExecutorException("Ali data store service unreachable!");

			InputStream is = this.getClass().getResourceAsStream("/idcardPhoto.jpg");
			File temp = File.createTempFile(idcardNum, ".jpg");
			OutputStream os = new FileOutputStream(temp);
			IOUtils.copy(is, os);
			is.close();
			os.close();
			String path = ossService.getIdcardPhotoFilePath(idcardNum, "jpg");
			ossService.uploadFile(path, temp);

			temp.delete();

			return path;
		} catch (IOException e) {
			// Set DataStoreService status=false, adapter will not load nciic photos then
			ossService.setStatus(false);

			log.error("Failed to upload image to aliyun for {} {}.", idcardNum, name);
			throw new ExecutorException(e);
		}
	}
}
