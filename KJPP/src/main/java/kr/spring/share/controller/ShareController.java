package kr.spring.share.controller;

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

import kr.spring.member.domain.MemberCommand;
import kr.spring.calendar.domain.CalendarCommand;
import kr.spring.calendar.service.CalendarService;
import kr.spring.gowith.service.GowithService;
import kr.spring.share.domain.ShareCommand;
import kr.spring.share.service.ShareService;
import kr.spring.util.PagingUtil;

@Controller
public class ShareController {
   private Logger log = Logger.getLogger(this.getClass());
   private int rowCount = 3; 
   private int pageCount = 10; 
   
   @Resource
   private ShareService shareService;
   
   @Resource
   private GowithService gowithService;
   
   @Resource
   private CalendarService calendarservice;
   
   //=======�Խ��� �� ���=======//
   //��� ��
   @RequestMapping(value="/share/write.do", method=RequestMethod.GET)
   public String form(HttpSession session, Model model) {
      String email = (String)session.getAttribute("user_email");
      
      ShareCommand command = new ShareCommand();
      command.setEmail(email); //�ڹٺ� �־���
      
      model.addAttribute("command",command);
         
      return "shareWrite"; //definition ���� ȣ��
   }
   
   //���۵� ������ ó��
   @RequestMapping(value="/share/write.do", method=RequestMethod.POST)
   public String submit(@ModelAttribute("command") @Valid ShareCommand shareCommand, BindingResult result, @RequestParam("email") String email,
                 RedirectAttributes redirect) {// RedirectAttributes : �����̷�Ʈ�� �� �� ���� ���Ǵ� �����͸� ����
      
      if(log.isDebugEnabled()) {
         log.debug("<<shareCommand>> : " + shareCommand);
      }
      
      //������ ��ȿ�� üũ
      if(result.hasErrors()) {
         return "shareWrite";
      }   
      
      //�۾���
      shareService.insert(shareCommand);
      
      //�� ��Ͻ� ȸ�� ���� �ø���
      gowithService.updateScore(email);
      
      //RedirectAttributes ��ü�� �����̷�Ʈ ������ �� �� ���� ���Ǵ� �����͸� ����
      //�������� �����͸� ���������� URL�󿡴� ������ �ʴ� ������ �������� ���·� ���� 
      redirect.addFlashAttribute("result", "success"); //result�� ���ؼ� success�� ǥ��
      
      return "redirect:/share/list.do";
   }
   
   //=======�Խ��� �� ���=======//
   @RequestMapping("/share/list.do")
   public ModelAndView process(
         @RequestParam(value="pageNum",defaultValue="1")
         int currentPage,
         @RequestParam(value="keyfield",defaultValue="")
         String keyfield,
         @RequestParam(value="keyword",defaultValue="")
         String keyword) {
      
      Map<String, Object> map = new HashMap<String, Object>();
      map.put("keyfield", keyfield);
      map.put("keyword", keyword);
      
      //�� ���� ���� �Ǵ� �˻��� ���� ����
      int count = shareService.selectRowCount(map);
      
      if(log.isDebugEnabled()) {
         log.debug("<<count>> : " + count);
      }
      
      PagingUtil page = new PagingUtil(keyfield, keyword, currentPage, count, rowCount, pageCount, "list.do");
      map.put("start", page.getStartCount());
      map.put("end", page.getEndCount());
      
      List<ShareCommand> list = null;
      if(count > 0) {
         list = shareService.selectList(map);
      }
      
      ModelAndView mav = new ModelAndView();
      mav.setViewName("shareList"); //���⼭ �����ϴ� �̸��� definition ����, jsp���� �ƴ�!!
      mav.addObject("count", count);
      mav.addObject("list", list);
      mav.addObject("pagingHtml", page.getPagingHtml());
      
      return mav;
   }
   
   //����� �̹��� ȣ��
 	@RequestMapping("/share/imageView.do")
 	public ModelAndView viewImage(@RequestParam("num") int num) {
 		
 		ShareCommand share = shareService.selectShare(num);
 	   
 	     if(log.isDebugEnabled()) {
 	        log.debug("<<ShareCommand>> :" + share);
 	     }
 		
 		ModelAndView mav = new ModelAndView();
 		mav.setViewName("imageView");
 		mav.addObject("imageFile", share.getThumb());
 		mav.addObject("filename", "image.jpg");
 		
 		return mav;
 	}   
   
