package cn.ezid.cert.app.executor.adapter.loong;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;

import javax.imageio.ImageIO;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import cn.ezid.cert.app.executor.adapter.loong.axis.CheckRequest;
import cn.ezid.cert.app.executor.adapter.loong.axis.CheckResponse;
import cn.ezid.cert.app.executor.adapter.loong.axis.IdentifierServiceSoapProxy;
import cn.ezid.cert.app.executor.adapter.loong.axis.LoginResponse;
import cn.ezid.cert.app.executor.adapter.loong.axis.LoginResposeCode;
import cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData;
import cn.ezid.cert.app.executor.adapter.loong.axis.QueryFeeResponse;
import cn.ezid.cert.core.util.EZUtils;

@RunWith(JUnit4.class)
@Ignore
public class LoongTest {
	private static final String SERVICE_URL = "http://120.25.225.22/identifierservice.asmx";
	private static final String USER = "ydwl_admin";
	private static final String PASSWORD = "NE9fdNHR";
	private static final String UNIQUEID = "888888";
	
	private static final String IDCARD_NUM = "130622198405218013";
	private static final String NAME = "王帆";
	
	
	private IdentifierServiceSoapProxy proxy;
	private LoginUserData userData;

	@Before
	public void test() {
		proxy = new IdentifierServiceSoapProxy(SERVICE_URL);
		this.login();
	}
	
	
	@Test
	public void checkLoongBalance() throws RemoteException {
		QueryFeeResponse freeResponse = proxy.queryFeeData(null, userData);
		System.out.println("Total used quota :" + freeResponse.getTotalQueryCount());
		System.out.println("Remaining quota :" + freeResponse.getRemainQueryCount());
	}
	
	@Test
	public void testSingleCheck() throws Exception {
		CheckRequest request = new CheckRequest(IDCARD_NUM, NAME);
		CheckResponse response = proxy.singleCheck(request, userData);

		System.out.println(response.getResponseCode());
		System.out.println(response.getIdentifier());
		Image image = decodeImage(response.getIdentifier().getPhotos());
		
		
		File temp = File.createTempFile(IDCARD_NUM, ".jpg");
		ImageIO.write((BufferedImage) image, "jpg", temp);
		System.out.println(temp);
	}
	
	private void login() {
		LoginUserData request = new LoginUserData(USER, PASSWORD, UNIQUEID);
		try {
			LoginResponse response = proxy.login(request);
			if (response.getResponseCode() == LoginResposeCode.SUCCESS) {
				userData = response.getUserData();
			} else {
				System.out.println("Failed to login to loong WS. Error code is " + response.getResponseCode());
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	
	private Image decodeImage(String xp) throws IOException {
		if (!EZUtils.isValidString(xp))
			throw new IOException();
		
		xp = xp.replaceAll("\u0020","");
		xp = xp.replaceAll("\r\n","");
		
		@SuppressWarnings("restriction")
		byte[] byteBuffer = new sun.misc.BASE64Decoder().decodeBuffer(xp);
		InputStream in = new ByteArrayInputStream(byteBuffer);
		Image img = ImageIO.read(in);
		in.close();
		if (img == null) {
			throw new IOException();
		}

		return img;
	}
}
