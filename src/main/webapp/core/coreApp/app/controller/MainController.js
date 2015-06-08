/**
 * 程序的主控制器
 */
Ext.define("core.app.controller.MainController",{
	extend:"Ext.app.Controller",
	//导入的父类 
	mixins: {
		//导入各自的控制器
		btnCtr:"core.app.controller.ButtonController",
		formCtr:"core.app.controller.FormController",
		gridCtr:"core.app.controller.GridController",
		panelCtr:"core.app.controller.PanelController",
		//注入我们的 工具类
		gridActionUtil:"core.util.GridActionUtils",
		//导入我们的ajax请求工具类
		suppleUtil:"core.util.SuppleUtil",
		//信息提示效果
		messageUtil:"core.util.MessageUtil",
		//表单工具类
		formUtil:"core.util.FormUtil",
		//数据库js工具类
		sqlUtil:"core.util.SqlUtil"
	},
	//ctr对象
	ctr:{},
	//初始化
	init:function(){
		var self=this;
		//将我们的主控制器对象静态化
		coreApp=self;
		//初始化 btn对象
		self.initBtn();
		
		//初始化函数,就是我们里面的方法
		self.initForm();
		self.initGrid();
		self.initPanel();
		
		//获取comm中声明的变量  1024
//		alert(comm.get("clientWidth"));
		
		//事件注册, ctr 中是我们定义的 对象
		this.control(self.ctr);
	},
	//界面
	views:[
		"core.test.view.TestGrid",
		"core.test.view.TestForm",
		"core.test.view.TestPanel"
	]
})