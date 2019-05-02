package kr.spring.member.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.member.domain.MemberCommand;
import kr.spring.member.service.MemberService;
import kr.spring.util.CipherTemplate;
import kr.spring.util.LoginException;

@Controller
public class MemberController {
	private Logger log = Logger.getLogger(this.getClass());

	@Resource
	private MemberService memberService;
	
	//암호화
	@Resource
	private CipherTemplate cipherAES;
	
	//자바빈(커맨드 객체) 초기화
	@ModelAttribute("command")
	public MemberCommand initCommand() {
		return new MemberCommand();
	}
	//==========회원가입============
	//회원 등록 폼 호출
	@RequestMapping(value="/member/write.do",method=RequestMethod.GET)
	public String form() {
		return "memberWrite";
	}

	//회원가입 데이터 전송
	@RequestMapping(value="/member/write.do",method=RequestMethod.POST)
	public String submit(@ModelAttribute("command") @Valid MemberCommand memberCommand,BindingResult result) {

		if(log.isDebugEnabled()) {
			log.debug("<<memberCommand>> : " + memberCommand);
		}
		
		if(result.hasErrors()) {
			return form();
		}
		
		//CipherTemplate를 이용한 암호화
		memberCommand.setTd_password(cipherAES.encrypt(memberCommand.getTd_password()));
		
		//회원가입
		memberService.insert(memberCommand);

		return "redirect:/main/main.do";
	}
	//==========회원로그인============
	//로그인 폼
	@RequestMapping(value="/member/login.do",method=RequestMethod.GET)
	public String formLogin() {
		return "memberLogin";
	}
	//로그인 폼에 전송된 데이터 처리
	@RequestMapping(value="/member/login.do",method=RequestMethod.POST)
	public String submitLogin(@ModelAttribute("command")
	@Valid MemberCommand memberCommand,
	BindingResult result,HttpSession session) {

		if(log.isDebugEnabled()) {
			log.debug("<<memberCommand>> : " + memberCommand);
		}

		//유효성 체크 (email와 td_password 필드만 체크)
		if(result.hasFieldErrors("email") || result.hasFieldErrors("td_password")) {
			return formLogin();
		}

		//로그인 체크(id, 비밀번호 일치 여부 체크)
		try {
			MemberCommand member = memberService.selectMember(memberCommand.getEmail());

			boolean check = false;

			if(member!=null) {
				//비밀번호 일치 여부 체크
				check = member.isCheckedPasswd(cipherAES.encrypt(memberCommand.getTd_password()));
			}
			if(check) {
				//인증 성공, 로그인 처리
				session.setAttribute("user_email", member.getEmail());
				session.setAttribute("user_auth", member.getT_auth());
				session.setAttribute("user_nickname", member.getTd_nickname());
				session.setAttribute("user_score", member.getTd_score());
				session.setAttribute("user_gender", member.getTd_gender());

				if(log.isDebugEnabled()) {
					log.debug("<<인증 성공>>");
					log.debug("<<user_email>> : " + member.getEmail());
					log.debug("<<user_auth>> : " + member.getT_auth());
				}

				return "redirect:/main/main.do";
			}else {
				//인증 실패
				throw new LoginException();
			}
		}catch(LoginException e) {
			//인증 실패로 폼 호출
			result.reject("invalidIdOrPassword");

			if(log.isDebugEnabled()) {
				log.debug("<<인증 실패>>");
			}
			return formLogin();//로그인 폼 호출
		}
	}
	//=======회원로그아웃=========
	@RequestMapping("/member/logout.do")
	public String processLogout(HttpSession session) {
		//로그아웃
		session.invalidate();

		return "redirect:/main/main.do";
	}
	//=======회원상세정보========
	@RequestMapping("/member/detail.do")
	public String process(HttpSession session,Model model) {

		String email = (String)session.getAttribute("user_email");

		MemberCommand member = memberService.selectMember(email);

		if(log.isDebugEnabled()) {
			log.debug("<<memberCommand>> : " + member);
		}


		model.addAttribute("member", member);//=request.setAttribute

		return "memberView";
	}
	
