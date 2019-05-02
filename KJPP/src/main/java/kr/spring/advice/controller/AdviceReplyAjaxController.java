package kr.spring.advice.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.spring.advice.domain.AdviceReplyCommand;
import kr.spring.advice.service.AdviceService;
import kr.spring.util.PagingUtil;

@Controller
public class AdviceReplyAjaxController {
	private Logger log = Logger.getLogger(this.getClass());
	private int rowCount = 5;
	private int pageCount = 10;
	
	@Resource
	private AdviceService adviceService;

	//자바빈(커맨드 객체) 초기화
	@ModelAttribute("adviceReplyCommand")
	public AdviceReplyCommand initCommand() {
		return new AdviceReplyCommand();
	}
	
	//댓글 등록
	//댓글 등록 폼
	@RequestMapping(value="/advice/adviceReplyWrite.do", method=RequestMethod.GET)
	public String form(@RequestParam("adv_num") int adv_num, HttpSession session, Model model) {
		String email = (String)session.getAttribute("user_email");
		
		AdviceReplyCommand command = new AdviceReplyCommand();
		command.setEmail(email); //자바빈에 넣어줌
		command.setAdv_num(adv_num);
		
		model.addAttribute("command",command);

		return "replyWrite"; //definition 설정 호출
	}

	//전송된 데이터 처리
	@RequestMapping(value="/advice/replyWrite.do", method=RequestMethod.POST)
	public String submit(@ModelAttribute("command") @Valid AdviceReplyCommand adviceReplyCommand, BindingResult result, RedirectAttributes redirect) { 
		//HttpServletRequest : ip주소 받기 위함,
		//RedirectAttributes : 객체는 리다이렉트 시점에 한 번만 사용되는 데이터를 전송
		//				     	   브라우저에 데이터를 전송하지만 URI상에는 보이지 않는 숨겨진
		//				     	  데이터의 형태로 전달

		if(log.isDebugEnabled()) {
			log.debug("<<adviceReplyCommand>> : " + adviceReplyCommand);
		}

		//데이터 유효성 체크
		if(result.hasErrors()) {
			return "replyWrite";
		}	

		//글쓰기
		adviceService.insertReply(adviceReplyCommand);

		//RedirectAttributes 객체는 리다이렉트 시점에 단 한 번만 사용되는 데이터를 전송
		//브라우저에 데이터를 전송하지만 URL상에는 보이지 않는 숨겨진 데이터의 형태로 전달 
		redirect.addFlashAttribute("result", "success"); //result를 통해서 success를 표시

		return "redirect:/advice/adviceDetail.do?adv_num="+adviceReplyCommand.getAdv_num();
	}

	//댓글 목록 
	@RequestMapping("/advice/listReply.do")
	@ResponseBody
	public Map<String,Object> getList(@RequestParam(value="pageNum",defaultValue="1") int currentPage, @RequestParam("adv_num") int adv_num){
		if(log.isDebugEnabled()) {
			log.debug("<<currentPage>> : " +currentPage);
			log.debug("<<adv_num>> : " +adv_num);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("adv_num", adv_num);
		
		// 총 글의 갯수
		int count = adviceService.selectRowCountReply(map);

		PagingUtil page = new PagingUtil(currentPage, count, rowCount, pageCount, null);
		map.put("start",page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<AdviceReplyCommand> list = null;
		if(count >0) {
			list = adviceService.selectListReply(map);
		}else {
			list = Collections.emptyList(); //비어있는걸 보냄
		}

		Map<String, Object> mapJson = new HashMap<String, Object>();
		mapJson.put("count", count);
		mapJson.put("rowCount", rowCount);
		mapJson.put("list", list);

		return mapJson;
	}

	//댓글 상세
	
	
	//댓글삭제
	@RequestMapping("/advice/deleteReply.do")
	@ResponseBody
	public Map<String, String> deleteReply(@RequestParam("ar_num") int ar_num, @RequestParam("email") String email,HttpSession session){
		if(log.isDebugEnabled()) {
			log.debug("<<ar_num>> : " +ar_num);
			log.debug("<<email>> : " +email);
		}
		Map<String, String> map = new HashMap<String, String>();

		String user_email = (String)session.getAttribute("user_email");
		if(user_email==null) {
			//로그인이 되어있지 않음
			map.put("result", "logout");
		}else if(user_email!=null && user_email.equals(email)){
			//로그인이 되어 있고 로그인한 아이디와 작성자 아이디 일치
			adviceService.deleteReply(ar_num);
			map.put("result","success");
		}else {
			//로그인한 아이디와 작성자 아이디 불일치
			map.put("result", "wrongAccess");
		}

		return map;
	}

	//댓글 수정
	@RequestMapping("/advice/updateReply.do")
	@ResponseBody
	public Map<String, String> modifyReply(AdviceReplyCommand adviceReplyCommand, HttpSession session, HttpServletRequest request){

		if(log.isDebugEnabled()) {
			log.debug("<<adviceReplyCommand>> : " +adviceReplyCommand);
		}

		Map<String, String> map = new HashMap<String, String>();
		String user_email = (String)session.getAttribute("user_email");
		if(user_email==null) {
			//로그인이 안되어 있는 경우
			map.put("result", "logout");
		}else if(user_email!=null && user_email.equals(adviceReplyCommand.getEmail())) {
			//로그인 아이디와 작성자 아이디 일치
			//댓글 수정
			adviceService.updateReply(adviceReplyCommand);
			map.put("result", "success");
		}else {
			//로그인 아이디와 작성자 아이디 불일치
			map.put("result","wrongAccess");
		}
		return map;
	}
	
	//댓글 상세 일정
	
}
