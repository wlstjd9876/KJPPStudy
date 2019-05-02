package kr.spring.location.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class SigunguAjaxController{
	private Logger log = Logger.getLogger(this.getClass());

	//�����ڽ� ����
	@RequestMapping("/data/location/sigunguAjax.do")
	@ResponseBody
	public Map<String,Object> writeReply(@RequestParam("val") int val,
			HttpServletRequest request){
		if(log.isDebugEnabled()) {
			log.debug("<<val>> : " + val);
		}

		Map<String,Object> map = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		if(val==1) {
			list.add("������");
			list.add("������");
			list.add("���ϱ�");
			list.add("������");
			list.add("���Ǳ�");
			list.add("������");
			list.add("���α�");
			list.add("��õ��");
			list.add("��õ��");
			list.add("������");
			list.add("���빮��");
			list.add("���۱�");
			list.add("������");
			list.add("���빮��");
			list.add("���ʱ�");
			list.add("������");
			list.add("���ϱ�");
			list.add("���ı�");
			list.add("��õ��");
			list.add("��������");
			list.add("��걸");
			list.add("����");
			list.add("���α�");
			list.add("�߱�");
			list.add("�߶���");
		}else if(val==2) {
			list.add("��ȭ��");
			list.add("��籸");
			list.add("����Ȧ��");
			list.add("������");
			list.add("����");
			list.add("����");
			list.add("����");
			list.add("������");
			list.add("������");
			list.add("�߱�");
		}else if(val==3) {
			list.add("�����");
			list.add("����");
			list.add("����");
			list.add("������");
			list.add("�߱�");
		}else if(val==4) {
			list.add("����");
			list.add("�޼���");
			list.add("�޼���");
			list.add("����");
			list.add("�ϱ�");
			list.add("����");
			list.add("������");
			list.add("�߱�");
		}else if(val==5) {
			list.add("���걸");
			list.add("����");
			list.add("����");
			list.add("�ϱ�");
			list.add("����");
		}else if(val==6) {
			list.add("������");
			list.add("������");
			list.add("���屺");
			list.add("����");
			list.add("����");
			list.add("������");
			list.add("�λ�����");
			list.add("�ϱ�");
			list.add("���");
			list.add("���ϱ�");
			list.add("����");
			list.add("������");
			list.add("������");
			list.add("������");
			list.add("�߱�");
			list.add("�ؿ�뱸");
		}else if(val==7) {
			list.add("�߱�");
			list.add("����");
			list.add("����");
			list.add("�ϱ�");
			list.add("���ֱ�");
		}else if(val==8) {
			list.add("����Ư����ġ��");
		}else if(val==9) {
			list.add("����");
			list.add("����");
			list.add("��õ��");
			list.add("�����");
			list.add("���ֽ�");
			list.add("������");
			list.add("������");
			list.add("������");
			list.add("�����ֽ�");
			list.add("����õ��");
			list.add("��õ��");
			list.add("������");
			list.add("������");
			list.add("�����");
			list.add("�Ȼ��");
			list.add("�ȼ���");
			list.add("�Ⱦ��");
			list.add("���ֽ�");
			list.add("����");
			list.add("���ֽ�");
			list.add("��õ��");
			list.add("�����");
			list.add("���ν�");
			list.add("�ǿս�");
			list.add("�����ν�");
			list.add("��õ��");
			list.add("���ֽ�");
			list.add("���ý�");
			list.add("��õ��");
			list.add("�ϳ���");
			list.add("ȭ����");
		}else if(val==10) {
			list.add("������");
			list.add("����");
			list.add("���ؽ�");
			list.add("��ô��");
			list.add("���ʽ�");
			list.add("�籸��");
			list.add("��籺");
			list.add("������");
			list.add("���ֽ�");
			list.add("���籺");
			list.add("������");
			list.add("ö����");
			list.add("��õ��");
			list.add("�¹��");
			list.add("��â��");
			list.add("ȫõ��");
			list.add("ȸõ��");
			list.add("Ⱦ����");
		}else if(val==11) {
			list.add("���걺");
			list.add("�ܾ籺");
			list.add("������");
			list.add("������");
			list.add("��õ��");
			list.add("������");
			list.add("��õ��");
			list.add("��õ��");
			list.add("û����");
			list.add("û�ֽ�");
			list.add("���ֽ�");
			list.add("����");
		}else if(val==12) {
			list.add("���ֽ�");
			list.add("�ݻ걺");
			list.add("����");
			list.add("������");
			list.add("���ɽ�");
			list.add("�ο���");
			list.add("�����");
			list.add("��õ��");
			list.add("�ƻ��");
			list.add("���걺");
			list.add("õ�Ƚ�");
			list.add("û�籺");
			list.add("�¾ȱ�");
			list.add("ȫ����");
			list.add("����");
		}else if(val==13) {
			list.add("����");
			list.add("���ֽ�");
			list.add("��ɱ�");
			list.add("���̽�");
			list.add("������");
			list.add("��õ��");
			list.add("�����");
			list.add("��ȭ��");
			list.add("���ֽ�");
			list.add("���ֱ�");
			list.add("�ȵ���");
			list.add("������");
			list.add("���籺");
			list.add("���ֽ�");
			list.add("��õ��");
			list.add("��õ��");
			list.add("�︪��");
			list.add("������");
			list.add("�Ǽ���");
			list.add("û����");
			list.add("û�۱�");
			list.add("ĥ�");
			list.add("���׽�");
		}else if(val==14) {
			list.add("������");
			list.add("��â��");
			list.add("����");
			list.add("���ؽ�");
			list.add("���ر�");
			list.add("�����");
			list.add("�о��");
			list.add("��õ��");
			list.add("��û��");
			list.add("����");
			list.add("�Ƿɱ�");
			list.add("���ֽ�");
			list.add("���ؽ�");
			list.add("â�籺");
			list.add("â����");
			list.add("�뿵��");
			list.add("�ϵ���");
			list.add("�Ծȱ�");
			list.add("�Ծ籺");
			list.add("��õ��");
		}else if(val==15) {
			list.add("��â��");
			list.add("�����");
			list.add("������");
			list.add("������");
			list.add("���ֱ�");
			list.add("�ξȱ�");
			list.add("��â��");
			list.add("���ֱ�");
			list.add("�ͻ��");
			list.add("�ӽǱ�");
			list.add("�����");
			list.add("���ֽ�");
			list.add("������");
			list.add("���ȱ�");
		}else if(val==16) {
			list.add("������");
			list.add("���ﱺ");
			list.add("���");
			list.add("�����");
			list.add("���ʱ�");
			list.add("���ֽ�");
			list.add("��籺");
			list.add("������");
			list.add("���ȱ�");
			list.add("������");
			list.add("��õ��");
			list.add("�žȱ�");
			list.add("������");
			list.add("������");
			list.add("���ϱ�");
			list.add("�ϵ���");
			list.add("�强��");
			list.add("���ﱺ");
			list.add("������");
			list.add("����");
			list.add("�س���");
			list.add("ȭ����");
		}else if(val==17) {
			list.add("�����ֱ�");
			list.add("�����ֱ�");
			list.add("��������");
			list.add("���ֽ�");
		}
		map.put("list", list);

		return map;
	}
}
