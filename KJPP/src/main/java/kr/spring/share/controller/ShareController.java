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
   
   //=======게시판 글 등록=======//
   //등록 폼
   @RequestMapping(value="/share/write.do", method=RequestMethod.GET)
   public String form(HttpSession session, Model model) {
      String email = (String)session.getAttribute("user_email");
      
      ShareCommand command = new ShareCommand();
      command.setEmail(email); //자바빈에 넣어줌
      
      model.addAttribute("command",command);
         
      return "shareWrite"; //definition 설정 호출
   }
   
   //전송된 데이터 처리
   @RequestMapping(value="/share/write.do", method=RequestMethod.POST)
   public String submit(@ModelAttribute("command") @Valid ShareCommand shareCommand, BindingResult result, @RequestParam("email") String email,
                 RedirectAttributes redirect) {// RedirectAttributes : 리다이렉트시 단 한 번만 사용되는 데이터를 전송
      
      if(log.isDebugEnabled()) {
         log.debug("<<shareCommand>> : " + shareCommand);
      }
      
      //데이터 유효성 체크
      if(result.hasErrors()) {
         return "shareWrite";
      }   
      
      //글쓰기
      shareService.insert(shareCommand);
      
      //글 등록시 회원 점수 올리기
      gowithService.updateScore(email);
      
      //RedirectAttributes 객체는 리다이렉트 시점에 단 한 번만 사용되는 데이터를 전송
      //브라우저에 데이터를 전송하지만 URL상에는 보이지 않는 숨겨진 데이터의 형태로 전달 
      redirect.addFlashAttribute("result", "success"); //result를 통해서 success를 표시
      
      return "redirect:/share/list.do";
   }
   
   //=======게시판 글 목록=======//
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
      
      //총 글의 개수 또는 검색된 글의 개수
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
      mav.setViewName("shareList"); //여기서 설정하는 이름은 definition 설정, jsp명이 아님!!
      mav.addObject("count", count);
      mav.addObject("list", list);
      mav.addObject("pagingHtml", page.getPagingHtml());
      
      return mav;
   }
   
   //썸네일 이미지 호출
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
   
   //=======게시판 글 상세=======//
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
                        //view name, 속성명, 속성값
      return mav;
   }
   
  
 //이미지 출력----------------------------------------------------
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
   /*//이미지 출력
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
   
 //이미지 출력----------------------------------------------------
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
   
   //==================게시판 글 수정 ====================//
   //수정폼
   @RequestMapping(value="/share/update.do",method=RequestMethod.GET)
   public String form(@RequestParam("num") int num, Model model) {
	 
	   ShareCommand shareCommand = shareService.selectShare(num);
	   model.addAttribute("command",shareCommand);
	   return "shareModify";
   }
   //수정 폼에서 전송된 데이터 처리
   @RequestMapping(value="/share/update.do",method=RequestMethod.POST)
   public String submit(@ModelAttribute("command") @Valid ShareCommand shareCommand, BindingResult result, HttpServletRequest request) {
	  if( log.isDebugEnabled()) {
		  log.debug("<<shareCommand>> : "+ shareCommand );
	  }
	  
	  //데이터 유효성 체크 
	  if(result.hasErrors()) {
		  return "shareModify";
	  }
	  
	  //글수정
	  shareService.update(shareCommand);
	   return "redirect:/share/list.do";
   }
   
   //==================게시판 글 삭제 ====================//
   @RequestMapping("/share/delete.do")
   public String submit(@RequestParam("num") int num) {
	   if(log.isDebugEnabled()) {
		   log.debug("<<num>> : " + num);
	   }
	   //글삭제
	   shareService.delete(num);
	   return "redirect:/share/list.do";
   }
   

   /*//==============글쓰기 이미지 업로드=====================//
   @RequestMapping("/share/imageUploader.do")
   public void uploadImage(MultipartFile file, HttpServletRequest request, HttpServletResponse response, HttpSession session)throws Exception{
	   response.setContentType("trxt/html;charset=utf-8");
	   PrintWriter out = response.getWriter();
	   
	   //업로드할 폴더 경로 (절대경로 ->session 사용)
	   String realFolder = session.getServletContext().getRealPath("/resources/image_upload");
	   
	   
	   String filepath = realFolder + "\\" ;
	   
	   File f = new File(filepath);
	   
	   if(log.isDebugEnabled()) {
		   log.debug("<<파일경로>> " + filepath);
	   }
	   
	   //지정한 경로에 파일을 저장
	   file.transferTo(f);
	   out.println(request.getContextPath()+"/resources/image_upload/");
	   out.close();
   }*/

}