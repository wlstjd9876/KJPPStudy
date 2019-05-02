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
		log.debug("=============WriterCheckInterceptor ����===================");
		
	}	
		
	int num = Integer.parseInt(request.getParameter("num"));
	ShareCommand share = shareService.selectShare(num);
	

	//�α��� ���̵� ���ϱ�
	HttpSession session = request.getSession();
	String user_email = (String)session.getAttribute("user_email");
	
	if(log.isDebugEnabled()) {
		log.debug("<<user_email>> : " + user_email);
		log.debug("<<�ۼ��� email>> : " + share.getEmail());
	}
	
	//�α��� ���̵�� �ۼ��� ���̵� ��ġ ���� üũ
	if(user_email == null || !user_email.equals(share.getEmail())) {
		if(log.isDebugEnabled()) {
			log.debug("<<�α��� ���̵�� �ۼ��� ���̵� ����ġ>>");
		}
		
		request.setAttribute("accessMsg", "�α��� ���̵�� �ۼ��� ���̵� ����ġ");
		request.setAttribute("accessUrl", request.getContextPath()+"/share/list.do");
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("");
		dispatcher.forward(request, response);
		
		return false;
	}
	
	if(log.isDebugEnabled()) {
		log.debug("<<�α��� ���̵�� �ۼ��� ���̵� ��ġ>>");
	}
		
		return true;
	}

}
