/**
 * 定义控制器的方法
 */
 Ext.define("core.app.controller.ButtonController",{
 	//继承
 	extend:"Ext.app.Controller",
 	//定义初始化方法
 	initBtn:function(){
 		//定义获取到自己
 		var self = this;
 		//定义对象
		var btnCtr = {//删除事件
 		"basegrid button[ref=gridDelete]":{
			click:function(btn){
				
				//我们可以直接通过Id 来获取我们的panel 对象
//				var mypanel  = btn.up("basepanel[itemId=testmodule_main_panel]");
				//获取panel和 grid
				var grid = btn.up("basegrid");
//				console.log(grid);
				//获取到funCode 获取这个栏目的 panel
				var funcode = grid.funCode;
				var panel = grid.up("basepanel[itemId="+funcode+"_panel]");
//				console.log(panel);
				
				//获取到选择模式
				//获取选中的数据
				var recoreds = grid.getSelectionModel().getSelection();
				//获取存储的数据
				var stors = grid.getStore();
				//获取panel中的数据
				var fundata = panel.funData;
				//获取指定的外键  id 啥的
				var pkName=fundata.pkName;
				console.log(pkName);
				
				if(recoreds.length >0){
					var ids = [];
					//遍历数据
					Ext.each(recoreds,function(rec){
//						console.log(rec.get(pkName));
						//添加需要删除的对象， pkName指的是 Model 中唯一的索引 是设定的
						ids.push(rec.get(pkName));
					});
//					console.log(ids.join(","));
					
					//发送ajax
//					var resObj = self.ajax({url:"xx",params:{id:ids.join(","),pkName:pkName}});
					//反悔的数据
					var resObj = {success:true,date:{}};
					//如果成功
					if(resObj.success){
							Ext.each(recoreds,function(date){
					      		//删除数据
					      		stors.remove(date);
					      	});

					}else{
						Ext.MessageBox.alert("系统提示","删除失败");
					}
				}else{
					Ext.MessageBox.alert("系统提示","请选中数据！！！");
				}				
			}
		},
		//表格插入事件
		"basegrid button[ref=gridInsertF]":{
			click:function(btn){
				//通过id获取我们的对象
				var baseGrid = btn.up("basegrid");
				//获取到grid对象
				console.log(baseGrid);
//				console.log(baseGrid);
				
				//通过获取上一层
//				var basepanel = btn.ownerCt.ownerCt;
//				console.log(basepanel);


				//获取属性   这个是我们自己定义的一个属性
				var funCode = baseGrid.funCode;
				//获取到
				//testmodule_main
//				console.log(funCode);
				
				//获取到这个窗口的顶层中的 属性对象
				//testmodule_main_panel
				var mypanel  = baseGrid.up("basepanel[itemId="+funCode+"_panel]");
				
				//获取form表单,向下查找
				//根据自定义的item的来找到我们下面的表格控件
				var myform  = mypanel.down("baseform[itemId="+funCode+"_form]");
				//获取表格对象,就可以简单来说就
				var fromObj = myform.getForm();
//				console.log(myform);
				
				
				//获取Pannel 中定义的一些方法
				var data = mypanel.funData;
				
				//获取默认数据
				var defaultObj = data.defaultObj;
				
				//默认设定数据
				//Object { name: "狂飙のyellowcong", birthday: "@createTime@" }
//				console.log(defaultObj);
				
				/*//迭代里面的字段
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
						//@createTime@ //创建事件  这个直接获取
						// @createDept@  创建部门 可以更具用户获取部门
						//  @createUser@ //创建用户， 可以根据Session 获取对象
						//添加数据， 这些数据需要我们动态加入的
						if(val== "createUser"){
							val="yellowcong"
						}else if(val == "createTime"){
							val = new Date();
						}
					}
					
					//查找表单的字段,如果存在就设定数据
					var field = fromObj.findField(key);
					if(field){
						field.setValue(val);
					}
				}*/
				
				//通过抽取方法，然后调用我们的utils
				var insertObj = self.getDefaultVal(defaultObj);
				//迭代数据
				for(var key in  insertObj){
					//获取数据
					var val = insertObj[key];
					//设定默认数据
					//查找表单的字段,如果存在就设定数据
					var field = fromObj.findField(key);
					if(field){
						field.setValue(val);
					}
				}
				//隐藏 数据 列表
				baseGrid.hide();
				//显示表单， 我们讲表单默认隐藏的
				myform.show();
			}
		},
		//保存事件
		"basegrid button[ref=gridSave]":{
				click:function(btn){
	//				alert("保存事件");
					
					var grid =btn.up("basegrid");
					var funCode = grid.funCode;
					var panel  = grid.up("basepanel[itemId="+funCode+"_panel]");
					var funData  = panel.funData;
					//获取里面的pkName
					var pkname = panel.funData.pkName;
//					console.log(pkname);
					//获取stroe
					var store = grid.getStore();
					//获取更改的数据
					var records = store.getUpdatedRecords();
					
					//封装修改的数据对象
					
					if(records.length>0){
						var updates = [];
						//遍历数据 
						Ext.each(records,function(rec){
//							console.log(rec);
							//获取更改的字段
							var obj = rec.getChanges();
							//主键 赋值到新的对象中
							obj[pkname] = rec.get(pkname);
							//添加到数组中
							updates.push(obj);
						});
						
						//打印更改的数据
						//Array [ Object, Object ]
//						console.log(updates);
						
						//获取到更新的SQL语句，如果是多条更新，就会传递一个数组对象
						var updateSql=self.getUpdateSql(updates,funData.modelName,funData.pkName);
						console.log(updateSql);
						
						//发送ajax到服务器中
//						var resObj=self.ajax({action:funData.action+"!doUpdateList.action",params:{strData:strData}});
						var resObj = {success:true,data:{}};
						//返回结果
						if(resObj.success){
							self.msgbox("保存成功");
							//去掉我们的小红点
							Ext.each(records,function(rec){
								//添加到数组中,去掉小红点
								rec.commit();
							});
						}else{
							alert(resObj.obj);
						}
					}else{
						self.msgbox("保存成功");
					}
				}
		},
		
		//编辑事件
		"basegrid button[ref=gridEdit]":{
				click:function(btn){
					//获取组建
					var grid = btn.up("basegrid");
//					console.log(grid);
					//获取grid中的funCode属性
					var funCode = grid.funCode;
					//获取items
					//获取panel
					var panel = grid.up("basepanel[itemId="+funCode+"_panel]");
					//获取表格
					var form = panel.down("baseform[itemId="+funCode+"_form]");
					//获取编辑的对象
					var records = grid.getSelectionModel().getSelection();
					//当选中一跳数据的情况，多条数据不能编辑
					if(records.length == 1){
						var data = records[0].data;
						//获取到选中的数据
						console.log(data);
						//调用工具类设定表单中的数据
						self.setFormValue(form.getForm(),data);
						//编辑的时候，日期没了
						
						//表单显示
						form.show();
						//列表隐藏
						grid.hide();
					}else{
						Ext.MessageBox.alert("系统提示","请选择数据");
					}
				}
		},
		//插入表格对象
		"basegrid button[ref=gridInsert]":{
			
			click:function(btn){
				//获取主控制面板
				var grid = btn.up("basegrid");
//				console.log(grid);
				var funcode = grid.funCode;
				//获取到控制面板 panel
				var panel = btn.up("basepanel[itemId="+funcode+"_panel]");
				var funData = panel.funData;
				//获取默认的对象
				var defaultObj = funData.defaultObj;
				//通过我们的utils来获取数据
				//获取插入的对象          
				var insertObj=self.getDefaultVal(defaultObj);
				//Object { name: "yellowcong", birthday: Date 2015-06-03T00:39:05.215Z }
//				console.log(insertObj);
				
				//通过ajax的方法提交到后台中
				//var resObj=self.ajax({action:funData.action+"!doSave.action",params:insertObj});
				
				//自定义返回数据
//				var resObj = {success:true,obj:{ name: "yellowcong", birthday: Ext.Date.format(new　Date(),"Y年m月d日")}};
				//注意日期类型
				var resObj = {success:true,obj:{ name: "yellowcong", birthday: "2013-01-04"}};
				
				//获取数据
				var store = grid.getStore();
				//获取到模型对象
				var Model = store.model;
				
				//通过grid对象得到store的编辑器
				var edit = grid.editing;
				
				//判断数据是否添加成功
				if(resObj.success){
					//获取到我们定义的obj
					var obj = new Model(resObj.obj);
					//在第一条插入数据
					store.insert(0,obj);
					//提交
					obj.commit();
					
					//编辑
					//取消别的编辑器的状态
					edit.cancelEdit();
					//设定默认的编辑框
					edit.startEditByPosition({
						//第一行
						row:0,
						//第二列
						column:2
					});
					
					//通过util中的提示菜单
					self.msgbox("保存成功");
				}
				
			}
		},
		//表单返回事件
		"baseform button[ref=formReturn]":{
				click:function(btn){
				//获取表单
				var myform = btn.up("baseform");
//					console.log(myform.funCode);
				//获取panel和 grid对象
				var funCode = myform.funCode;
				//找到另外两个组建
				var panel = btn.up("basepanel[itemId="+funCode+"_panel]");
				//通过panel 找到grid
				var grid = panel.down("basegrid[itemId="+funCode+"_grid]");
				//表格隐藏
				myform.hide();
				//grid显示
				grid.show();
					
				}			
			},
			"baseform button[ref=formSave]":{
					click:function(btn){
						//获取from、panel、 grid三个对象
						var form = btn.up("baseform");
						var funCode = form.funCode;
						var panel = form.up("basepanel[itemId="+funCode+"_panel]");
						var grid = panel.down("basegrid[itemId="+funCode+"_grid]");
						//获取到里面的数据
						var funData = panel.funData;
						//主键对象
						var pkName=funData.pkName;
						
						//拿到formObj对象
						var formObj=form.getForm();
						//查询我们的 pkField字段
						var pkField=formObj.findField(pkName);
						
						//判断主键是否为空 还是 保存
						var act=Ext.isEmpty(pkField.getValue())?"doSave":"doUpdate";
//						console.log(act);
						//提交数据
						formObj.submit({
							//提交地址
							url:"/form.jsp",
							//提交参数
							params:{},
							//可以提交空的字段值
							submitEmptyText:true,
							success:function(form,action){
									//获取返回结果的obj, 我们把 obj的所有数据都删了，返回的是一个空集合{}
									var obj=action.result.obj;
									if(action.result.success){
										//对象成功后处理
										//刷新表单
										self.setFormValue(formObj,obj);
										//load表格, 重新加载 表格数据
										grid.getStore().load();
										if(act=="doSave"){
											self.msgbox("数据添加成功");
										}else{
											self.msgbox("数据保存成功");
										}
									}else{
										alert(obj);
									}
								},
								//错误的处理方法
								failure:function(form, action){
									Ext.MessageBox.alert("系统提示","添加数据失败");
								}
						});
						self.msgbox("保存成功");
					}
			}
		
		}
		//继承关系
		Ext.apply(self.ctr,btnCtr);
 	}
 })
