package cn.ezid.cert.app.executor.adapter.nciic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ezid.cert.app.executor.adapter.AbstractAdapterService;
import cn.ezid.cert.app.executor.adapter.VendorService;

@Service
public class AdapterServiceNciicImpl extends AbstractAdapterService {

	@Autowired
	private VendorServiceNciicImpl vendorService;

	@Override
	protected VendorService getVendorService() {
		// TODO Auto-generated method stub
		return vendorService;
	}

}
