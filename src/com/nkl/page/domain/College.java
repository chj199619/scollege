package com.nkl.page.domain;

import com.nkl.common.domain.BaseDomain;
import com.nkl.common.util.StringUtil;
import com.nkl.common.util.Transcode;

public class College extends BaseDomain {
	/**
	 * @Fields serialVersionUID : TODO
	 */
	private static final long serialVersionUID = 4669629267688671267L;
	private Integer college_id; // 
	private String college_name; // 
	private String college_type; // 
	private User user; // 
	private String create_date; // 
	private Integer college_persons; // 
	private Double college_money; // 
	private String college_pic; // 
	private String college_note; // 
	private String college_plan; // 
	private Integer college_flag; // 1：待审批 2：审批通过
	
	private String real_name; // 
	private String ids;
	private String random;
	
	public String getCollege_flagDesc(){
		switch (college_flag) {
		case 1:
			return "待审批";
		case 2:
			return "审批通过";
		default:
			return "";
		}
	}
	
	public String getCollege_planShow(){
		if (!StringUtil.isEmptyString(college_plan)) {
			return Transcode.htmlDiscode(college_plan);
		}
		return college_plan;
	}
	
	public String getCollege_noteShow(){
		if (!StringUtil.isEmptyString(college_note)) {
			return Transcode.htmlDiscode(college_note);
		}
		return college_note;
	}

	public void setCollege_id(Integer college_id){
		this.college_id=college_id;
	}

	public Integer getCollege_id(){
		return college_id;
	}

	public void setCollege_name(String college_name){
		this.college_name=college_name;
	}

	public String getCollege_name(){
		return college_name;
	}

	public void setCollege_type(String college_type){
		this.college_type=college_type;
	}

	public String getCollege_type(){
		return college_type;
	}

	public void setCreate_date(String create_date){
		this.create_date=create_date;
	}

	public String getCreate_date(){
		return create_date;
	}

	public void setCollege_persons(Integer college_persons){
		this.college_persons=college_persons;
	}

	public Integer getCollege_persons(){
		return college_persons;
	}

	public void setCollege_money(Double college_money){
		this.college_money=college_money;
	}

	public Double getCollege_money(){
		return college_money;
	}

	public void setCollege_pic(String college_pic){
		this.college_pic=college_pic;
	}

	public String getCollege_pic(){
		return college_pic;
	}

	public void setCollege_note(String college_note){
		this.college_note=college_note;
	}

	public String getCollege_note(){
		return college_note;
	}

	public void setCollege_plan(String college_plan){
		this.college_plan=college_plan;
	}

	public String getCollege_plan(){
		return college_plan;
	}

	public void setCollege_flag(Integer college_flag){
		this.college_flag=college_flag;
	}

	public Integer getCollege_flag(){
		return college_flag;
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

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

}
