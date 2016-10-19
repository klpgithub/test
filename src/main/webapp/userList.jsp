<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户页面</title>
<link rel="STYLESHEET" type="text/css" href="<%= this.getServletContext().getContextPath() %>/resources/dhtmlx/codebase/dhtmlx_debug.css">
<link rel="STYLESHEET" type="text/css" href="<%= this.getServletContext().getContextPath() %>/resources/dhtmlx/sources/dhtmlxGrid/codebase/skins/dhtmlxgrid_dhx_web.css">
<link rel="STYLESHEET" type="text/css" href="<%= this.getServletContext().getContextPath() %>/resources/dhtmlx/sources/dhtmlxGrid/codebase/skins/dhtmlxgrid_dhx_skyblue.css">
<link rel="STYLESHEET" type="text/css" href="<%= this.getServletContext().getContextPath() %>/resources/dhtmlx/sources/dhtmlxGrid/codebase/skins/dhtmlxgrid_dhx_terrace.css">
<script src="<%= this.getServletContext().getContextPath() %>/resources/dhtmlx/codebase/dhtmlx_debug.js"></script>
<script src="<%= this.getServletContext().getContextPath() %>/resources/dhtmlx/sources/dhtmlxGrid/codebase/dhtmlxgrid.js"></script>
<script src="<%= this.getServletContext().getContextPath() %>/resources/dhtmlx/sources/dhtmlxGrid/codebase/ext/dhtmlxgrid_json.js"></script>
<!-- 如果需要给某列数据添加链接，则需要引入这个js -->
<script src="<%= this.getServletContext().getContextPath() %>/resources/dhtmlx/sources/dhtmlxGrid/codebase/excells/dhtmlxgrid_excell_link.js"></script>
<script src="<%= this.getServletContext().getContextPath() %>/resources/dhtmlx/sources/dhtmlxToolbar/codebase/dhtmlxtoolbar.js"></script>
<script src="<%= this.getServletContext().getContextPath() %>/resources/dhtmlx/sources/dhtmlxForm/codebase/dhtmlxform.js"></script>
<script src="<%= this.getServletContext().getContextPath() %>/resources/dhtmlx/sources/dhtmlxForm/codebase/dhtmlxform_deprecated.js"></script>
<script src="<%= this.getServletContext().getContextPath() %>/resources/dhtmlx/sources/dhtmlxForm/codebase/ext/dhtmlxform_item_upload.js"></script>
<script src="<%= this.getServletContext().getContextPath() %>/resources/jquery/jquery.js"></script>
<style>
    html, body {
        width: 100%;      /*provides the correct work of a full-screen layout*/ 
        height: 100%;     /*provides the correct work of a full-screen layout*/
        overflow: hidden; /*hides the default body's space*/
        margin: 0px;      /*hides the body's scrolls*/
    }
</style>
</head>
<body onload="doOnLoad()" id="win">
	<div id="toolbarObj"></div>
	<div id="gridbox" style="width:100%;height:300px;background-color:white;"></div>
<script>
    mygrid = new dhtmlXGridObject('gridbox');
    mygrid.setImagePath("resources/dhtmlx/sources/dhtmlxGrid/codebase/imgs/");
    //标题显示内容
    mygrid.setHeader(",登陆名,登陆密码,用户姓名,单位编码,单位名称,管理级别,移动电话,固定电话,电子邮箱,邮政编码,用户类型ID");
    //指明列的宽度，*表示是余下的宽度显示
    mygrid.setInitWidths("30,100,100,100,100,100,100,100,100,*,100,100");
    //表示内容排序位置，分别是左、中、中
    mygrid.setColAlign("center,center,center,center,center,center,center,center,center,center,center,center");
   /*  ch ---- checkbox
       ra ---- radio
       ro ---- readonly，双击具体某项数据时不可编辑的
       txt ---- 显示的是字符串，双击某项数据会弹出编辑页面*/
    mygrid.setColTypes("ch,ed,ed,ed,ed,ed,ed,ed,ed,ed,ed,dyn");
    //mygrid.setColTypes("ed,dhxCalendar,dhxCalendarA");
    //按照什么方式排序int,str,date 三个类型
    mygrid.setColSorting("dyn,str,str,str,str,str,str,str,str,str,str,str");
    //指明使用什么皮肤（天蓝色）,这个和前面引用是CSS对应起来
   mygrid.setSkin("dhx_skyblue");
//     mygrid.setSkin("dhx_black");
    //初始化数据
    mygrid.init();
