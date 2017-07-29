package cn.ezid.cert.core.topo.impl;

import org.springframework.stereotype.Repository;

import cn.ezid.cert.core.dao.mybatis.GenericMyBatisDao;
import cn.ezid.cert.core.topo.TopologyDao;
import cn.ezid.cert.core.topo.TopologyEntity;

@Repository
public class TopologyDaoImpl extends GenericMyBatisDao<TopologyEntity, Long> implements
		TopologyDao {

}
