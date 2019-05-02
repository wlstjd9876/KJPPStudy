package kr.spring.calendar.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.spring.calendar.domain.CalendarDetailCommand;
import kr.spring.calendar.service.CalendarService;

@Controller
public class CalendarDetailController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private CalendarService calendarService;
	
	//�ڹٺ�(Ŀ�ǵ� ��ü) �ʱ�ȭ
	@ModelAttribute("calendarDetailCommand")
	public CalendarDetailCommand initCommand() {
		return new CalendarDetailCommand();
	}
	
	//======���� �� ���======//
	//���� �� ��� ��
	@RequestMapping(value="/calendar/writeDetail.do", method=RequestMethod.GET)
	public String formDetail() {
		return "calendarDetailWriteForm";
	}
	//���� �� ���
	@RequestMapping(value="/calendar/writeDetail.do", method=RequestMethod.POST)
	public String submitDetail(@ModelAttribute("calendarDetailCommand") CalendarDetailCommand calendarDetailCommand) {
		if(log.isDebugEnabled()) {
			log.debug("<<calendarDetailCommand>> : " + calendarDetailCommand);
		}
		calendarService.insertDetail(calendarDetailCommand);
		
		return "redirect:/calendar/view.do?s_num="+calendarDetailCommand.getS_num();
	}
	
	//======���� �� �˾�======//
	//�˾� �� ���� �� 
	@RequestMapping("/calendar/popup/detail.do")
	public String popupDetail(@RequestParam("sd_num") int sd_num, Model model) {
		CalendarDetailCommand detail = calendarService.selectDetail(sd_num);
		model.addAttribute("command", detail);
		
		return "calendarDetailPopupView";
	}
	
	//�˾� �� ���� ����
	@RequestMapping(value="/calendar/popup/updateDetail.do", method=RequestMethod.GET)
	public String popupUpdateForm(@RequestParam("sd_num") int sd_num, @ModelAttribute("scheduleDetailCommand") CalendarDetailCommand scheduleDetailCommand,Model model) {
		CalendarDetailCommand detail = calendarService.selectDetail(sd_num);
		model.addAttribute("command", detail);
		
		return "calendarDetailPopupUpdateForm";
	}
	
	@RequestMapping(value="/calendar/popup/updateDetail.do", method=RequestMethod.POST)
	public String popupUpdate(@RequestParam("sd_num") int sd_num, @ModelAttribute("scheduleDetailCommand") CalendarDetailCommand scheduleDetailCommand ,Model model
							  ) {
		CalendarDetailCommand detail = calendarService.selectDetail(sd_num);
		model.addAttribute("command", detail);
		
		calendarService.updateDetail(scheduleDetailCommand);
		
		return "calendarDetailPopupUpdate";
	}	
	@RequestMapping(value="/calendar/popup/deleteDetail.do", method=RequestMethod.GET)
	public String popupDelete(@RequestParam("sd_num") int sd_num) {
		
		calendarService.deleteDetail(sd_num);
		
		return "calendarDetailPopupDelete";
	}
	//======�˾� ��======//
		//���
		@RequestMapping("/calendar/popup/popupList.do")
		public String popupList(HttpSession session, Model model) {
			
			
			return null;
		}
		//��� ��
		@RequestMapping(value="/calendar/popup/popup.do", method=RequestMethod.GET)
		public String form(/*@RequestParam("sd_num") int sd_num,*/
							HttpSession session, Model model) {
			
			
			
			CalendarDetailCommand scheduleDetailCommand = new CalendarDetailCommand();/*scheduleService.selectDetailSchedule(sd_num);*/
			
			model.addAttribute("scheduleDetailCommand", scheduleDetailCommand);

			if(log.isDebugEnabled()) {
				log.debug("<<memberCommand>> : " + scheduleDetailCommand);
			}		
			
			return "calendar/popup/popup";
		}
	
	
}
