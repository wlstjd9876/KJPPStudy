package kr.spring.calendar.domain;

import java.io.IOException;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class CalendarCommand {
	private CalendarDetailCommand detail;
	@NotEmpty
	private int s_num;  //일정번호
	@NotEmpty
	private String s_title;  //일정제목
	@NotEmpty
	private int s_share;  //일정 공유여부
	@NotEmpty
	private int s_finish;  //일정 확정여부
	@NotEmpty
	private int s_style;  //여행 스타일
	@NotEmpty
	private String s_traffic;  //교통수단
	private String s_color;  //일정 색상 저장
	@NotEmpty
	private String email;  //회원 이메일
	private String s_tag;  //태그
	private String s_startdate;  //여행 시작 날짜
	private String s_enddate;  //여행 끝 날짜
	private String s_content;  //여행 메모
	private MultipartFile upload_s_photo1;  //여행 사진
	private MultipartFile upload_s_photo2;
	private MultipartFile upload_s_photo3;
	private MultipartFile upload_s_photo4;
	private MultipartFile upload_s_photo5;
	private MultipartFile upload_s_photo6;
	private MultipartFile upload_s_photo7;
	private MultipartFile upload_s_photo8;
	private MultipartFile upload_s_photo9;
	private MultipartFile upload_s_photo10;
	private byte[] s_photo1;  //DB에 저장된 파일
	private byte[] s_photo2;
	private byte[] s_photo3;
	private byte[] s_photo4;
	private byte[] s_photo5;
	private byte[] s_photo6;
	private byte[] s_photo7;
	private byte[] s_photo8;
	private byte[] s_photo9;
	private byte[] s_photo10;
	private String real_enddate;
	
	
	public String getReal_enddate() {
		return real_enddate;
	}
	public void setReal_enddate(String real_enddate) {
		this.real_enddate = real_enddate;
	}
	public String getS_color() {
		return s_color;
	}
	public void setS_color(String s_color) {
		this.s_color = s_color;
	}
	public CalendarDetailCommand getDetail() {
		return detail;
	}
	public void setDetail(CalendarDetailCommand detail) {
		this.detail = detail;
	}
	public int getS_num() {
		return s_num;
	}
	public void setS_num(int s_num) {
		this.s_num = s_num;
	}
	public String getS_title() {
		return s_title;
	}
	public void setS_title(String s_title) {
		this.s_title = s_title;
	}
	public int getS_share() {
		return s_share;
	}
	public void setS_share(int s_share) {
		this.s_share = s_share;
	}
	public int getS_finish() {
		return s_finish;
	}
	public void setS_finish(int s_finish) {
		this.s_finish = s_finish;
	}
	public int getS_style() {
		return s_style;
	}
	public void setS_style(int s_style) {
		this.s_style = s_style;
	}
	public String getS_traffic() {
		return s_traffic;
	}
	public void setS_traffic(String s_traffic) {
		this.s_traffic = s_traffic;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getS_tag() {
		return s_tag;
	}
	public void setS_tag(String s_tag) {
		this.s_tag = s_tag;
	}
	public String getS_startdate() {
		return s_startdate;
	}
	public void setS_startdate(String s_startdate) {
		this.s_startdate = s_startdate;
	}
	public String getS_enddate() {
		return s_enddate;
	}
	public void setS_enddate(String s_enddate) {
		this.s_enddate = s_enddate;
	}
	public String getS_content() {
		return s_content;
	}
	public void setS_content(String s_content) {
		this.s_content = s_content;
	}
	//사진파일
	//MultipartFile -> byte[]변환
	public MultipartFile getUpload_s_photo1(){
		return upload_s_photo1;
	}
	public void setUpload_s_photo1(MultipartFile upload_s_photo1) throws IOException {
		this.upload_s_photo1 = upload_s_photo1;
		//byte[] 데이터 저장
		setS_photo1(upload_s_photo1.getBytes());
	}
	public MultipartFile getUpload_s_photo2() {
		return upload_s_photo2;
	}
	public void setUpload_s_photo2(MultipartFile upload_s_photo2) throws IOException {
		this.upload_s_photo2 = upload_s_photo2;
		//byte[] 데이터 저장
		setS_photo2(upload_s_photo2.getBytes());
	}
	public MultipartFile getUpload_s_photo3() {
		return upload_s_photo3;
	}
	public void setUpload_s_photo3(MultipartFile upload_s_photo3) throws IOException {
		this.upload_s_photo3 = upload_s_photo3;
		//byte[] 데이터 저장
		setS_photo3(upload_s_photo3.getBytes());
	}
	public MultipartFile getUpload_s_photo4() {
		return upload_s_photo4;
	}
	public void setUpload_s_photo4(MultipartFile upload_s_photo4) throws IOException {
		this.upload_s_photo4 = upload_s_photo4;
		//byte[] 데이터 저장
		setS_photo4(upload_s_photo4.getBytes());
	}
	public MultipartFile getUpload_s_photo5() {
		return upload_s_photo5;
	}
	public void setUpload_s_photo5(MultipartFile upload_s_photo5) throws IOException {
		this.upload_s_photo5 = upload_s_photo5;
		//byte[] 데이터 저장
		setS_photo5(upload_s_photo5.getBytes());
	}
	public MultipartFile getUpload_s_photo6() {
		return upload_s_photo6;
	}
	public void setUpload_s_photo6(MultipartFile upload_s_photo6) throws IOException {
		this.upload_s_photo6 = upload_s_photo6;
		//byte[] 데이터 저장
		setS_photo6(upload_s_photo6.getBytes());
	}
	public MultipartFile getUpload_s_photo7() {
		return upload_s_photo7;
	}
	public void setUpload_s_photo7(MultipartFile upload_s_photo7) throws IOException {
		this.upload_s_photo7 = upload_s_photo7;
		//byte[] 데이터 저장
		setS_photo7(upload_s_photo7.getBytes());
	}
	public MultipartFile getUpload_s_photo8() {
		return upload_s_photo8;
	}
	public void setUpload_s_photo8(MultipartFile upload_s_photo8) throws IOException {
		this.upload_s_photo8 = upload_s_photo8;
		//byte[] 데이터 저장
		setS_photo8(upload_s_photo8.getBytes());
	}
	public MultipartFile getUpload_s_photo9() {
		return upload_s_photo9;
	}
	public void setUpload_s_photo9(MultipartFile upload_s_photo9) throws IOException {
		this.upload_s_photo9 = upload_s_photo9;
		//byte[] 데이터 저장
		setS_photo9(upload_s_photo9.getBytes());
	}
	public MultipartFile getUpload_s_photo10() {
		return upload_s_photo10;
	}
	public void setUpload_s_photo10(MultipartFile upload_s_photo10) throws IOException {
		this.upload_s_photo10 = upload_s_photo10;
		//byte[] 데이터 저장
		setS_photo10(upload_s_photo10.getBytes());
	}
	public byte[] getS_photo1() {
		return s_photo1;
	}
	public void setS_photo1(byte[] s_photo1) {
		this.s_photo1 = s_photo1;
	}
	public byte[] getS_photo2() {
		return s_photo2;
	}
	public void setS_photo2(byte[] s_photo2) {
		this.s_photo2 = s_photo2;
	}
	public byte[] getS_photo3() {
		return s_photo3;
	}
	public void setS_photo3(byte[] s_photo3) {
		this.s_photo3 = s_photo3;
	}
	public byte[] getS_photo4() {
		return s_photo4;
	}
	public void setS_photo4(byte[] s_photo4) {
		this.s_photo4 = s_photo4;
	}
	public byte[] getS_photo5() {
		return s_photo5;
	}
	public void setS_photo5(byte[] s_photo5) {
		this.s_photo5 = s_photo5;
	}
	public byte[] getS_photo6() {
		return s_photo6;
	}
	public void setS_photo6(byte[] s_photo6) {
		this.s_photo6 = s_photo6;
	}
	public byte[] getS_photo7() {
		return s_photo7;
	}
	public void setS_photo7(byte[] s_photo7) {
		this.s_photo7 = s_photo7;
	}
	public byte[] getS_photo8() {
		return s_photo8;
	}
	public void setS_photo8(byte[] s_photo8) {
		this.s_photo8 = s_photo8;
	}
	public byte[] getS_photo9() {
		return s_photo9;
	}
	public void setS_photo9(byte[] s_photo9) {
		this.s_photo9 = s_photo9;
	}
	public byte[] getS_photo10() {
		return s_photo10;
	}
	public void setS_photo10(byte[] s_photo10) {
		this.s_photo10 = s_photo10;
	}
	@Override
	public String toString() {
		return "CalendarCommand [detail=" + detail + ", s_num=" + s_num + ", s_title=" + s_title + ", s_share="
				+ s_share + ", s_finish=" + s_finish + ", s_style=" + s_style + ", s_traffic=" + s_traffic
				+ ", s_color=" + s_color + ", email=" + email + ", s_tag=" + s_tag + ", s_startdate=" + s_startdate
				+ ", s_enddate=" + s_enddate + ", s_content=" + s_content + "]";
	}
}
