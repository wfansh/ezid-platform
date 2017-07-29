package cn.ezid.cert.core.reject.impl;

import org.springframework.stereotype.Repository;

import cn.ezid.cert.core.dao.mybatis.GenericMyBatisDao;
import cn.ezid.cert.core.reject.TaskRejectReasonDao;
import cn.ezid.cert.core.reject.TaskRejectReasonEntity;

@Repository
public class TaskRejectReasonDaoImpl extends GenericMyBatisDao<TaskRejectReasonEntity, Long> implements
		TaskRejectReasonDao {

}
