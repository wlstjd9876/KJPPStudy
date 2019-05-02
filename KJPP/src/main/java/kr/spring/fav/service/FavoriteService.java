package kr.spring.fav.service;

import java.util.List;
import java.util.Map;

import kr.spring.fav.domain.FavoriteCommand;

public interface FavoriteService {
	
	//즐겨찾기 목록 페이징, 검색 
	public List<FavoriteCommand> selectList1(Map<String, Object> map);
	public int selectRowCount1(Map<String, Object> map);	
	public List<FavoriteCommand> selectList2(Map<String, Object> map2);
	public int selectRowCount2(Map<String, Object> map2);	
	public List<FavoriteCommand> selectList3(Map<String, Object> map3);
	public int selectRowCount3(Map<String, Object> map3);	
	
	//즐겨찾기 목록 보기
	public List<FavoriteCommand> selectFav1(); //장소
	public List<FavoriteCommand> selectFav2(); //일정
	public List<FavoriteCommand> selectFav3(); //회원	
	
	//즐겨찾기 추가
	public void insertPlace(FavoriteCommand favorite);
	public void insertPlan(FavoriteCommand favorite);
	public void insertMember(FavoriteCommand favorite);	
	//즐겨찾기 삭제
	public void deleteFav(Integer f_num);
	
	public List<FavoriteCommand> selectList(Map<String, Object> map);
	public int selectRowCount(Map<String, Object> map);
	
	
}
