package kr.spring.fav.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.fav.domain.FavoriteCommand;
import kr.spring.fav.service.FavoriteService;
import kr.spring.member.domain.MemberCommand;
import kr.spring.member.service.MemberService;

@Controller
public class FavoriteController {
	private Logger log = Logger.getLogger(this.getClass()); //Logger가 인식이 안되는 경우 pom.xml에 log4j 명시
	
	/*private int rowCount = 2;
	private int pageCount = 2;*/
	
	@Resource
	private FavoriteService favoriteService;
	
	@Resource
	private MemberService memberService;
	
	//자바빈(커맨드 객체) 초기화
	@ModelAttribute("command")
	public FavoriteCommand initCommand() {
		return new FavoriteCommand();
	}
	
	//=======즐겨찾기 목록=======//
	@RequestMapping(value="/calendar/favList.do")
	public ModelAndView list(){
			/*@RequestParam(value="pageNum",defaultValue="1")
			int currentPage,
			@RequestParam(value="keyfield",defaultValue="")
			String keyfield,
			@RequestParam(value="keyword",defaultValue="")
			String keyword) {*/
		
		//즐겨찾기 메인 목록
		List<FavoriteCommand> list1 = favoriteService.selectFav1();
		List<FavoriteCommand> list2 = favoriteService.selectFav2();
		List<FavoriteCommand> list3 = favoriteService.selectFav3();
		System.out.println("==============1 : " + list1);
		System.out.println("==============2 : " + list2);
		System.out.println("==============3 : " + list3);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("favList"); //여기서 설정하는 이름은 definition 설정, jsp명이 아님!!
		mav.addObject("list1", list1);
		mav.addObject("list2", list2);
		mav.addObject("list3", list3);		
		
		return mav;
	}
	
	@RequestMapping(value="/calendar/imageView2.do")
	public ModelAndView viewImage2(@RequestParam("email") String email) {
		System.out.println(email);
		
		MemberCommand member = memberService.selectMember(email);
		
		if(log.isDebugEnabled()) {
			log.debug("<<member>> : " + member);
		}
		
		ModelAndView mav2 = new ModelAndView();
		mav2.setViewName("imageView");
		mav2.addObject("imageFile", member.getTd_profile());
		mav2.addObject("filename", "image.jpg");
		return mav2;
	}
	
	
	//=======글 삭제=======
	@RequestMapping(value="/calendar/favDelete.do", method={RequestMethod.GET, RequestMethod.POST})
	public String submit(@RequestParam("f_num") int f_num) {
		
		if(log.isDebugEnabled()) {
			log.debug("<<num>> : " + f_num);
		}
		//글 삭제
		favoriteService.deleteFav(f_num);
		
		return "redirect:/calendar/favList.do";
	}	
	
}
