package kr.spring.goapp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.goapp.domain.GoappCommand;

public interface GoappMapper {
	//신청서 작성
	@Select("SELECT a.*,g.email w_email FROM gowith_app a JOIN gowith g ON a.go_num = g.go_num WHERE app_num=#{app_num}")
	public GoappCommand selectMember(int app_num);
	
	//신청서 목록
	@Select("SELECT * FROM gowith_app a JOIN t_member_detail t ON t.email = a.email WHERE a.go_num=#{go_num} ORDER BY a.app_num")
	/*@Select("SELECT * FROM gowith g JOIN gowith_app a ON g.go_num = a.go_num JOIN t_member_detail t ON t.email = a.email WHERE a.go_num=#{go_num} ORDER BY a.app_num")*/
	public List<GoappCommand> selectList(int go_num);
	
	public int selectRowCount(Map<String, Object> map);
	
	@Insert("INSERT INTO gowith_app (app_num,email,app_member,app_gen,app_photo,app_why,go_num,app_status) "
			+ "VALUES (gowith_app_seq.nextval,#{email},#{app_member},#{app_gen},#{app_photo},#{app_why},#{go_num},0)")
	public void insert(GoappCommand goapp);
	
	//신청서 수정
	@Update("UPDATE gowith_app SET app_member=#{app_member},app_gen=#{app_gen},app_photo=#{app_photo},app_why=#{app_why} WHERE app_num=#{app_num}")
	public void update(GoappCommand goapp);
	
	@Delete("DELETE FROM gowith_app WHERE app_num=#{app_num}")
	public void delete(int app_num);
	
	//신청 수락
	@Update("UPDATE gowith_app SET app_status = 1 WHERE app_num=#{app_num}")
	public void confirm(int app_num); 
	
	//중복 신청 방지
	@Select("SELECT COUNT(*) FROM gowith_app WHERE go_num=#{go_num} AND email=#{email}")
	public int check(Map<String, Object> map);

}
