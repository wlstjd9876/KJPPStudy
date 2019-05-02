package kr.spring.location.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.cxf.helpers.IOUtils;
import org.json.simple.JSONObject;

public class SearchAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/plain; charset=utf-8");
		String areaCode = request.getParameter("areaCode");
		if(areaCode.equals("0"))
			areaCode = "";
		String sigunguCode = request.getParameter("sigunguCode");
		if(sigunguCode.equals("0"))
			sigunguCode = "";
		String addr = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchKeyword?serviceKey=";
		String serviceKey = "PlO%2FeztroiuGsBUdEcH7R8xs9XHSTjrBBQJmeQCOKFMqRn1pvIsF8urJ8mw3OaDFgWZkzhZYLzXrjHD%2FFcekNA%3D%3D";
		String parameter = "";
		PrintWriter out = response.getWriter();
		parameter = parameter + "&" + "pageNo="+request.getParameter("pageNo")+"&numOfRows=12";
		parameter = parameter + "&" + "listYN=Y";
		parameter = parameter + "&" + "arrange=A";
		parameter = parameter + "&" + "MobileOS=ETC";
		parameter = parameter + "&" + "MobileApp=AppTest";
		parameter = parameter + "&" + "contentTypeId=";
		parameter = parameter + "&" + "areaCode="+areaCode;
		parameter = parameter + "&" + "sigunguCode="+sigunguCode;
		parameter = parameter + "&" + "cat1=";
		parameter = parameter + "&" + "cat2=";
		parameter = parameter + "&" + "cat3=";
		parameter = parameter + "&" + "keyword="+URLEncoder.encode(request.getParameter("keyword"),"UTF-8");
		parameter = parameter + "&" + "_type=json";
		
		
		addr = addr + serviceKey + parameter;
		URL url = new URL(addr);

		System.out.println(addr);

		InputStream in = url.openStream(); 
		ByteArrayOutputStream  bos1 = new ByteArrayOutputStream();
		IOUtils.copy(in, bos1);
		in.close();
		bos1.close();
	    
		String mbos = bos1.toString("UTF-8");
		System.out.println("mbos: "+mbos);
	    
		byte[] b = mbos.getBytes("UTF-8");
		String s = new String(b, "UTF-8");
		out.println(s);
		
		JSONObject json = new JSONObject();
		json.put("data", s);
		System.out.println("json: "+json);
		
	}
}
