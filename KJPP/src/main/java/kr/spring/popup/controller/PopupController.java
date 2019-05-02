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
	
	//��û�� ������ ��//
	//����Ʈ
	@RequestMapping(value="/popup/popup.do", method=RequestMethod.GET)
	public ModelAndView form(@RequestParam("app_num") int app_num, @RequestParam("go_num") int go_num) {
					//app_num pk���� command�� �ٲ��ذ��̴�.
		GoappCommand command = goappService.selectMember(app_num);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("popup/popup");
		mav.addObject("command", command);
		mav.addObject("go_num", go_num);
		
		return mav;
	}
	
		//==========��û���� ����===========//
	   //��û ���� �� get
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
	   //��û ���� post
	   @RequestMapping(value="/popup/update.do", method=RequestMethod.POST)
	   public String submitUpdate(@ModelAttribute("command") GoappCommand goapp, BindingResult result, 
				Model model) { //HttpServletRequest : ip�ּ� �ޱ�)		   
		   
		   goappService.update(goapp);
		   
			model.addAttribute("result", "success"); 
		   return "popup/updateSuccess";
	   }
	   
	/*   //=========��û ����=========//
	   //��û ���� get
	   @RequestMapping(value="/popup/delete.do", method=RequestMethod.GET)
	   public ModelAndView formDelete(@RequestParam("app_num") int app_num) {
		   
		   GoappCommand command = goappService.selectMember(app_num);
		   ModelAndView mav = new ModelAndView();
		   mav.addObject("command", command);
		   
		   return mav;
	   }*/
	   
	   //��û ���� post
	   @RequestMapping("/popup/delete.do")
	   public String submitDelete(@ModelAttribute("app_num") int app_num, Model model) {
		   
		   if(log.isDebugEnabled()) {
			   log.debug("<<app_num>> : " + app_num);
		   }
		   //��û�� ����
		   goappService.delete(app_num);
		   
		   model.addAttribute("result", "success");
		   return "popup/deleteSuccess";
	   }
	   
	   //�̹��� ���
	   @RequestMapping("/popup/imageView.do")
	   											  //pk�������� �־��ذ��̴�.(app_num)
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
