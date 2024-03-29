package com.nkl.page.domain;

import com.nkl.common.domain.BaseDomain;
import com.nkl.common.util.StringUtil;
import com.nkl.common.util.Transcode;

public class Activity extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 3809870499420283179L;
	private Integer activity_id; // 
	private Integer user_id; // 
	private String activity_title; // 
	private String activity_content; // 
	private String activity_date; // 
	private String activity_address; // 
	private String activity_equip; // 
	private Double activity_money; // 
	private Integer activity_type; // 1:社团活动 2：校园活动
	private Integer activity_flag; // 1：待审批 2：审批通过

	private String ids;
	private String random;

	public String getActivity_flagDesc(){
		switch (activity_flag) {
		case 1:
			return "待审批";
		case 2:
			return "审批通过";
		default:
			return "";
		}
	}
	
	public String getActivity_typeDesc(){
		switch (activity_type) {
		case 1:
			return "社团活动";
		case 2:
			return "校园活动";
		default:
			return "";
		}
	}
	
	public String getActivity_contentShow(){
		if (!StringUtil.isEmptyString(activity_content)) {
			return Transcode.htmlDiscode(activity_content);
		}
		return activity_content;
	}
	
	public void setActivity_id(Integer activity_id){
		this.activity_id=activity_id;
	}

	public Integer getActivity_id(){
		return activity_id;
	}

	public void setUser_id(Integer user_id){
		this.user_id=user_id;
	}

	public Integer getUser_id(){
		return user_id;
	}

	public void setActivity_title(String activity_title){
		this.activity_title=activity_title;
	}

	public String getActivity_title(){
		return activity_title;
	}

	public void setActivity_content(String activity_content){
		this.activity_content=activity_content;
	}

	public String getActivity_content(){
		return activity_content;
	}

	public void setActivity_date(String activity_date){
		this.activity_date=activity_date;
	}

	public String getActivity_date(){
		return activity_date;
	}

	public void setActivity_address(String activity_address){
		this.activity_address=activity_address;
	}

	public String getActivity_address(){
		return activity_address;
	}

	public void setActivity_equip(String activity_equip){
		this.activity_equip=activity_equip;
	}

	public String getActivity_equip(){
		return activity_equip;
	}

	public void setActivity_money(Double activity_money){
		this.activity_money=activity_money;
	}

	public Double getActivity_money(){
		return activity_money;
	}

	public void setActivity_type(Integer activity_type){
		this.activity_type=activity_type;
	}

	public Integer getActivity_type(){
		return activity_type;
	}

	public void setActivity_flag(Integer activity_flag){
		this.activity_flag=activity_flag;
	}

	public Integer getActivity_flag(){
		return activity_flag;
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

}
