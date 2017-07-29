package cn.ezid.cert.core.history.impl;

import org.springframework.stereotype.Repository;

import cn.ezid.cert.core.dao.mybatis.GenericMyBatisDao;
import cn.ezid.cert.core.history.PhotoDao;
import cn.ezid.cert.core.history.PhotoEntity;

@Repository
public class PhotoDaoImpl extends GenericMyBatisDao<PhotoEntity, Long> implements PhotoDao {

}
