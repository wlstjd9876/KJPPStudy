package kr.spring.fav.service;

import java.util.List;
import java.util.Map;

import kr.spring.fav.domain.FavoriteCommand;

public interface FavoriteService {
	
	//���ã�� ��� ����¡, �˻� 
	public List<FavoriteCommand> selectList1(Map<String, Object> map);
	public int selectRowCount1(Map<String, Object> map);	
	public List<FavoriteCommand> selectList2(Map<String, Object> map2);
	public int selectRowCount2(Map<String, Object> map2);	
	public List<FavoriteCommand> selectList3(Map<String, Object> map3);
	public int selectRowCount3(Map<String, Object> map3);	
	
	//���ã�� ��� ����
	public List<FavoriteCommand> selectFav1(); //���
	public List<FavoriteCommand> selectFav2(); //����
	public List<FavoriteCommand> selectFav3(); //ȸ��	
	
	//���ã�� �߰�
	public void insertPlace(FavoriteCommand favorite);
	public void insertPlan(FavoriteCommand favorite);
	public void insertMember(FavoriteCommand favorite);	
	//���ã�� ����
	public void deleteFav(Integer f_num);
	
	public List<FavoriteCommand> selectList(Map<String, Object> map);
	public int selectRowCount(Map<String, Object> map);
	
	
}
