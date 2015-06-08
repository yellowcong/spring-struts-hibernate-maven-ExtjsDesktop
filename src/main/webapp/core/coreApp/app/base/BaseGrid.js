/**
 * 我们的 gridpannel
 */
Ext.define("core.app.base.BaseGrid",{
	//继承于grid的panel对象
	extend:"Ext.grid.Panel",
	//别名
	alias:"widget.basegrid",
	//边框
	border:0,
	//多选
	multiSelect:true,
	//渲染
	frame:true,
	//选择样式
	selModel:{
		selType:"checkboxmodel"
	},
	//工具栏 
	tbar:[
		{xtype:'button',text:'添加到表单',ref:'gridInsertF',iconCls:'table_add'},
		{xtype:'button',text:'添加',ref:'gridInsert',iconCls:'table_add'},
		//编辑菜单，我们添加了额disabled:true 表示 不可用
//		{xtype:'button',text:'编辑',ref:'gridEdit',iconCls:'table_remove',disabled:true},
		{xtype:'button',text:'编辑',ref:'gridEdit',iconCls:'table_remove'},
		{xtype:'button',text:'删除',ref:'gridDelete',iconCls:'table_remove'},
		{xtype:'button',text:'保存',ref:'gridSave',iconCls:'table_save'}
	],
	columns:[],
	//可以使用键盘控制上下
	enableKeyNav:true,  
	//展示竖线
	columnLines:true, 
	//初始化操作
	initComponent:function(){
		this.editing = Ext.create('Ext.grid.plugin.CellEditing',{clicksToEdit:1});
		this.plugins = [this.editing];
		this.callParent(arguments);
	}
});