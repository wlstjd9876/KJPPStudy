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
	private Logger log = Logger.getLogger(this.getClass()); //Logger�� �ν��� �ȵǴ� ��� pom.xml�� log4j ���

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
	
	//������ �˻� popup controller
	@RequestMapping(value="/data/location/listPopup.do", method=RequestMethod.GET)
	public String search_formPopup() {
		//footer�� header�� ¥���� �������� ���� jsp�� ȣ��������ؼ� �̷��� ����ؾ��Ѵ�.
		return "data/location/listPopup";
	}
	@RequestMapping(value="/data/location/viewPopup.do", method=RequestMethod.GET)
	public ModelAndView view_formPopup(@RequestParam("contentId") int contentId) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("data/location/viewPopup");
		mav.addObject("contentId", contentId);

		return mav;
	}
	//������� popup controller
	
	/*  //�ڹٺ�(Ŀ�ǵ� ��ü) �ʱ�ȭ
	   @ModelAttribute("command")
	   public LocationCommand initCommand() {
	      return new LocationCommand();
	   }

	   //���� �浵 �ֱ�
	   @RequestMapping(value="/location/getlocation.do",method=RequestMethod.GET)
	   public String submit(@ModelAttribute("command") @Valid LocationCommand locationCommand, BindingResult result) {

		   if(log.isDebugEnabled()) {
		         log.debug("<<locationCommand>> : " + locationCommand);
		      }

		   locationService.insert(locationCommand);

		   return "redirect:/main/main.do";

<<<<<<< HEAD
	   }*/
	//location ��
	@RequestMapping(value="/data/location.do", method=RequestMethod.GET)
	public String form() {
		return "location";
	}


}
