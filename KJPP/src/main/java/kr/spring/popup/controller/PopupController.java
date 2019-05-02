package kr.spring.popup.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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

import kr.spring.goapp.domain.GoappCommand;
import kr.spring.goapp.service.GoappService;
import kr.spring.gowith.domain.GowithCommand;
import kr.spring.gowith.service.GowithService;

@Controller
public class PopupController {
	private Logger log = Logger.getLogger(this.getClass());
	@Resource
	private GoappService goappService;
	
	@Resource
	private GowithService gowithService;
	
	//신청서 디테일 폼//
	//리스트
	@RequestMapping(value="/popup/popup.do", method=RequestMethod.GET)
	public ModelAndView form(@RequestParam("app_num") int app_num, @RequestParam("go_num") int go_num) {
					//app_num pk값을 command로 바꿔준것이다.
		GoappCommand command = goappService.selectMember(app_num);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("popup/popup");
		mav.addObject("command", command);
		mav.addObject("go_num", go_num);
		
		return mav;
	}
	
		//==========신청정보 수정===========//
	   //신청 수정 폼 get
	   @RequestMapping(value="/popup/update.do", method=RequestMethod.GET)
	   public ModelAndView formUpdate(@RequestParam("app_num") int app_num, @RequestParam("go_num") int go_num) {
		   if(log.isDebugEnabled()) {
			   log.debug("<<go_num>>@@@@@@@@@@@@@@@@@@@@@@@ : " + go_num);
		   }
		   int count = gowithService.count(go_num);
		   
		   GoappCommand command = goappService.selectMember(app_num);
		   ModelAndView mav = new ModelAndView();
		   mav.setViewName("popup/goappModify");
		   mav.addObject("command", command);
		   mav.addObject("count", count);

		   
		   return mav;
	   }
	   //신청 수정 post
	   @RequestMapping(value="/popup/update.do", method=RequestMethod.POST)
	   public String submitUpdate(@ModelAttribute("command") GoappCommand goapp, BindingResult result, 
				Model model) { //HttpServletRequest : ip주소 받기)		   
		   
		   goappService.update(goapp);
		   
			model.addAttribute("result", "success"); 
		   return "popup/updateSuccess";
	   }
	   
	/*   //=========신청 삭제=========//
	   //신청 삭제 get
	   @RequestMapping(value="/popup/delete.do", method=RequestMethod.GET)
	   public ModelAndView formDelete(@RequestParam("app_num") int app_num) {
		   
		   GoappCommand command = goappService.selectMember(app_num);
		   ModelAndView mav = new ModelAndView();
		   mav.addObject("command", command);
		   
		   return mav;
	   }*/
	   
	   //신청 삭제 post
	   @RequestMapping("/popup/delete.do")
	   public String submitDelete(@ModelAttribute("app_num") int app_num, Model model) {
		   
		   if(log.isDebugEnabled()) {
			   log.debug("<<app_num>> : " + app_num);
		   }
		   //신청서 삭제
		   goappService.delete(app_num);
		   
		   model.addAttribute("result", "success");
		   return "popup/deleteSuccess";
	   }
	   
	   //이미지 출력
	   @RequestMapping("/popup/imageView.do")
	   											  //pk지정값을 넣어준것이다.(app_num)
	   public ModelAndView viewImage(@RequestParam("app_num") int app_num) {
		 
	      GoappCommand goapp = goappService.selectMember(app_num);
	      
	      if(log.isDebugEnabled()) {
	         log.debug("<<member>> : " + goapp);
	      }
	      
	      ModelAndView mav = new ModelAndView();
	      mav.setViewName("imageView");
	      mav.addObject("imageFile", goapp.getApp_photo());
	      mav.addObject("filename", "image.jpg");
	      
	      return mav;
	   }
	
}
