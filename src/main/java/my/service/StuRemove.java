package my.service;

import java.util.Iterator;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import af.servlet.RestServlet;
import my.DataSource;
import my.Student;

@WebServlet("/StuRemove")
public class StuRemove extends RestServlet
{

	@Override
	protected Object handle(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws Exception
	{
		JSONArray idlist = json.getJSONArray("idlist");
		
		// 删除相关记录
		int[] iddd = JSON.toJavaObject(idlist, int[].class);		
		for(int id : iddd)
		{
			DataSource.remove(id);
		}
		
		return null;
	}
}
