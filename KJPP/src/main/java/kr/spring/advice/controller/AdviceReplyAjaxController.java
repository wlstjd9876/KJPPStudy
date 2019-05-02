package kr.spring.advice.controller;

import java.util.Collections;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.spring.advice.domain.AdviceReplyCommand;
import kr.spring.advice.service.AdviceService;
import kr.spring.util.PagingUtil;

@Controller
public class AdviceReplyAjaxController {
	private Logger log = Logger.getLogger(this.getClass());
	private int rowCount = 5;
	private int pageCount = 10;
	
	@Resource
	private AdviceService adviceService;

	//�ڹٺ�(Ŀ�ǵ� ��ü) �ʱ�ȭ
	@ModelAttribute("adviceReplyCommand")
	public AdviceReplyCommand initCommand() {
		return new AdviceReplyCommand();
	}
	
	//��� ���
	//��� ��� ��
	@RequestMapping(value="/advice/adviceReplyWrite.do", method=RequestMethod.GET)
	public String form(@RequestParam("adv_num") int adv_num, HttpSession session, Model model) {
		String email = (String)session.getAttribute("user_email");
		
		AdviceReplyCommand command = new AdviceReplyCommand();
		command.setEmail(email); //�ڹٺ� �־���
		command.setAdv_num(adv_num);
		
		model.addAttribute("command",command);

		return "replyWrite"; //definition ���� ȣ��
	}

	//���۵� ������ ó��
	@RequestMapping(value="/advice/replyWrite.do", method=RequestMethod.POST)
	public String submit(@ModelAttribute("command") @Valid AdviceReplyCommand adviceReplyCommand, BindingResult result, RedirectAttributes redirect) { 
		//HttpServletRequest : ip�ּ� �ޱ� ����,
		//RedirectAttributes : ��ü�� �����̷�Ʈ ������ �� ���� ���Ǵ� �����͸� ����
		//				     	   �������� �����͸� ���������� URI�󿡴� ������ �ʴ� ������
		//				     	  �������� ���·� ����

		if(log.isDebugEnabled()) {
			log.debug("<<adviceReplyCommand>> : " + adviceReplyCommand);
		}

		//������ ��ȿ�� üũ
		if(result.hasErrors()) {
			return "replyWrite";
		}	

		//�۾���
		adviceService.insertReply(adviceReplyCommand);

		//RedirectAttributes ��ü�� �����̷�Ʈ ������ �� �� ���� ���Ǵ� �����͸� ����
		//�������� �����͸� ���������� URL�󿡴� ������ �ʴ� ������ �������� ���·� ���� 
		redirect.addFlashAttribute("result", "success"); //result�� ���ؼ� success�� ǥ��

		return "redirect:/advice/adviceDetail.do?adv_num="+adviceReplyCommand.getAdv_num();
	}

	//��� ��� 
	@RequestMapping("/advice/listReply.do")
	@ResponseBody
	public Map<String,Object> getList(@RequestParam(value="pageNum",defaultValue="1") int currentPage, @RequestParam("adv_num") int adv_num){
		if(log.isDebugEnabled()) {
			log.debug("<<currentPage>> : " +currentPage);
			log.debug("<<adv_num>> : " +adv_num);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("adv_num", adv_num);
		
		// �� ���� ����
		int count = adviceService.selectRowCountReply(map);

		PagingUtil page = new PagingUtil(currentPage, count, rowCount, pageCount, null);
		map.put("start",page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<AdviceReplyCommand> list = null;
		if(count >0) {
			list = adviceService.selectListReply(map);
		}else {
			list = Collections.emptyList(); //����ִ°� ����
		}

		Map<String, Object> mapJson = new HashMap<String, Object>();
		mapJson.put("count", count);
		mapJson.put("rowCount", rowCount);
		mapJson.put("list", list);

		return mapJson;
	}

	//��� ��
	
	
	//��ۻ���
	@RequestMapping("/advice/deleteReply.do")
	@ResponseBody
	public Map<String, String> deleteReply(@RequestParam("ar_num") int ar_num, @RequestParam("email") String email,HttpSession session){
		if(log.isDebugEnabled()) {
			log.debug("<<ar_num>> : " +ar_num);
			log.debug("<<email>> : " +email);
		}
		Map<String, String> map = new HashMap<String, String>();

		String user_email = (String)session.getAttribute("user_email");
		if(user_email==null) {
			//�α����� �Ǿ����� ����
			map.put("result", "logout");
		}else if(user_email!=null && user_email.equals(email)){
			//�α����� �Ǿ� �ְ� �α����� ���̵�� �ۼ��� ���̵� ��ġ
			adviceService.deleteReply(ar_num);
			map.put("result","success");
		}else {
			//�α����� ���̵�� �ۼ��� ���̵� ����ġ
			map.put("result", "wrongAccess");
		}

		return map;
	}

	//��� ����
	@RequestMapping("/advice/updateReply.do")
	@ResponseBody
	public Map<String, String> modifyReply(AdviceReplyCommand adviceReplyCommand, HttpSession session, HttpServletRequest request){

		if(log.isDebugEnabled()) {
			log.debug("<<adviceReplyCommand>> : " +adviceReplyCommand);
		}

		Map<String, String> map = new HashMap<String, String>();
		String user_email = (String)session.getAttribute("user_email");
		if(user_email==null) {
			//�α����� �ȵǾ� �ִ� ���
			map.put("result", "logout");
		}else if(user_email!=null && user_email.equals(adviceReplyCommand.getEmail())) {
			//�α��� ���̵�� �ۼ��� ���̵� ��ġ
			//��� ����
			adviceService.updateReply(adviceReplyCommand);
			map.put("result", "success");
		}else {
			//�α��� ���̵�� �ۼ��� ���̵� ����ġ
			map.put("result","wrongAccess");
		}
		return map;
	}
	
	//��� �� ����
	
}
