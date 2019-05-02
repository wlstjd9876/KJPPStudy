package kr.spring.advice.domain;

public class AdviceReplyCommand {
	private int adv_num;  //조언글 번호
	private String email;  //작성자
	private String ar_date;  //댓글 등록일
	private int ar_like;  //추천수
	private String startdate;  //여행 시작날짜
	private String enddate;  //여행 종료날짜
	private String ar_comment;  //내용
	private int s_num;  //일정번호
	private int ar_num;  //댓글 번호
	public int getAdv_num() {
		return adv_num;
	}
	public void setAdv_num(int adv_num) {
		this.adv_num = adv_num;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAr_date() {
		return ar_date;
	}
	public void setAr_date(String ar_date) {
		this.ar_date = ar_date;
	}
	public int getAr_like() {
		return ar_like;
	}
	public void setAr_like(int ar_like) {
		this.ar_like = ar_like;
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
	public String getAr_comment() {
		return ar_comment;
	}
	public void setAr_comment(String ar_comment) {
		this.ar_comment = ar_comment;
	}
	public int getS_num() {
		return s_num;
	}
	public void setS_num(int s_num) {
		this.s_num = s_num;
	}
	public int getAr_num() {
		return ar_num;
	}
	public void setAr_num(int ar_num) {
		this.ar_num = ar_num;
	}
	@Override
	public String toString() {
		return "AdviceReplyCommand [adv_num=" + adv_num + ", email=" + email + ", ar_date=" + ar_date + ", ar_like="
				+ ar_like + ", startdate=" + startdate + ", enddate=" + enddate + ", ar_comment=" + ar_comment
				+ ", s_num=" + s_num + ", ar_num=" + ar_num + "]";
	}
}
