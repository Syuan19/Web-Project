package my.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import af.servlet.RestServlet;
import af.servlet.util.FastJSON;
import my.DataSource;
import my.Student;

@WebServlet("/StuQuery")
public class StuQuery extends RestServlet
{

	@Override
	protected Object handle(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws Exception
	{
		// String filter = json.getString("filter");
		String filter = FastJSON.stringValue(json, "filter", "").trim();
		
		List<Student> sss = DataSource.query(filter);
		
		return sss;
		
	}

}
