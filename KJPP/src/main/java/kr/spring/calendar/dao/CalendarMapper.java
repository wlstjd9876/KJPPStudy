package kr.spring.calendar.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.calendar.domain.CalendarCommand;
import kr.spring.calendar.domain.CalendarDetailCommand;

public interface CalendarMapper {
	//일정
	public void insert(CalendarCommand calendar);
	public List<CalendarCommand> selectList(Map<String, Object> map);
	public int selectRowCount(Map<String, Object> map);
	@Select("SELECT * FROM schedule WHERE s_num = #{s_num}")
	public CalendarCommand selectCalendar(Integer s_num);
	public void update(CalendarCommand calendar);
	@Delete("DELETE FROM schedule WHERE s_num = #{s_num}")
	public void delete(Integer s_num);
	@Select("SELECT s_title, s_startdate,  TO_DATE( s_enddate, 'yyyy-mm-dd HH:MI:SS')+1 s_enddate, s_num, s_color, s_enddate real_enddate FROM schedule WHERE email=#{email} ORDER BY s_startdate")
	public List<CalendarCommand> selectCal(Map<String, Object> map);
	@Select("SELECT * FROM schedule_detail WHERE s_num=#{s_num} ORDER BY sd_day, sd_starttime")
	public List<CalendarDetailCommand> selectDetailCal(Map<String, Object> map);
	
	//일정상세
	@Insert("INSERT INTO schedule_detail (s_num,sd_num,sd_code,sd_day,sd_starttime,sd_endtime,sd_memo,sd_money) "
			+ "VALUES (#{s_num},schedule_detail_seq.nextval,#{sd_code},#{sd_day},#{sd_starttime},#{sd_endtime},#{sd_memo},#{sd_money})")
	public void insertDetail(CalendarDetailCommand calendarDetail);
	@Select("SELECT * FROM schedule_detail ")
	public List<CalendarDetailCommand> selectDetailList(Map<String, Object> map);
	@Select("SELECT * FROM schedule_detail WHERE sd_num = #{sd_num}")
	public CalendarDetailCommand selectDetail(Integer sd_num);
	@Update("UPDATE schedule_detail SET sd_code=#{sd_code},sd_memo=#{sd_memo},sd_money=#{sd_money} WHERE sd_num=#{sd_num}")
	public void updateDetail(CalendarDetailCommand calendarDetail);
	@Delete("DELETE FROM schedule_detail WHERE sd_num=#{sd_num}")
	public void deleteDetail(Integer sd_num);
}
