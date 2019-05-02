package kr.spring.advice.domain;

import java.sql.Date;

import org.hibernate.validator.constraints.NotEmpty;

public class AdviceCommand {
	private int adv_num;
	@NotEmpty
	private String adv_title;
	private String email;
	private Date adv_date;//글 등록 날짜
	@NotEmpty
	private String adv_phone;
	private int adv_like;
	@NotEmpty
	private String startdate;
	@NotEmpty
	private String enddate;
	private int s_num;
	
	private String td_nickname;
	
	public String getTd_nickname() {
		return td_nickname;
	}
	public void setTd_nickname(String td_nickname) {
		this.td_nickname = td_nickname;
	}
	public int getAdv_num() {
		return adv_num;
	}
	public void setAdv_num(int adv_num) {
		this.adv_num = adv_num;
	}
	public String getAdv_title() {
		return adv_title;
	}
	public void setAdv_title(String adv_title) {
		this.adv_title = adv_title;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getAdv_date() {
		return adv_date;
	}
	public void setAdv_date(Date adv_date) {
		this.adv_date = adv_date;
	}
	public String getAdv_phone() {
		return adv_phone;
	}
	public void setAdv_phone(String adv_phone) {
		this.adv_phone = adv_phone;
	}
	public int getAdv_like() {
		return adv_like;
	}
	public void setAdv_like(int adv_like) {
		this.adv_like = adv_like;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public int getS_num() {
		return s_num;
	}
	public void setS_num(int s_num) {
		this.s_num = s_num;
	}
	@Override
	public String toString() {
		return "AdviceCommand [adv_num=" + adv_num + ", adv_title=" + adv_title + ", email=" + email + ", adv_date="
				+ adv_date + ", adv_phone=" + adv_phone + ", adv_like=" + adv_like + ", startdate=" + startdate
				+ ", enddate=" + enddate + ", s_num=" + s_num + ", td_nickname=" + td_nickname + "]";
	}
}
