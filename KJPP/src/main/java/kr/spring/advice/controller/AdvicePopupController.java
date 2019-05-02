package kr.spring.advice.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.spring.advice.domain.AdviceDetailCommand;
import kr.spring.advice.domain.AdviceReplyCommand;
import kr.spring.advice.service.AdviceService;

@Controller
public class AdvicePopupController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private AdviceService adviceService;
	
	//자바빈(커맨드 객체) 초기화
	@ModelAttribute("adviceDetailCommand")
	public AdviceDetailCommand initCommand() {
		return new AdviceDetailCommand();
	}
	
	//===================팝업 검색 폼
	@RequestMapping("/advice/popup/searchLoc.do")
	public String searchLoc() {
		return "adviceSearchView";
	}
	
	//===================팝업 최종 입력
	@RequestMapping(value="/advice/popup/searchAdd.do", method=RequestMethod.GET)
	public String searchAddForm() {
		
		return "adviceSearchAdd";
	}
	@RequestMapping(value="/advice/popup/searchAdd.do", method=RequestMethod.POST)
	public String searchAdd() {
		
		return "redirect:/advice/popup/searchLoc.do";
	}
	
	//===================조언 상세 팝업
	@RequestMapping("/advice/popup/adviceDetailPopup.do")
	public String adviceDetailPopup(@RequestParam("ar_num") int ar_num, 
									HttpSession session, 
									Model model) {
		AdviceReplyCommand command = adviceService.selectReplyDetail(ar_num);
		
		model.addAttribute("command", command);
		
		return "adviceDetailPopup";
	}
	@RequestMapping(value="/advice/popup/adviceModify.do", method=RequestMethod.GET)
	public String adviceModifyForm(@RequestParam("ar_num") int ar_num, HttpSession session, Model model) {
		
		AdviceReplyCommand command = adviceService.selectReplyDetail(ar_num);
		
		model.addAttribute("command", command);
		
		return "adviceModifyPopup";
	}
	@RequestMapping(value="/advice/popup/adviceModify.do", method=RequestMethod.POST)
	public String adviceModify(@ModelAttribute("command") AdviceReplyCommand adviceReplyCommand) {
		
		adviceService.updateReply(adviceReplyCommand);
		
		return "redirect:/advice/popup/adviceDetailPopup.do?ar_num="+adviceReplyCommand.getAr_num();
	}	
	//==================글 삭제 ====================//
	@RequestMapping("/advice/popup/adviceDelete.do")
	public String submit(@RequestParam("ar_num") int ar_num, Model model, RedirectAttributes redirect, @ModelAttribute("command") AdviceReplyCommand adviceReplyCommand) {
		if(log.isDebugEnabled()) {
			log.debug("<<ar_num>> : " + ar_num);
		}
		
		AdviceReplyCommand command = adviceService.selectReplyDetail(ar_num);
		
		model.addAttribute("command", command);		
		
		//adv_num,ar_num넘겨
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ar_num", ar_num);
		
		//글삭제
		adviceService.deleteReply(ar_num);
		//deleteSuccess의 값을 넣어주려고 명시해준다!?
		redirect.addFlashAttribute("result", "success");
		
		return "adviceDeletePopup";
	}
}
