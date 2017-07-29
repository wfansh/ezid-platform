package cn.ezid.cert.app.executor.adapter;

import java.awt.Image;

public interface VendorService {
	public Image getPhoto(String idcardNum, String name, boolean photoIgnored);
}
