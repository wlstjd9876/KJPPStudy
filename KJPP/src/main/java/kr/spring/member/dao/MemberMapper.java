package kr.spring.member.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.member.domain.MemberCommand;

public interface MemberMapper {
	@Insert("INSERT INTO t_member (email) VALUES (#{email})")
	public void insert(MemberCommand member);
	@Insert("INSERT INTO t_member_detail (email,td_nickname,td_password,td_profile,td_reg_date,td_gender,td_content,td_birth) "
			+ "VALUES (#{email},#{td_nickname},#{td_password},#{td_profile},SYSDATE,#{td_gender},#{td_content},#{td_birth})")
	public  void insertDetail(MemberCommand member);
	@Select("SELECT * FROM t_member t LEFT OUTER JOIN t_member_detail td ON t.email=td.email WHERE t.email=#{email}")
	public MemberCommand selectMember(String email);
	@Select("SELECT * FROM t_member_detail WHERE td_nickname=#{td_nickname}")
	public MemberCommand checkNickname(String td_nickname);
	@Update("UPDATE t_member_detail SET td_nickname=#{td_nickname},td_profile=#{td_profile},td_content=#{td_content},td_birth=#{td_birth},td_gender=#{td_gender} WHERE email=#{email}")
	public void update(MemberCommand member);
	@Update("UPDATE t_member_detail SET td_password=#{td_password} WHERE email=#{email}")
	public void updatePassword(MemberCommand member);
	@Update("UPDATE t_member_detail SET td_nickname=#{td_nickname} WHERE email=#{email}")
	public void updateNick(MemberCommand member);
	@Update("UPDATE t_member SET t_auth=3 WHERE email=#{email}")
	public void delete(String email);
	@Delete("DELETE FROM t_member_detail WHERE email=#{email}")
	public void deleteDetail(String email);
	
	//관리자 회원목록
	public List<MemberCommand> selectList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
}
