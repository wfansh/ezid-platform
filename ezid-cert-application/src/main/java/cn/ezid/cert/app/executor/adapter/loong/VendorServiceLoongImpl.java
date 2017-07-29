package cn.ezid.cert.app.executor.adapter.loong;

import java.awt.Image;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.ezid.cert.app.executor.ExecutorException;
import cn.ezid.cert.app.executor.adapter.AdaptException;
import cn.ezid.cert.app.executor.adapter.AdaptException.ErrorType;
import cn.ezid.cert.app.executor.adapter.VendorService;
import cn.ezid.cert.app.executor.adapter.loong.axis.CheckRequest;
import cn.ezid.cert.app.executor.adapter.loong.axis.CheckResponse;
import cn.ezid.cert.app.executor.adapter.loong.axis.IdentifierData;
import cn.ezid.cert.app.executor.adapter.loong.axis.IdentifierServiceSoapProxy;
import cn.ezid.cert.app.executor.adapter.loong.axis.LoginResponse;
import cn.ezid.cert.app.executor.adapter.loong.axis.LoginResposeCode;
import cn.ezid.cert.app.executor.adapter.loong.axis.LoginUserData;
import cn.ezid.cert.app.executor.adapter.loong.axis.ResponseCode;
import cn.ezid.cert.core.NLSSupport;
import cn.ezid.cert.core.PropConfig;
import cn.ezid.cert.core.util.EZUtils;

@Service
public class VendorServiceLoongImpl implements VendorService {

	private static final Logger log = LoggerFactory.getLogger(VendorServiceLoongImpl.class);
	
	private static final String SERVICE_URL = PropConfig.getParameter("Loong.WS.Url", "http://203.148.57.104:8899/identifierservice.asmx");
	private static final String USER = PropConfig.getParameter("Loong.User", "shbc_test");
	private static final String PASSWORD = PropConfig.getParameter("Loong.Password", "888888");
	private static final String UNIQUEID = "888888";
	
	private IdentifierServiceSoapProxy proxy;
	private LoginUserData userData;
//	private String largeWatermarkIcon;
//	private String smallWatermarkIcon;
	
	public VendorServiceLoongImpl() {
		proxy = new IdentifierServiceSoapProxy(SERVICE_URL);
		this.login();
//		largeWatermarkIcon = this.getClass().getResource("/largewatermark.png").getPath();
//		smallWatermarkIcon = this.getClass().getResource("/smallwatermark.png").getPath();
	}
	
	
	@Override
	public Image getPhoto(String idcardNum, String name, boolean photoIgnored) {
		// TODO Auto-generated method stub
		if (userData == null) {
			this.login();
			
			if (userData == null) {
				log.warn("Loong WS login user data is not initialized.");
				throw new ExecutorException("Loong WS login user data is not initialized.");
			}
		}
		
		CheckRequest request = new CheckRequest(idcardNum, name);
		CheckResponse response = null;
		
		try {
			response = proxy.singleCheck(request, userData);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			this.logout();
			log.error("Failed to call singleCheck() for {} {}.", idcardNum, name);
			log.error("", e);
			throw new ExecutorException(e);
		}
		
		if (response.getResponseCode() != ResponseCode.SUCCESS) {
			if (response.getResponseCode() == ResponseCode.PARAMETER_ERROR) {
				log.error("Bad parameter for singleCheck(), {} {}.", idcardNum, name);
				throw new AdaptException(NLSSupport.getMessage("Loong.exception.title"), ErrorType.NOT_FOUND, 
						NLSSupport.getMessage("Loong.parameter.error", idcardNum, name));
			} else {
				this.logout();
				log.error("Failed to call singleCheck(). Error code is {}, name:{}, idcardNum:{}",
						response.getResponseCode(), name, idcardNum);
				throw new ExecutorException("Failed to call singleCheck(). Error code is " + response.getResponseCode());
			}
		}
		
		IdentifierData identifier = response.getIdentifier();
		if (!identifier.getCitizenIdResult().equals(NLSSupport.getMessage("Loong.CitizenIdResult.Consistent"))) {
			log.warn("Inconsistent {} {}, result is {}.", idcardNum, name, identifier.getCitizenIdResult());
			throw new AdaptException(NLSSupport.getMessage("Loong.exception.title"), ErrorType.INCONSISTENT, 
					NLSSupport.getMessage("Loong.validation.inconsistent"));
		}
		
		
		try {
			return decodeImage(identifier.getPhotos());
		} catch (IOException e) {
			log.warn("Photo result for {} {} is {}", idcardNum, name, identifier.getPhotos());
			if (photoIgnored) {
				return null;
			}
			
			throw new AdaptException(NLSSupport.getMessage("Loong.exception.title"), ErrorType.PHOTO_UNFORMAT,
					NLSSupport.getMessage("Loong.photo.unformatted"));
		}
	}
	
	private void login() {
		LoginUserData request = new LoginUserData(USER, PASSWORD, UNIQUEID);
		try {
			LoginResponse response = proxy.login(request);
			if (response.getResponseCode() == LoginResposeCode.SUCCESS) {
				userData = response.getUserData();
			} else {
				log.error("Failed to login to loong WS. Error code is {}.", response.getResponseCode());
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			log.error("Failed to login to loong WS.", e);
		}
	}
	
	private void logout() {
		this.userData = null;
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
		
		//add watermark
//		Image processedImg = waterMarkImageByIcon(img);
//		if (processedImg == null) {
//			processedImg = img;
//		}
		
		//don't process watermark
		Image processedImg = img;;
		
		return processedImg;
	}
	
//	private Image waterMarkImageByIcon( Image srcImg ) {
//        try {
//        	String iconPath;
//        	Integer width;
//        	Integer height;
//        	float clarity = 0.8f;
//            BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),
//                    srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);
//            
//            if (buffImg.getWidth() > 110) {
//            	iconPath = largeWatermarkIcon;
//            	width = 0;
//            	height = 32;
//            } else {
//            	iconPath = smallWatermarkIcon;
//            	width = 0;
//            	height = 15;
//            }
//            
//            // 得到画笔对象
//            // Graphics g= buffImg.getGraphics();
//            Graphics2D g = buffImg.createGraphics();
//            // 设置对线段的锯齿状边缘处理
//            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
//                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//            g.drawImage(
//                    srcImg.getScaledInstance(srcImg.getWidth(null),
//                            srcImg.getHeight(null), Image.SCALE_SMOOTH), 0, 0,
//                    null);
//            // 水印图象的路径 水印一般为gif或者png的，这样可设置透明度
//            ImageIcon imgIcon = new ImageIcon(iconPath);
//            // 得到Image对象。
//            Image img = imgIcon.getImage();
//            float alpha = clarity; // 透明度
//            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
//                    alpha));
//            // 表示水印图片的位置
//            g.drawImage(img, width, height, null);
//            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
//            g.dispose();
//            return buffImg;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        } 
//    }

}
