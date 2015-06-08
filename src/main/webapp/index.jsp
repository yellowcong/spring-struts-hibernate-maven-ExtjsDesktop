<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 导入desktop 的资源文件 -->
	
  <!-- 系统的css样式 -->
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/extjs/resources/css/ext-all.css" />
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/extjs/examples/shared/example.css" />
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/extjs/examples/ux/css/TabScrollerMenu.css" />
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/extjs/examples/ux/css/ItemSelector.css" />
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/MyDesktop/css/desktop.css" />
  <!-- 自定义css样式 -->
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/core/css/comm.css" />
  <!-- 导入js文件 -->
  <script type="text/javascript" src="<%=request.getContextPath() %>/extjs/ext-all-debug.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath() %>/extjs/locale/ext-lang-zh_CN.js"></script>

   <!--项目入口  -->
   
   <!--外部组建-->
   <script type="text/javascript" src="<%=request.getContextPath() %>/core/loader.js"></script>
  <!-- 自己定义的组建 --> 
  <script type="text/javascript" src="<%=request.getContextPath() %>/core/app.js"></script>
   
   <!-- 静态变量声明 -->
  <script type="text/javascript" src="<%=request.getContextPath() %>/core/coreApp/util/comm.js"></script>
  
  <!-- 导入example.js -->
  <script type="text/javascript" src="<%=request.getContextPath() %>/extjs/examples/shared/examples.js"></script>
  <link style="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/extjs/examples/shared/example.css"/>
 </head>
<body>
	<script type="text/javascript">
			<!--加载分辨率大小,添加静态变量-->
			var clientWidth = document.body.clientWidth;
			var clientHeight = document.body.clientHeight;
			var screenWidth = document.body.scrollWidth;
			var screenHeight = document.body.scrollHeight;
			var resolutionHeight = window.screen.height;
			var resolutionWidth = window.screen.width;
			comm.add("clientWidth",clientWidth);
			comm.add("clientHeight",clientHeight);
			comm.add("screenWidth",screenWidth);
			comm.add("screenHeight",screenHeight);
			comm.add("resolutionWidth",resolutionWidth);
		    comm.add("resolutionHeight",resolutionHeight);   
           <!--改造窗体的层次-->
           Ext.override(Ext.ZIndexManager, {
               tempHidden: [],
               show: function () {
                   var comp, x, y;
                   while (comp = this.tempHidden.shift()) {
                       x = comp.x;
                       y = comp.y;
                       comp.show();
                       comp.setPosition(x, y);
                   }
               }
           });
           <!--加载程序-->
           Ext.Loader.setConfig({
                 enabled: true,
                 paths: {
                	 //导入MyDesktop 的路径
                	 //这个路径要弄对，不然找不到, 
                     'Ext.ux.desktop': 'MyDesktop/js',
                     //我们的MyDesktop 的路径
                     'MyDesktop': 'MyDesktop'
                 }
             });
           <!--引用类-->
           Ext.require([
                   'MyDesktop.App'
                   ]);
            <!--实例化-->
           var myDesktopApp;
           Ext.onReady(function () {
               myDesktopApp = new MyDesktop.App();
           });
       </script>
</body>
</html>