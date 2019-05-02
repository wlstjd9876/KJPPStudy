package kr.spring.advice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.advice.domain.AdviceDetailCommand;
import kr.spring.advice.service.AdviceService;
import kr.spring.calendar.domain.CalendarCommand;
import kr.spring.calendar.service.CalendarService;
import kr.spring.util.PagingUtil;

@Controller
public class AdviceAjaxController {
	private Logger log = Logger.getLogger(this.getClass());
	
	//내 일정 목록 페이징 처리
	private int rowCount = 5;
	private int pageCount = 10;
	
	@Resource
	private AdviceService adviceService;
	@Resource
	private CalendarService calendarService;
	
	/*//조언구해요 일정 등록폼
	@RequestMapping("/adivce/advicePlanWrite.do")
	@ResponseBody
	public Map<String, String> writePlan(AdviceCommand adviceCommand, HttpSession session){
		
		if(log.isDebugEnabled()) {
			log.debug("<<adviceCommand>> : " + adviceCommand);
		}
		
		Map<String, String> map = new HashMap<String, String>();
		
		String user_email = (String)session.getAttribute("user_email");
		if(user_email==null) {
			//로그인 안 됨
			map.put("result", "logout"); //ajax로 전달
		}else {
			//댓글 등록
			adviceService.insert(adviceCommand);
			map.put("result", "success"); //ajax로 전달
		}
		
		return map;
	}*/
	
	//내 일정 가져오기
	@RequestMapping("/advice/adviceMyPlan.do")
	@ResponseBody
	public Map<String, Object> getMyPlanList(@RequestParam(value="pageNum", defaultValue="1") int currentPage){
		if(log.isDebugEnabled()) {
			log.debug("<<currentPage>> : " + currentPage);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		int count = calendarService.selectRowCount(map);
		if(log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}
		
		//페이징 처리
		PagingUtil page = new PagingUtil(currentPage, currentPage, rowCount, pageCount, "adviceMyPlan.do");
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		
		List<CalendarCommand> list = null;
		if(count>0) {
			list = calendarService.selectList(map);
		}
		
		Map<String, Object> mapJson = new HashMap<String, Object>();
		mapJson.put("list", list);
		mapJson.put("pagingHtml", page.getPagingHtml());
		
		return mapJson;
	}
	
	//일정 상세 페이지
	@RequestMapping("/advice/adviceDetailList.do")
	@ResponseBody
	public Map<String, Object> getDetail(@RequestParam("adv_num") int adv_num){
		if(log.isDebugEnabled()) {
			log.debug("<<adv_num>> : " + adv_num);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("adv_num", adv_num);
		
		List<AdviceDetailCommand> list = null;
		list = adviceService.selectDetailList(map);
		
		Map<String, Object> mapJson = new HashMap<String, Object>();
		mapJson.put("list", list);
		
		return mapJson;
	}
	
	//일정 상세 목록
	@RequestMapping("/advice/updateFormDetailList.do")
	@ResponseBody
	public Map<String, Object> modifyFormDetailList(@RequestParam("adv_num") int adv_num){
		if(log.isDebugEnabled()) {
			log.debug("<<adv_num>> : " + adv_num);
		}
	
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("adv_num", adv_num);
		
		List<AdviceDetailCommand> list = adviceService.selectDetailList(map);
		Map<String, Object> mapJson = new HashMap<String, Object>();
		mapJson.put("list", list);
		
		return mapJson;
	}
	
	//일정 상세 폼
	/*@RequestMapping(value="/advice/updateDetail.do", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> modifyDetail(@RequestParam("adv_num") int adv_num){
		if(log.isDebugEnabled()) {
			log.debug("<<adv_num>> : " + adv_num);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("adv_num", adv_num);
		
		List<AdviceDetailCommand> list = adviceService.selectDetailList(map);
		Map<String, Object> mapJson = new HashMap<String, Object>();
		mapJson.put("list", list);
		
		return mapJson;
	}*/
	
	//조언 글에 일정 띄우기
	/*@RequestMapping("/advice/scheduleAll.do")
	@ResponseBody
	public Map<String, Object> getSchedule(){
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<CalendarDetailCommand> list = null;
		list = calendarService.selectDetailList(map);
		
		AdviceDetailCommand detail = new AdviceDetailCommand();
		
		Map<String, Object> mapJson = new HashMap<String, Object>();
		mapJson.put("list", list);
		mapJson.put("day", detail.getAd_day());
		mapJson.put("starttime", detail.getStarttime());
		mapJson.put("endtime", detail.getEndtime());
		
		return mapJson;
	}*/
	
}
