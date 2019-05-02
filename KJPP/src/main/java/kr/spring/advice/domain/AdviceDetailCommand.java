package kr.spring.advice.domain;

import org.hibernate.validator.constraints.NotEmpty;

public class AdviceDetailCommand {
	private int ad_num;  //상세 일정 글번호
	@NotEmpty
	private int ad_code;  //관광지 코드
	@NotEmpty
	private int ad_day;  //일수
	@NotEmpty
	private String starttime;  //시작시간
	@NotEmpty
	private String endtime;  //끝시간
	private String ad_memo;  //메모
	private int ad_money;  //경비
	private int adv_num;  //조언글 번호
	
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
	public int getAd_money() {
		return ad_money;
	}
	public void setAd_money(int ad_money) {
		this.ad_money = ad_money;
	}
	public int getAdv_num() {
		return adv_num;
	}
	public void setAdv_num(int adv_num) {
		this.adv_num = adv_num;
	}
	@Override
	public String toString() {
		return "AdviceDetailCommand [ad_num=" + ad_num + ", ad_code=" + ad_code + ", ad_day=" + ad_day + ", starttime="
				+ starttime + ", endtime=" + endtime + ", ad_memo=" + ad_memo + ", ad_money=" + ad_money + ", adv_num="
				+ adv_num + "]";
	}
}
