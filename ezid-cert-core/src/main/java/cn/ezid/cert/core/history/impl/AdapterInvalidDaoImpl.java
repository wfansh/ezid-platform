package cn.ezid.cert.core.history.impl;

import org.springframework.stereotype.Repository;

import cn.ezid.cert.core.dao.mybatis.GenericMyBatisDao;
import cn.ezid.cert.core.history.AdapterInvalidDao;
import cn.ezid.cert.core.history.AdapterInvalidEntity;

@Repository
public class AdapterInvalidDaoImpl extends GenericMyBatisDao<AdapterInvalidEntity, Long> implements AdapterInvalidDao {

}
