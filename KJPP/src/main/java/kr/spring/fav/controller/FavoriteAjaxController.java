package kr.spring.fav.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.fav.domain.FavoriteCommand;
import kr.spring.fav.service.FavoriteService;
import kr.spring.member.domain.MemberCommand;
import kr.spring.member.service.MemberService;
import kr.spring.util.PagingUtil;

@Controller
public class FavoriteAjaxController {
	
	private Logger log = Logger.getLogger(this.getClass()); //Logger가 인식이 안되는 경우 pom.xml에 log4j 명시
	
	private int rowCount = 4;
	private int pageCount = 10;
	
	@Resource
	private FavoriteService favoriteService;

	@Resource
	private MemberService memberService;
	//자바빈(커맨드 객체) 초기화
	@ModelAttribute("command")
	public FavoriteCommand initCommand() {
		return new FavoriteCommand();
	}
	
	//장소 모달창 (검색+페이징)
	@RequestMapping(value="/calendar/searchPlace.do")
	@ResponseBody
	public Map<String, Object> searchPlace(
			@RequestParam(value="pageNo",defaultValue="1")
			int currentPage,
			@RequestParam(value="keyfield",defaultValue="")
			String keyfield,
			@RequestParam(value="keyword",defaultValue="")
			String keyword) {
		
		if(log.isDebugEnabled()) {
			log.debug("<<currentPage>> : " + currentPage);
			log.debug("<<keyfield>> : " + keyfield);
			log.debug("<<keyword>> : " + keyword);
		}

		//즐겨찾기 페이징 처리 목록
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("keyfield", keyfield);
		map.put("keyword", keyword);
		
		int count1 = favoriteService.selectRowCount1(map);

		if(log.isDebugEnabled()) {
			log.debug("<<count1>> : " + count1);
		}
		
		PagingUtil page1 = new PagingUtil(keyfield,keyword,currentPage, count1, rowCount, pageCount, null); //마지막 null은 호출하는 url인데, ajax 처리 하므로 넘길 url이 없으므로 null로 명시
		map.put("start", page1.getStartCount());
		map.put("end", page1.getEndCount());
		//즐겨찾기 메인 목록
		List<FavoriteCommand> list1;
		
		if(count1>0) {
		list1= favoriteService.selectList1(map);
			System.out.println("==============" + list1);
		}else {
			list1 = Collections.emptyList();
		}
		
		Map<String, Object> mapJson = new HashMap<String, Object>();
		mapJson.put("place", list1);
		mapJson.put("count", count1);
		mapJson.put("rowCount", rowCount);
		mapJson.put("pageCount", pageCount);
		mapJson.put("pageNo", currentPage);
		
		return mapJson;
	}	
	
	
	
	
	//일정 모달창 (검색+페이징)
	@RequestMapping(value="/calendar/searchSchedule.do")
	@ResponseBody
	public Map<String, Object> searchSchedule(
			@RequestParam(value="pageNo",defaultValue="1")
			int currentPage,
			@RequestParam(value="keyfield",defaultValue="")
			String keyfield,
			@RequestParam(value="keyword",defaultValue="")
			String keyword) {

		if(log.isDebugEnabled()) {
			log.debug("<<currentPage>> : " + currentPage);
			log.debug("<<keyfield>> : " + keyfield);
			log.debug("<<keyword>> : " + keyword);
		}

		//즐겨찾기 페이징 처리 목록
		Map<String, Object> map2 = new HashMap<String, Object>();

		map2.put("keyfield", keyfield);
		map2.put("keyword", keyword);

		int count2 = favoriteService.selectRowCount2(map2);

		if(log.isDebugEnabled()) {
			log.debug("<<count2>> : " + count2);
		}

		PagingUtil page2 = new PagingUtil(keyfield,keyword,currentPage, count2, rowCount, pageCount, null); //마지막 null은 호출하는 url인데, ajax 처리 하므로 넘길 url이 없으므로 null로 명시
		map2.put("start", page2.getStartCount());
		map2.put("end", page2.getEndCount());
		//즐겨찾기 메인 목록
		List<FavoriteCommand> list2;

		if(count2>0) {
			list2= favoriteService.selectList2(map2);
			System.out.println("==============" + list2);
		}else {
			list2 = Collections.emptyList();
		}

		Map<String, Object> mapJson2 = new HashMap<String, Object>();
		mapJson2.put("schedule", list2);
		mapJson2.put("count", count2);
		mapJson2.put("rowCount", rowCount);
		mapJson2.put("pageCount", pageCount);
		mapJson2.put("pageNo", currentPage);

		return mapJson2;
	}

	@RequestMapping(value="/calendar/searchMember.do")
	@ResponseBody
	public Map<String, Object> searchMember(
			@RequestParam(value="pageNo",defaultValue="1")
			int currentPage,
			@RequestParam(value="keyfield",defaultValue="")
			String keyfield,
			@RequestParam(value="keyword",defaultValue="")
			String keyword) {

		if(log.isDebugEnabled()) {
			log.debug("<<currentPage>> : " + currentPage);
			log.debug("<<keyfield>> : " + keyfield);
			log.debug("<<keyword>> : " + keyword);
		}

		//즐겨찾기 페이징 처리 목록
		Map<String, Object> map3 = new HashMap<String, Object>();

		map3.put("keyfield", keyfield);
		map3.put("keyword", keyword);

		int count3 = favoriteService.selectRowCount3(map3);

		if(log.isDebugEnabled()) {
			log.debug("<<count3>> : " + count3);
		}

		PagingUtil page3 = new PagingUtil(keyfield,keyword,currentPage, count3, rowCount, pageCount, null); //마지막 null은 호출하는 url인데, ajax 처리 하므로 넘길 url이 없으므로 null로 명시
		map3.put("start", page3.getStartCount());
		map3.put("end", page3.getEndCount());
		//즐겨찾기 메인 목록
		List<FavoriteCommand> list3;

		if(count3>0) {
			list3= favoriteService.selectList3(map3);
			System.out.println("==============" + list3);
		}else {
			list3 = Collections.emptyList();
		}

		Map<String, Object> mapJson3 = new HashMap<String, Object>();
		mapJson3.put("member", list3);
		mapJson3.put("count", count3);
		mapJson3.put("rowCount", rowCount);
		mapJson3.put("pageCount", pageCount);
		mapJson3.put("pageNo", currentPage);

		return mapJson3;
	}
	@RequestMapping(value="/calendar/imageView3.do")
	public ModelAndView viewImage3(@RequestParam("email") String email) {
		System.out.println(email);
		
		MemberCommand member = memberService.selectMember(email);
		
		if(log.isDebugEnabled()) {
			log.debug("<<member>> : " + member);
		}
		
		ModelAndView mav2 = new ModelAndView();
		mav2.setViewName("imageView");
		mav2.addObject("imageFile", member.getTd_profile());
		mav2.addObject("filename", "image.jpg");
		return mav2;
	}

}