package com.nkl.page.dao;

import java.util.ArrayList;
import java.util.List;

import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;
import com.nkl.page.domain.Member;

public class MemberDao extends BaseDao {

	public void addMember(Member member){
		super.add(member);
	}

	public void delMember(Integer member_id){
		super.del(Member.class, member_id);
	}

	public void delMembers(String[] member_ids){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <member_ids.length; i++) {
			sBuilder.append(member_ids[i]);
			if (i !=member_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String hql = "DELETE FROM Member WHERE member_id IN(" +sBuilder.toString()+")";

		Object[] params = null;

		super.executeUpdateHql(hql, params);
	}

	public void updateMember(Member member){
		Member _member = (Member)super.get(Member.class, member.getMember_id());
		if (member.getMember_flag()!=null && member.getMember_flag()!=0) {
			_member.setMember_flag(member.getMember_flag());
		}
		super.update(_member);
	}

	@SuppressWarnings("rawtypes")
	public Member getMember(Member member){
		Member _member=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Member m join fetch m.user join fetch m.college join fetch m.college.user WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (member.getMember_id()!=null && member.getMember_id()!=0) {
			sBuilder.append(" and member_id = ? ");
			paramsList.add(member.getMember_id());
		}
		if (member.getCollege()!=null && member.getCollege().getCollege_id()!=null && member.getCollege().getCollege_id()!=0) {
			sBuilder.append(" and m.college.college_id = " + member.getCollege().getCollege_id() +" ");
		}
		if (member.getUser()!=null && member.getUser().getUser_id()!=null && member.getUser().getUser_id()!=0) {
			sBuilder.append(" and m.user.user_id = " + member.getUser().getUser_id() +" ");
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
			_member = (Member)list.get(0);
		}

		return _member;
	}

	@SuppressWarnings("rawtypes")
	public List<Member>  listMembers(Member member){
		List<Member> members = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Member m join fetch m.user join fetch m.college join fetch m.college.user WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (member.getMember_id()!=null && member.getMember_id()!=0) {
			sBuilder.append(" and member_id = ? ");
			paramsList.add(member.getMember_id());
		}
		if (member.getCollege()!=null && member.getCollege().getCollege_id()!=null && member.getCollege().getCollege_id()!=0) {
			sBuilder.append(" and m.college.college_id = " + member.getCollege().getCollege_id() +" ");
		}
		if (member.getUser()!=null && member.getUser().getUser_id()!=null && member.getUser().getUser_id()!=0) {
			sBuilder.append(" and m.user.user_id = " + member.getUser().getUser_id() +" ");
		}
		if (!StringUtil.isEmptyString(member.getReal_name())) {
			sBuilder.append(" and m.user.real_name like '%" + member.getReal_name() + "%'");
		}
		if (!StringUtil.isEmptyString(member.getCollege_name())) {
			sBuilder.append(" and m.college.college_name like '%" + member.getCollege_name() + "%'");
		}
		if (member.getMember_flag()!=null && member.getMember_flag()!=0) {
			sBuilder.append(" and member_flag =" + member.getMember_flag() + " ");
		}
		if (member.getAdmin_id()!=0) {
			sBuilder.append(" and m.college.user.user_id =" + member.getAdmin_id() + " ");
		}

		Object[] params = null;
		if (paramsList.size()>0) {
			params = new Object[paramsList.size()];
			for (int i = 0; i < paramsList.size(); i++) {
				params[i] = paramsList.get(i);
			}
		}

		sBuilder.append(" order by member_id asc ");

		List list = null;
		if (member.getStart()!=-1) {
			list = super.findByPageHql(sBuilder.toString(), params, member.getStart(), member.getLimit());
		}else {
			list = super.executeQueryHql(sBuilder.toString(), params);
		}
		if (list != null && list.size() > 0) {
			members = new ArrayList<Member>();
			for (Object object : list) {
				members.add((Member)object);
			}
		}

		return members;
	}

	public int  listMembersCount(Member member){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM Member m join m.user join m.college join m.college.user WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (member.getMember_id()!=null && member.getMember_id()!=0) {
			sBuilder.append(" and member_id = ? ");
			paramsList.add(member.getMember_id());
		}
		if (member.getCollege()!=null && member.getCollege().getCollege_id()!=null && member.getCollege().getCollege_id()!=0) {
			sBuilder.append(" and m.college.college_id = " + member.getCollege().getCollege_id() +" ");
		}
		if (member.getUser()!=null && member.getUser().getUser_id()!=null && member.getUser().getUser_id()!=0) {
			sBuilder.append(" and m.user.user_id = " + member.getUser().getUser_id() +" ");
		}
		if (!StringUtil.isEmptyString(member.getReal_name())) {
			sBuilder.append(" and m.user.real_name like '%" + member.getReal_name() + "%'");
		}
		if (!StringUtil.isEmptyString(member.getCollege_name())) {
			sBuilder.append(" and m.college.college_name like '%" + member.getCollege_name() + "%'");
		}
		if (member.getMember_flag()!=null && member.getMember_flag()!=0) {
			sBuilder.append(" and member_flag =" + member.getMember_flag() + " ");
		}
		if (member.getAdmin_id()!=0) {
			sBuilder.append(" and m.college.user.user_id =" + member.getAdmin_id() + " ");
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
