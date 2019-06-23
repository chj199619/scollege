package com.nkl.page.dao;

import java.util.ArrayList;
import java.util.List;

import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;
import com.nkl.page.domain.News;

public class NewsDao extends BaseDao {

	public void addNews(News news){
		super.add(news);
	}

	public void delNews(Integer news_id){
		super.del(News.class, news_id);
	}

	public void delNewss(String[] news_ids){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <news_ids.length; i++) {
			sBuilder.append(news_ids[i]);
			if (i !=news_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String hql = "DELETE FROM News WHERE news_id IN(" +sBuilder.toString()+")";

		Object[] params = null;

		super.executeUpdateHql(hql, params);
	}

	public void updateNews(News news){
		News _news = (News)super.get(News.class, news.getNews_id());
		if (!StringUtil.isEmptyString(news.getNews_title())) {
			_news.setNews_title(news.getNews_title());
		}
		if (!StringUtil.isEmptyString(news.getNews_content())) {
			_news.setNews_content(news.getNews_content());
		}
		if (!StringUtil.isEmptyString(news.getNews_picture())) {
			_news.setNews_picture(news.getNews_picture());
		}
		if (news.getNews_flag()!=null && news.getNews_flag()!=0) {
			_news.setNews_flag(news.getNews_flag());
		}
		super.update(_news);
	}

	@SuppressWarnings("rawtypes")
	public News getNews(News news){
		News _news=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM News WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (news.getNews_id()!=null && news.getNews_id()!=0) {
			sBuilder.append(" and news_id = ? ");
			paramsList.add(news.getNews_id());
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
			_news = (News)list.get(0);
		}

		return _news;
	}

	@SuppressWarnings("rawtypes")
	public List<News>  listNewss(News news){
		List<News> newss = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM News WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (news.getNews_id()!=null && news.getNews_id()!=0) {
			sBuilder.append(" and news_id = ? ");
			paramsList.add(news.getNews_id());
		}
		if (!StringUtil.isEmptyString(news.getNews_title())) {
			sBuilder.append(" and news_title like '%" + news.getNews_title() + "%'");
		}
		if (news.getNews_type() !=null && news.getNews_type() !=0) {
			sBuilder.append(" and news_type = " + news.getNews_type());
		}
		if (news.getNews_flag()!=null && news.getNews_flag()!=0) {
			sBuilder.append(" and news_flag =" + news.getNews_flag() + " ");
		}
		if (news.getUser_id()!=null && news.getUser_id()!=0) {
			sBuilder.append(" and user_id =" + news.getUser_id() + " ");
		}

		Object[] params = null;
		if (paramsList.size()>0) {
			params = new Object[paramsList.size()];
			for (int i = 0; i < paramsList.size(); i++) {
				params[i] = paramsList.get(i);
			}
		}

		sBuilder.append(" order by news_date desc,news_id asc ");

		List list = null;
		if (news.getStart()!=-1) {
			list = super.findByPageHql(sBuilder.toString(), params, news.getStart(), news.getLimit());
		}else {
			list = super.executeQueryHql(sBuilder.toString(), params);
		}
		if (list != null && list.size() > 0) {
			newss = new ArrayList<News>();
			for (Object object : list) {
				newss.add((News)object);
			}
		}

		return newss;
	}

	public int  listNewssCount(News news){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM News WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (news.getNews_id()!=null && news.getNews_id()!=0) {
			sBuilder.append(" and news_id = ? ");
			paramsList.add(news.getNews_id());
		}
		if (!StringUtil.isEmptyString(news.getNews_title())) {
			sBuilder.append(" and news_title like '%" + news.getNews_title() + "%'");
		}
		if (news.getNews_type() !=null && news.getNews_type() !=0) {
			sBuilder.append(" and news_type = " + news.getNews_type());
		}
		if (news.getNews_flag()!=null && news.getNews_flag()!=0) {
			sBuilder.append(" and news_flag =" + news.getNews_flag() + " ");
		}
		if (news.getUser_id()!=null && news.getUser_id()!=0) {
			sBuilder.append(" and user_id =" + news.getUser_id() + " ");
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
