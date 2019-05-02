package kr.spring.location.service;

import java.util.List;

import kr.spring.location.domain.ChartCommand;

public interface ChartService {
	
	//ȸ������ ���� ��Ÿ�� ��Ʈ
	public List<ChartCommand> style();
	
	//����� �Բ��ϴ� ���� Ÿ�� ��Ʈ
	public List<ChartCommand> type();
	
	//���� �ñ� ��Ʈ
	public List<ChartCommand> month();
	
	
}
