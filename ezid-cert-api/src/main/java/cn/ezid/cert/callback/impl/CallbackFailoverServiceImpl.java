package cn.ezid.cert.callback.impl;

import java.util.Collections;
import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import cn.ezid.cert.callback.CallbackFailoverEntity;
import cn.ezid.cert.callback.CallbackFailoverService;

public class CallbackFailoverServiceImpl extends SqlSessionDaoSupport implements CallbackFailoverService {

	private String namespace;

	public CallbackFailoverServiceImpl() {
		this.namespace = "cn.ezid.cert.callback.CallbackFailoverEntity";
	}

	public void setSqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		this.setSqlSessionTemplate(new SqlSessionTemplate(sqlSessionFactory));
	}

	@Override
	public List<CallbackFailoverEntity> selectByUrlPattern(String urlPattern) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList(namespace + "." + SQLID_SELECT_BY_URL_PATTERN,
				Collections.singletonMap("pattern", urlPattern));
	}

	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		return getSqlSession().delete(namespace + "." + SQLID_DELETE, id);
	}
}
