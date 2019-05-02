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

public class DetailAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/plain; charset=utf-8");
		String addr = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?serviceKey=";
		String serviceKey = "PlO%2FeztroiuGsBUdEcH7R8xs9XHSTjrBBQJmeQCOKFMqRn1pvIsF8urJ8mw3OaDFgWZkzhZYLzXrjHD%2FFcekNA%3D%3D";
		String parameter = "";
		PrintWriter out = response.getWriter();
		parameter = parameter + "&" + "contentTypeId=";
		parameter = parameter + "&" + "contentId="+request.getParameter("contentId");
		parameter = parameter + "&" + "MobileOS=ETC";
		parameter = parameter + "&" + "MobileApp=TripsComeTrue";
		parameter = parameter + "&" + "defaultYN=Y";
		parameter = parameter + "&" + "firstImageYN=Y";
		parameter = parameter + "&" + "areaYN=Y";
		parameter = parameter + "&" + "catcodeYN=Y";
		parameter = parameter + "&" + "addrinfoYN=Y";
		parameter = parameter + "&" + "mapinfoYN=Y";
		parameter = parameter + "&" + "overviewYN=Y";
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
		System.out.println("mbos detail: "+mbos);
	    
		byte[] b = mbos.getBytes("UTF-8");
		String s = new String(b, "UTF-8");
		out.println(s);
		
		JSONObject json = new JSONObject();
		json.put("data", s);
		System.out.println("json detail: "+json);
		
	}
}
