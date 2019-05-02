package kr.spring.gowith.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.spring.calendar.domain.CalendarCommand;
import kr.spring.goapp.domain.GoappCommand;
import kr.spring.goapp.service.GoappService;
import kr.spring.gowith.domain.GowithCommand;
import kr.spring.gowith.service.GowithService;
import kr.spring.member.domain.MemberCommand;
import kr.spring.member.service.MemberService;
import kr.spring.util.PagingUtil;


@Controller
public class GowithController{
	private Logger log = Logger.getLogger(this.getClass());
	private int rowCount = 10;
	private int pageCount = 10;	
	
	@Resource
	private MemberService memberService;
	
	@Resource
	private GowithService gowithService;

	//�ڹٺ� �ʱ�ȭ 
	public GowithCommand initCommand() {
		return new GowithCommand();
	}	
	
	//=============������===============
	//register�� ȣ��//
	@RequestMapping(value="/gowith/gowithRegister.do", method=RequestMethod.GET)
	public String registerform(Model model, HttpSession session) {
		String email = (String)session.getAttribute("user_email");
		GowithCommand command = new GowithCommand();
		
		command.setEmail(email);
		
		model.addAttribute("command", command);
		
		return "gowithRegister";
	}
	
	@RequestMapping(value="/gowith/gowithRegister.do", method=RequestMethod.POST)
	public String submit(@ModelAttribute("command") @Valid GowithCommand gowithCommand, BindingResult result, @RequestParam("email") String email) {
		if(log.isDebugEnabled()) {
			log.debug("<<gowithCommand>>:" + gowithCommand);
		}
		
		/*MemberCommand member = new MemberCommand();
		
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@" + member);
		
		member = memberService.selectMember(email);*/
		
		//����� ���
		gowithService.insert(gowithCommand);
		
		//�۵�Ͻ� ���� �ø���
		gowithService.updateScore(email);
		
		/*//�α׾ƿ�
		session.invalidate();

		//���� ����, �α��� ó��
		session.setAttribute("user_email", member.getEmail());
		session.setAttribute("user_auth", member.getT_auth());
		session.setAttribute("user_nickname", member.getTd_nickname());
		session.setAttribute("user_score", member.getTd_score());
		session.setAttribute("user_gender", member.getTd_gender());*/
		
		return "redirect:/gowith/gowithList.do";
	}

