package com.yulore.nsm;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class NSMDemo
 */
@WebServlet("/NSMDemo")
public class NSMDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public NSMDemo() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("YULORE ").append(request.getContextPath());
		
//		response.setCharacterEncoding("UTF-8");
//		response.setHeader("content-type", "text/html;charset=UTF-8");
//		
//		PrintWriter out = response.getWriter();  
//		
////		String queryString = request.getQueryString();
////        out.println(queryString);  
//		
//		String feedback = request.getParameter("result");
//		out.println(feedback);
//		if (feedback.isEmpty()) {
//			out.println("OK");
//		}else{
//			out.println("wrong");
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		
		PrintWriter out = response.getWriter();
		//get client info
		String requestUrl = request.getRequestURL().toString();
		System.out.println("request URL is: " + requestUrl);
		
		Enumeration<String> reqHeadInfos = request.getHeaderNames();//获取所有的请求头
		System.out.println("获取到的客户端所有的请求头信息如下：");
		while (reqHeadInfos.hasMoreElements()) {
			String headName = (String) reqHeadInfos.nextElement();
			String headValue = request.getHeader(headName);
			System.out.println(headName+":"+headValue);
		 }
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		Enumeration paramNames = request.getParameterNames();

		  String parName="NULL";

		  boolean hasEnum = false;
		  if (paramNames.hasMoreElements())
		      hasEnum = true;

//		  PrintWriter out = response.getWriter();

		  if (hasEnum){
			  while(paramNames.hasMoreElements()){
			      parName = (String) paramNames.nextElement();
			      System.out.println("request params is: " + parName);
			      JSONObject jsStr = JSONObject.fromObject(parName); 
			      try {
			    	  int statusCode = Integer.parseInt(jsStr.getString("status"));
			    	  if(statusCode == 100) {
				    	  out.println("OK");
				      }else if (statusCode == 99) {
				    	  out.println("Failed");
				      }else {
				    	  out.println("Wrong status code");
				      }
			      }catch(Exception e) {
			    	  out.println("Miss param: \"status\"");
			      }
			  }
			  
		  } else {
			  out.println("No parameter.");
		  }
		  
	}

}
