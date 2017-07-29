package cn.ezid.cert.core.topo.impl;

import org.springframework.stereotype.Repository;

import cn.ezid.cert.core.dao.mybatis.GenericMyBatisDao;
import cn.ezid.cert.core.topo.LandscapeDao;
import cn.ezid.cert.core.topo.LandscapeEntity;

@Repository
public class LandscapeDaoImpl extends GenericMyBatisDao<LandscapeEntity, Long> implements
		LandscapeDao {
	
}
