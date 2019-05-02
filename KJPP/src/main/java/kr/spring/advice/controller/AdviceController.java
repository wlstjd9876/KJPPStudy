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

	//�ڹٺ�(Ŀ�ǵ� ��ü) �ʱ�ȭ
	@ModelAttribute("command")
	public AdviceCommand initCommand() {
		return new AdviceCommand();
	}
	//=======����Ʈ �� ���=======//
	@RequestMapping("/advice/adviceList.do")
	public ModelAndView process(@RequestParam(value="pageNum",defaultValue="1") int currentPage, 
			@RequestParam(value="keyfield",defaultValue="") String keyfield,
			@RequestParam(value="keyword",defaultValue="") String keyword) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);

		//�� ���� ���� �Ǵ� �˻��� ���� ����
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
		mav.setViewName("adviceList"); //���⼭ �����ϴ� �̸��� definition ����, jsp���� �ƴ�!!
		mav.addObject("count", count);
		mav.addObject("list", list);
		mav.addObject("pagingHtml", page.getPagingHtml());

		return mav;
	}

	//=======�Խ��� �� ���=======//
	//��� ��
	@RequestMapping(value="/advice/adviceWrite.do", method=RequestMethod.GET)
	public String form(HttpSession session, Model model) {
		String email = (String)session.getAttribute("user_email");

		AdviceCommand command = new AdviceCommand();
		command.setEmail(email); //�ڹٺ� �־���

		model.addAttribute("command",command);

		return "adviceWrite"; //definition ���� ȣ��
	}

	//���۵� ������ ó��
	@RequestMapping(value="/advice/adviceWrite.do", method=RequestMethod.POST)
	public String submit(@ModelAttribute("command") @Valid AdviceCommand adviceCommand, BindingResult result, RedirectAttributes redirect, @RequestParam("email") String email) { //HttpServletRequest : ip�ּ� �ޱ� ����,
		//RedirectAttributes : ��ü�� �����̷�Ʈ ������ �� ���� ���Ǵ� �����͸� ����
		//				     	   �������� �����͸� ���������� URI�󿡴� ������ �ʴ� ������
		//				     	  �������� ���·� ����

		if(log.isDebugEnabled()) {
			log.debug("<<adviceCommand>> : " + adviceCommand);
		}

		//������ ��ȿ�� üũ
		if(result.hasErrors()) {
			return "adviceWrite";
		}

		//�۾���
		adviceService.insert(adviceCommand);
		
		//���ۼ��� ȸ�� ���� �ø���
		gowithService.updateScore(email);

		//RedirectAttributes ��ü�� �����̷�Ʈ ������ �� �� ���� ���Ǵ� �����͸� ����
		//�������� �����͸� ���������� URL�󿡴� ������ �ʴ� ������ �������� ���·� ���� 
		redirect.addFlashAttribute("result", "success"); //result�� ���ؼ� success�� ǥ��
		
		return "redirect:/advice/adviceList.do";
	}

	//=======�Խ��� �� ��=======//
	@RequestMapping("/advice/adviceDetail.do")
	public ModelAndView process(@RequestParam("adv_num") int adv_num) {
		if(log.isDebugEnabled()) {
			log.debug("<<adv_num>> : " + adv_num);
		}
  
		//�ش� ���� ��õ�� ���� 
		/*adviceService.updateAdv_like(adv_num);*/

		AdviceCommand advice = adviceService.selectAdvice(adv_num);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("adviceDetail");
		mav.addObject("advice", advice);
		
		return mav;
	}
	  
	//������
	@RequestMapping(value="/advice/adviceModify.do",method=RequestMethod.GET)
	public String form(@RequestParam("adv_num") int adv_num, Model model) {
 
		AdviceCommand adviceCommand = adviceService.selectAdvice(adv_num);
		model.addAttribute("command", adviceCommand);
		
		return "adviceModify";
	}
   
	//���� ������ ���۵� ������ ó��
	@RequestMapping(value="/advice/adviceModify.do",method=RequestMethod.POST)
	public String submit(@ModelAttribute("command") @Valid AdviceCommand adviceCommand, BindingResult result) {
		if( log.isDebugEnabled()) {
			log.debug("<<adviceCommand>> : "+ adviceCommand );
		}
  
		//������ ��ȿ�� üũ 
		if(result.hasErrors()) {
			return "adviceModify";
		}
  
		//�ۼ���
		adviceService.update(adviceCommand);
		
		return "redirect:/advice/adviceList.do";
	}
   
	//==================�� ���� ====================//
	@RequestMapping("/advice/adviceDelete.do")
	public String submit(@RequestParam("adv_num") int adv_num, RedirectAttributes redirect) {
		if(log.isDebugEnabled()) {
			log.debug("<<adv_num>> : " + adv_num);
		}
		
		//adv_num,ar_num�Ѱ�
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("adv_num", adv_num);
		
		//�ۻ���
		adviceService.delete(map);
		//deleteSuccess�� ���� �־��ַ��� ������ش�!?
		redirect.addFlashAttribute("result", "success");
		return "redirect:/advice/adviceList.do";
	}

   /*//==============�۾��� �̹��� ���ε�=====================//
   @RequestMapping("/board/imageUploader.do")
   public void uploadImage(MultipartFile file, HttpServletRequest request, HttpServletResponse response, HttpSession session)throws Exception{
	   response.setContentType("trxt/html;charset=utf-8");
	   PrintWriter out = response.getWriter();
	   
	   //���ε��� ���� ��� (������ ->session ���)
	   String realFolder = session.getServletContext().getRealPath("/resources/image_upload");
	   
	   //���ε��� ���� �̸�
	   String org_filename = file.getOriginalFilename();
	   String str_filename = System.currentTimeMillis() + org_filename;
	   
	   if(log.isDebugEnabled()) {
		   log.debug("<<���� ���ϸ�>> " + org_filename);
		   log.debug("<<������ ���ϸ�>> " + str_filename);
	   }
	   
	   String filepath = realFolder + "\\" + str_filename;
	   
	   File f = new File(filepath);
	   
	   if(log.isDebugEnabled()) {
		   log.debug("<<���ϰ��>> " + filepath);
	   }
	   
	   //������ ��ο� ������ ����
	   file.transferTo(f);
	   out.println(request.getContextPath()+"/resources/image_upload/" + str_filename);
	   out.close();
   }*/

	
}
