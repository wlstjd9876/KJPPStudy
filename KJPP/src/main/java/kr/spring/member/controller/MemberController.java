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
	
	//��ȣȭ
	@Resource
	private CipherTemplate cipherAES;
	
	//�ڹٺ�(Ŀ�ǵ� ��ü) �ʱ�ȭ
	@ModelAttribute("command")
	public MemberCommand initCommand() {
		return new MemberCommand();
	}
	//==========ȸ������============
	//ȸ�� ��� �� ȣ��
	@RequestMapping(value="/member/write.do",method=RequestMethod.GET)
	public String form() {
		return "memberWrite";
	}

	//ȸ������ ������ ����
	@RequestMapping(value="/member/write.do",method=RequestMethod.POST)
	public String submit(@ModelAttribute("command") @Valid MemberCommand memberCommand,BindingResult result) {

		if(log.isDebugEnabled()) {
			log.debug("<<memberCommand>> : " + memberCommand);
		}
		
		if(result.hasErrors()) {
			return form();
		}
		
		//CipherTemplate�� �̿��� ��ȣȭ
		memberCommand.setTd_password(cipherAES.encrypt(memberCommand.getTd_password()));
		
		//ȸ������
		memberService.insert(memberCommand);

		return "redirect:/main/main.do";
	}
	//==========ȸ���α���============
	//�α��� ��
	@RequestMapping(value="/member/login.do",method=RequestMethod.GET)
	public String formLogin() {
		return "memberLogin";
	}
	//�α��� ���� ���۵� ������ ó��
	@RequestMapping(value="/member/login.do",method=RequestMethod.POST)
	public String submitLogin(@ModelAttribute("command")
	@Valid MemberCommand memberCommand,
	BindingResult result,HttpSession session) {

		if(log.isDebugEnabled()) {
			log.debug("<<memberCommand>> : " + memberCommand);
		}

		//��ȿ�� üũ (email�� td_password �ʵ常 üũ)
		if(result.hasFieldErrors("email") || result.hasFieldErrors("td_password")) {
			return formLogin();
		}

		//�α��� üũ(id, ��й�ȣ ��ġ ���� üũ)
		try {
			MemberCommand member = memberService.selectMember(memberCommand.getEmail());

			boolean check = false;

			if(member!=null) {
				//��й�ȣ ��ġ ���� üũ
				check = member.isCheckedPasswd(cipherAES.encrypt(memberCommand.getTd_password()));
			}
			if(check) {
				//���� ����, �α��� ó��
				session.setAttribute("user_email", member.getEmail());
				session.setAttribute("user_auth", member.getT_auth());
				session.setAttribute("user_nickname", member.getTd_nickname());
				session.setAttribute("user_score", member.getTd_score());
				session.setAttribute("user_gender", member.getTd_gender());

				if(log.isDebugEnabled()) {
					log.debug("<<���� ����>>");
					log.debug("<<user_email>> : " + member.getEmail());
					log.debug("<<user_auth>> : " + member.getT_auth());
				}

				return "redirect:/main/main.do";
			}else {
				//���� ����
				throw new LoginException();
			}
		}catch(LoginException e) {
			//���� ���з� �� ȣ��
			result.reject("invalidIdOrPassword");

			if(log.isDebugEnabled()) {
				log.debug("<<���� ����>>");
			}
			return formLogin();//�α��� �� ȣ��
		}
	}
	//=======ȸ���α׾ƿ�=========
	@RequestMapping("/member/logout.do")
	public String processLogout(HttpSession session) {
		//�α׾ƿ�
		session.invalidate();

		return "redirect:/main/main.do";
	}
	//=======ȸ��������========
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
	
	//�̹��� ���
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

	//==========ȸ����������============
	//������
	@RequestMapping(value="/member/update.do",method=RequestMethod.GET)
	public String formUpdate(HttpSession session,Model model) {
		
		String email = (String)session.getAttribute("user_email");

		MemberCommand member = memberService.selectMember(email);

		model.addAttribute("command", member);

		return "memberModify";
	}
	//���������� ���۵� ������ ó��
	@RequestMapping(value="/member/update.do",method=RequestMethod.POST)
	public String submitUpdate(@ModelAttribute("command")@Valid MemberCommand memberCommand,BindingResult result) {

		if(log.isDebugEnabled()) {
			log.debug("<<memberCommand>> : " + memberCommand);
		}

		if(result.hasErrors()) {
			return "memberModify";
		}

		//ȸ����������
		memberService.update(memberCommand);

		return "redirect:/member/detail.do";
	}

	//============��й�ȣ ����============//
	//������
	@RequestMapping(value="/member/changePassword.do",method=RequestMethod.GET)
	public String formChangePassword(HttpSession session,Model model) {
		String email = (String)session.getAttribute("user_email");

		MemberCommand member = memberService.selectMember(email);

		model.addAttribute("command", member);

		return "memberChangePassword";
	}
	//��й�ȣ ���������� ���۵� ������ ó��
	@RequestMapping(value="/member/changePassword.do",method=RequestMethod.POST)
	public String submitChangePassword(@ModelAttribute("command")@Valid MemberCommand memberCommand,BindingResult result) {
		
		if(log.isDebugEnabled()) {
			log.debug("<<memberCommand>> : " + memberCommand);
		}
		
		if(result.hasFieldErrors("email") || result.hasFieldErrors("old_passwd") || result.hasFieldErrors("td_password")){
			return "memberChangePassword";
		}
		
		//���� ��й�ȣ(old_passwd) ��ġ ���� üũ
		MemberCommand member = memberService.selectMember(memberCommand.getEmail());
		
		//����ڰ� �Է��� ���� ��й�ȣ�� DB�� ���� ��й�ȣ ��ġ ���� üũ
		if(!member.getTd_password().equals(cipherAES.encrypt(memberCommand.getOld_passwd()))) {
			result.rejectValue("old_passwd", "invalidPassword");
			return "memberChangePassword";
		}
		
		//CipherTemplate�� �̿��� ��ȣȭ
		memberCommand.setTd_password(cipherAES.encrypt(memberCommand.getTd_password()));
		
		//��й�ȣ ����
		memberService.updatePassword(memberCommand);
		
		return "redirect:/member/detail.do";
	}
	//===========ȸ������(ȸ��Ż��)==============
	//ȸ�� ���� ��
	@RequestMapping(value="/member/delete.do",method=RequestMethod.GET)
	public String formDelete(HttpSession session,Model model) {
		
		String email = (String)session.getAttribute("user_email");
		
		MemberCommand member = new MemberCommand();
		member.setEmail(email);
		
		model.addAttribute("command", member);
		
		return "memberDelete";
	}
	//ȸ�� ������ ����
	@RequestMapping(value="/member/delete.do",method=RequestMethod.POST)
	public String submitDelete(@ModelAttribute("command")@Valid MemberCommand memberCommand,BindingResult result,HttpSession session) {
		
		if(log.isDebugEnabled()) {
			log.debug("<<memberCommand>> : " + memberCommand);
		}
		
		//email,td_password �ʵ��� ������ üũ
		if(result.hasFieldErrors("email") || result.hasFieldErrors("td_password")) {
			
			return "memberDelete";
		}
		
		//��й�ȣ ��ġ ���� üũ
		try {
			MemberCommand member = memberService.selectMember(memberCommand.getEmail());
			
			boolean check = false;
			
			if(member!=null) {
				//��й�ȣ ��ġ ���� üũ
				check = member.isCheckedPasswd(cipherAES.encrypt(memberCommand.getTd_password()));
			}
			if(check) {
				//��������, ȸ������ ����
				memberService.delete(memberCommand.getEmail());
				//�α׾ƿ�
				session.invalidate();
				return "redirect:/main/main.do";
			}else {
				//���� ����
				throw new LoginException();
			}
			
		}catch(LoginException e) {
			result.rejectValue("td_password", "invalidPassword");
			//ȸ��Ż�� �� ȣ��
			return "memberDelete";
		}
	}
}
