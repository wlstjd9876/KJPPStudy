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
	
	//�ڹٺ�(Ŀ�ǵ� ��ü) �ʱ�ȭ
	@ModelAttribute("adviceDetailCommand")
	public AdviceDetailCommand initCommand() {
		return new AdviceDetailCommand();
	}
	
	//===================�˾� �˻� ��
	@RequestMapping("/advice/popup/searchLoc.do")
	public String searchLoc() {
		return "adviceSearchView";
	}
	
	//===================�˾� ���� �Է�
	@RequestMapping(value="/advice/popup/searchAdd.do", method=RequestMethod.GET)
	public String searchAddForm() {
		
		return "adviceSearchAdd";
	}
	@RequestMapping(value="/advice/popup/searchAdd.do", method=RequestMethod.POST)
	public String searchAdd() {
		
		return "redirect:/advice/popup/searchLoc.do";
	}
	
	//===================���� �� �˾�
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
	//==================�� ���� ====================//
	@RequestMapping("/advice/popup/adviceDelete.do")
	public String submit(@RequestParam("ar_num") int ar_num, Model model, RedirectAttributes redirect, @ModelAttribute("command") AdviceReplyCommand adviceReplyCommand) {
		if(log.isDebugEnabled()) {
			log.debug("<<ar_num>> : " + ar_num);
		}
		
		AdviceReplyCommand command = adviceService.selectReplyDetail(ar_num);
		
		model.addAttribute("command", command);		
		
		//adv_num,ar_num�Ѱ�
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ar_num", ar_num);
		
		//�ۻ���
		adviceService.deleteReply(ar_num);
		//deleteSuccess�� ���� �־��ַ��� ������ش�!?
		redirect.addFlashAttribute("result", "success");
		
		return "adviceDeletePopup";
	}
}
