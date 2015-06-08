/**
 * 测试的控制层
 */
Ext.define("core.test.controller.TestController",{
	extend:"Ext.app.Controller",
	//我们会调用这个初始化函数
	init:function(){
		var self=this
		//事件注册
		this.control({
			//注册添加按钮的回调函数
			"testgrid button[ref=gridInsert]":{
				click:function(btn){
//					console.log("xx");
//					//弹出两次,蛋疼
//					alert("弹出框，有两次");
				}
			},
			//表格 对象的 保存按钮
			"testform button[ref=formSave]":{
				beforeclick:function(btn){
					btn.callback=function(){
						
					}
				}
			}
		});
	},
	//界面层,加载组建
	views:[
	"core.test.view.TestGrid",
	"core.test.view.TestForm",
	"core.test.view.TestPanel"
	]
});