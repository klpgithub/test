<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%= this.getServletContext().getContextPath() %>/resources/dhtmlx/codebase/dhtmlx_debug.css"/>
<script src="<%= this.getServletContext().getContextPath() %>/resources/dhtmlx/codebase/dhtmlx_debug.js"></script>
<style>
    html, body {
        width: 100%;      /*provides the correct work of a full-screen layout*/ 
        height: 100%;     /*provides the correct work of a full-screen layout*/
        overflow: hidden; /*hides the default body's space*/
        margin: 0px;      /*hides the body's scrolls*/
    }
</style>
<script type="text/javascript">
	var layout,leftCell,rightCell,userManagerMenu,rtab;
	dhtmlxEvent(window,"load",function(){      
		var layout = new dhtmlXLayoutObject(document.body,"2E");  
// 		layout.cells("a").setText("LOGO");
		layout.cells("a").setHeight(90);
		layout.cells("a").fixSize(false, true);//禁止拖动
		layout.cells("a").hideHeader();//去掉layout的头部
		layout.cells("b").hideHeader();
		bLayout = layout.cells("b").attachLayout("2U");
		leftCell = bLayout.cells("a");
		rightCell = bLayout.cells("b");
		rightCell.hideHeader();
// 		rightCell.expand();//展开
// 		leftCell.collapse();//闭合
		leftCell.setWidth(200);
		leftCell.setText("功能菜单");
		//添加Accordion
		userAccordion = leftCell.attachAccordion({
			icons_path: "<%= this.getServletContext().getContextPath() %>/resources/dhtmlx/imgs/",
			items: [
				{ id: "accUser", text: "用户管理", icon: "open.gif" },
				{ id: "a2", text: "数据统计", icon: "flag_green.png" },
				{ id: "a3", text: "其他", icon: "flag_blue.png" }
			]
		});
		//添加按钮
		userManagerMenu = userAccordion.cells("accUser").attachMenu({
		    icons_path: "<%= this.getServletContext().getContextPath() %>/resources/dhtmlx/imgs/",
		    //添加accordion里的按钮的json
		    json: "[{id:'userManager',text:'用户管理', img: 'about.gif'}]"
		});
		rtab = rightCell.attachTabbar();
		rtab.addTab("wel","欢迎",null,null,false);//添加欢迎页 
		rtab.cells("wel").setActive();//设置默认选中
		userManagerMenu.attachEvent("onClick", function(id, zoneId, cas){
			//判断tab的状态...
// 			alert(rtab.cells(id).isActive())
// 			var flog = rtab.cells(id).isActive();
// 			if (flog) {
// 				rtab.cells(id).setActive();
// 			}else if (id == 'userManager') {
// 				showUserList();
// 			}
			showUserList();
		});
		
	});   
	function showUserList(){
		rtab.addTab("user","用户管理",null,null,false,true);
		rtab.cells("user").setActive();
		rtab.cells("user").attachURL("userList.jsp");
	}
</script>
</head>
<body>
</body>
</html>