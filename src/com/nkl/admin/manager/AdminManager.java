package com.nkl.admin.manager;

import java.util.Date;
import java.util.List;

import com.nkl.common.util.DateUtil;
import com.nkl.common.util.Md5;
import com.nkl.common.util.Param;
import com.nkl.common.util.StringUtil;
import com.nkl.common.util.Transcode;
import com.nkl.page.dao.ActivityDao;
import com.nkl.page.dao.CollegeDao;
import com.nkl.page.dao.EquipDao;
import com.nkl.page.dao.MemberDao;
import com.nkl.page.dao.NewsDao;
import com.nkl.page.dao.PicnewsDao;
import com.nkl.page.dao.SblogDao;
import com.nkl.page.dao.UserDao;
import com.nkl.page.domain.Activity;
import com.nkl.page.domain.College;
import com.nkl.page.domain.Equip;
import com.nkl.page.domain.Member;
import com.nkl.page.domain.News;
import com.nkl.page.domain.Picnews;
import com.nkl.page.domain.Sblog;
import com.nkl.page.domain.User;

public class AdminManager {

	ActivityDao activityDao;
	CollegeDao collegeDao;
	EquipDao equipDao;
	MemberDao memberDao;
	NewsDao newsDao;
	PicnewsDao picnewsDao;
	SblogDao sblogDao;
	UserDao userDao;
	
	/**
	 * @Title: listUsers
	 * @Description: 用户查询
	 * @param user
	 * @return List<User>
	 */
	public List<User>  listUsers(User user,int[] sum){
		
		if (sum!=null) {
			sum[0] = userDao.listUsersCount(user);
		}
		List<User> users = userDao.listUsers(user);
		
		
		return users;
	}
	
	/**
	 * @Title: getUser
	 * @Description: 用户查询
	 * @param user
	 * @return User
	 */
	public User  getUser(User user){
		
		User _user = userDao.getUser(user);
		
		return _user;
	}
	 
	/**
	 * @Title: addUser
	 * @Description: 添加用户
	 * @param user
	 * @return void
	 */
	public void  addUser(User user){
		
		if (!StringUtil.isEmptyString(user.getUser_pass())) {
			user.setUser_pass(Md5.makeMd5(user.getUser_pass()));
		}
		userDao.addUser(user);
		
	}
	
	/**
	 * @Title: updateUser
	 * @Description: 更新用户信息
	 * @param user
	 * @return void
	 */
	public void  updateUser(User user){
		
		if (!StringUtil.isEmptyString(user.getUser_pass())) {
			user.setUser_pass(Md5.makeMd5(user.getUser_pass()));
		}
		userDao.updateUser(user);
		
	}
	
	/**
	 * @Title: delUsers
	 * @Description: 删除用户信息
	 * @param user
	 * @return void
	 */
	public void  delUsers(User user){
		
		userDao.delUsers(user.getIds().split(","));
		
	}
	
	/**
	 * @Title: listColleges
	 * @Description: 社团查询
	 * @param college
	 * @return List<College>
	 */
	public List<College>  listColleges(College college,int[] sum){
		
		if (sum!=null) {
			sum[0] = collegeDao.listCollegesCount(college);
		}
		List<College> colleges = collegeDao.listColleges(college);
		
		
		return colleges;
	}
	
	/**
	 * @Title: getCollege
	 * @Description: 社团查询
	 * @param college
	 * @return College
	 */
	public College  getCollege(College college){
		
		College _college = collegeDao.getCollege(college);
		
		return _college;
	}
	 
	/**
	 * @Title: approveCollege
	 * @Description:创建社团审批
	 * @param college
	 * @return void
	 */
	public void  approveCollege(College college){
		
		//审批通过
		college.setCollege_flag(2);
		collegeDao.updateCollege(college);
		//设置申请人为社团管理员
		User user = new User();
		user.setUser_id(college.getUser().getUser_id());
		user.setUser_type(2);
		userDao.updateUserType(user);
		
	}
	
	/**
	 * @Title: updateCollege
	 * @Description: 更新社团信息
	 * @param college
	 * @return void
	 */
	public void  updateCollege(College college){
		
		//简介内容编码
		if (!StringUtil.isEmptyString(college.getCollege_note())) {
			college.setCollege_note(Transcode.htmlEncode(college.getCollege_note()));
		}
		collegeDao.updateCollege(college);
		
	}
	
	/**
	 * @Title: delColleges
	 * @Description: 删除社团信息
	 * @param college
	 * @return void
	 */
	public void  delColleges(College college){
		
		collegeDao.delColleges(college.getIds().split(","));
		
	}
	
