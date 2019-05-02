package kr.spring.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.member.domain.MemberCommand;
import kr.spring.member.service.MemberService;

@Controller
public class MemberConfirmIdAjaxController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private MemberService memberService;
	
	@RequestMapping("/member/confirmId.do")
	@ResponseBody
	public Map<String,String> process(@RequestParam("email") String email){
		if(log.isDebugEnabled()) {
			log.debug("<<email>> : " + email);
		}
		
		Map<String,String> map = new HashMap<String,String>();
		
		MemberCommand member = memberService.selectMember(email);
		if(member!=null) {
			//���̵� �ߺ�
			map.put("result", "idDuplicated");
		}else {
			//���̵� ���ߺ�
			map.put("result", "idNotFound");
		}
		
		return map;
	}
}
