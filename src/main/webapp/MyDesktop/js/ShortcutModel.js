/**
 * 桌面 菜单的对象
 */
Ext.define('Ext.ux.desktop.ShortcutModel', {
    extend: 'Ext.data.Model',
    fields: [
       //显示名称
    	{ name: 'name' },
    	//图标
       { name: 'iconCls' },
       //模块
       { name: 'module' },
       //窗口类型
       {name:"xtype"},
       //控制器
       {name:"controller"},
       //窗口的 样式
       {name:"viewIconCls"}
    ]
});