	//==============�Բ� ������ ���(�����ο� ����)==============//
	@RequestMapping(value="/gowith/gowithList.do" )
	public ModelAndView process(
			 @RequestParam(value="pageNum", defaultValue="1") 
			 int currentPage,
			 @RequestParam(value="keyfield", defaultValue="") 
	         String keyfield, 
			 @RequestParam(value="keyword", defaultValue="") 
			 String keyword) {		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		
		//�� ���� ���� �Ǵ� �˻��� ���� ���� 
		int count = gowithService.selectRowCount(map);
		
		if(log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}
		
		PagingUtil page = new PagingUtil(keyfield, keyword, currentPage, count, rowCount, pageCount, "gowithList.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<GowithCommand> list = null;
		if(count > 0) {
			list = gowithService.selectList(map);
		}
		System.out.println("list �� :"+list);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("gowithList");//definition ���� �̸� gowithList
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("pagingHtml", page.getPagingHtml());
		
		return mav;
	}
	
	//�ۻ� (��û�� ����Ʈ ����, �ߺ���û X)
	@RequestMapping(value="gowith/gowithRegisterDetail.do", method=RequestMethod.GET)
	public ModelAndView process(@RequestParam("go_num") int go_num, @RequestParam("email") String email,
			@RequestParam(value="pageNum",defaultValue="1") int currentPage) {
		
		int count = 0;
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		PagingUtil page = new PagingUtil(currentPage, 1, 10, 10, "list.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		map.put("go_num", go_num);
		map.put("email", email);
		
		List<GoappCommand> list = null;
		//����Ʈ�� ���� ����ִ°Ŵ�
		list = goappService.selectList(go_num);		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("gowithRegisterDetail"); //���⼭ �����ϴ� �̸��� definition ����, jsp���� �ƴ�!!
		//��� ó�� ����
		mav.addObject("list", list);
		mav.addObject("go_num", go_num);
				
		//�� ȣ���ϱ� ���ؼ� �ڹٺ� ����
		GoappCommand command = new GoappCommand();
		mav.addObject("command", command);//�� ȣ�����ؼ� ����
		
		if(log.isDebugEnabled()) {
			log.debug("<<num>> : " + go_num);
		}
		
		//��û�ߴ��� ���� üũ
		count = goappService.check(map);		
		System.out.println(count);
		
		GowithCommand gowith = gowithService.selectGowith(go_num);
		if(log.isDebugEnabled()) {
			log.debug("<<GowithCommand>> : " + gowith);
		}
		
		mav.addObject("gowith", gowith);
		mav.addObject("count", count);
		
		return mav;
	}
	
	
	//�̹��� ���----------------------------------------------------
	@RequestMapping("/gowith/imageView1.do")
	public ModelAndView viewImage(@RequestParam("go_num") int go_num, @RequestParam("photo_type") int photo_type) {
		
		GowithCommand gowith = gowithService.selectGowith(go_num);
	
		if(log.isDebugEnabled()) {
			log.debug("<<GowithCommand>> :" + gowith);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		if(photo_type==1) {
			mav.addObject("imageFile", gowith.getGo_photo1());
		}else if(photo_type==2) {
			mav.addObject("imageFile", gowith.getGo_photo2());
		}else if(photo_type==3) {
			mav.addObject("imageFile", gowith.getGo_photo3());
		}else if(photo_type==4) {
			mav.addObject("imageFile", gowith.getGo_photo4());
		}
		mav.addObject("filename", "image.jpg");
		
		return mav;
		
	}
	
	//�������̹��� ȣ��
	@RequestMapping("/gowith/imageView.do")
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
	
	//------------------��û �˾�â---------------------
	@Resource
	private GoappService goappService;


	//���۵� ������ ó��
	@RequestMapping(value="/gowith/gowithappList.do", method=RequestMethod.POST)
	public String submit(@ModelAttribute("command") GoappCommand goappCommand, BindingResult result, 
			HttpServletRequest request, RedirectAttributes redirect) { //HttpServletRequest : ip�ּ� �ޱ� ����, RedirectAttributes : 

		if(log.isDebugEnabled()) {
			log.debug("<<goappCommand>> :" + goappCommand);
		}
		//��û�ϱ�
		goappService.insert(goappCommand);

		//RedirectAttributes ��ü�� �����̷�Ʈ ������ �� �� ���� ���Ǵ� �����͸� ����
		//�������� �����͸� ���������� URL�󿡴� ������ �ʴ� ������ �������� ���·� ���� 
		redirect.addFlashAttribute("result", "success"); //result�� ���ؼ� success�� ǥ��
		
		return "redirect:/gowith/gowithRegisterDetail.do?go_num="+goappCommand.getGo_num()+"&email="+goappCommand.getW_email();
	
	}
	
	//----------------------��û����--------------------------
	@RequestMapping(value="/popup/goappConfirm.do", method=RequestMethod.GET)
	public String confirm(@RequestParam("app_num") int app_num, @RequestParam("go_num") int go_num) {
		GoappCommand goapp = new GoappCommand();
		goappService.confirm(app_num);
		
		return "popup/close";
	}
	

	//===================����� ����========================
	
	@RequestMapping("/gowith/delete.do")
	public String submit(@RequestParam("go_num") int go_num) {
		if(log.isDebugEnabled()) {
			log.debug("<<go_num>> : " + go_num);
		}
		//�ۻ���
		gowithService.delete(go_num);
		
		return "redirect:/gowith/gowithList.do";
	}
	
	//=================����� ����===========================
	//��ȣ��
	
	@RequestMapping(value="/gowith/gowithModify.do", method=RequestMethod.GET)
	public String form(@RequestParam("go_num") int go_num, Model model) {
		
		GowithCommand gowithCommand = gowithService.selectGowith(go_num);
		model.addAttribute("command", gowithCommand);
		return "gowithModify";
	}
	//���������� ���۵� ������ ó�� 
	@RequestMapping(value="/gowith/gowithModify.do", method=RequestMethod.POST)
	public String submit(@ModelAttribute("command") @Valid GowithCommand gowithCommand, BindingResult result, HttpServletRequest request) {
		
		if(log.isDebugEnabled()) {
			log.debug("<<gowithCommand>> : " + gowithCommand);
		}
		
		//�� ����
		gowithService.update(gowithCommand);
		return "redirect:/gowith/gowithList.do";
	}
	
	

}






