Ext.define("core.util.SqlUtil",{
	/**
	 * 构建执行的Sql字符串
	 * @param {} updateArray 更新的数组对象
	 * @param {} modelName  模型名称
	 * @param {} pkName  唯一名称
	 */
	getUpdateSql:function(updateArray,modelName,pkName){
		var datas=new Array();
		Ext.each(updateArray,function(obj){
			var pkValue="";
			var setArray=new Array();
			var fields=new Array();
			for(var f in obj){
				//如果没有数据，就设定 数据为 ''
				var value=Ext.value(obj[f],'');
				//更改我们的 字符
				value=value.replace("'","''");//因为sql语句执行的'有特殊意义，前面加一个单引号标识转义
				//当是主键的时候，就获取 主键值
				if(f==pkName){
					pkValue=obj[pkName];					
				}else{
					//当不是我们的主键，就添加之短
					fields.push(f);
					if(typeof(value)=="string"){
						setArray.push(f+"='"+value+"'");
					}else if(typeof(value)=="int"){
						setArray.push(f+"="+value);
					}else{
						setArray.push(f+"='"+value+"'");
					}
				}
			}
			datas.push("{pkValue:'"+pkValue+"',fields:'"+fields.join(",")+"',sql:\"update "+modelName+" set "+setArray+" where "+pkName+"='"+pkValue+"'\"}")
		});
		return "["+datas.join(",")+"]";
	}
});