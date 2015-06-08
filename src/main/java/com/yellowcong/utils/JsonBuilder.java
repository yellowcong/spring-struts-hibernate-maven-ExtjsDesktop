package com.yellowcong.utils;

import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;


import com.yellowcong.enums.ExtFieldType;
import com.yellowcong.enums.StringVeriable;
import com.yellowcong.model.vo.ExtFieldVo;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Json数据 工具类
 * @author zhangshuaipeng
 *
 */
public class JsonBuilder {
	/**
	 * 得到JsonBuilder实例化对象
	 * @return
	 */
	public static JsonBuilder getInstance(){
		return JsonHolder.JSON_BUILDER;
	}
	/**
	 * 静态内部类
	 * @author zhangshuaipeng
	 *
	 */
	private static class JsonHolder{
		private static final JsonBuilder JSON_BUILDER=new JsonBuilder();
		private static ObjectMapper mapper=new ObjectMapper();
	}
	/**
	 * 将一个数据实体解析成Json数据格式
	 * @param obj
	 * @return
	 */
	public String toJson(Object obj){
		try {
//			return JsonHolder.mapper.writeValueAsString(obj);
			return null;
		} catch (Exception e) {
			return "";
		}
	}
	/**
	 * 将一个Json字符串封装为指定类型对象
	 * @param json
	 * @param c
	 * @return
	 */
	public Object fromJson(String json, Class<?> c){
		json = cleanJson(json);
		try {
//			Object obj = JsonHolder.mapper.readValue(json, c);
//			return obj;
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 将一个JsonArray数据转换成一个List的键值对   [{name:'zsp',value:1},{name:'zsp',value:2}]
	 * @param json
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Map> fromJsonArray(String json){
		json = cleanJson(json);
		List<Map> dataList = (List<Map>) fromJson(json, ArrayList.class);
		
		return dataList;
	}
	/**
	 * 为操作成功返回Json
	 * @param strData
	 * @return
	 */
	public String returnSuccessJson(String strData){
		StringBuffer returnJson = new StringBuffer("{ success : true, obj : ");
		returnJson.append(strData);
		returnJson.append("}");
		return returnJson.toString();
	}
	/**
	 * 为操作失败返回Json
	 * @param strData
	 * @return
	 */
	public String returnFailureJson(String strData){
		StringBuffer returnJson = new StringBuffer("{ success : false, obj : ");
		returnJson.append(strData);
		returnJson.append("}");
		return returnJson.toString();
	}
	/**
	 * 为分页列表提供Json封装
	 * 
	 * @param count
	 *            记录总数
	 * @param entities
	 *            实体列表
	 * @param excludes
	 *            在json生成时需要排除的属性名称
	 * @return
	 */
	public String  buildObjListToJson(Long count, Collection<? extends Object> records, boolean listJson){
		try {
			StringBuffer pageJson = null;
			//判断是否展示list的数据
			if (listJson) {
				pageJson = new StringBuffer("{totalCount:" + count + ","
						+ "rows" + ":");
			} else {
				pageJson = new StringBuffer("");
			}
			//构建集合的json数据
			StringWriter w = new StringWriter();
//			JsonHolder.mapper.writeValue(w, records);
			pageJson.append(w);
			w.close();
			
			if (listJson) {
				pageJson.append("}");
			} else {
				pageJson.append("");
			}
			return pageJson.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	/**
	 * 格式化Json
	 * @param json
	 * @return
	 */
	public String cleanJson(String json){
		if(StringUtil.isNotEmpty(json)){
			return json.replaceAll("\n", "");
		}else{
			return "";
		}
	}
	/**
	 * 
	 * @param jsonSql [{sql:''},{}]     ["asd","asdas"]
	 * @return
	 */
	public String[] jsonSqlToString(String jsonSql){
		//得到对象数据
		Object[] os = JSONArray.fromObject(jsonSql).toArray();
		String[] sqls = new String[os.length];
		for (int i = 0; i < os.length; i++) {
			//使用JSONObject和sql键取出值
			JSONObject k = (JSONObject) os[i];
			String kk = (String) k.get("sql");
			sqls[i] = kk;
		}
		return sqls;
	}
	/**
	 * 构建对象json数据[{},{},{}]
	 * @param values
	 * @param excludes
	 * @return
	 */
	public String buildList(List<?> values,String excludes){
		StringBuffer returnJson = new StringBuffer("[");
		for(Object obj:values){
			//声明json配置对象
			JsonConfig cfg = new JsonConfig();
			String[] excluds=excludes.split(",");
			if(excluds.length>0){
				//增加排除属性数组
				cfg.setExcludes(excluds);
			}
			//设置循环策略为忽略，避免死循环
			cfg.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
			JSONObject jsonObject = JSONObject.fromObject(obj, cfg);
			returnJson.append(jsonObject.toString()+StringVeriable.STR_SPLIT);
		}
		if(values.size()>0){
			returnJson.deleteCharAt(returnJson.length()-1);
		}
		returnJson.append("]");
		return returnJson.toString();
	}
	/**
	 * 构建类的ExtJs的fields字段数据
	 * @param modelName
	 * @param fields
	 * @param excludes
	 * @return
	 */
	public String getModelFileds(String modelName,Field[] fields,String excludes){
		List<ExtFieldVo> lists=new ArrayList<ExtFieldVo>();
		for(Field f:fields){
			String[] excludeArray=excludes.split(StringVeriable.STR_SPLIT);
			Boolean flag=false;
			for(String exclude:excludeArray){
				if(f.equals(exclude)){
					flag=true;
					break;
				}
			}
			if(flag){
				continue;
			}
			String fieldType=f.getType().getSimpleName().toLowerCase();
			Boolean excludeFlag=false;
			if(fieldType.equals("double")){
				fieldType=ExtFieldType.FLOAT;
			}else if(fieldType.equals("long")){
				fieldType=ExtFieldType.INT;				
			}else if(fieldType.equals("bigdecimal")){
				fieldType=ExtFieldType.INT;				
			}else if(fieldType.equals("timestamp")){
				fieldType=ExtFieldType.STRING;				
			}else if(fieldType.equals("date")){
				fieldType=ExtFieldType.STRING;				
			}else if(fieldType.equals("integer")){
				fieldType=ExtFieldType.INT;				
			}else if(fieldType.equals("string")){
				fieldType=ExtFieldType.STRING;
			}else{
				excludeFlag=true;
			}
			ExtFieldVo vo=new ExtFieldVo(f.getName(), fieldType);
			if(!excludeFlag){
				lists.add(vo);
			}
		}
		JSONArray jsonArray=JSONArray.fromObject(lists);
		String strData=jsonArray.toString();
		ModelUtil.modelJson.put(modelName, strData);
		return strData;
	}

}
