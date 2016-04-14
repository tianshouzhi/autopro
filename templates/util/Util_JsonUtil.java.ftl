package ${package};

import java.lang.reflect.Field;
import java.util.*;
import java.util.Map.Entry;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.FieldNamingStrategy;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
/**
 *@Description Json操作工具类，提供了将对象和json字符串相互转换的方法，基于Google的gson编写
 *@Author ${author}
 *@Date   ${createDate?datetime}	
 */
public class JsonUtil {
	/*日期默认的序列化格式*/
	private static String default_dateFormat="yyyy-MM-dd";
	/*对于字段值为null的属性默认进行序列化*/
	private static boolean default_serializeNulls=true;
	/*默认以漂亮的格式进行打印*/
	private static boolean default_prettyPrint=true;
	/**
	 * 将对象转换为JSON字符串
	 * @param param
	 * @return
	 */
	public static String toJson(Object param){
        return toJson(param,null,default_serializeNulls,default_dateFormat,default_prettyPrint);
	}
	public static String toJson(Object param,boolean default_serializeNulls){
        return toJson(param,null,default_serializeNulls,default_dateFormat,default_prettyPrint);
	}
	
	/**
	 * 
	 * @param param 需要转换成json字符串的对象
	 * @param excludeProperties 不需要进行序列化的字段的名称
	 * @return
	 */
	public static String toJson(Object param,String[] excludeProperties){
        return toJson(param,excludeProperties,default_serializeNulls,default_dateFormat,default_prettyPrint);
	}
	/**
	 * 
	 * @param param 需要转换成json字符串的对象
	 * @param dateFormat 日期类型序列化的格式
	 * @return
	 */
	public static String toJson(Object param,String dateFormat){
		return toJson(param,null,default_serializeNulls,dateFormat,default_prettyPrint);
	}
	/**
	 * 
	 * @param param 需要转换成json字符串的对象
	 * @param excludeProperties 不需要进行序列化的字段的名称
	 * @param dateFormat 日期类型序列化的格式
	 * @return
	 */
	public static String toJson(Object param,String[] excludeProperties,String dateFormat){
		return toJson(param,excludeProperties,default_serializeNulls,dateFormat,default_prettyPrint);
	}
	/**
	 * 
	 * @param param 需要转换成json字符串的对象
	 * @param excludeProperties 不需要进行序列化的字段的名称
	 * @param serializeNulls 对于值为null的字段是否需要进行序列化
	 * @param dateFormat 日期类型序列化的格式
	 * @return
	 */
	public static String toJson(Object param,String[] excludeProperties,boolean serializeNulls,String dateFormat){
		return toJson(param,excludeProperties,serializeNulls,dateFormat,default_prettyPrint);
	}
	/**
	 * 
	 * @param param 需要转换成json字符串的对象
	 * @param excludeProperties 不需要进行序列化的字段的名称
	 * @param serializeNulls 对于值为null的字段是否需要进行序列化
	 * @param dateFormat 日期类型序列化的格式
	 * @param prettyPrint 是否以"漂亮的格式"进行打印，设置为false,打印结果会在一行
	 * @return
	 */
	public static String toJson(Object param,final String[] excludeProperties,boolean serializeNulls,String dateFormat,boolean prettyPrint){
		return toJson(param, excludeProperties, serializeNulls, dateFormat, prettyPrint, null);
	}
	
	public static String toJson(Object param, Map<String,String> replacedPropertyNameMapping){
		return toJson(param,null,default_serializeNulls,default_dateFormat,default_prettyPrint,replacedPropertyNameMapping);
	}
	
	public static String toJson(Object param, boolean serializeNulls,Map<String,String> replacedPropertyNameMapping){
		return toJson(param,null,serializeNulls,default_dateFormat,default_prettyPrint,replacedPropertyNameMapping);
	}
	
	public static String toJson(Object param,final String[] excludeProperties,boolean serializeNulls,String dateFormat,boolean prettyPrint,final Map<String,String> replacedPropertyNameMapping){
		 if(param==null){
			 return null;
		 }
		 GsonBuilder gsonBuilder=new GsonBuilder().setDateFormat(dateFormat);
		 if(replacedPropertyNameMapping!=null&&replacedPropertyNameMapping.size()>0){
			 gsonBuilder.setFieldNamingStrategy(new FieldNamingStrategy() {
					public String translateName(Field field) {
						if(replacedPropertyNameMapping.keySet().contains(field.getName())){
							return replacedPropertyNameMapping.get(field.getName());
						}
						return field.getName();
					}
				});
		 }
		 if(excludeProperties!=null&&excludeProperties.length>0){
			 gsonBuilder.setExclusionStrategies(new ExclusionStrategy() {
				
				public boolean shouldSkipField(FieldAttributes f) {
					return Arrays.asList(excludeProperties).contains(f.getName());
				}
				public boolean shouldSkipClass(Class<?> clazz) {
					return false;
				}
			}); 
		 }
		 if(serializeNulls){
			gsonBuilder.serializeNulls();
		 }
		 if(prettyPrint){
			gsonBuilder.setPrettyPrinting();
		 }
		return gsonBuilder.create().toJson(param);
	}
	
