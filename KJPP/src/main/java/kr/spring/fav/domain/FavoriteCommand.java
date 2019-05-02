package kr.spring.fav.domain;

public class FavoriteCommand {
	
	//즐겨찾기
	private int f_num;
	private int f_category;
	private String f_code1;
	private String f_code2;
	private String f_code3;
	private String email;
	
	//일정 디테일
	private String s_title;
	private String s_tag;
	
	//회원 디테일
	private String td_nickname;
	private int td_score;
	
	

	public String getS_title() {
		return s_title;
	}
	public void setS_title(String s_title) {
		this.s_title = s_title;
	}
	public String getS_tag() {
		return s_tag;
	}
	public void setS_tag(String s_tag) {
		this.s_tag = s_tag;
	}
	public String getTd_nickname() {
		return td_nickname;
	}
	public void setTd_nickname(String td_nickname) {
		this.td_nickname = td_nickname;
	}
	public int getTd_score() {
		return td_score;
	}
	public void setTd_score(int td_score) {
		this.td_score = td_score;
	}
	public int getF_num() {
		return f_num;
	}
	public void setF_num(int f_num) {
		this.f_num = f_num;
	}
	public int getF_category() {
		return f_category;
	}
	public void setF_category(int f_category) {
		this.f_category = f_category;
	}
	
	public String getF_code1() {
		return f_code1;
	}
	public void setF_code1(String f_code1) {
		this.f_code1 = f_code1;
	}
	public String getF_code2() {
		return f_code2;
	}
	public void setF_code2(String f_code2) {
		this.f_code2 = f_code2;
	}
	public String getF_code3() {
		return f_code3;
	}
	public void setF_code3(String f_code3) {
		this.f_code3 = f_code3;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "FavoriteCommand [f_num=" + f_num + ", f_category=" + f_category + ", f_code1=" + f_code1 + ", f_code2="
				+ f_code2 + ", f_code3=" + f_code3 + ", email=" + email + ", s_title=" + s_title + ", s_tag=" + s_tag
				+ ", td_nickname=" + td_nickname + ", td_score=" + td_score + "]";
	}
	


	
}
