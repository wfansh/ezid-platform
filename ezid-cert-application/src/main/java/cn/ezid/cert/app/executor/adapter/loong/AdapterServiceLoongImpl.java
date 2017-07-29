package cn.ezid.cert.app.executor.adapter.loong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ezid.cert.app.executor.adapter.AbstractAdapterService;
import cn.ezid.cert.app.executor.adapter.VendorService;

@Service
public class AdapterServiceLoongImpl extends AbstractAdapterService {

	@Autowired
	private VendorServiceLoongImpl vendorService;

	@Override
	protected VendorService getVendorService() {
		// TODO Auto-generated method stub
		return vendorService;
	}
	
	
	
}
