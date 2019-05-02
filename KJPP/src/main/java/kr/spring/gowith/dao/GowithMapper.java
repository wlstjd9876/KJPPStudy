package kr.spring.gowith.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.gowith.domain.GowithCommand;

public interface GowithMapper {
	@Insert("INSERT INTO gowith VALUES(gowith_seq.nextval,#{s_num},#{go_member},#{go_startdate},#{go_enddate},#{go_deadline},#{go_area},#{go_photo1},#{go_photo2},#{go_photo3},#{go_photo4},#{go_gen},#{go_age},#{go_say},#{go_type},#{email})")
	public void insert(GowithCommand gowith);
	public GowithCommand selectGowith(Integer go_num);
	
	public GowithCommand selectGowith(String email);
	@Delete("DELETE FROM gowith WHERE go_num=#{go_num}")
	public void delete(Integer go_num);
	@Delete("DELETE FROM gowith_app WHERE go_num=#{go_num}")
	public void deleteApp(Integer go_num);
	@Select("SELECT COUNT(*) FROM gowith_app WHERE go_num=#{go_num} AND app_status=0")
	public int count(int go_num);
	
	public void update(GowithCommand gowith);
	public List<GowithCommand> selectList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	
	//글작성시 회원 점수 올리기
	@Update("UPDATE t_member_detail SET td_score=td_score+100 WHERE email=#{email}")
	public void updateScore(String email);
	
}
