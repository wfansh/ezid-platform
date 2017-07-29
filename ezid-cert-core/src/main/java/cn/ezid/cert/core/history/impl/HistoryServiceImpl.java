package cn.ezid.cert.core.history.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ezid.cert.core.dao.DynamicSqlParameter;
import cn.ezid.cert.core.history.AdapterInvalidDao;
import cn.ezid.cert.core.history.AdapterInvalidEntity;
import cn.ezid.cert.core.history.HistoryService;
import cn.ezid.cert.core.history.PhotoDao;
import cn.ezid.cert.core.history.PhotoEntity;

@Service
public class HistoryServiceImpl implements HistoryService {
	private static final Logger log = LoggerFactory.getLogger(HistoryServiceImpl.class);

	@Autowired
	private PhotoDao photoDao;

	@Autowired
	private AdapterInvalidDao adapterInvalidDao;

	@Override
	public PhotoEntity getPhoto(String idcardNum, String name) {
		// TODO Auto-generated method stub
		DynamicSqlParameter param = new DynamicSqlParameter();
		param.put("idcardNum", idcardNum);
		param.put("name", name);

		List<PhotoEntity> photoCache = photoDao.select(param);
		if (photoCache != null && photoCache.size() > 0)
			return photoCache.get(0);

		return null;
	}

	@Override
	public void insertPhoto(String idcardNum, String name, String uri) {
		// TODO Auto-generated method stub
		PhotoEntity entity = new PhotoEntity();
		entity.setIdcardNum(idcardNum);
		entity.setName(name);
		entity.setPhotoUri(uri);
		entity.setPhotoType(PhotoEntity.PhotoEntityType.PHOTO_TYPE_IDCARD);
		photoDao.insert(entity);
	}

	@Override
	public AdapterInvalidEntity getInvalid(String idcardNum, String name) {
		// TODO Auto-generated method stub
		DynamicSqlParameter param = new DynamicSqlParameter();
		param.put("idcardNum", idcardNum);
		param.put("name", name);
		List<AdapterInvalidEntity> invalidEntities = adapterInvalidDao.select(param);

		if (invalidEntities != null && !invalidEntities.isEmpty()) {
			log.warn("ID {} name {} inconsistent by adapter history.", idcardNum, name);
			return invalidEntities.get(0);
		}

		return null;
	}

	@Override
	public void insertInvalid(String idcardNum, String name, String error, String message) {
		// TODO Auto-generated method stub
		adapterInvalidDao.insert(new AdapterInvalidEntity(idcardNum, name, error, message));
	}

}
