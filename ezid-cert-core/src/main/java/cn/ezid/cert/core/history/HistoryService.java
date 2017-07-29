package cn.ezid.cert.core.history;


public interface HistoryService {

	public PhotoEntity getPhoto(String idcardNum, String name);

	public void insertPhoto(String idcardNum, String name, String uri);

	public AdapterInvalidEntity getInvalid(String idcardNum, String name);

	public void insertInvalid(String idcardNum, String name, String error, String message);

}
