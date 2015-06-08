Ext.define("core.test.view.TestForm",{
	extend:"core.app.base.BaseForm",
	//别名 testform
	alias:"widget.testform",
	//添加一个 funCode属性
//	funCode:"testmodule_main",
//	itemId:"testmodule_main_form",
	items:[{
		name:"name",
		fieldLabel:"名称"
	},{
		name:"code",
		fieldLabel:"编码"
	},{
		//日期
		name:"birthday",
		fieldLabel:"出生日期",
		xtype:"datetimefield",
		dateType:"date"
	}]
});