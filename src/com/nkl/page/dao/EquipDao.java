package com.nkl.page.dao;

import java.util.ArrayList;
import java.util.List;

import com.nkl.common.dao.BaseDao;
import com.nkl.common.util.StringUtil;
import com.nkl.page.domain.Equip;

public class EquipDao extends BaseDao {

	public void addEquip(Equip equip){
		super.add(equip);
	}

	public void delEquip(Integer equip_id){
		super.del(Equip.class, equip_id);
	}

	public void delEquips(String[] equip_ids){
		StringBuilder sBuilder = new StringBuilder();
		for (int i = 0; i <equip_ids.length; i++) {
			sBuilder.append(equip_ids[i]);
			if (i !=equip_ids.length-1) {
				sBuilder.append(",");
			}
		}
		String hql = "DELETE FROM Equip WHERE equip_id IN(" +sBuilder.toString()+")";

		Object[] params = null;

		super.executeUpdateHql(hql, params);
	}

	public void updateEquip(Equip equip){
		Equip _equip = (Equip)super.get(Equip.class, equip.getEquip_id());
		if (!StringUtil.isEmptyString(equip.getEquip_name())) {
			_equip.setEquip_name(equip.getEquip_name());
		}
		_equip.setEquip_note(equip.getEquip_note());
		super.update(_equip);
	}

	@SuppressWarnings("rawtypes")
	public Equip getEquip(Equip equip){
		Equip _equip=null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Equip WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (equip.getEquip_id()!=null && equip.getEquip_id()!=0) {
			sBuilder.append(" and equip_id = ? ");
			paramsList.add(equip.getEquip_id());
		}
		if (!StringUtil.isEmptyString(equip.getEquip_name())) {
			sBuilder.append(" and equip_name = '" + equip.getEquip_name() + "' ");
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
			_equip = (Equip)list.get(0);
		}

		return _equip;
	}

	@SuppressWarnings("rawtypes")
	public List<Equip>  listEquips(Equip equip){
		List<Equip> equips = null;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("FROM Equip WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (equip.getEquip_id()!=null && equip.getEquip_id()!=0) {
			sBuilder.append(" and equip_id = ? ");
			paramsList.add(equip.getEquip_id());
		}
		if (!StringUtil.isEmptyString(equip.getEquip_name())) {
			sBuilder.append(" and equip_name like '%" + equip.getEquip_name() + "%' ");
		}

		Object[] params = null;
		if (paramsList.size()>0) {
			params = new Object[paramsList.size()];
			for (int i = 0; i < paramsList.size(); i++) {
				params[i] = paramsList.get(i);
			}
		}

		sBuilder.append(" order by equip_id asc ");

		List list = null;
		if (equip.getStart()!=-1) {
			list = super.findByPageHql(sBuilder.toString(), params, equip.getStart(), equip.getLimit());
		}else {
			list = super.executeQueryHql(sBuilder.toString(), params);
		}
		if (list != null && list.size() > 0) {
			equips = new ArrayList<Equip>();
			for (Object object : list) {
				equips.add((Equip)object);
			}
		}

		return equips;
	}

	public int  listEquipsCount(Equip equip){
		int sum = 0;
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append("SELECT count(*) FROM Equip WHERE 1=1");
		List<Object> paramsList = new ArrayList<Object>();
		if (equip.getEquip_id()!=null && equip.getEquip_id()!=0) {
			sBuilder.append(" and equip_id = ? ");
			paramsList.add(equip.getEquip_id());
		}
		if (!StringUtil.isEmptyString(equip.getEquip_name())) {
			sBuilder.append(" and equip_name like '%" + equip.getEquip_name() + "%' ");
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
