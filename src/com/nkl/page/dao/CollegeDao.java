package com.nkl.page.dao;

import java.util.ArrayList;
import java.util.List;

import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;
import com.nkl.page.domain.College;

public class CollegeDao extends BaseDao {

	public void addCollege(College college){
		super.add(college);
	}

	public void delCollege(Integer college_id){
		super.del(College.class, college_id);
	}

	public void delColleges(String[] college_ids){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <college_ids.length; i++) {
			sBuilder.append(college_ids[i]);
			if (i !=college_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String hql = "DELETE FROM College WHERE college_id IN(" +sBuilder.toString()+")";

		Object[] params = null;

		super.executeUpdateHql(hql, params);
	}

	public void updateCollege(College college){
		College _college = (College)super.get(College.class, college.getCollege_id());
		if (!StringUtil.isEmptyString(college.getCollege_name())) {
			_college.setCollege_name(college.getCollege_name());
		}
		if (!StringUtil.isEmptyString(college.getCollege_type())) {
			_college.setCollege_type(college.getCollege_type());
		}
		if (college.getCollege_persons()!=null && college.getCollege_persons()!=0) {
			_college.setCollege_persons(college.getCollege_persons());
		}
		if (college.getCollege_money()!=null && college.getCollege_money()!=0) {
			_college.setCollege_money(college.getCollege_money());
		}
		if (!StringUtil.isEmptyString(college.getCollege_pic())) {
			_college.setCollege_pic(college.getCollege_pic());
		}
		if (!StringUtil.isEmptyString(college.getCollege_note())) {
			_college.setCollege_note(college.getCollege_note());
		}
		if (!StringUtil.isEmptyString(college.getCollege_plan())) {
			_college.setCollege_plan(college.getCollege_plan());
		}
		if (college.getCollege_flag()!=null && college.getCollege_flag()!=0) {
			_college.setCollege_flag(college.getCollege_flag());
		}
		super.update(_college);
	}

	@SuppressWarnings("rawtypes")
	public College getCollege(College college){
		College _college=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM College c join fetch c.user WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (college.getCollege_id()!=null && college.getCollege_id()!=0) {
			sBuilder.append(" and college_id = ? ");
			paramsList.add(college.getCollege_id());
		}
		if (!StringUtil.isEmptyString(college.getCollege_name())) {
			sBuilder.append(" and college_name ='" + college.getCollege_name() + "' ");
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
			_college = (College)list.get(0);
		}

		return _college;
	}

	@SuppressWarnings("rawtypes")
	public List<College>  listColleges(College college){
		List<College> colleges = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM College c join fetch c.user WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (college.getCollege_id()!=null && college.getCollege_id()!=0) {
			sBuilder.append(" and college_id = ? ");
			paramsList.add(college.getCollege_id());
		}
		if (!StringUtil.isEmptyString(college.getCollege_name())) {
			sBuilder.append(" and college_name like '%" + college.getCollege_name() + "%' ");
		}
		if (!StringUtil.isEmptyString(college.getCollege_type())) {
			sBuilder.append(" and college_type like '%" + college.getCollege_type() + "%' ");
		}
		if (college.getCollege_flag()!=null && college.getCollege_flag()!=0) {
			sBuilder.append(" and college_flag =" + college.getCollege_flag() + " ");
		}
		if (college.getUser()!=null && college.getUser().getUser_id()!=null &&college.getUser().getUser_id()!=0) {
			sBuilder.append(" and c.user.user_id =" + college.getUser().getUser_id() + " ");
		}
		if (!StringUtil.isEmptyString(college.getReal_name())) {
			sBuilder.append(" and c.user.real_name like '%" + college.getReal_name() + "%' ");
		}

		Object[] params = null;
		if (paramsList.size()>0) {
			params = new Object[paramsList.size()];
			for (int i = 0; i < paramsList.size(); i++) {
				params[i] = paramsList.get(i);
			}
		}

		sBuilder.append(" order by create_date desc,college_id asc ");

		List list = null;
		if (college.getStart()!=-1) {
			list = super.findByPageHql(sBuilder.toString(), params, college.getStart(), college.getLimit());
		}else {
			list = super.executeQueryHql(sBuilder.toString(), params);
		}
		if (list != null && list.size() > 0) {
			colleges = new ArrayList<College>();
			for (Object object : list) {
				colleges.add((College)object);
			}
		}

		return colleges;
	}

	public int  listCollegesCount(College college){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM College c join c.user WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (college.getCollege_id()!=null && college.getCollege_id()!=0) {
			sBuilder.append(" and college_id = ? ");
			paramsList.add(college.getCollege_id());
		}
		if (!StringUtil.isEmptyString(college.getCollege_name())) {
			sBuilder.append(" and college_name like '%" + college.getCollege_name() + "%' ");
		}
		if (!StringUtil.isEmptyString(college.getCollege_type())) {
			sBuilder.append(" and college_type like '%" + college.getCollege_type() + "%' ");
		}
		if (college.getCollege_flag()!=null && college.getCollege_flag()!=0) {
			sBuilder.append(" and college_flag =" + college.getCollege_flag() + " ");
		}
		if (college.getUser()!=null && college.getUser().getUser_id()!=null &&college.getUser().getUser_id()!=0) {
			sBuilder.append(" and c.user.user_id =" + college.getUser().getUser_id() + " ");
		}
		if (!StringUtil.isEmptyString(college.getReal_name())) {
			sBuilder.append(" and c.user.real_name like '%" + college.getReal_name() + "%' ");
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
