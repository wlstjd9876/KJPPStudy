package kr.spring.location.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.location.domain.ChartCommand;
import kr.spring.location.service.ChartService;

@Controller
public class ChartController {
	
	@Resource
	private ChartService chartService;
	
	@RequestMapping(value="/data/chart.do")
	public String form(){
		
		return "chart";
				
	}
	
	//���� ��Ÿ��
	@RequestMapping("/data/styleChart.do")
	@ResponseBody//ajax ���� ��� �ʿ�!!!
	public Map<String, Object> style() {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<ChartCommand> list = chartService.style();
		
		System.out.println("@@@@@@@@@@@" + list.iterator().next().getCountstyle());
				
		map.put("styleList", list);
		
		return map;
	}
	
	//���� Ÿ��
	@RequestMapping("/data/typeChart.do")
	@ResponseBody //ajax ���� ��� �ʿ�!!!
	public Map<String, Object> type() {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<ChartCommand> list = chartService.type();
		
		System.out.println("@@@@@@@@@@@" + list.iterator().next().getCount());
				
		map.put("typeList", list);
		
		return map;
	}
	
	//���� �ñ�
	@RequestMapping("/data/monthChart.do")
	@ResponseBody //ajax ���� ��� �ʿ�!!!
	public Map<String, Object> month() {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<ChartCommand> list = chartService.month();
		
		System.out.println("@@@@@@@@@@@" + list.iterator().next().getCountmonth());
				
		map.put("monthList", list);
		
		return map;
	}
	
	
}
