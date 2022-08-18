package my.service;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import af.servlet.RestServlet;
import my.DataSource;
import my.Student;

@WebServlet("/StuAdd")
public class StuAdd extends RestServlet
{

	@Override
	protected Object handle(HttpServletRequest request, HttpServletResponse response, JSONObject json) throws Exception
	{
		// 请求数据：直接转成一个POJO对象
		Student stu = JSON.toJavaObject(json, Student.class);
		
		DataSource.add(stu);
		
		return "";
	}

}
