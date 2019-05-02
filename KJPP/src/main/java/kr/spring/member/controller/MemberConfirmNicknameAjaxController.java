package kr.spring.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.member.domain.MemberCommand;
import kr.spring.member.service.MemberService;

@Controller
public class MemberConfirmNicknameAjaxController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private MemberService memberService;
	
	@RequestMapping("/member/confirmNickname.do")
	@ResponseBody
	public Map<String,String> process(@RequestParam("td_nickname") String td_nickname){
		if(log.isDebugEnabled()) {
			log.debug("<<td_nickname>> : " + td_nickname);
		}
		
		Map<String,String> map = new HashMap<String,String>();
		
		MemberCommand member = memberService.checkNickname(td_nickname);
		
		if(member!=null) {
			//�г��� �ߺ�
			map.put("result", "nicknameDuplicated");
		}else {
			//�г��� ���ߺ�
			map.put("result", "nicknameNotFound");
		}
		
		return map;
	}
	
	//==========ȸ����������============
	//���������� ���۵� ������ ó��
	@RequestMapping(value="/member/updateNick.do",method=RequestMethod.POST)
	public String submitUpdate(@ModelAttribute("command")@Valid MemberCommand memberCommand,BindingResult result) {

		if(log.isDebugEnabled()) {
			log.debug("<<memberCommand>> : " + memberCommand);
		}

		if(result.hasFieldErrors("td_nickname")) {
			return "memberModify";
		}

		//ȸ����������
		memberService.updateNick(memberCommand);

		return "redirect:/member/detail.do";
	}	
	
}
