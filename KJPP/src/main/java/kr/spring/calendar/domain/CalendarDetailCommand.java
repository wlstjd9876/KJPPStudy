package kr.spring.calendar.domain;

import org.hibernate.validator.constraints.NotEmpty;
      
public class CalendarDetailCommand {
	private int s_num;  //일정번호
	private int sd_num;  //일정상세번호
	//@NotEmpty
	private String sd_code;  //관광지 코드
	//@NotEmpty
	private int sd_day;  //일 수
	//@NotEmpty
	private String sd_starttime;  //일정 시작 시간
	//@NotEmpty
	private String sd_endtime;  //일정 끝 시간
	private String sd_memo;  //일정 메모
	private int sd_money;  //여행 경비
	public int getS_num() {
		return s_num;
	}
	public void setS_num(int s_num) {
		this.s_num = s_num;
	}
	public int getSd_num() {
		return sd_num;
	}
	public void setSd_num(int sd_num) {
		this.sd_num = sd_num;
	}
	public String getSd_code() {
		return sd_code;
	}
	public void setSd_code(String sd_code) {
		this.sd_code = sd_code;
	}
	public int getSd_day() {
		return sd_day;
	}
	public void setSd_day(int sd_day) {
		this.sd_day = sd_day;
	}
	public String getSd_starttime() {
		return sd_starttime;
	}
	public void setSd_starttime(String sd_starttime) {
		this.sd_starttime = sd_starttime;
	}
	public String getSd_endtime() {
		return sd_endtime;
	}
	public void setSd_endtime(String sd_endtime) {
		this.sd_endtime = sd_endtime;
	}
	public String getSd_memo() {
		return sd_memo;
	}
	public void setSd_memo(String sd_memo) {
		this.sd_memo = sd_memo;
	}
	public int getSd_money() {
		return sd_money;
	}
	public void setSd_money(int sd_money) {
		this.sd_money = sd_money;
	}
	@Override
	public String toString() {
		return "CalendarDetailCommand [s_num=" + s_num + ", sd_num=" + sd_num + ", sd_code=" + sd_code + ", sd_day="
				+ sd_day + ", sd_starttime=" + sd_starttime + ", sd_endtime=" + sd_endtime + ", sd_memo=" + sd_memo
				+ ", sd_money=" + sd_money + "]";
	}
}
