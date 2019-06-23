package com.nkl.page.manager;

import java.util.Date;
import java.util.List;

import com.nkl.common.util.DateUtil;
import com.nkl.common.util.Md5;
import com.nkl.common.util.StringUtil;
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

public class IndexManager {

	ActivityDao activityDao;
	CollegeDao collegeDao;
	EquipDao equipDao;
	MemberDao memberDao;
	NewsDao newsDao;
	PicnewsDao picnewsDao;
	SblogDao sblogDao;
	UserDao userDao;
	
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
	 * @Description: 社团详情查询
	 * @param college
	 * @return College
	 */
	public College  getCollege(College college){
		
		College _college = collegeDao.getCollege(college);
		
		return _college;
	}
	
	/**
	 * @Title: createCollege
	 * @Description:申请创建社团
	 * @param college
	 * @return void
	 */
	public void  createCollege(College college){
		
		//申请时间
		college.setCreate_date(DateUtil.dateToDateString(new Date(),"yyyy-MM-dd"));
		//审批标志
		college.setCollege_flag(1);
		collegeDao.addCollege(college);
		
	}
	
	/**
	 * @Title: joinMember
	 * @Description:申请加入社团
	 * @param Member
	 * @return void
	 */
	public void  joinMember(Member member){
		
		//申请时间
		member.setReg_date(DateUtil.dateToDateString(new Date()));
		//审批标志
		member.setMember_flag(1);
		memberDao.addMember(member);
		
	}
	
	/**
	 * @Title: listMembers
	 * @Description: 社员加入查询
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
	 * @Title: getMember
	 * @Description: 查询社团成员
	 * @param member
	 * @return Member
	 */
	public Member  getMember(Member member){
		
		Member _member = memberDao.getMember(member);
		
		return _member;
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
	 * @Title: listEquip
	 * @Description: 查询学校器材
	 * @param equip
	 * @return List<Equip>
	 */
	public List<Equip>  listEquip(Equip equip){
		
		
		List<Equip> equips = equipDao.listEquips(equip);
		
		
		return equips;
	}
	
	/**
	 * @Title: listPicnews
	 * @Description: 查询图片新闻
	 * @param picnews
	 * @return List<Picnews>
	 */
	public List<Picnews>  listPicnews(Picnews picnews){
		
		
		List<Picnews> picnewss = picnewsDao.listPicnewss(picnews);
		
		
		return picnewss;
	}
	
	/**
	 * @Title: getPicnews
	 * @Description: 图片新闻详情
	 * @param picnews
	 * @return Picnews
	 */
	public Picnews  getPicnews(Picnews picnews){
		
		
		Picnews _picnews = picnewsDao.getPicnews(picnews);
		
		
		return _picnews;
	}

	
	/**
	 * @Title: listNewss
	 * @Description: 查询1:社团新闻 2：校园新闻
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
	 * @Description: 查询详情  1:社团新闻 2：校园新闻
	 * @param news
	 * @return News
	 */
	public News  getNews(News news){
		
		News _news = newsDao.getNews(news);
		
		return _news;
	}
	
	/**
	 * @Title: listActivitys
	 * @Description: 查询活动消息
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
	 * @Description: 查询活动消息详情
	 * @param activity
	 * @return Activity
	 */
	public Activity  getActivity(Activity activity){
		
		Activity _activity = activityDao.getActivity(activity);
		
		return _activity;
	}
	
	/**
	 * @Title: listSblogs
	 * @Description: 查询留言信息
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
	 * @Title: addSblog
	 * @Description: 新增留言
	 * @param sblog
	 * @return void
	 */
	public void  addSblog(Sblog sblog){
		
		sblog.setSblog_date(DateUtil.dateToDateString(new Date(), "yyyy-MM-dd HH:mm:ss"));//留言时间
		sblog.setSblog_flag(1);//待审核
		sblogDao.addSblog(sblog);
		
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
