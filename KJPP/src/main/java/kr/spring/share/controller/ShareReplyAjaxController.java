package kr.spring.share.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.share.domain.ShareReplyCommand;
import kr.spring.share.service.ShareService;
import kr.spring.util.PagingUtil;

@Controller
public class ShareReplyAjaxController {
	private Logger log = Logger.getLogger(this.getClass());
	private int rowCount = 10;
	private int pageCount = 10;
	
	@Resource
	private ShareService shareService;
	
	//��۵��
	@RequestMapping("/share/writeReply.do")
	@ResponseBody
	public Map<String,String> writeReply(ShareReplyCommand shareReplyCommand,
										HttpSession session, HttpServletRequest request){
		if(log.isDebugEnabled()) {
			log.debug("<<shareReplyCommand>> : " + shareReplyCommand);
		}
		
		Map<String,String> map = new HashMap<String, String>();
		String user_email = (String)session.getAttribute("user_email");
		if(user_email ==null) {
			//�α��� �� ��
			map.put("result", "logout");
		}else {

			//��� ���
			shareService.insertReply(shareReplyCommand);
			map.put("result", "success");
			
		}
		
		return map;
	}
	
	//��� ��� 
	@RequestMapping("/share/listReply.do")
	@ResponseBody
	public Map<String,Object> getList(
			@RequestParam(value="pageNum",defaultValue="1")
			int currentPage,
			@RequestParam("num") int num){
				if(log.isDebugEnabled()) {
					log.debug("<<currentPage>> : " +currentPage);
					log.debug("<<num>> : " +num);
				}
			
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("num", num);
				
				// �� ���� ����
				int count = shareService.selectRowCountReply(map);
				
				PagingUtil page = new PagingUtil(currentPage, count, rowCount, pageCount, null);
				map.put("start",page.getStartCount());
				map.put("end", page.getEndCount());
				List<ShareReplyCommand> list = null;
				if(count >0) {
					list = shareService.selectListReply(map);
				}else {
					list = Collections.emptyList(); //����ִ°� ����
				}
						
				Map<String, Object> mapJson = new HashMap<String, Object>();
				mapJson.put("count", count);
				mapJson.put("rowCount", rowCount);
				mapJson.put("list", list);
				
				
		return mapJson;
	}
	
	//��ۻ���
	@RequestMapping("/share/deleteReply.do")
	@ResponseBody
	public Map<String, String> deleteReply(
		@RequestParam("sr_num") int sr_num, @RequestParam("email") String email,HttpSession session){
		if(log.isDebugEnabled()) {
			log.debug("<<sr_num>> : " +sr_num);
			log.debug("<<email>> : " +email);
		}
		Map<String, String> map = new HashMap<String, String>();
		
		String user_email = (String)session.getAttribute("user_email");
		if(user_email==null) {
			//�α����� �Ǿ����� ����
			map.put("result", "logout");
		}else if(user_email!=null && user_email.equals(email)){
			//�α����� �Ǿ� �ְ� �α����� ���̵�� �ۼ��� ���̵� ��ġ
			shareService.deleteReply(sr_num);
			map.put("result","success");
		}else {
			//�α����� ���̵�� �ۼ��� ���̵� ����ġ
			map.put("result", "wrongAccess");
		}
		
		return map;
	}
	
	//��� ����
	@RequestMapping("/share/updateReply.do")
	@ResponseBody
	public Map<String, String> modifyReply(
			ShareReplyCommand shareReplyCommand,
			HttpSession session,
			HttpServletRequest request){
		
		if(log.isDebugEnabled()) {
			log.debug("<<shareReplyCommand>> : " +shareReplyCommand);
		}
		
		Map<String, String> map = new HashMap<String, String>();
		String user_email = (String)session.getAttribute("user_email");
		if(user_email==null) {
			//�α����� �ȵǾ� �ִ� ���
			map.put("result", "logout");
		}else if(user_email!=null && user_email.equals(shareReplyCommand.getEmail())) {
			//�α��� ���̵�� �ۼ��� ���̵� ��ġ
			
			//��� ����
			shareService.updateReply(shareReplyCommand);
			map.put("result", "success");
		}else {
			//�α��� ���̵�� �ۼ��� ���̵� ����ġ
			map.put("result","wrongAccess");
		}
		return map;
	}
}
