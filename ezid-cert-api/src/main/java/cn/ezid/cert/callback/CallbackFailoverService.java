package cn.ezid.cert.callback;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;


public interface CallbackFailoverService {
	public static final String SQLID_SELECT_BY_URL_PATTERN = "selectByUrlPattern";
	public static final String SQLID_DELETE = "delete";
	
	public void setSqlSessionTemplate(SqlSessionFactory sqlSessionFactory);
	public List<CallbackFailoverEntity> selectByUrlPattern(String urlPattern);
	public int delete(long id);
}
