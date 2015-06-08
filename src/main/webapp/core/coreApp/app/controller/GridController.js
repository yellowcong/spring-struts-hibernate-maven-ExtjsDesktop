/**
 * 表格控制器
 */
Ext.define("core.app.controller.GridController",{
	extend:"Ext.app.Controller",
	initGrid:function(){
		var self=this;
		var gridCtr={
			//basegrid添加事件
			"basegrid":{
				/**
				 * 表格的render事件
				 */
				render:function(grid){
					var basePanel=grid.up("basepanel");
					var funCode=basePanel.funCode;
					grid.funCode=funCode;
					grid.itemId=funCode+"_grid";
				},
				/**
				 * 表格的双击事件
				 */
				itemdblclick:function(grid,record,item,index,e,eOpts){
					var basePanel=grid.up("basepanel");
					var funCode=basePanel.funCode;
					var baseForm=basePanel.down("baseform[itemId="+funCode+"_form]");
					var baseGrid=basePanel.down("basegrid[itemId="+funCode+"_grid]");
					//表单赋值
					self.setFormValue(baseForm.getForm(),record.data);
					//隐藏grid
					baseGrid.hide();
					//显示
					baseForm.show();
				}
			}
			
		}
		Ext.apply(self.ctr,gridCtr);
	}
});