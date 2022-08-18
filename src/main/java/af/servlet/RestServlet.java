package af.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

public abstract class RestServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		// 1 提取正文
		JSONObject json = null;
		try{
			String requestBody = requestBody(req, "UTF-8");
			json = JSON.parseObject(requestBody);
		} catch (Exception e)
		{
			json = new JSONObject();
		}

		// 2 处理
		JSONObject result = new JSONObject(true);
		result.put("error", 0);
		result.put("reason", "OK");

		try
		{
			// 处理请求
			Object data = this.handle(req, resp, json);
			if (data != null)
			{
				if (data instanceof JSON) // 本身就是 JSONObject 或 JSONArray
					result.put("data", data);
				else
					result.put("data", JSON.toJSON(data));
			}

		} catch (Exception e)
		{
			String reason = e.getMessage();
			if (reason == null)
				reason = e.getClass().getName();
			result.put("error", -1);
			result.put("reason", e.getMessage());
		}
		
		
		// 3 返回应答数据
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		resp.getWriter().write( JSON.toJSONString(result, SerializerFeature.PrettyFormat) );
	}

	// 具体的业务处理
	protected abstract Object handle(HttpServletRequest request
			, HttpServletResponse response
			, JSONObject json)throws Exception;
	
	
	// 读取请求正文 
	// JavaIO
	private String requestBody(HttpServletRequest req, String charset) throws IOException
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
}
