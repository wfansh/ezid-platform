package cn.ezid.cert.core.oss;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.ezid.cert.core.PropConfig;

import com.aliyun.openservices.oss.OSSClient;
import com.aliyun.openservices.oss.model.GetObjectRequest;
import com.aliyun.openservices.oss.model.OSSObject;
import com.aliyun.openservices.oss.model.ObjectMetadata;

@Service
public class AliOSSServiceImpl implements OSSService {
	
	private static final Logger log = LoggerFactory.getLogger(AliOSSServiceImpl.class);
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	private SimpleDateFormat sdfForYear = new SimpleDateFormat("yyyy");
	
	private String ossUrl = PropConfig.getParameter("OSS.URL");
	private String accessKeyId =  PropConfig.getParameter("OSS.AccessKeyId");
	private String accessKeySecret = PropConfig.getParameter("OSS.AccessKeySecret");
	private String bucketName = PropConfig.getParameter("OSS.BucketName");

	private boolean status = true;
	
	@Override
	public void uploadFile(String path, File file) throws IOException {
         try {
            OSSClient client = new OSSClient(ossUrl, accessKeyId, accessKeySecret);
            ObjectMetadata objectMeta = new ObjectMetadata();
            objectMeta.setContentLength(file.length());

            // objectMeta.setContentType("image/jpeg");
            InputStream in = new FileInputStream(file);
            client.putObject(bucketName, path, in, objectMeta);
         } catch (IOException e) {
             log.warn("Upload file to aliyun failed", e);
             throw e;
         }
	}

	@Override
    public void downloadFile(String path, File file) {
        OSSClient client = new OSSClient(ossUrl, accessKeyId, accessKeySecret);
        GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, path);
        client.getObject(getObjectRequest, file);
	}
	
	@Override
	public String getIdcardPhotoFilePath(String personIdcardNum, String fileExt) {
		long createdTime = System.currentTimeMillis();
		Date date = new Date();
		StringBuffer sb = new StringBuffer();
		sb.append("idcardphoto")
			.append("/")
			.append(sdfForYear.format(date))
			.append("/")
			.append(sdf.format(date))
			.append("/")
			.append(createdTime%100)
			.append("/")
			.append(personIdcardNum)
			.append("_")
			.append(System.currentTimeMillis())
			.append(".")
			.append(fileExt);
		return sb.toString();
	}

	@Override
	public boolean getStatus() {
		return status;
	}

	@Override
	public void setStatus(boolean status) {
		this.status = status;
	}
	
    @Override
    public OSSObject getObject(String path) {
        OSSClient client = new OSSClient(ossUrl, accessKeyId, accessKeySecret);
        OSSObject object = client.getObject(bucketName, path);
        return object;
    }
}
