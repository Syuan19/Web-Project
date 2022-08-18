package af.servlet.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class FastJSON
{
	public static String niceFormat(Object j)
	{
		return JSON.toJSONString(j, SerializerFeature.PrettyFormat);
	}
	
	
	///////////////////////////
	
	public static int intValue(JSONObject j, String key, int defValue)
	{
		try {
			return j.getIntValue(key);
		}catch(Exception e) {
			return defValue;
		}
	}
	
	public static long longValue(JSONObject j, String key, long defValue)
	{
		try {
			return j.getLongValue(key);
		}catch(Exception e) {
			return defValue;
		}
	}
	
	public static boolean booleanValue(JSONObject j, String key, boolean defValue)
	{
		try {
			return j.getBooleanValue(key);
		}catch(Exception e) {
			return defValue;
		}
	}
	
	public static String stringValue(JSONObject j, String key, String defValue)
	{
		try {
			return j.getString(key);
		}catch(Exception e) {
			return defValue;
		}
	}
	
	public static double doubleValue(JSONObject j, String key, double defValue)
	{
		try {
			return j.getDoubleValue(key);
		}catch(Exception e) {
			return defValue;
		}
	}
	
	public static float floatValue(JSONObject j, String key, float defValue)
	{
		try {
			return j.getFloatValue(key);
		}catch(Exception e) {
			return defValue;
		}
	}	
}
