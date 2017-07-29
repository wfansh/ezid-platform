package cn.ezid.cert.core.oss;

import java.io.File;
import java.io.IOException;

import com.aliyun.openservices.oss.model.OSSObject;

public interface OSSService {
	public void uploadFile(String path, File file) throws IOException;

	public void downloadFile(String path, File file);
	
	public String getIdcardPhotoFilePath(String personIdcardNum, String ext);

	public boolean getStatus();

	public void setStatus(boolean status);
	
    public OSSObject getObject(String path);

}
