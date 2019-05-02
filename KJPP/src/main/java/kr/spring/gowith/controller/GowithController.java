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

	//자바빈 초기화 
	public GowithCommand initCommand() {
		return new GowithCommand();
	}	
	
	//=============동행등록===============
	//register폼 호출//
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
		
		//동행글 등록
		gowithService.insert(gowithCommand);
		
		//글등록시 점수 올리기
		gowithService.updateScore(email);
		
		/*//로그아웃
		session.invalidate();

		//인증 성공, 로그인 처리
		session.setAttribute("user_email", member.getEmail());
		session.setAttribute("user_auth", member.getT_auth());
		session.setAttribute("user_nickname", member.getTd_nickname());
		session.setAttribute("user_score", member.getTd_score());
		session.setAttribute("user_gender", member.getTd_gender());*/
		
		return "redirect:/gowith/gowithList.do";
	}

	//==============함께 떠나요 목록(모집인원 조건)==============//
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
		
		//총 글의 개수 또는 검색된 글의 개수 
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
		System.out.println("list 값 :"+list);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("gowithList");//definition 설정 이름 gowithList
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("pagingHtml", page.getPagingHtml());
		
		return mav;
	}
	
	//글상세 (신청자 리스트 포함, 중복신청 X)
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
		//리스트에 값을 담아주는거다
		list = goappService.selectList(go_num);		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("gowithRegisterDetail"); //여기서 설정하는 이름은 definition 설정, jsp명이 아님!!
		//목록 처리 셋팅
		mav.addObject("list", list);
		mav.addObject("go_num", go_num);
				
		//폼 호출하기 위해서 자바빈 생성
		GoappCommand command = new GoappCommand();
		mav.addObject("command", command);//폼 호출위해서 셋팅
		
		if(log.isDebugEnabled()) {
			log.debug("<<num>> : " + go_num);
		}
		
		//신청했는지 여부 체크
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
	
	
	//이미지 출력----------------------------------------------------
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
	
	//프로필이미지 호출
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
	
	//------------------신청 팝업창---------------------
	@Resource
	private GoappService goappService;


	//전송된 데이터 처리
	@RequestMapping(value="/gowith/gowithappList.do", method=RequestMethod.POST)
	public String submit(@ModelAttribute("command") GoappCommand goappCommand, BindingResult result, 
			HttpServletRequest request, RedirectAttributes redirect) { //HttpServletRequest : ip주소 받기 위함, RedirectAttributes : 

		if(log.isDebugEnabled()) {
			log.debug("<<goappCommand>> :" + goappCommand);
		}
		//신청하기
		goappService.insert(goappCommand);

		//RedirectAttributes 객체는 리다이렉트 시점에 단 한 번만 사용되는 데이터를 전송
		//브라우저에 데이터를 전송하지만 URL상에는 보이지 않는 숨겨진 데이터의 형태로 전달 
		redirect.addFlashAttribute("result", "success"); //result를 통해서 success를 표시
		
		return "redirect:/gowith/gowithRegisterDetail.do?go_num="+goappCommand.getGo_num()+"&email="+goappCommand.getW_email();
	
	}
	
	//----------------------신청수락--------------------------
	@RequestMapping(value="/popup/goappConfirm.do", method=RequestMethod.GET)
	public String confirm(@RequestParam("app_num") int app_num, @RequestParam("go_num") int go_num) {
		GoappCommand goapp = new GoappCommand();
		goappService.confirm(app_num);
		
		return "popup/close";
	}
	

	//===================동행글 삭제========================
	
	@RequestMapping("/gowith/delete.do")
	public String submit(@RequestParam("go_num") int go_num) {
		if(log.isDebugEnabled()) {
			log.debug("<<go_num>> : " + go_num);
		}
		//글삭제
		gowithService.delete(go_num);
		
		return "redirect:/gowith/gowithList.do";
	}
	
	//=================동행글 수정===========================
	//폼호출
	
	@RequestMapping(value="/gowith/gowithModify.do", method=RequestMethod.GET)
	public String form(@RequestParam("go_num") int go_num, Model model) {
		
		GowithCommand gowithCommand = gowithService.selectGowith(go_num);
		model.addAttribute("command", gowithCommand);
		return "gowithModify";
	}
	//수정폼에서 전송된 데이터 처리 
	@RequestMapping(value="/gowith/gowithModify.do", method=RequestMethod.POST)
	public String submit(@ModelAttribute("command") @Valid GowithCommand gowithCommand, BindingResult result, HttpServletRequest request) {
		
		if(log.isDebugEnabled()) {
			log.debug("<<gowithCommand>> : " + gowithCommand);
		}
		
		//글 수정
		gowithService.update(gowithCommand);
		return "redirect:/gowith/gowithList.do";
	}
	
	

}






