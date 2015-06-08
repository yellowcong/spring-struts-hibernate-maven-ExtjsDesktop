package com.yellowcong.enums;
/**
 * 树形字段类型枚举
 * @author 狂飙のyellowcong
 *
 * 2015年6月1日
 */
public enum TreeNodeType {
	   ID,
       TEXT, 
       CODE, 
       CLS,
       HREF,
       HREFTARGET,  
       EXPANDABLE,
       EXPANDED,
       DESCRIPTION, 
       ICON,
       CHECKED,   
       PARENT,
       NODETYPE,
       NODEINFO,    
       NODEINFOTYPE, 
       ORDERINDEX,
       DISABLED;
	   public Boolean equalsType(TreeNodeType other){
		   int i=this.compareTo(other);
		   if(i!=0){
			   return false;
		   }
		   return true;
	   }
}
