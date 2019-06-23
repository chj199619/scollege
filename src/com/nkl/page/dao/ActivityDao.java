package com.nkl.page.dao;

import java.util.ArrayList;
import java.util.List;

import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;
import com.nkl.page.domain.Activity;

public class ActivityDao extends BaseDao {

	public void addActivity(Activity activity){
		super.add(activity);
	}

	public void delActivity(Integer activity_id){
		super.del(Activity.class, activity_id);
	}

	public void delActivitys(String[] activity_ids){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <activity_ids.length; i++) {
			sBuilder.append(activity_ids[i]);
			if (i !=activity_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String hql = "DELETE FROM Activity WHERE activity_id IN(" +sBuilder.toString()+")";

		Object[] params = null;

		super.executeUpdateHql(hql, params);
	}

	public void updateActivity(Activity activity){
		Activity _activity = (Activity)super.get(Activity.class, activity.getActivity_id());
		if (!StringUtil.isEmptyString(activity.getActivity_title())) {
			_activity.setActivity_title(activity.getActivity_title());
		}
		if (!StringUtil.isEmptyString(activity.getActivity_content())) {
			_activity.setActivity_content(activity.getActivity_content());
		}
		if (!StringUtil.isEmptyString(activity.getActivity_date())) {
			_activity.setActivity_date(activity.getActivity_date());
		}
		if (!StringUtil.isEmptyString(activity.getActivity_address())) {
			_activity.setActivity_address(activity.getActivity_address());
		}
		if (!StringUtil.isEmptyString(activity.getActivity_equip())) {
			_activity.setActivity_equip(activity.getActivity_equip());
		}
		if (activity.getActivity_money()!=null && activity.getActivity_money()!=0) {
			_activity.setActivity_money(activity.getActivity_money());
		}
		if (activity.getActivity_flag()!=null && activity.getActivity_flag()!=0) {
			_activity.setActivity_flag(activity.getActivity_flag());
		}
		super.update(_activity);
	}

	@SuppressWarnings("rawtypes")
	public Activity getActivity(Activity activity){
		Activity _activity=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Activity WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (activity.getActivity_id()!=null && activity.getActivity_id()!=0) {
			sBuilder.append(" and activity_id = ? ");
			paramsList.add(activity.getActivity_id());
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
			_activity = (Activity)list.get(0);
		}

		return _activity;
	}

	@SuppressWarnings("rawtypes")
	public List<Activity>  listActivitys(Activity activity){
		List<Activity> activitys = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Activity WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (activity.getActivity_id()!=null && activity.getActivity_id()!=0) {
			sBuilder.append(" and activity_id = ? ");
			paramsList.add(activity.getActivity_id());
		}
		if (!StringUtil.isEmptyString(activity.getActivity_title())) {
			sBuilder.append(" and activity_title like '%" + activity.getActivity_title() + "%' ");
		}
		if (!StringUtil.isEmptyString(activity.getActivity_content())) {
			sBuilder.append(" and activity_content like '%" + activity.getActivity_content() + "%' ");
		}
		if (!StringUtil.isEmptyString(activity.getActivity_date())) {
			sBuilder.append(" and activity_date ='" + activity.getActivity_date() + "' ");
		}
		if (activity.getActivity_flag()!=null && activity.getActivity_flag()!=0) {
			sBuilder.append(" and activity_flag =" + activity.getActivity_flag() + " ");
		}
		if (activity.getActivity_type()!=null && activity.getActivity_type()!=0) {
			sBuilder.append(" and activity_type =" + activity.getActivity_type() + " ");
		}
		if (activity.getUser_id()!=null && activity.getUser_id()!=0) {
			sBuilder.append(" and user_id =" + activity.getUser_id() + " ");
		}

		Object[] params = null;
		if (paramsList.size()>0) {
			params = new Object[paramsList.size()];
			for (int i = 0; i < paramsList.size(); i++) {
				params[i] = paramsList.get(i);
			}
		}

		sBuilder.append(" order by activity_date desc,activity_id asc ");

		List list = null;
		if (activity.getStart()!=-1) {
			list = super.findByPageHql(sBuilder.toString(), params, activity.getStart(), activity.getLimit());
		}else {
			list = super.executeQueryHql(sBuilder.toString(), params);
		}
		if (list != null && list.size() > 0) {
			activitys = new ArrayList<Activity>();
			for (Object object : list) {
				activitys.add((Activity)object);
			}
		}

		return activitys;
	}

	public int  listActivitysCount(Activity activity){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM Activity WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (activity.getActivity_id()!=null && activity.getActivity_id()!=0) {
			sBuilder.append(" and activity_id = ? ");
			paramsList.add(activity.getActivity_id());
		}
		if (!StringUtil.isEmptyString(activity.getActivity_title())) {
			sBuilder.append(" and activity_title like '%" + activity.getActivity_title() + "%' ");
		}
		if (!StringUtil.isEmptyString(activity.getActivity_content())) {
			sBuilder.append(" and activity_content like '%" + activity.getActivity_content() + "%' ");
		}
		if (!StringUtil.isEmptyString(activity.getActivity_date())) {
			sBuilder.append(" and activity_date ='" + activity.getActivity_date() + "' ");
		}
		if (activity.getActivity_flag()!=null && activity.getActivity_flag()!=0) {
			sBuilder.append(" and activity_flag =" + activity.getActivity_flag() + " ");
		}
		if (activity.getActivity_type()!=null && activity.getActivity_type()!=0) {
			sBuilder.append(" and activity_type =" + activity.getActivity_type() + " ");
		}
		if (activity.getUser_id()!=null && activity.getUser_id()!=0) {
			sBuilder.append(" and user_id =" + activity.getUser_id() + " ");
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
