/*!
 * Ext JS Library 4.0
 * Copyright(c) 2006-2011 Sencha Inc.
 * licensing@sencha.com
 * http://www.sencha.com/license
 */

Ext.define('MyDesktop.App', {
    extend: 'Ext.ux.desktop.App',

    requires: [
        //消息弹出框
        'Ext.window.MessageBox',
        //手工输入 module,这个是左面图标对象
        'Ext.ux.desktop.Module',
        //图标MOdel
        'Ext.ux.desktop.ShortcutModel',
        'MyDesktop.Settings'
    ],

    init: function() {
        // custom logic before getXYZ methods get called...

        this.callParent();

        // now ready...
    },

    getModules : function(){
    	//没有模块 
        return [
        ];
    },

    getDesktopConfig: function () {
        var me = this, ret = me.callParent();

        return Ext.apply(ret, {
            //cls: 'ux-desktop-black',

        	//左面的Menu
            contextMenuItems: [
                { text: '修改背景', handler: me.onSettings, scope: me }
            ],
			
            //左面图标按钮
            shortcuts: Ext.create('Ext.data.Store', {
                model: 'Ext.ux.desktop.ShortcutModel',
                data: [
                	//定义图标
                	/**
                	 * name 显示名称
                	 * iconCls  桌面显示的图标
                	 * viewIconCls  任务栏和界面显示的图标
                	 * xtype 类型
                	 * controller 控制对象
                	 * module 模型
                	 */
                   { name: '用户管理', iconCls: 'customers',viewIconCls:"icon_customers",xtype:"testpanel",controller:"core.test.controller.TestController",module:"testmainview"}
                ]
            }),
			//设定的背景,我设定了我的桌面背景，我比较喜欢这个背景 
            //java+php+python+nodejs+ruby
            wallpaper: '/MyDesktop/wallpapers/beauty.jpg',
            wallpaperStretch: false
            
        });
    },

    // 设定开始菜单
    getStartConfig : function() {
        var me = this, ret = me.callParent();

        return Ext.apply(ret, {
            title: '桌面系统',
            iconCls: 'settings',
            height: 300,
            toolConfig: {
                width: 100,
                items: [
                    {
                        text:'系统设定',
                        iconCls:'settings',
                        handler: me.onSettings,
                        scope: me
                    },
                    '-',
                    {
                        text:'退出系统',
                        iconCls:'logout',
                        handler: me.onLogout,
                        scope: me
                    }
                ]
            }
        });
    },

    getTaskbarConfig: function () {
        var ret = this.callParent();

        return Ext.apply(ret, {
            quickStart: [
                { name: '折叠窗口', iconCls: 'accordion', module: 'acc-win' },
                { name: '平铺窗口', iconCls: 'icon-grid', module: 'grid-win' }
            ],
            trayItems: [
                { xtype: 'trayclock', flex: 1 }
            ]
        });
    },

    onLogout: function () {
        Ext.Msg.confirm('系统提示', '你真的要退出?');
    },

    onSettings: function () {
        var dlg = new MyDesktop.Settings({
            desktop: this.desktop
        });
        dlg.show();
    }
});
