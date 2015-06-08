Ext.define("core.util.FormUtil",{
	/**
	 * 设置表单的值,给所有的字段设定数据，如果没有就设定为 null
	 * @param {} formObj
	 * @param {} obj
	 */
	setFormValue:function(formObj,obj){
		//获取我们表单中的所有字段集合
		var fields=formObj.getFields().items;
		//获取字段里面的数据，迭代集合
		Ext.each(fields,function(field){
			//获取值
			var value=Ext.value(obj[field.name],null);
			//设定我们的表单中的数据
			formObj.findField(field.name).setValue(value);
		});
	}
})