//这个对象是在 core项目下的 util中，不是在 app中
Ext.define("core.util.GridActionUtils",{

	
	//通过传入的默认对象，来获取数据
	getDefaultVal:function(defaultObj){
		//返回的结果对象
		var reuslt = {};
		//迭代里面的字段
		for(var key in  defaultObj){
			//获取到 val
			var val = defaultObj[key];
			//当是 @ 的时候是true
//					console.log(!val.indexOf("@"));
			//当数据有哦默认设定的  @@ 这种方法来设定默认数据
			if(!val.indexOf("@")){
				//这个 replace方法只能去掉前面的一个
//						val = val.replace("@","");
				//去掉前后的@
				val = val.substring(1,val.length-1);
//						console.log(val);
				//默认属性有
				/**
				 * @createTime@ //创建事件  这个直接获取
				 * @createDept@  创建部门 可以更具用户获取部门
				 *  @createUser@ //创建用户， 可以根据Session 获取对象
				 */
				//添加数据， 这些数据需要我们动态加入的
				if(val== "createUser"){
					val="yellowcong"
				}else if(val == "createTime"){
					val = new Date();
				}
			}
			//对于不是默认值的就不修改了，直接赋值
			reuslt[key] = val;
		}
		return reuslt;
		
	}	
	
})