	/**
	 * 解析Json到指定的对象中
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T parseJsonToObject(String json,Class<T> clazz){
		Gson gson=new Gson();
		return gson.fromJson(json, clazz);
	}
	
	/**
	 * 解析Json字符串数组到指定的List集合中
	 * @param json 例如：[{id:1,email:'tianshouzhi@126.com'},{id:2,email:'tianshouzhi@1qq.com'}]
	 * @param clazz 集合中要存放的对象
	 * @return
	 */
	public static  <T>  List<T> parseJsonToList(String json,Class<T> clazz){
		List<T> list = null;
		Gson gson=new Gson();
		JsonParser jsonParser=new JsonParser();
		JsonElement jsonElementOuter = jsonParser.parse(json);
		
		//如果是标准JSON数组字符串
		if(jsonElementOuter.isJsonArray()){
			list=new ArrayList<T>();
			JsonArray jsonArray = jsonElementOuter.getAsJsonArray();
			for (JsonElement jsonElementInner : jsonArray) {
				if(jsonElementInner.isJsonObject()){
					T e = gson.fromJson(jsonElementInner, clazz);
					list.add(e);
				}
			}
		return list;
		}
		
		//如果是Json对象并且集合大小为1
		if(jsonElementOuter.isJsonObject()){
			JsonObject jsonObject = jsonElementOuter.getAsJsonObject();
			Set<Entry<String,JsonElement>> entrySet = jsonObject.entrySet();
			if(entrySet.size()==1){
				Entry<String, JsonElement> entry = entrySet.iterator().next();
				String propertyName = entry.getKey();
				list=parseJsonToList(json, clazz, propertyName);
			}
		}
		
		return list;
	}

	/**
	 * 解析Json字符串中指定属性对应的数组
	 * 到指定的List集合中
	 * @param jsonArrayStr 例如：{subs:[{id:1,email:'tianshouzhi@126.com'},{id:2,email:'tianshouzhi@1qq.com'}]}
	 * @param clazz 集合中要存放的对象
	 * @return
	 */
	public static  <T>  List<T> parseJsonToList(String json,Class<T> clazz,String propertyName){
		List<T> list = null;
		Gson gson=new Gson();
		JsonParser jsonParser=new JsonParser();
		JsonElement jsonElementOuter = jsonParser.parse(json);
		
//		//2、不是标准JSON数组字符串,
		//{subs:[{id:1,email:'tianshouzhi@126.com'},{id:2,email:'tianshouzhi@1qq.com'}]}
		//2.1 如果是Json对象中包含Json字符串
		if(jsonElementOuter.isJsonObject()){
			list=new ArrayList<T>();
			JsonObject jsonObject = jsonElementOuter.getAsJsonObject();
			Set<Entry<String,JsonElement>> entrySet = jsonObject.entrySet();
			for (Entry<String, JsonElement> entry : entrySet) {
				String key = entry.getKey();
				JsonElement jsonElement = entry.getValue();
				if(key.equalsIgnoreCase(propertyName)&&jsonElement.isJsonArray()){
					T t = gson.fromJson(jsonElement, clazz);
					list.add(t);
				}
			}
		}
		return list;
	}
	
	/**
	 * 获取默认情况下日期的序列化格式
	 * @return
	 */
	public static String getDefaultDateFormat(){
		return default_dateFormat;
	}
	/**
	 * 设置json序列化时，默认的日期格式
	 * @param dateFormat
	 */
	public static void setDefaultDateFormat(String dateFormat){
		default_dateFormat=dateFormat;
	}
	/**
	 * 获取默认情况下，对于值为null的字段是否进行序列化
	 * @return
	 */
	public static boolean getDefaultSerializeNulls(){
		return default_serializeNulls;
	}
	/**
	 * 设置默认情况下，对于值为null的字段，是否需要序列化
	 * @return
	 */
	public static void setDefaultSerializeNulls(boolean flag){
		default_serializeNulls=flag;
	}
	
	public static boolean getDefaultPrettyPrint(){
		return default_prettyPrint;
	}
	public static void setDefaultPrettyPrint(boolean flag){
		default_prettyPrint=flag;
	}
}
