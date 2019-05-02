package kr.spring.share.domain;

import kr.spring.util.DurationFromNow;

public class ShareReplyCommand {
	private int sr_num;
	private String sr_content;
	private String sr_date;
	private String email;
	private int num;
	private String nickname;
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getSr_num() {
		return sr_num;
	}
	public void setSr_num(int sr_num) {
		this.sr_num = sr_num;
	}
	public String getSr_content() {
		return sr_content;
	}
	public void setSr_content(String sr_content) {
		this.sr_content = sr_content;
	}
	public String getSr_date() {
		return DurationFromNow.getTimeDiffLabel(sr_date);
	}
	public void setSr_date(String sr_date) {
		this.sr_date = sr_date;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	@Override
	public String toString() {
		return "ShareReplyCommand [sr_num=" + sr_num + ", sr_content=" + sr_content + ", sr_date=" + sr_date
				+ ", email=" + email + ", num=" + num + ", nickname=" + nickname + "]";
	}
	
}
