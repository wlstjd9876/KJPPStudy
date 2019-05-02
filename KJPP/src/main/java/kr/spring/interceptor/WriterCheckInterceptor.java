package kr.spring.interceptor;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.spring.share.domain.ShareCommand;
import kr.spring.share.service.ShareService;

public class WriterCheckInterceptor  extends HandlerInterceptorAdapter{
	private Logger log = Logger.getLogger(getClass());
	
	@Resource
	private ShareService shareService;
	
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler)throws Exception{
		
	if(log.isDebugEnabled()) {
		log.debug("=============WriterCheckInterceptor 진입===================");
		
	}	
		
	int num = Integer.parseInt(request.getParameter("num"));
	ShareCommand share = shareService.selectShare(num);
	

	//로그인 아이디 구하기
	HttpSession session = request.getSession();
	String user_email = (String)session.getAttribute("user_email");
	
	if(log.isDebugEnabled()) {
		log.debug("<<user_email>> : " + user_email);
		log.debug("<<작성자 email>> : " + share.getEmail());
	}
	
	//로그인 아이디와 작성자 아이디 일치 여부 체크
	if(user_email == null || !user_email.equals(share.getEmail())) {
		if(log.isDebugEnabled()) {
			log.debug("<<로그인 아이디와 작성자 아이디 불일치>>");
		}
		
		request.setAttribute("accessMsg", "로그인 아이디와 작성자 아이디 불일치");
		request.setAttribute("accessUrl", request.getContextPath()+"/share/list.do");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("");
		dispatcher.forward(request, response);
		
		return false;
	}
	
	if(log.isDebugEnabled()) {
		log.debug("<<로그인 아이디와 작성자 아이디 일치>>");
	}
		
		return true;
	}

}
