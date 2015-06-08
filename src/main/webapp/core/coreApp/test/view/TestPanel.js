Ext.define("core.test.view.TestPanel",{
	extend:"core.app.base.BasePanel",
	//别名
	alias:"widget.testpanel",
	
	//添加一个 funCode属性，添加这两个字段来完成对象查找
	funCode:"testmodule_main",
//	itemId:"testmodule_main_panel",
funData:{
        action:"/test/testAction", //请求Action
        whereSql:"",//表格查询条件
        orderSql:"",//表格排序条件
        //这个是一个唯一的字段，用户操作对象, pkName 表示唯一的 那个 对象
        pkName:"name",
        //id表示的是id为操作的字段
//      pkName:"id",
        modelName:"com.desktop.model.Test",//实体全路径
        tableName:"",//表名
        //默认信息，用于表格添加的时候字段默认值, 自己定义 @@ 表示设定的默认信息
        defaultObj:{name:"@createUser@",birthday:"@createTime@"},
        childFun:[]
    },
    //这个pannel导入了 testgrid和testform两个对象
	items:[{
		xtype:"testgrid"
	},{
		//有一个表单对象， 设定为隐藏的
		xtype:"testform",
		hidden:true
	}]
});