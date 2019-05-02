package kr.spring.fav.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.spring.fav.domain.FavoriteCommand;

public interface FavoriteMapper {
	//즐겨찾기 장소 페이징
	public List<FavoriteCommand> selectList1(Map<String, Object> map);
	public int selectRowCount1(Map< String, Object> map);
	public List<FavoriteCommand> selectList2(Map<String, Object> map2);
	public int selectRowCount2(Map< String, Object> map2);
	public List<FavoriteCommand> selectList3(Map<String, Object> map3);
	public int selectRowCount3(Map< String, Object> map3);
	
	//즐겨찾기 장소 리스트
	@Select("SELECT f_num, f_category, f_code1, email FROM favorite WHERE f_category=1 ORDER BY f_num DESC")
	public List<FavoriteCommand> selectFav1();
	//즐겨찾기 일정 리스트
	@Select("SELECT  *  FROM favorite f JOIN schedule s ON f.f_code2 = s.s_num JOIN t_member_detail t " + 
			"ON t.email = s.email WHERE f_category=2 ORDER BY f.f_num DESC")
	public List<FavoriteCommand> selectFav2();
	//즐겨찾기 회원 리스트
	@Select("SELECT * FROM favorite f JOIN t_member_detail t ON f.f_code3 = t.email "
			+ "WHERE f_category=3 ORDER BY f.f_num DESC")	public List<FavoriteCommand> selectFav3();
	
	
	//즐겨찾기 추가
	@Insert("INSERT INTO favorite (f_num,f_category,f_code,email) VALUES (favorite_seq.nextval,#{f_category},#{f_code},#{email})")
	public void insertPlace(FavoriteCommand favorite);
	public void insertPlan(FavoriteCommand favorite);
	public void insertMember(FavoriteCommand favorite);
	//즐겨찾기 삭제
	@Delete("DELETE FROM favorite WHERE f_num=#{f_num}")
	public void deleteFav(int f_num);
	
			
		
	/*@Select("SELECT * FROM favorite WHERE email=#{email}")
	public List<FavoriteCommand> selectList(Map<String, Object> map);
	@Select("SELECT * FROM favorite WHERE email=#{email}")
	public int selectRowCount(Map<String, Object> map);
	*/

	
}
