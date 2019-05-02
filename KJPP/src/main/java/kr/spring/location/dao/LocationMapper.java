package kr.spring.location.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.spring.fav.domain.FavoriteCommand;


public interface LocationMapper {
		
	@Insert("INSERT INTO FAVORITE (f_num,f_category,f_code1,email)VALUES (favorite_seq.nextval,1,#{contentid},#{email})")
	public void insert(Map<String, Object> map);
	
	@Select("SELECT * FROM FAVORITE WHERE F_CODE1 =#{contentid} and email = #{email}")
	public FavoriteCommand select(Map<String, Object> map);
	
} 
