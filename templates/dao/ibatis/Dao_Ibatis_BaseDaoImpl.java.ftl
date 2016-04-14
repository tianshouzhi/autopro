package com._21cn.order.dao.ibatis;

import com._21cn.framework.base.db.ibatis.BaseDaoIbatis;
import com._21cn.framework.util.PageHashMap;
import com._21cn.framework.util.PageList;
import com._21cn.order.dao.FlowOrderLogDao;
import com._21cn.order.dao.generator.command.FlowOrderLogIdGenerator;
import com._21cn.order.entity.FlowOrderLog;
import com.ibatis.sqlmap.client.SqlMapClient;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Repository;

@Repository("flowOrderLogDao")
public class FlowOrderLogDaoImpl extends BaseDaoIbatis<FlowOrderLog> implements FlowOrderLogDao {

	public FlowOrderLogDaoImpl() {
		super();
		setNameSpace("t_flow_order_log");
	}

	@Autowired
	@Qualifier("flowOrderLogIdGenerator")
	private FlowOrderLogIdGenerator flowOrderLogIdGenerator;

	@Required
	@Autowired
	@Qualifier("sqlMapClient")
	public void setSqlMapClientAutowired(SqlMapClient sqlMapClient) {
		super.setSqlMapClient(sqlMapClient);
	}

	@SuppressWarnings("unchecked")
	public PageHashMap<Long, FlowOrderLog> selectMapByMap(Map<String, Object> params, int pageNo, int pageSize,
			boolean doCount) {
		return queryforPageMap(getNameSpace() + ".selectByMap", params, pageNo, pageSize, doCount, "id");
	}

	@Override
	public void insert(FlowOrderLog object) {
		if (object.getId() == 0L) {
			object.setId(Long.parseLong(flowOrderLogIdGenerator.getNextId().toString())); // 生成id
			super.insert(object);
		} else {
			getSqlMapClientTemplate().insert(getNameSpace() + ".insert", object);
		}
	}

	@Override
	public FlowOrderLog selectByOrderId(String orderId) {
		return (FlowOrderLog) getSqlMapClientTemplate().queryForObject(getNameSpace() + ".selectOrderId", orderId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageList<FlowOrderLog> QueryFlowOrder(Map<String, Object> params, int pageNo, int pageSize, boolean doCount) {
		return queryforPageList(getNameSpace() + ".selectFlowList", params, pageNo, pageSize, doCount);
	}

}