	/**
	 * @Title: listMembers
	 * @Description: 社团成员查询
	 * @param member
	 * @return List<Member>
	 */
	public List<Member>  listMembers(Member member,int[] sum){
		
		if (sum!=null) {
			sum[0] = memberDao.listMembersCount(member);
		}
		List<Member> members = memberDao.listMembers(member);
		
		
		return members;
	}
	
	/**
	 * @Title: approveMember
	 * @Description: 社团成员加入审批
	 * @param member
	 * @return void
	 */
	public void  approveMember(Member member){
		
		member.setMember_flag(2);//审批通过
		memberDao.updateMember(member);
		
	}
	
	/**
	 * @Title: delMembers
	 * @Description: 删除社团成员信息
	 * @param member
	 * @return void
	 */
	public void  delMembers(Member member){
		
		memberDao.delMembers(member.getIds().split(","));
		
	}
	
	/**
	 * @Title: listEquips
	 * @Description: 学校器材查询
	 * @param equip
	 * @return List<Equip>
	 */
	public List<Equip>  listEquips(Equip equip,int[] sum){
		
		if (sum!=null) {
			sum[0] = equipDao.listEquipsCount(equip);
		}
		List<Equip> equips = equipDao.listEquips(equip);
		
		
		return equips;
	}
	
	/**
	 * @Title: getEquip
	 * @Description: 学校器材查询
	 * @param equip
	 * @return Equip
	 */
	public Equip  getEquip(Equip equip){
		
		Equip _equip = equipDao.getEquip(equip);
		
		return _equip;
	}
	 
	/**
	 * @Title: addEquip
	 * @Description: 添加学校器材
	 * @param equip
	 * @return void
	 */
	public void  addEquip(Equip equip){
		
		equipDao.addEquip(equip);
		
	}
	
	/**
	 * @Title: updateEquip
	 * @Description: 更新学校器材信息
	 * @param equip
	 * @return void
	 */
	public void  updateEquip(Equip equip){
		
		equipDao.updateEquip(equip);
		
	}
	
	/**
	 * @Title: delEquips
	 * @Description: 删除学校器材信息
	 * @param equip
	 * @return void
	 */
	public void  delEquips(Equip equip){
		
		equipDao.delEquips(equip.getIds().split(","));
		
	}
	
	/**
	 * @Title: listPicnewss
	 * @Description: 查询图片新闻
	 * @param picnews
	 * @param sum
	 * @return List<Picnews>
	 */
	public List<Picnews>  listPicnewss(Picnews picnews,int[] sum){
		
		if (sum!=null) {
			sum[0] = 4;
		}
		List<Picnews> picnewss = picnewsDao.listPicnewss(picnews);
		
		
		return picnewss;
	}
	
	/**
	 * @Title: getPicnews
	 * @Description: 查询图片新闻
	 * @param picnews
	 * @return Picnews
	 */
	public Picnews  getPicnews(Picnews picnews){
		
		Picnews _picnews = picnewsDao.getPicnews(picnews);
		
		return _picnews;
	}
	
	/**
	 * @Title: updatePicnews
	 * @Description: 更新图片新闻
	 * @param user
	 * @return void
	 */
	public void  updatePicnews(Picnews picnews){
		
		//图片新闻内容编码
		picnews.setPicnews_content(Transcode.htmlEncode(picnews.getPicnews_content()));
		picnewsDao.updatePicnews(picnews);
		
		
	}
	
	
	/**
	 * @Title: listActivitys
	 * @Description: 活动消息查询
	 * @param activity
	 * @return List<Activity>
	 */
	public List<Activity>  listActivitys(Activity activity,int[] sum){
		
		if (sum!=null) {
			sum[0] = activityDao.listActivitysCount(activity);
		}
		List<Activity> activitys = activityDao.listActivitys(activity);
		
		
		return activitys;
	}
	
	/**
	 * @Title: getActivity
	 * @Description: 活动消息查询
	 * @param activity
	 * @return Activity
	 */
	public Activity  getActivity(Activity activity){
		
		Activity _activity = activityDao.getActivity(activity);
		
		return _activity;
	}
	 
	/**
	 * @Title: addActivity
	 * @Description: 添加活动消息
	 * @param activity
	 * @return void
	 */
	public void  addActivity(Activity activity){
		User admin = (User)Param.getSession("admin");
		activity.setUser_id(admin.getUser_id());
		//活动消息内容编码后保存
		if (!StringUtil.isEmptyString(activity.getActivity_content())) {
			activity.setActivity_content(Transcode.htmlEncode(activity.getActivity_content()));
		}
		activityDao.addActivity(activity);
		
	}
	
	/**
	 * @Title: approveActivity
	 * @Description: 审批活动消息
	 * @param activity
	 * @return void
	 */
	public void  approveActivity(Activity activity){
		
		activity.setActivity_flag(2);
		activityDao.updateActivity(activity);
		
	}
	
