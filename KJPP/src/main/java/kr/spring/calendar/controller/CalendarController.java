package kr.spring.calendar.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.calendar.domain.CalendarCommand;
import kr.spring.calendar.domain.CalendarDetailCommand;
import kr.spring.calendar.service.CalendarService;
import kr.spring.fav.domain.FavoriteCommand;
import kr.spring.fav.service.FavoriteService;
import kr.spring.util.PagingUtil;

@Controller
public class CalendarController {
	private Logger log = Logger.getLogger(this.getClass());
	//����¡ ó��
	private int rowCount = 5;
	private int pageCount = 10;
	@Resource
	private FavoriteService favoriteService;
	
	@Resource
	private CalendarService calendarService;
	
	/*@ModelAttribute("command")
	public FavoriteCommand initCommand1() {
		return new FavoriteCommand();
	}*/

	//�ڹٺ�(Ŀ�ǵ� ��ü) �ʱ�ȭ
	@ModelAttribute("calendarCommand")
	public CalendarCommand initCommand() {
		return new CalendarCommand();
	}
	
	//======���� ���======//
	//���� ��� �޷� ��
	@RequestMapping("/calendar/registerPlan.do")
	public String registerForm(@RequestParam(value="pageNum", defaultValue="1") int currentPage, 
			@RequestParam(value="keyfield", defaultValue="") String keyfield, @RequestParam(value="keyword", defaultValue="") String keyword,HttpSession session, Model model) {
		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
		String email = (String)session.getAttribute("user_email");
		String mydate = "";
		
		CalendarCommand command = new CalendarCommand();
		command.setEmail(email);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email", email);
		List<CalendarCommand> list = calendarService.selectCal(map);
		if(list!=null) {
			mydate = list.iterator().next().getS_startdate();
		}else {
			mydate = form.format(new Date());
		}
		/*����Ʈ*/
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		
		//�� ���� ���� �Ǵ� �˻��� ���� ����
		int count = calendarService.selectRowCount(map);
		
		System.out.println("ī��Ʈ @@@@ : " + count);
		if(log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}
		
		//����¡ ó��
		PagingUtil page = new PagingUtil(keyfield, keyword, currentPage, count, rowCount, pageCount, "registerPlan.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		System.out.println("@@@@@@@@@@@@End : " + page.getEndCount());
		
		List<CalendarCommand> list2 = null;
		if(count > 0) {
			list2 = calendarService.selectList(map);
		}
		
		CalendarCommand calendar = new CalendarCommand();
		
		model.addAttribute("count", count);
		System.out.println("@@@@@@@@@@@? :" + count);
		model.addAttribute("list2", list2);
		System.out.println("@@@@@@@@@@@" + list2);
		model.addAttribute("finish", calendarService.selectCalendar(calendar.getS_finish()));
		model.addAttribute("pagingHtml", page.getPagingHtml());
		
		model.addAttribute("command", command);
		model.addAttribute("mydate", mydate);
		
		return "calendarRegisterForm";
	}
	//���� ��� �Ϸ� ��
	@RequestMapping(value="/calendar/finish.do", method=RequestMethod.GET)
	public String registerSubmit(HttpSession session, Model model) {
		String email = (String)session.getAttribute("user_email");
		
		CalendarCommand command = new CalendarCommand();
		command.setEmail(email);
			
		model.addAttribute("command", command);
		
		return "calendarRegisterDetailForm";
	}
	//���� ��� �Ϸ�
	@RequestMapping(value="/calendar/finish.do", method=RequestMethod.POST)
	public String register(@ModelAttribute("command") CalendarCommand calendarCommand) {
		calendarService.insert(calendarCommand);
		
		return "redirect:/calendar/registerPlan.do";
	}
	
	//======���� ���======//
	@RequestMapping("/calendar/list.do")
	public ModelAndView process(@RequestParam(value="pageNum", defaultValue="1") int currentPage, 
			@RequestParam(value="keyfield", defaultValue="") String keyfield, @RequestParam(value="keyword", defaultValue="") String keyword) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		
		//�� ���� ���� �Ǵ� �˻��� ���� ����
		int count = calendarService.selectRowCount(map);
		
		System.out.println("ī��Ʈ @@@@ : " + count);
		if(log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}
		
		//����¡ ó��
		PagingUtil page = new PagingUtil(keyfield, keyword, currentPage, count, rowCount, pageCount, "list.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		System.out.println("@@@@@@@@@@@@End : " + page.getEndCount());
		
		List<CalendarCommand> list = null;
		if(count > 0) {
			list = calendarService.selectList(map);
		}
		
		CalendarCommand calendar = new CalendarCommand();
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("calendarList");
		mav.addObject("count", count);
		System.out.println("@@@@@@@@@@@? :" + count);
		mav.addObject("list", list);
		System.out.println("@@@@@@@@@@@" + list);
		mav.addObject("finish", calendarService.selectCalendar(calendar.getS_finish()));
		mav.addObject("pagingHtml", page.getPagingHtml());
		
		return mav;
	}
	
	//======���� �� ������======//
	//���� �� ������
	@RequestMapping("/calendar/view.do")
	public String process(@RequestParam("s_num") int s_num, Model model) {
		CalendarCommand command = calendarService.selectCalendar(s_num);
		//form:form�� view.do�� calendarDetailCommand �Ѱ��� ���Ἥ view.do�� �� ������ ������Ѵ�.
		CalendarDetailCommand calendarDetailCommand = new CalendarDetailCommand();

		//���� �޼���
		model.addAttribute("command", command);
		//���ο� �޼��� ����Ѱ�
		model.addAttribute("calendarDetailCommand", calendarDetailCommand);

		model.addAttribute("s_num",s_num);
		
		return "calendarDetail";
	}
	
	//�̹��� ���
	@RequestMapping("/calendar/imageView.do")
	public ModelAndView viewImg(@RequestParam("s_num") int s_num, @RequestParam("type") int type) {
		CalendarCommand calendar = calendarService.selectCalendar(s_num);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		if(type == 1) {
			mav.addObject("imageFile", calendar.getS_photo1());
		}else if(type == 2) {
			mav.addObject("imageFile", calendar.getS_photo2());
		}else if(type == 3) {
			mav.addObject("imageFile", calendar.getS_photo3());
		}else if(type == 4) {
			mav.addObject("imageFile", calendar.getS_photo4());
		}else if(type == 5) {
			mav.addObject("imageFile", calendar.getS_photo5());
		}else if(type == 6) {
			mav.addObject("imageFile", calendar.getS_photo6());
		}else if(type == 7) {
			mav.addObject("imageFile", calendar.getS_photo7());
		}else if(type == 8) {
			mav.addObject("imageFile", calendar.getS_photo8());
		}else if(type == 9) {
			mav.addObject("imageFile", calendar.getS_photo9());
		}else if(type == 10) {
			mav.addObject("imageFile", calendar.getS_photo10());
		}
		
		mav.addObject("filename", "image.jpg");
		
		return mav;
	}
	
	//======���� ����======//
	//���� ���� ��
	@RequestMapping(value="/calendar/update.do", method=RequestMethod.GET)
	public String updateForm() {
		return "";
	}
	//���� ����
	@RequestMapping(value="/calendar/update.do", method=RequestMethod.POST)
	public String update() {
		return "";
	}
	
	//======���� ����======//
	@RequestMapping("/calendar/delete.do")
	public String delete(@RequestParam("s_num") int s_num) {
		if(log.isDebugEnabled()) {
			log.debug("<<s_num>> : " + s_num);
		}
		
		calendarService.delete(s_num);
		
		return "redirect:/calendar/list.do";
	}
	
	/*�˾�*/
	@RequestMapping(value="/calendar/listPopup.do", method=RequestMethod.GET)
	public String callistPopup(@RequestParam(value="pageNum", defaultValue="1") int currentPage, 
			@RequestParam(value="keyfield", defaultValue="") String keyfield, @RequestParam(value="keyword", defaultValue="") String keyword,HttpSession session, Model model) {
		//footer�� header�� ¥���� �������� ���� jsp�� ȣ��������ؼ� �̷��� ����ؾ��Ѵ�.
		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
		String email = (String)session.getAttribute("user_email");
		String mydate = "";
		
		CalendarCommand command = new CalendarCommand();
		command.setEmail(email);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email", email);
		List<CalendarCommand> list = calendarService.selectCal(map);
		if(list!=null) {
			mydate = list.iterator().next().getS_startdate();
		}else {
			mydate = form.format(new Date());
		}
		/*����Ʈ*/
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		
		//�� ���� ���� �Ǵ� �˻��� ���� ����
		int count = calendarService.selectRowCount(map);
		
		System.out.println("ī��Ʈ @@@@ : " + count);
		if(log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}
		
		//����¡ ó��
		PagingUtil page = new PagingUtil(keyfield, keyword, currentPage, count, rowCount, pageCount, "registerPlan.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		System.out.println("@@@@@@@@@@@@End : " + page.getEndCount());
		
		List<CalendarCommand> list2 = null;
		if(count > 0) {
			list2 = calendarService.selectList(map);
		}
		
		CalendarCommand calendar = new CalendarCommand();
		
		model.addAttribute("count", count);
		System.out.println("@@@@@@@@@@@? :" + count);
		model.addAttribute("list2", list2);
		System.out.println("@@@@@@@@@@@" + list2);
		model.addAttribute("finish", calendarService.selectCalendar(calendar.getS_finish()));
		model.addAttribute("pagingHtml", page.getPagingHtml());
		
		model.addAttribute("command", command);
		model.addAttribute("mydate", mydate);
		
		return "gowith/popupregisterForm";
	}
	
	@RequestMapping(value="/calendar/finPopup.do", method=RequestMethod.GET)
	public String finlistPopup(@RequestParam("s_startdate") String s_startdate, @RequestParam("s_enddate") String s_enddate,  @RequestParam("s_num") int s_num, Model model) {
		
		model.addAttribute("s_startdate",s_startdate);
		model.addAttribute("s_enddate",s_enddate);
		model.addAttribute("s_num",s_num);
		return "gowith/popupregister";
	}
	
	/*�˾�*/
	@RequestMapping(value="/calendar/sharelisttPopup.do", method=RequestMethod.GET)
	public String shareListPopup(@RequestParam(value="pageNum", defaultValue="1") int currentPage, 
			@RequestParam(value="keyfield", defaultValue="") String keyfield, @RequestParam(value="keyword", defaultValue="") String keyword,HttpSession session, Model model) {
		//footer�� header�� ¥���� �������� ���� jsp�� ȣ��������ؼ� �̷��� ����ؾ��Ѵ�.
		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
		String email = (String)session.getAttribute("user_email");
		String mydate = "";
		
		CalendarCommand command = new CalendarCommand();
		command.setEmail(email);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email", email);
		List<CalendarCommand> list = calendarService.selectCal(map);
		if(list!=null) {
			mydate = list.iterator().next().getS_startdate();
		}else {
			mydate = form.format(new Date());
		}
		/*����Ʈ*/
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		
		//�� ���� ���� �Ǵ� �˻��� ���� ����
		int count = calendarService.selectRowCount(map);
		
		System.out.println("ī��Ʈ @@@@ : " + count);
		if(log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}
		
		//����¡ ó��
		PagingUtil page = new PagingUtil(keyfield, keyword, currentPage, count, rowCount, pageCount, "registerPlan.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		System.out.println("@@@@@@@@@@@@End : " + page.getEndCount());
		
		List<CalendarCommand> list2 = null;
		if(count > 0) {
			list2 = calendarService.selectList(map);
		}
		
		CalendarCommand calendar = new CalendarCommand();
		
		model.addAttribute("count", count);
		System.out.println("@@@@@@@@@@@? :" + count);
		model.addAttribute("list2", list2);
		System.out.println("@@@@@@@@@@@" + list2);
		model.addAttribute("finish", calendarService.selectCalendar(calendar.getS_finish()));
		model.addAttribute("pagingHtml", page.getPagingHtml());
		
		model.addAttribute("command", command);
		model.addAttribute("mydate", mydate);
		
		return "share/popupsharew";
	}
	
	@RequestMapping(value="/calendar/sfinPopup.do", method=RequestMethod.GET)
	public String sfinlistPopup(@RequestParam("s_startdate") String s_startdate, @RequestParam("s_enddate") String s_enddate,  @RequestParam("s_num") int s_num, Model model) {
		
		model.addAttribute("s_startdate",s_startdate);
		model.addAttribute("s_enddate",s_enddate);
		model.addAttribute("s_num",s_num);
		return "share/popupshare";
	}
}
