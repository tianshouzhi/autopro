package ${package};

/**
 *字符串处理工具类 .
 */
public class StringUtil{

	/**
	 *判断字符串是否为空，null或者" "都会返回true，不为空则返回false
	 */
	public static boolean isEmpty(String str){
		if(str!=null&&str.trim().length()>0){
			return false;
		}
		return true;
	}
}