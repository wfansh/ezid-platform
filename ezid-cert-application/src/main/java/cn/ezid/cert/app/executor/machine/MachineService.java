package cn.ezid.cert.app.executor.machine;

import cn.ezid.cert.app.executor.ExecutorException;

public interface MachineService {	
	public boolean detectFace(String srcPhotoUri) throws ExecutorException;
	
	public long compareFace(String srcPhotoUri, String targetPhotoUri) throws ExecutorException;

}
