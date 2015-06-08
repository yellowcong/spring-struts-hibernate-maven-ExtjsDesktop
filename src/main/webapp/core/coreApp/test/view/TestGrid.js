/**
 * 定义Grid 的子类样式
 */
Ext.define("core.test.view.TestGrid",{
	extend:"core.app.base.BaseGrid",
	//testgrid别名
	alias:"widget.testgrid",
	//添加一个 funCode属性
//	funCode:"testmodule_main",
//	itemId:"testmodule_main_grid",
	//设定行号
	//以前我们直接用的方法，通过这个方法尅设定行号
	//Ext.create("Ext.grid.RowNumberer",{text:"id"}),
	columns:[{
		//通过rownumberer 这个组建也可以插入行号
		xtype:"rownumberer",
		width : 35,
		text :'No.',
		align : 'center'
	},{
		text:"名称",
		dataIndex:"name",
		field:{
			xtype:"textfield"
		}
	},{
		//显示字段
		text:"编码",
		//模型的字段
		dataIndex:"code",
		//字段的类型
		field:{
			xtype:"textfield"
		}
	},{
		text:"出生日期",
		dataIndex:"birthday",
		field:{
			//字段类型
			xtype:"datetimefield",
			//数据类型
			dateType:"date"
		}		
	}],
	//设定的 store，在真正的做的时候，我们需要另外去指定我们的Store
	store:Ext.create("Ext.data.Store",{
		fields:[
			{name:"name",type:"string"},
			{name:"code",type:"string"},
			{name:"birthday",type:"string"}
			]
	})
});