   //=======�Խ��� �� ��=======//
   @RequestMapping(value="/share/shareDetail.do", method=RequestMethod.GET)
   public ModelAndView process(@RequestParam("num") int num) {
      
      if(log.isDebugEnabled()) {
         log.debug("<<num>> : " + num);
      }
      
      ShareCommand share = shareService.selectShare(num);
      ModelAndView mav = new ModelAndView();
      mav.setViewName("shareDetail");
      mav.addObject("startdate", calendarservice.selectCalendar(share.getS_num()).getS_startdate());
      mav.addObject("share",share);
                        //view name, �Ӽ���, �Ӽ���
      return mav;
   }
   
  
 //�̹��� ���----------------------------------------------------
   @RequestMapping("/share/imageView1.do")
   public ModelAndView viewImage1(@RequestParam("num") int num, @RequestParam("photo_type") int photo_type) {
      
      ShareCommand share = shareService.selectShare(num);
   
      if(log.isDebugEnabled()) {
         log.debug("<<ShareCommand>> :" + share);
      }
      
      ModelAndView mav = new ModelAndView();
      mav.setViewName("imageView");
      if(photo_type==1) {
         mav.addObject("imageFile", share.getThumb());
      }else if(photo_type==2) {
         mav.addObject("imageFile", share.getPhoto2());
      }else if(photo_type==3) {
         mav.addObject("imageFile", share.getPhoto3());
      }
      mav.addObject("filename", "image.jpg");
      
      return mav;
      
   }
   /*//�̹��� ���
   @RequestMapping("/share/imageView2.do")
   public ModelAndView viewImage2(@RequestParam("num") int num) {
	   
	   ShareCommand share = shareService.selectShare(num);
	   
	   ModelAndView mav = new ModelAndView();
	   mav.setViewName("imageView");
	   mav.addObject("imageFile", share.getThumb());
	   mav.addObject("imageFile", share.getPhoto2());
	   mav.addObject("imageFile", share.getPhoto3());
	   
	   return mav;
   }*/
   
 //�̹��� ���----------------------------------------------------
 	/*@RequestMapping("/share/imageView1.do")
 	public ModelAndView viewImage1(@RequestParam("num") int num, @RequestParam("photo_type") int photo_type) {
 		
 		ShareCommand share = shareService.selectShare(num);
 	
 		if(log.isDebugEnabled()) {
 			log.debug("<<ShareCommand>> :" + share);
 		}
 		
 		ModelAndView mav = new ModelAndView();
 		mav.setViewName("imageView");
 		if(photo_type==1) {
 			mav.addObject("imageFile", share.getThumb());
 		}else if(photo_type==2) {
 			mav.addObject("imageFile", share.getPhoto2());
 		}else if(photo_type==3) {
 			mav.addObject("imageFile", share.getPhoto3());
 		}
 		mav.addObject("filename", "image.jpg");
 		
 		return mav;
 		
 	}*/
   
   //==================�Խ��� �� ���� ====================//
   //������
   @RequestMapping(value="/share/update.do",method=RequestMethod.GET)
   public String form(@RequestParam("num") int num, Model model) {
	 
	   ShareCommand shareCommand = shareService.selectShare(num);
	   model.addAttribute("command",shareCommand);
	   return "shareModify";
   }
   //���� ������ ���۵� ������ ó��
   @RequestMapping(value="/share/update.do",method=RequestMethod.POST)
   public String submit(@ModelAttribute("command") @Valid ShareCommand shareCommand, BindingResult result, HttpServletRequest request) {
	  if( log.isDebugEnabled()) {
		  log.debug("<<shareCommand>> : "+ shareCommand );
	  }
	  
	  //������ ��ȿ�� üũ 
	  if(result.hasErrors()) {
		  return "shareModify";
	  }
	  
	  //�ۼ���
	  shareService.update(shareCommand);
	   return "redirect:/share/list.do";
   }
   
   //==================�Խ��� �� ���� ====================//
   @RequestMapping("/share/delete.do")
   public String submit(@RequestParam("num") int num) {
	   if(log.isDebugEnabled()) {
		   log.debug("<<num>> : " + num);
	   }
	   //�ۻ���
	   shareService.delete(num);
	   return "redirect:/share/list.do";
   }
   

   /*//==============�۾��� �̹��� ���ε�=====================//
   @RequestMapping("/share/imageUploader.do")
   public void uploadImage(MultipartFile file, HttpServletRequest request, HttpServletResponse response, HttpSession session)throws Exception{
	   response.setContentType("trxt/html;charset=utf-8");
	   PrintWriter out = response.getWriter();
	   
	   //���ε��� ���� ��� (������ ->session ���)
	   String realFolder = session.getServletContext().getRealPath("/resources/image_upload");
	   
	   
	   String filepath = realFolder + "\\" ;
	   
	   File f = new File(filepath);
	   
	   if(log.isDebugEnabled()) {
		   log.debug("<<���ϰ��>> " + filepath);
	   }
	   
	   //������ ��ο� ������ ����
	   file.transferTo(f);
	   out.println(request.getContextPath()+"/resources/image_upload/");
	   out.close();
   }*/

}