	/**
	 * @Title: updateActivity
	 * @Description: 更新活动消息信息
	 * @param activity
	 * @return void
	 */
	public void  updateActivity(Activity activity){
		
		//活动消息内容编码后保存
		if (!StringUtil.isEmptyString(activity.getActivity_content())) {
			activity.setActivity_content(Transcode.htmlEncode(activity.getActivity_content()));
		}
		activityDao.updateActivity(activity);
		
	}
	
	/**
	 * @Title: delActivitys
	 * @Description: 删除活动消息信息
	 * @param activity
	 * @return void
	 */
	public void  delActivitys(Activity activity){
		
		activityDao.delActivitys(activity.getIds().split(","));
		
	}
	
	/**
	 * @Title: listNewss
	 * @Description: 新闻查询
	 * @param news
	 * @return List<News>
	 */
	public List<News>  listNewss(News news,int[] sum){
		
		if (sum!=null) {
			sum[0] = newsDao.listNewssCount(news);
		}
		List<News> newss = newsDao.listNewss(news);
		
		
		return newss;
	}
	
	/**
	 * @Title: getNews
	 * @Description: 新闻查询
	 * @param news
	 * @return News
	 */
	public News  getNews(News news){
		
		News _news = newsDao.getNews(news);
		
		return _news;
	}
	 
	/**
	 * @Title: addNews
	 * @Description: 添加新闻
	 * @param news
	 * @return void
	 */
	public void  addNews(News news){
		User admin = (User)Param.getSession("admin");
		news.setUser_id(admin.getUser_id());
		
		news.setNews_date(DateUtil.dateToDateString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		//新闻内容编码后保存
		if (!StringUtil.isEmptyString(news.getNews_content())) {
			news.setNews_content(Transcode.htmlEncode(news.getNews_content()));
		}
		newsDao.addNews(news);
		
	}
	
	/**
	 * @Title: updateNews
	 * @Description: 更新新闻信息
	 * @param news
	 * @return void
	 */
	public void  updateNews(News news){
		
		//新闻内容编码后保存
		if (!StringUtil.isEmptyString(news.getNews_content())) {
			news.setNews_content(Transcode.htmlEncode(news.getNews_content()));
		}
		newsDao.updateNews(news);
		
	}
	
	/**
	 * @Title: approveNews
	 * @Description: 审批新闻信息
	 * @param news
	 * @return void
	 */
	public void  approveNews(News news){
		
		news.setNews_flag(2);
		newsDao.updateNews(news);
		
	}
	
	/**
	 * @Title: delNewss
	 * @Description: 删除新闻信息
	 * @param news
	 * @return void
	 */
	public void  delNewss(News news){
		
		newsDao.delNewss(news.getIds().split(","));
		
	}
	
	/**
	 * @Title: listSblogs
	 * @Description: 留言查询
	 * @param sblog
	 * @return List<Sblog>
	 */
	public List<Sblog>  listSblogs(Sblog sblog,int[] sum){
		
		if (sum!=null) {
			sum[0] = sblogDao.listSblogsCount(sblog);
		}
		List<Sblog> sblogs = sblogDao.listSblogs(sblog);
		
		
		return sblogs;
	}
	
	/**
	 * @Title: approveSblog
	 * @Description: 留言审核
	 * @param Sblog
	 * @return void
	 */
	public void  approveSblog(Sblog sblog){
		
		sblog.setSblog_flag(2);
		sblogDao.updateSblog(sblog);
		
	}

	/**
	 * @Title: delSblogs
	 * @Description: 删除留言
	 * @param sblog
	 * @return void
	 */
	public void  delSblogs(Sblog sblog){
		
		sblogDao.delSblogs(sblog.getIds().split(","));
		
	}

	public ActivityDao getActivityDao() {
		return activityDao;
	}

	public CollegeDao getCollegeDao() {
		return collegeDao;
	}

	public EquipDao getEquipDao() {
		return equipDao;
	}

	public MemberDao getMemberDao() {
		return memberDao;
	}

	public NewsDao getNewsDao() {
		return newsDao;
	}

	public PicnewsDao getPicnewsDao() {
		return picnewsDao;
	}

	public SblogDao getSblogDao() {
		return sblogDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setActivityDao(ActivityDao activityDao) {
		this.activityDao = activityDao;
	}

	public void setCollegeDao(CollegeDao collegeDao) {
		this.collegeDao = collegeDao;
	}

	public void setEquipDao(EquipDao equipDao) {
		this.equipDao = equipDao;
	}

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public void setNewsDao(NewsDao newsDao) {
		this.newsDao = newsDao;
	}

	public void setPicnewsDao(PicnewsDao picnewsDao) {
		this.picnewsDao = picnewsDao;
	}

	public void setSblogDao(SblogDao sblogDao) {
		this.sblogDao = sblogDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
}
