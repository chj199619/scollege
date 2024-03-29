<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>加入社团申请</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/store.css">
<script language="javascript" type="text/javascript" src=""></script>
<script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
<script language="javascript" type="text/javascript"> 
	
</script>
<style type="text/css">
 body,td,div
 {
   font-size:12px;
 }
</style>
</head>
<body>
<jsp:include page="top.jsp"></jsp:include>
<div id="middle">
	 <div id="product_menu">
	 	<table class="ptable_menu">
			<tr class="ptable_menu_title">
				<td>个人中心</td>
			</tr>
			<tr class="ptable_menu_text">
				<td><a href="page_myInfo.action">修改个人信息</a></td>
			</tr>
			<tr class="ptable_menu_text">
				<td><a href="page_myPwd.action">修改登录密码</a></td>
			</tr>
			<tr class="ptable_menu_text">
				<td><a href="page_myCollegeApply.action">创建社团申请</a></td>
			</tr>
			<tr class="ptable_menu_text">
				<td><a href="page_myMemberApply.action">加入社团申请</a></td>
			</tr>
		 </table>
	 </div>
	 <!--  购物车 -->
	 <div id="product_info">
			<div class="title">个人中心  &gt;&gt;  加入社团申请</div>
			<div style="margin-top:5px">
				 <table class="ptable" style="margin-bottom:5px;">
					<tr class="head_text">
						<td align="center">社团名称</td>
						<td align="center">申请人</td>
						<td align="center">申请日期</td>
						<td align="center">申请原因</td>
						<td align="center">审批结果</td>
						<td align="center">操作</td>
					</tr>
					<s:if test="#attr.members!=null && #attr.members.size()>0">
   					<s:iterator value="#attr.members" id="member" status="status">
					<tr>
						 <td width="" align="center"><s:property value="#member.college.college_name"/></td>
					     <td width="" align="center"><s:property value="#member.user.real_name"/></td>
					     <td width="" align="center"><s:property value="#member.reg_date.substring(0,10)"/></td>  
					     <td width="" align="center"><s:property value="#member.member_reason"/></td>
					     <td width="" align="center"><s:property value="#member.member_flagDesc"/></td>
					     <td width="" align="center">&nbsp;
					     	<s:if test="#member.member_flag==2">
					     		<s:a id="delMembers_%{#member.member_id}" href="javascript:void(0)">退团</s:a>
					     	</s:if>
					     </td>
					</tr>
					</s:iterator>
				    </s:if>
				    <s:else>
				    <tr>
				      <td height="60" colspan="6" align="center"><b>&lt;不存在加入社团申请信息&gt;</b></td>
				    </tr>
				    </s:else>
				 </table>
			</div>
			<div class="pages">
				<jsp:include page="page.jsp"></jsp:include>
			</div>
		</div>
	 <!--  购物车 -->
</div>
<jsp:include page="bottom.jsp"></jsp:include>
<script language="javascript" type="text/javascript">
	function GoPage()
	{
	  var pagenum=document.getElementById("goPage").value;
	  var patten=/^\d+$/;
	  if(!patten.exec(pagenum))
	  {
	    alert("页码必须为大于0的数字");
	    return false;
	  }
	  window.location.href="page_myMemberApply.action?pageNo="+pagenum;
	}
	function ChangePage(pagenum)
	{
		window.location.href="page_myMemberApply.action?pageNo="+pagenum;
	}
	
	$(document).ready(function(){
		$("a[id^='delMembers']").bind('click',function(){
		    if(confirm('确认退出社团吗!?'))
		    {
		    	var member_id=$(this).attr('id').split('_')[1];
		    	var aQuery = {
						'paramsMember.ids':member_id
				};  
				$.post('page_delMembers.action',aQuery,
					function(responseObj) {
							if(responseObj.success) {	
								 alert('退出社团成功！');
								 window.location.reload();
							}else  if(responseObj.err.msg){
								 alert('退出社团失败：'+responseObj.err.msg);
							}else{
								 alert('退出社团失败：服务器异常！');
							}	
				},'json');
		    }
		 });
		
	});
</script>
</body>
</html>