<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="STYLESHEET" type="text/css" href="<%= this.getServletContext().getContextPath() %>/resources/dhtmlx/codebase/dhtmlx_debug.css">
<link rel="STYLESHEET" type="text/css" href="<%= this.getServletContext().getContextPath() %>/resources/dhtmlx/sources/dhtmlxGrid/codebase/skins/dhtmlxgrid_dhx_web.css">
<!-- 标题栏会显示图片，这样更美观 ，具体使用哪个CSS样式根据后面使用mygrid.setSkin("dhx_skyblue");方法决定-->
<link rel="STYLESHEET" type="text/css" href="<%= this.getServletContext().getContextPath() %>/resources/dhtmlx/sources/dhtmlxGrid/codebase/skins/dhtmlxgrid_dhx_skyblue.css">
<link rel="STYLESHEET" type="text/css" href="<%= this.getServletContext().getContextPath() %>/resources/dhtmlx/sources/dhtmlxGrid/codebase/skins/dhtmlxgrid_dhx_terrace.css">
<script src="<%= this.getServletContext().getContextPath() %>/resources/dhtmlx/codebase/dhtmlx_debug.js"></script>
<script src="<%= this.getServletContext().getContextPath() %>/resources/dhtmlx/sources/dhtmlxGrid/codebase/dhtmlxgrid.js"></script>
<script src="<%= this.getServletContext().getContextPath() %>/resources/dhtmlx/sources/dhtmlxGrid/codebase/ext/dhtmlxgrid_json.js"></script>
<!-- 如果需要给某列数据添加链接，则需要引入这个js -->
<script src="<%= this.getServletContext().getContextPath() %>/resources/dhtmlx/sources/dhtmlxGrid/codebase/excells/dhtmlxgrid_excell_link.js"></script>
<script src="<%= this.getServletContext().getContextPath() %>/resources/dhtmlx/sources/dhtmlxToolbar/codebase/dhtmlxtoolbar.js"></script>
<script src="<%= this.getServletContext().getContextPath() %>/resources/jquery/jquery.js"></script>
	<script>
		var userUpdateForm, formData;
		function doOnLoad() {
			formData = [
				{type: "settings", position: "label-left", labelWidth: 130, inputWidth: 160},
				{type: "hidden", label: "用户ID",name:"userId"},
				{type: "input", label: "登陆名",name:"userLoginName"},
				{type: "input", label: "登陆密码",name:"userLoginPW", tooltip: "请输入你的登录密码"},
				{type: "input", label: "用户姓名", name: "userName", tooltip: "请数据用户姓名"},
				{type: "input", label: "单位名称", name:"userUnitName",tooltip: "请输入单位名称"},
				{type: "input", label: "单位编码", name:"userUnitCode",tooltip: "请输入单位编码"},
				{type: "combo", label: "管理级别", name:"userMgtLevel",tooltip: "请选择管理级别",options:[
     				{text: "职员", value: "职员"},
  					{text: "经理", value: "经理", selected: true},
  					{text: "部门经理", value: "部门经理"},
  					{text: "主管", value: "主管"}
  				]},
				{type: "input", label: "移动电话", name:"userMobTel",tooltip: "请输入移动电话"},
				{type: "input", label: "固定电话", name:"userFixTel",tooltip: "请输入固定电话"},
				{type: "input", label: "电子邮箱", name:"userUsrEmail",tooltip: "请输入电子邮箱"},
				{type: "input", label: "邮政编码", name:"userPostCode",tooltip: "请输入邮政编码"},
				{type: "combo", label: "用户类型", name:"userType",tooltip: "请选择用户类型",options:[
     				{text: "普通用户", value: "1", selected: true},
   					{text: "管理", value: "2"},
   					{text: "boss", value: "3"}
   				]},
				{type: "button", value: "提交"}
			];
			userUpdateForm = new dhtmlXForm("userUpdateForm", formData);
			userUpdateForm.enableLiveValidation(true);
			userUpdateForm.disableItem("userLoginName");
			userUpdateForm.setReadonly("userType", true);
			userUpdateForm.setReadonly("userMgtLevel", true);
			$.post("<%= this.getServletContext().getContextPath() %>/user/getUserById.do",{userId:'5800949cfe1dd120a0742d68'},function(data){
				console.log(data);
				userUpdateForm.setFormData(data);
			});
			userUpdateForm.attachEvent("onButtonClick",function(){
				userUpdateForm.validate();
				var userinfo = userUpdateForm.getFormData();
				console.log(userinfo);
				$.post("<%= this.getServletContext().getContextPath() %>/user/userUpdate.do",userinfo,function(){
					dhtmlx.alert("用户信息修改成功",function(){});
				});
			});
		}
	</script>
</head>
<body onload="doOnLoad();">
	<div id="userUpdateForm" style="height:510px;"></div>
	<div id="userAddForm" style="height:510px;"></div>
</body>
</html>