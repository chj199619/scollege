package com.nkl.page.dao;

import java.util.ArrayList;
import java.util.List;

import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;
import com.nkl.page.domain.Picnews;

public class PicnewsDao extends BaseDao {

	public void addPicnews(Picnews picnews){
		super.add(picnews);
	}

	public void delPicnews(Integer picnews_id){
		super.del(Picnews.class, picnews_id);
	}

	public void delPicnewss(String[] picnews_ids){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <picnews_ids.length; i++) {
			sBuilder.append(picnews_ids[i]);
			if (i !=picnews_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String hql = "DELETE FROM Picnews WHERE picnews_id IN(" +sBuilder.toString()+")";

		Object[] params = null;

		super.executeUpdateHql(hql, params);
	}

	public void updatePicnews(Picnews picnews){
		Picnews _picnews = (Picnews)super.get(Picnews.class, picnews.getPicnews_id());
		if (!StringUtil.isEmptyString(picnews.getPicnews_title())) {
			_picnews.setPicnews_title(picnews.getPicnews_title());
		}
		if (!StringUtil.isEmptyString(picnews.getPicnews_content())) {
			_picnews.setPicnews_content(picnews.getPicnews_content());
		}
		if (!StringUtil.isEmptyString(picnews.getPicnews_picture())) {
			_picnews.setPicnews_picture(picnews.getPicnews_picture());
		}
		if (!StringUtil.isEmptyString(picnews.getPicnews_admin())) {
			_picnews.setPicnews_admin(picnews.getPicnews_admin());
		}else {
			_picnews.setPicnews_admin(null);
		}
		super.update(_picnews);
	}

	@SuppressWarnings("rawtypes")
	public Picnews getPicnews(Picnews picnews){
		Picnews _picnews=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Picnews WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (picnews.getPicnews_id()!=null && picnews.getPicnews_id()!=0) {
			sBuilder.append(" and picnews_id = ? ");
			paramsList.add(picnews.getPicnews_id());
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
			_picnews = (Picnews)list.get(0);
		}

		return _picnews;
	}

	@SuppressWarnings("rawtypes")
	public List<Picnews>  listPicnewss(Picnews picnews){
		List<Picnews> picnewss = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Picnews WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (picnews.getPicnews_id()!=null && picnews.getPicnews_id()!=0) {
			sBuilder.append(" and picnews_id = ? ");
			paramsList.add(picnews.getPicnews_id());
		}

		Object[] params = null;
		if (paramsList.size()>0) {
			params = new Object[paramsList.size()];
			for (int i = 0; i < paramsList.size(); i++) {
				params[i] = paramsList.get(i);
			}
		}

		sBuilder.append(" order by picnews_date desc,picnews_id asc ");

		List list = super.executeQueryHql(sBuilder.toString(), params);
		if (list != null && list.size() > 0) {
			picnewss = new ArrayList<Picnews>();
			for (Object object : list) {
				picnewss.add((Picnews)object);
			}
		}

		return picnewss;
	}

}
