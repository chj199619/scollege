package com.nkl.page.dao;

import java.util.ArrayList;
import java.util.List;

import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;
import com.nkl.page.domain.Sblog;

public class SblogDao extends BaseDao {

	public void addSblog(Sblog sblog){
		super.add(sblog);
	}

	public void delSblog(Integer sblog_id){
		super.del(Sblog.class, sblog_id);
	}

	public void delSblogs(String[] sblog_ids){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <sblog_ids.length; i++) {
			sBuilder.append(sblog_ids[i]);
			if (i !=sblog_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String hql = "DELETE FROM Sblog WHERE sblog_id IN(" +sBuilder.toString()+")";

		Object[] params = null;

		super.executeUpdateHql(hql, params);
	}

	public void updateSblog(Sblog sblog){
		Sblog _sblog = (Sblog)super.get(Sblog.class, sblog.getSblog_id());
		if (sblog.getSblog_flag()!=null && sblog.getSblog_flag()!=0) {
			_sblog.setSblog_flag(sblog.getSblog_flag());
		}
		super.update(_sblog);
	}

	@SuppressWarnings("rawtypes")
	public Sblog getSblog(Sblog sblog){
		Sblog _sblog=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Sblog s join fetch s.user WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (sblog.getSblog_id()!=null && sblog.getSblog_id()!=0) {
			sBuilder.append(" and sblog_id = ? ");
			paramsList.add(sblog.getSblog_id());
		}

		Object[] params = null;
		if (paramsList.size()>0) {
			params = new Object[paramsList.size()];
			for (int i = 0; i < paramsList.size(); i++) {
				params[i] = paramsList.get(i);
			}
		}

		List list = super.executeQueryHql(sBuilder.toString(), params);
		if (list != null && list.size() > 0) {
			_sblog = (Sblog)list.get(0);
		}

		return _sblog;
	}

	@SuppressWarnings("rawtypes")
	public List<Sblog>  listSblogs(Sblog sblog){
		List<Sblog> sblogs = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Sblog s join fetch s.user WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (sblog.getSblog_id()!=null && sblog.getSblog_id()!=0) {
			sBuilder.append(" and sblog_id = ? ");
			paramsList.add(sblog.getSblog_id());
		}
		if (!StringUtil.isEmptyString(sblog.getSblog_title())) {
			sBuilder.append(" and sblog_title like '%" + sblog.getSblog_title() +"%'");
		}
		if (sblog.getSblog_flag()!=null && sblog.getSblog_flag()!=0) {
			sBuilder.append(" and sblog_flag = " + sblog.getSblog_flag() );
		}

		Object[] params = null;
		if (paramsList.size()>0) {
			params = new Object[paramsList.size()];
			for (int i = 0; i < paramsList.size(); i++) {
				params[i] = paramsList.get(i);
			}
		}

		sBuilder.append(" order by sblog_date desc,sblog_id asc ");

		List list = null;
		if (sblog.getStart()!=-1) {
			list = super.findByPageHql(sBuilder.toString(), params, sblog.getStart(), sblog.getLimit());
		}else {
			list = super.executeQueryHql(sBuilder.toString(), params);
		}
		if (list != null && list.size() > 0) {
			sblogs = new ArrayList<Sblog>();
			for (Object object : list) {
				sblogs.add((Sblog)object);
			}
		}

		return sblogs;
	}

	public int  listSblogsCount(Sblog sblog){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM Sblog s join s.user WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (sblog.getSblog_id()!=null && sblog.getSblog_id()!=0) {
			sBuilder.append(" and sblog_id = ? ");
			paramsList.add(sblog.getSblog_id());
		}
		if (!StringUtil.isEmptyString(sblog.getSblog_title())) {
			sBuilder.append(" and sblog_title like '%" + sblog.getSblog_title() +"%'");
		}
		if (sblog.getSblog_flag()!=null && sblog.getSblog_flag()!=0) {
			sBuilder.append(" and sblog_flag = " + sblog.getSblog_flag() );
		}

		Object[] params = null;
		if (paramsList.size()>0) {
			params = new Object[paramsList.size()];
			for (int i = 0; i < paramsList.size(); i++) {
				params[i] = paramsList.get(i);
			}
		}

		long count = (Long)super.executeQueryCountHql(sBuilder.toString(), params);
		sum = (int)count;
		return sum;
	}

}
