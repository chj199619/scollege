package com.nkl.page.domain;

import com.nkl.common.domain.BaseDomain;

public class Sblog extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = -7264282261132371834L;
	private Integer sblog_id; // 
	private User user; // 
	private String sblog_title; // 
	private String sblog_content; // 
	private String sblog_date; // 
	private Integer sblog_click; // 
	private String sblog_pic; // 
	private Integer sblog_flag; // 1：待审批 2：审批通过

	private String ids;
	private String random;
	
	public String getSblog_flagDesc(){
		switch (sblog_flag) {
		case 1:
			return "待审批";
		case 2:
			return "审批通过";
		default:
			return "";
		}
	}

	public void setSblog_id(Integer sblog_id){
		this.sblog_id=sblog_id;
	}

	public Integer getSblog_id(){
		return sblog_id;
	}

	public void setSblog_title(String sblog_title){
		this.sblog_title=sblog_title;
	}

	public String getSblog_title(){
		return sblog_title;
	}

	public void setSblog_content(String sblog_content){
		this.sblog_content=sblog_content;
	}

	public String getSblog_content(){
		return sblog_content;
	}

	public void setSblog_date(String sblog_date){
		this.sblog_date=sblog_date;
	}

	public String getSblog_date(){
		return sblog_date;
	}

	public void setSblog_click(Integer sblog_click){
		this.sblog_click=sblog_click;
	}

	public Integer getSblog_click(){
		return sblog_click;
	}

	public void setSblog_pic(String sblog_pic){
		this.sblog_pic=sblog_pic;
	}

	public String getSblog_pic(){
		return sblog_pic;
	}

	public void setSblog_flag(Integer sblog_flag){
		this.sblog_flag=sblog_flag;
	}

	public Integer getSblog_flag(){
		return sblog_flag;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getIds() {
		return ids;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	public String getRandom() {
		return random;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
