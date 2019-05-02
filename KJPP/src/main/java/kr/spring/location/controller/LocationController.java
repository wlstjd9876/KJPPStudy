package kr.spring.location.controller;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.location.service.LocationService;



@Controller
public class LocationController{
	private Logger log = Logger.getLogger(this.getClass()); //Logger가 인식이 안되는 경우 pom.xml에 log4j 명시

	 @Resource LocationService locationService; 

	@RequestMapping(value="/data/location/list.do", method=RequestMethod.GET)
	public String search_form() {
		return "data/location/list";
	}
	@RequestMapping(value="/data/location/view.do", method=RequestMethod.GET)
	public ModelAndView view_form(@RequestParam("contentId") int contentId) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("view");
		mav.addObject("contentId", contentId);
		
		return mav;
	}
	
	//관광지 검색 popup controller
	@RequestMapping(value="/data/location/listPopup.do", method=RequestMethod.GET)
	public String search_formPopup() {
		//footer랑 header를 짜르게 나오려면 직점 jsp를 호출해줘야해서 이렇게 명시해야한다.
		return "data/location/listPopup";
	}
	@RequestMapping(value="/data/location/viewPopup.do", method=RequestMethod.GET)
	public ModelAndView view_formPopup(@RequestParam("contentId") int contentId) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("data/location/viewPopup");
		mav.addObject("contentId", contentId);

		return mav;
	}
	//여기까지 popup controller
	
	/*  //자바빈(커맨드 객체) 초기화
	   @ModelAttribute("command")
	   public LocationCommand initCommand() {
	      return new LocationCommand();
	   }

	   //위도 경도 넣기
	   @RequestMapping(value="/location/getlocation.do",method=RequestMethod.GET)
	   public String submit(@ModelAttribute("command") @Valid LocationCommand locationCommand, BindingResult result) {

		   if(log.isDebugEnabled()) {
		         log.debug("<<locationCommand>> : " + locationCommand);
		      }

		   locationService.insert(locationCommand);

		   return "redirect:/main/main.do";

<<<<<<< HEAD
	   }*/
	//location 폼
	@RequestMapping(value="/data/location.do", method=RequestMethod.GET)
	public String form() {
		return "location";
	}


}
