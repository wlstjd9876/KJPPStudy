package kr.spring.advice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.spring.advice.domain.AdviceCommand;
import kr.spring.advice.service.AdviceService;
import kr.spring.gowith.service.GowithService;
import kr.spring.util.PagingUtil;

@Controller
public class AdviceController {
	private Logger log = Logger.getLogger(this.getClass());
	private int rowCount = 8;
	private int pageCount = 10;

	@Resource
	private AdviceService adviceService;
	
	@Resource
	private GowithService gowithService;

	//자바빈(커맨드 객체) 초기화
	@ModelAttribute("command")
	public AdviceCommand initCommand() {
		return new AdviceCommand();
	}
	//=======리스트 글 목록=======//
	@RequestMapping("/advice/adviceList.do")
	public ModelAndView process(@RequestParam(value="pageNum",defaultValue="1") int currentPage, 
			@RequestParam(value="keyfield",defaultValue="") String keyfield,
			@RequestParam(value="keyword",defaultValue="") String keyword) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);

		//총 글의 개수 또는 검색된 글의 개수
		int count = adviceService.selectRowCount(map);

		if(log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}

		PagingUtil page = new PagingUtil(keyfield, keyword, currentPage, count, rowCount, pageCount, "adviceList.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());

		List<AdviceCommand> list = null;
		if(count > 0) {
			list = adviceService.selectList(map);
		}

		ModelAndView mav = new ModelAndView();
		mav.setViewName("adviceList"); //여기서 설정하는 이름은 definition 설정, jsp명이 아님!!
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("pagingHtml", page.getPagingHtml());

		return mav;
	}

	//=======게시판 글 등록=======//
	//등록 폼
	@RequestMapping(value="/advice/adviceWrite.do", method=RequestMethod.GET)
	public String form(HttpSession session, Model model) {
		String email = (String)session.getAttribute("user_email");

		AdviceCommand command = new AdviceCommand();
		command.setEmail(email); //자바빈에 넣어줌

		model.addAttribute("command",command);

		return "adviceWrite"; //definition 설정 호출
	}

	//전송된 데이터 처리
	@RequestMapping(value="/advice/adviceWrite.do", method=RequestMethod.POST)
	public String submit(@ModelAttribute("command") @Valid AdviceCommand adviceCommand, BindingResult result, RedirectAttributes redirect, @RequestParam("email") String email) { //HttpServletRequest : ip주소 받기 위함,
		//RedirectAttributes : 객체는 리다이렉트 시점에 한 번만 사용되는 데이터를 전송
		//				     	   브라우저에 데이터를 전송하지만 URI상에는 보이지 않는 숨겨진
		//				     	  데이터의 형태로 전달

		if(log.isDebugEnabled()) {
			log.debug("<<adviceCommand>> : " + adviceCommand);
		}

		//데이터 유효성 체크
		if(result.hasErrors()) {
			return "adviceWrite";
		}

		//글쓰기
		adviceService.insert(adviceCommand);
		
		//글작성시 회원 점수 올리기
		gowithService.updateScore(email);

		//RedirectAttributes 객체는 리다이렉트 시점에 단 한 번만 사용되는 데이터를 전송
		//브라우저에 데이터를 전송하지만 URL상에는 보이지 않는 숨겨진 데이터의 형태로 전달 
		redirect.addFlashAttribute("result", "success"); //result를 통해서 success를 표시
		
		return "redirect:/advice/adviceList.do";
	}

	//=======게시판 글 상세=======//
	@RequestMapping("/advice/adviceDetail.do")
	public ModelAndView process(@RequestParam("adv_num") int adv_num) {
		if(log.isDebugEnabled()) {
			log.debug("<<adv_num>> : " + adv_num);
		}
  
		//해당 글의 추천수 증가 
		/*adviceService.updateAdv_like(adv_num);*/

		AdviceCommand advice = adviceService.selectAdvice(adv_num);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("adviceDetail");
		mav.addObject("advice", advice);
		
		return mav;
	}
	  
	//수정폼
	@RequestMapping(value="/advice/adviceModify.do",method=RequestMethod.GET)
	public String form(@RequestParam("adv_num") int adv_num, Model model) {
 
		AdviceCommand adviceCommand = adviceService.selectAdvice(adv_num);
		model.addAttribute("command", adviceCommand);
		
		return "adviceModify";
	}
   
	//수정 폼에서 전송된 데이터 처리
	@RequestMapping(value="/advice/adviceModify.do",method=RequestMethod.POST)
	public String submit(@ModelAttribute("command") @Valid AdviceCommand adviceCommand, BindingResult result) {
		if( log.isDebugEnabled()) {
			log.debug("<<adviceCommand>> : "+ adviceCommand );
		}
  
		//데이터 유효성 체크 
		if(result.hasErrors()) {
			return "adviceModify";
		}
  
		//글수정
		adviceService.update(adviceCommand);
		
		return "redirect:/advice/adviceList.do";
	}
   
	//==================글 삭제 ====================//
	@RequestMapping("/advice/adviceDelete.do")
	public String submit(@RequestParam("adv_num") int adv_num, RedirectAttributes redirect) {
		if(log.isDebugEnabled()) {
			log.debug("<<adv_num>> : " + adv_num);
		}
		
		//adv_num,ar_num넘겨
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("adv_num", adv_num);
		
		//글삭제
		adviceService.delete(map);
		//deleteSuccess의 값을 넣어주려고 명시해준다!?
		redirect.addFlashAttribute("result", "success");
		return "redirect:/advice/adviceList.do";
	}

   /*//==============글쓰기 이미지 업로드=====================//
   @RequestMapping("/board/imageUploader.do")
   public void uploadImage(MultipartFile file, HttpServletRequest request, HttpServletResponse response, HttpSession session)throws Exception{
	   response.setContentType("trxt/html;charset=utf-8");
	   PrintWriter out = response.getWriter();
	   
	   //업로드할 폴더 경로 (절대경로 ->session 사용)
	   String realFolder = session.getServletContext().getRealPath("/resources/image_upload");
	   
	   //업로드할 파일 이름
	   String org_filename = file.getOriginalFilename();
	   String str_filename = System.currentTimeMillis() + org_filename;
	   
	   if(log.isDebugEnabled()) {
		   log.debug("<<원본 파일명>> " + org_filename);
		   log.debug("<<저장할 파일명>> " + str_filename);
	   }
	   
	   String filepath = realFolder + "\\" + str_filename;
	   
	   File f = new File(filepath);
	   
	   if(log.isDebugEnabled()) {
		   log.debug("<<파일경로>> " + filepath);
	   }
	   
	   //지정한 경로에 파일을 저장
	   file.transferTo(f);
	   out.println(request.getContextPath()+"/resources/image_upload/" + str_filename);
	   out.close();
   }*/

	
}
