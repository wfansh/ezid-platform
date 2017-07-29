package cn.ezid.cert.core.history;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.ezid.cert.core.PropConfig;

import com.aliyun.openservices.oss.OSSClient;
import com.aliyun.openservices.oss.model.ListObjectsRequest;
import com.aliyun.openservices.oss.model.OSSObjectSummary;
import com.aliyun.openservices.oss.model.ObjectListing;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:root-test-context.xml" })
@Ignore
public class HistoryServiceTest {
	
	@Autowired
	private HistoryService historyService;
	
	private static final String BASE_DIR = "socialsec/open/tidy/20150710-1/";
	
	private String ossUrl;
	private String accessKeyId;
	private String accessKeySecret;
	private String bucketName;
	private OSSClient client;

    @Before
    public void prepare() {
    	ossUrl = PropConfig.getParameter("OSS.URL");
    	accessKeyId =  PropConfig.getParameter("OSS.AccessKeyId");
    	accessKeySecret = PropConfig.getParameter("OSS.AccessKeySecret");
    	bucketName = PropConfig.getParameter("OSS.BucketName");
    	
    	client = new OSSClient(ossUrl, accessKeyId, accessKeySecret);
    }
	
    
    
	@Test
	public void testTidyIdcardPhotosForOpen() {
		for (String certDir : this.listSubFiles(BASE_DIR).getCommonPrefixes()) {
			String idcardNum = this.getIdcardNum(certDir);
			String name = this.getName(certDir);
			System.out.println("IdcardNum : " + idcardNum + " " + name + " in folder " + certDir);
			
			PhotoEntity photo = historyService.getPhoto(idcardNum, name);
			System.out.println(photo);
			if (photo == null) {
				System.out.println("null " + idcardNum + name);
				continue;
			}
			this.copyObject(photo.getPhotoUri(), certDir + "idcard.jpg");
		}
	}
	
    private ObjectListing listSubFiles(String path) {
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest(bucketName);
        listObjectsRequest.setDelimiter("/");
        listObjectsRequest.setPrefix(path);
        listObjectsRequest.setMaxKeys(1000);
        return client.listObjects(listObjectsRequest);
    }
    
	private void copyObject(String path, String targetPath) {
		client.copyObject(bucketName, path, bucketName, targetPath);
	}

    
    private String getIdcardNum(String certDir) {
		for (OSSObjectSummary file : this.listSubFiles(certDir).getObjectSummaries()) {
			String[] split = file.getKey().split("/");
			String fileName = split[split.length - 1];
			
			if (fileName.endsWith(".txt")) {
				return fileName.split("_")[0];
			}
		}
		return null;
    }
    
    private String getName(String certDir) {
    	for (OSSObjectSummary file : this.listSubFiles(certDir).getObjectSummaries()) {
			String[] split = file.getKey().split("/");
			String fileName = split[split.length - 1];
			
			if (fileName.endsWith(".txt")) {
				return fileName.split("_")[1];
			}
		}
		return null;
    }
}
