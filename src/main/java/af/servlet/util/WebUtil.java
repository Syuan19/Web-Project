package af.servlet.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

public class WebUtil
{
	// 读取请求正文 
	// JavaIO技术较难，新人不宜深入研究。具体可以参考《网络通信篇》或《JavaIO公开课》
	public static String requestBody(HttpServletRequest req, String charset) throws IOException
	{
		ByteArrayOutputStream result = new ByteArrayOutputStream(1024*16);  
		
		InputStream inputStream = req.getInputStream();
        byte[] data = new byte[1024];  
        while (true)
        {
        	int n = inputStream.read(data); // n, 实际读取的字节数
        	if(n <= 0) break; // 读取完毕
        	
        	result.write(data, 0, n);  // 缓存起来      	
        	if(result.size() > 1024*1024) // 上限, 最多读取1M
        		break;
        }  
        
        return result.toString(charset);
	}
	
	// 
	public static int paramInt(HttpServletRequest req, String key, int defValue)
	{
		String str = req.getParameter(key);
		if(str == null || str.length()==0)
			return defValue;
		try {
			return Integer.valueOf(str);
		}catch(Exception e) {
			return defValue;
		}
	}
	
	public static long paramLong(HttpServletRequest req, String key, long defValue)
	{
		String str = req.getParameter(key);
		if(str == null || str.length()==0)
			return defValue;
		try {
			return Long.valueOf(str);
		}catch(Exception e) {
			return defValue;
		}
	}
	
	public static boolean paramBoolean(HttpServletRequest req, String key, boolean defValue)
	{
		String str = req.getParameter(key);
		if(str == null || str.length()==0)
			return defValue;
		try {
			return Boolean.valueOf(str);
		}catch(Exception e) {
			return defValue;
		}
	}
	
	public static String paramString(HttpServletRequest req, String key, String defValue)
	{
		String str = req.getParameter(key);
		if(str == null || str.length()==0)
			return defValue;
		
		return str;
	}
	
	// 生成一个唯一的ID
	public static String createUUID()
	{
		String s = UUID.randomUUID().toString();
		String s2 = s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23)
				+ s.substring(24);
		return s2.toUpperCase();
	}

	// 得到一个保证不重复的临时文件名
	public static String createTmpName(String suffix)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");
		String dateStr = sdf.format(new Date());
		String name = dateStr + "-" + createUUID() + "." + suffix;
		return name;
	}

	// 得到文件的后缀名
	public static String getSuffix(String fileName)
	{
		// 反斜杠 替换为 斜杠
		fileName = fileName.replace('\\' , '/');
		int p1 = fileName.lastIndexOf('/');
		if(p1 < 0) p1 = 0;
		
		int p2 = fileName.indexOf( '.' , p1);
		if(p2 > p1)
			return fileName.substring(p2 + 1).toLowerCase();
			
		return "";
	}
}
