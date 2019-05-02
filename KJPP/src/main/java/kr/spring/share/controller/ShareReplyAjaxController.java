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
	
	//댓글등록
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
			//로그인 안 됨
			map.put("result", "logout");
		}else {

			//댓글 등록
			shareService.insertReply(shareReplyCommand);
			map.put("result", "success");
			
		}
		
		return map;
	}
	
	//댓글 목록 
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
				
				// 총 글의 갯수
				int count = shareService.selectRowCountReply(map);
				
				PagingUtil page = new PagingUtil(currentPage, count, rowCount, pageCount, null);
				map.put("start",page.getStartCount());
				map.put("end", page.getEndCount());
				List<ShareReplyCommand> list = null;
				if(count >0) {
					list = shareService.selectListReply(map);
				}else {
					list = Collections.emptyList(); //비어있는걸 보냄
				}
						
				Map<String, Object> mapJson = new HashMap<String, Object>();
				mapJson.put("count", count);
				mapJson.put("rowCount", rowCount);
				mapJson.put("list", list);
				
				
		return mapJson;
	}
	
	//댓글삭제
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
			//로그인이 되어있지 않음
			map.put("result", "logout");
		}else if(user_email!=null && user_email.equals(email)){
			//로그인이 되어 있고 로그인한 아이디와 작성자 아이디 일치
			shareService.deleteReply(sr_num);
			map.put("result","success");
		}else {
			//로그인한 아이디와 작성자 아이디 불일치
			map.put("result", "wrongAccess");
		}
		
		return map;
	}
	
	//댓글 수정
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
			//로그인이 안되어 있는 경우
			map.put("result", "logout");
		}else if(user_email!=null && user_email.equals(shareReplyCommand.getEmail())) {
			//로그인 아이디와 작성자 아이디 일치
			
			//댓글 수정
			shareService.updateReply(shareReplyCommand);
			map.put("result", "success");
		}else {
			//로그인 아이디와 작성자 아이디 불일치
			map.put("result","wrongAccess");
		}
		return map;
	}
}
