package kr.spring.location.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.fav.domain.FavoriteCommand;
import kr.spring.location.service.LocationService;
@Controller
public class LocationAjaxController {
	private Logger log = Logger.getLogger(this.getClass());

	@Resource
	private LocationService locationService;

		

	@RequestMapping(value="/favinsert.do")
	@ResponseBody
	public Map<String, Object> submitContentid(@RequestParam("contentid") int contentid,HttpSession session) {

		String email = (String)session.getAttribute("user_email");

		if(log.isDebugEnabled()) {
			log.debug("<<email>> : " + email);
			log.debug("<<contentid>> : " + contentid);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email", email);
		map.put("contentid",contentid);
		
		FavoriteCommand favoriteCommand = new FavoriteCommand();
		favoriteCommand = locationService.select(map);
		if(log.isDebugEnabled()) {
			log.debug("<<favoriteCommand>> : " + favoriteCommand);
		
		}
		if(favoriteCommand ==null) {
			locationService.insert(map);	
			map.put("result", "success");
		
		}else {
			map.put("result", "fail");
		}
		


		return map;

	}

/*	@RequestMapping(value="/favselect.do")
	@ResponseBody
	public Map<String, Object> selectFav(@RequestParam("contentid") int contentid, HttpSession session){
		String email = (String)session.getAttribute("user_email");

		if(log.isDebugEnabled()) {
			log.debug("<<email>> : " + email);
			log.debug("<<contentid>> : " + contentid);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email", email);
		map.put("contentid",contentid);
		return map;
		
		
	}
	*/
	
}
