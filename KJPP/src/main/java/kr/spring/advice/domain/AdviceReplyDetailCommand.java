package kr.spring.advice.domain;

import org.hibernate.validator.constraints.NotEmpty;

public class AdviceReplyDetailCommand {
	private int ad_num;  //댓글 상세 일정 글번호
	@NotEmpty
	private int ad_code;  //관광지 코드
	@NotEmpty
	private int ad_day;  //일 차
	@NotEmpty
	private String starttime;  //시작시간
	@NotEmpty
	private String endtime;  //종료시간
	private String ad_memo;  //메모
	private int ar_num;   //댓글 번호
	private int ad_money;  //경비
	
	public int getAd_num() {
		return ad_num;
	}
	public void setAd_num(int ad_num) {
		this.ad_num = ad_num;
	}
	public int getAd_code() {
		return ad_code;
	}
	public void setAd_code(int ad_code) {
		this.ad_code = ad_code;
	}
	public int getAd_day() {
		return ad_day;
	}
	public void setAd_day(int ad_day) {
		this.ad_day = ad_day;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getAd_memo() {
		return ad_memo;
	}
	public void setAd_memo(String ad_memo) {
		this.ad_memo = ad_memo;
	}
	public int getAr_num() {
		return ar_num;
	}
	public void setAr_num(int ar_num) {
		this.ar_num = ar_num;
	}
	public int getAd_money() {
		return ad_money;
	}
	public void setAd_money(int ad_money) {
		this.ad_money = ad_money;
	}
	@Override
	public String toString() {
		return "AdviceReplyDetailCommand [ad_num=" + ad_num + ", ad_code=" + ad_code + ", ad_day=" + ad_day
				+ ", starttime=" + starttime + ", endtime=" + endtime + ", ad_memo=" + ad_memo + ", ar_num=" + ar_num
				+ ", ad_money=" + ad_money + "]";
	}
}
