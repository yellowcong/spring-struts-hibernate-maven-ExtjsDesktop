/**
 * 定义一个AJax 的请求方式
 */
Ext.define("core.util.SuppleUtil",{
	/**
	 * 同步请求Ajax
	 * @param {} config
	 * @return {}
	 */
	ajax:function(config){
		var data={};
		var request={
			//请求方法
			method:"POST",
			//同步请求
			async:false,
			//成功
			success:function(response){
				//返回数据，如果没有就直接返回空
				data = Ext.decode(Ext.value(response.responseText,'{}'));
			},
			//请求错误
			failure : function(response){
		    	alert('数据请求出错了！！！！\n错误信息：\n'+response.responseText);
		    }
		};
		//通过apply来替换，替换这个默认 request中没有有的数据
		var request=Ext.apply(request,config);
		//发送请求
		Ext.Ajax.request(request);
		//返回结果
		return data;		
	}
});