//     mygrid.loadXML("");
    //备注：数据列一定要和标题栏对应起来，否则会显示前面的数据
    mygrid.enableEditEvents(true,false,false);
    //
   <%-- 	window.dhx4.ajax.post("<%= this.getServletContext().getContextPath() %>/user/list.do", function(data){
   	   	console.log(data);
   	   	var d = window.dhx4.s2j(data.xmlDoc.responseText);
   	   	var js = {rows:d};
   		mygrid.parse(js, function(){
	    	mygrid.checkAll(false);//设置grid内所有checkbox是否全部选中
   		}, "json")
   	}); --%>
    var myToolbar,i=1;
    
    function doOnLoad() {
		myToolbar = new dhtmlXToolbarObject("toolbarObj");
		myToolbar.addButton("change", 1, "修改", "resources/dhtmlx/imgs/cut.gif", "resources/dhtmlx/imgs/cut_dis.gif");
		myToolbar.addButton("add", 2, "新增", "resources/dhtmlx/imgs/new.gif", "resources/dhtmlx/imgs/new_dis.gif");
		myToolbar.addButton("remove", 3, "删除", "resources/dhtmlx/imgs/undo.gif", "resources/dhtmlx/imgs/undo_dis.gif");
		
		myToolbar.attachEvent("onClick", function(id) {
			if (id == "change") {
				toChange();
			}else if (id=="add") {
				addUser();
			}else if (id=="remove") {
				removeUserById();
			}
		});
	}
    
  	//根据ID修改用户信息
    var userUpdateForm ;
    var formData = [
       			{type: "settings", position: "label-left", labelWidth: 70, inputWidth: 80},
       			{type: "hidden", label: "用户ID",name:"userId"},
       			{type: "input", label: "登陆名",name:"userLoginName", validate: "NotEmpty"},
       			{type: "input", label: "登陆密码",name:"userLoginPW", tooltip: "请输入你的登录密码", validate: "NotEmpty"},
       			{type: "input", label: "用户姓名", name: "userName", tooltip: "请数据用户姓名", validate: "NotEmpty"},
       			{type: "input", label: "单位名称", name:"userUnitName",tooltip: "请输入单位名称", validate: "NotEmpty"},
       			{type: "input", label: "单位编码", name:"userUnitCode",tooltip: "请输入单位编码", validate: "NotEmpty"},
       			{type: "combo", label: "管理级别", name:"userMgtLevel",tooltip: "请选择管理级别", validate: "NotEmpty",options:[
       		  				{text: "职员", value: "职员", selected: true},
       						{text: "经理", value: "经理"},
       						{text: "部门经理", value: "部门经理"},
       						{text: "主管", value: "主管"}
       					]},
       			{type: "input", label: "移动电话", name:"userMobTel",tooltip: "请输入移动电话", validate: "NotEmpty"},
       			{type: "input", label: "固定电话", name:"userFixTel",tooltip: "请输入固定电话", validate: "NotEmpty"},
       			{type: "input", label: "电子邮箱", name:"userUsrEmail",tooltip: "请输入电子邮箱", validate: "NotEmpty"},
       			{type: "input", label: "邮政编码", name:"userPostCode",tooltip: "请输入邮政编码", validate: "NotEmpty"},
       			{type: "combo", label: "用户类型", name:"userType",tooltip: "请选择用户类型", validate: "NotEmpty",options:[
       		  				{text: "普通用户", value: "1", selected: true},
       							{text: "管理", value: "2"},
       							{text: "boss", value: "3"}
       						]},
       			{type: "button", value: "提交"}
        	];
    function toChange(){
    	var dhxWins = new dhtmlXWindows();
		dhxWins.attachViewportTo("win");
	   	var checked=mygrid.getCheckedRows(0);//选中的ID
    	if (checked.length<24) {
    		dhtmlx.alert({text:"请选中一条数据再修改!",title:"提示"});
    	}else if (checked.length>24) {
    		dhtmlx.alert({text:"一次只能修改一条数据!",title:"提示"});			
		}else {
			var win = dhxWins.createWindow("userChangeWin", 280, 20, 800, 600);
			win.denyResize();//设置窗口大小不可变
			win.setModal(true);//设置模态窗口
			win.setText("用户信息修改");
			//...
			userUpdateForm = new dhtmlXForm("userUpdateForm", formData);
			userUpdateForm.enableLiveValidation(true);
// 			userUpdateForm.disableItem("userLoginName");
			userUpdateForm.setReadonly("userType", true);
			userUpdateForm.setReadonly("userMgtLevel", true);
			$.post("<%= this.getServletContext().getContextPath() %>/user/getUserById.do",{userId:checked},function(data){
// 				console.log(data);
				userUpdateForm.setFormData(data);
			});
			//把form表单添加到win窗口
			win.attachObject("userUpdateForm");
			//添加表单中按钮的点击事件
			userUpdateForm.attachEvent("onButtonClick",function(){
				userUpdateForm.validate();//验证貌似没用...
				var userinfo = userUpdateForm.getFormData();
				$.post("<%= this.getServletContext().getContextPath() %>/user/userUpdate.do",userinfo,function(){
					dhtmlx.alert({text:"用户信息修改成功",title:"提示"});
					//关闭window窗口    unload  
					win.detachObject();
					dhxWins.unload();
					userUpdateForm.unload();
					dhxWins =  null;
					//刷新grid...
					mygrid.clearAll();
					$.post("<%= this.getServletContext().getContextPath() %>/user/list.do",function(data){
			    		var js = {rows:data};
			    		mygrid.parse(js, function(){
					    	mygrid.checkAll(false);//设置grid内所有checkbox是否全部选中
			    		}, "json")
			    	},"json")
				});
			});
			//...
// 			dhxWins.attachEvent("onClose", function(win){
// 				location.reload();
// 			});
		}
    }
    //添加用户
    var userAddForm;
    function addUser(){
    	var dhxWins = new dhtmlXWindows();
		dhxWins.attachViewportTo("win");
		var win = dhxWins.createWindow("userAddForm", 280, 20, 800, 600);
		win.denyResize();//设置窗口大小不可变
		win.setModal(true);//设置模态窗口
		win.setText("用户信息添加");
		//...
		userAddForm = new dhtmlXForm("userAddForm", formData);
		userAddForm.setReadonly("userType", true);
		userAddForm.setReadonly("userMgtLevel", true);
		//把form表单添加到win窗口
		win.attachObject("userAddForm");
		userAddForm.attachEvent("onButtonClick",function(){
			userAddForm.validate();
			var userinfo = userAddForm.getFormData();
			$.post("<%= this.getServletContext().getContextPath() %>/user/addUser.do",userinfo,function(){
				dhtmlx.alert({text:"用户信息添加成功",title:"提示"});
				win.detachObject();
				userAddForm.unload();
				dhxWins.unload();
				userAddForm = null;
				//刷新grid...
				mygrid.clearAll();
				$.post("<%= this.getServletContext().getContextPath() %>/user/list.do",function(data){
		    		var js = {rows:data};
		    		mygrid.parse(js, function(){
				    	mygrid.checkAll(false);//设置grid内所有checkbox是否全部选中
		    		}, "json")
		    	},"json")
			});
		});
		//...
// 		dhxWins.attachEvent("onClose", function(win){
// 			location.reload();
// 		});
    }
    //根据ID删除用户
    function removeUserById(){
    	var checked=mygrid.getCheckedRows(0);
    	if (checked.length=="") {
    		dhtmlx.alert({text:"请选择你要删除的用户",title:"提示"});
		}else {
			dhtmlx.confirm({text:"你确定要删除选中的用户吗?",title:'提示',ok:"确定", cancel:"取消",callback: function(result){
					if (result) {
						$.post("<%= this.getServletContext().getContextPath() %>/user/removeUserById.do",{userId:checked},function(data){
				    	},"json");
						dhtmlx.alert({text:"删除用户成功",title:"提示"});
						//刷新grid...
						mygrid.clearAll();
						$.post("<%= this.getServletContext().getContextPath() %>/user/list.do",function(data){
				    		var js = {rows:data};
				    		mygrid.parse(js, function(){
						    	mygrid.checkAll(false);//设置grid内所有checkbox是否全部选中
				    		}, "json")
				    	},"json")
					}
				}
			})
		}
    }
    $(function(){
    	$.post("<%= this.getServletContext().getContextPath() %>/user/list.do",function(data){
	    		var js = {rows:data};
	    		mygrid.parse(js, function(){
		    	mygrid.checkAll(false);//设置grid内所有checkbox是否全部选中
    		}, "json")
    	},"json")
    	$("#selectId").click(function(){
    		var checked=mygrid.getCheckedRows(0);
    		alert(checked);
   		 })
    });
    //单元格直接修改...
   <%--  mygrid.attachEvent("onCellChanged", function(rId,cInd,nValue){
		var changeColumn = '';
		if (cInd==2) {
			changeColumn = "userLoginPW";
		}else if (cInd==3) {
			changeColumn = "userName";
		}else if (cInd==4) {
			changeColumn = "userUnitCode";
		}else if (cInd==5) {
			changeColumn = "userUnitName";
		}else if (cInd==6) {
			changeColumn = "userMgtLevel";
		}else if (cInd==7) {
			changeColumn = "userMobTel";
		}else if (cInd==8) {
			changeColumn = "userFixTel";
		}else if (cInd==9) {
			changeColumn = "userUsrEmail";
		}else if (cInd==10) {
			changeColumn = "userPostCode";
		}else if (cInd==11) {
			changeColumn = "userType";
		}
		window.dhx4.ajax.post("<%= this.getServletContext().getContextPath() %>/user/userUpdate.do", "userId="+rId+"&"+changeColumn+"="+nValue);
    }); --%>
</script>
<div id="userUpdateForm" style="display: none;margin: 30px;"></div>
<div id="userAddForm" style="display: none;margin: 30px;"></div>
<div id="logBar"></div>
</body>
</html>