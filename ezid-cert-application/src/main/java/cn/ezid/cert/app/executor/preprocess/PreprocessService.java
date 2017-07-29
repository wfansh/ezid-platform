package cn.ezid.cert.app.executor.preprocess;

import cn.ezid.cert.app.executor.ExecutorException;

public interface PreprocessService {
	public void preprocessPhoto(String photo) throws ExecutorException;
	public void preprocessVideo(String video) throws ExecutorException;
}
