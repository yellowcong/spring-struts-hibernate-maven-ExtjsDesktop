/**
 * 定义一个提示控件
 */
Ext.define("core.util.MessageUtil",{

	msgbox:function(config){
		var title="提示";
		var context="";
		if(typeof(config)=="string"){
			context=config;
		}
		//调用提示信息，这个导入example 的js css
		Ext.example.msg(title,context);
	}
})