	//이미지 출력
	@RequestMapping("/member/imageView.do")
	public ModelAndView viewImage(@RequestParam("email") String email) {
		
		MemberCommand member = memberService.selectMember(email);
		
		if(log.isDebugEnabled()) {
			log.debug("<<member>> : " + member);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		mav.addObject("imageFile", member.getTd_profile());
		mav.addObject("filename", "image.jpg");
		
		return mav;
	}

	//==========회원정보수정============
	//수정폼
	@RequestMapping(value="/member/update.do",method=RequestMethod.GET)
	public String formUpdate(HttpSession session,Model model) {
		
		String email = (String)session.getAttribute("user_email");

		MemberCommand member = memberService.selectMember(email);

		model.addAttribute("command", member);

		return "memberModify";
	}
	//수정폼에서 전송된 데이터 처리
	@RequestMapping(value="/member/update.do",method=RequestMethod.POST)
	public String submitUpdate(@ModelAttribute("command")@Valid MemberCommand memberCommand,BindingResult result) {

		if(log.isDebugEnabled()) {
			log.debug("<<memberCommand>> : " + memberCommand);
		}

		if(result.hasErrors()) {
			return "memberModify";
		}

		//회원정보수정
		memberService.update(memberCommand);

		return "redirect:/member/detail.do";
	}

	//============비밀번호 수정============//
	//수정폼
	@RequestMapping(value="/member/changePassword.do",method=RequestMethod.GET)
	public String formChangePassword(HttpSession session,Model model) {
		String email = (String)session.getAttribute("user_email");

		MemberCommand member = memberService.selectMember(email);

		model.addAttribute("command", member);

		return "memberChangePassword";
	}
	//비밀번호 수정폼에서 전송된 데이터 처리
	@RequestMapping(value="/member/changePassword.do",method=RequestMethod.POST)
	public String submitChangePassword(@ModelAttribute("command")@Valid MemberCommand memberCommand,BindingResult result) {
		
		if(log.isDebugEnabled()) {
			log.debug("<<memberCommand>> : " + memberCommand);
		}
		
		if(result.hasFieldErrors("email") || result.hasFieldErrors("old_passwd") || result.hasFieldErrors("td_password")){
			return "memberChangePassword";
		}
		
		//현재 비밀번호(old_passwd) 일치 여부 체크
		MemberCommand member = memberService.selectMember(memberCommand.getEmail());
		
		//사용자가 입력한 현재 비밀번호와 DB의 현재 비밀번호 일치 여부 체크
		if(!member.getTd_password().equals(cipherAES.encrypt(memberCommand.getOld_passwd()))) {
			result.rejectValue("old_passwd", "invalidPassword");
			return "memberChangePassword";
		}
		
		//CipherTemplate을 이용한 암호화
		memberCommand.setTd_password(cipherAES.encrypt(memberCommand.getTd_password()));
		
		//비밀번호 수정
		memberService.updatePassword(memberCommand);
		
		return "redirect:/member/detail.do";
	}
	//===========회원삭제(회원탈퇴)==============
	//회원 삭제 폼
	@RequestMapping(value="/member/delete.do",method=RequestMethod.GET)
	public String formDelete(HttpSession session,Model model) {
		
		String email = (String)session.getAttribute("user_email");
		
		MemberCommand member = new MemberCommand();
		member.setEmail(email);
		
		model.addAttribute("command", member);
		
		return "memberDelete";
	}
	//회원 데이터 삭제
	@RequestMapping(value="/member/delete.do",method=RequestMethod.POST)
	public String submitDelete(@ModelAttribute("command")@Valid MemberCommand memberCommand,BindingResult result,HttpSession session) {
		
		if(log.isDebugEnabled()) {
			log.debug("<<memberCommand>> : " + memberCommand);
		}
		
		//email,td_password 필드의 에러만 체크
		if(result.hasFieldErrors("email") || result.hasFieldErrors("td_password")) {
			
			return "memberDelete";
		}
		
		//비밀번호 일치 여부 체크
		try {
			MemberCommand member = memberService.selectMember(memberCommand.getEmail());
			
			boolean check = false;
			
			if(member!=null) {
				//비밀번호 일치 여부 체크
				check = member.isCheckedPasswd(cipherAES.encrypt(memberCommand.getTd_password()));
			}
			if(check) {
				//인증성공, 회원정보 삭제
				memberService.delete(memberCommand.getEmail());
				//로그아웃
				session.invalidate();
				return "redirect:/main/main.do";
			}else {
				//인증 실패
				throw new LoginException();
			}
			
		}catch(LoginException e) {
			result.rejectValue("td_password", "invalidPassword");
			//회원탈퇴 폼 호출
			return "memberDelete";
		}
	}
}
