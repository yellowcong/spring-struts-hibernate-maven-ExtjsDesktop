package com.yellowcong.utils;

import java.lang.reflect.Method;


/**
 * 反射工具类
 * @author 狂飙のyellowcong
 *
 * 2015年6月1日
 */
public class EntityUtil {
	/**
	 * 根据实体名称获取Class对象
	 * @param className
	 * @return
	 */
	public static Class<?> getClassByName(String className){
		Class<?> clazz=null;
		try {
			clazz=Class.forName(className);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clazz;
	}
	/**
	 * 执行指定类的方法
	 * @param owner
	 * @param methodName
	 * @param args
	 * @return
	 */
	public static Object invokeMethod(Object owner, String methodName,Object[] args){
		Class<?> ownerClass=owner.getClass();
		Class<?>[] argsClass=null;
		if(args!=null){
			argsClass=new Class<?>[args.length];
			for(int i=0;i<args.length;i++){
				if(args[i]!=null){
					if("java.lang.Bollean".equals(args[i].getClass())){
						argsClass[i]=boolean.class;
					}else{
						argsClass[i]=args[i].getClass();
					}
				}else{
					argsClass[i]=Object.class;
				}
			}
		}
		Method method=null;
		try {
			method=ownerClass.getMethod(methodName, argsClass);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("获取类函数失败");
		}
		Object obj=null;
		try {
			obj=method.invoke(owner, args);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("执行"+ownerClass.getName()+"类中的"+method.getName()+"函数失败");
		}
		return obj;
	}
	/**
	 * 
	 * 执行属性的set方法
	 * @param model
	 * @param keyName
	 * @param args
	 */
	public static void invokeSetMethod(Object model,String keyName,Object[] args){
		String setMethodName="set"+keyName.substring(0).toUpperCase()+keyName.substring(1);
		invokeMethod(model, setMethodName, args);
	}
	/**
	 * 执行属性的get方法
	 * @param model
	 * @param proName
	 * @return
	 */
	public static Object getPropertyValue(Object model, String proName){
		String getMethodName="get"+proName.substring(0).toUpperCase()+proName.substring(1);
		Object obj=invokeMethod(model, getMethodName, null);
		return obj;
	}

}
