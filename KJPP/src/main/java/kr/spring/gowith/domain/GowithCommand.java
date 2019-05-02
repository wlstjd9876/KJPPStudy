package kr.spring.gowith.domain;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class GowithCommand {
	private int go_num;
	private int go_member;
	private String email;
	private String go_startdate;
	private String go_enddate;
	private String go_deadline;
	private String go_area;
	private MultipartFile go_photofile1;
	private byte[] go_photo1;
	private MultipartFile go_photofile2;
	private byte[] go_photo2;
	private MultipartFile go_photofile3;
	private byte[] go_photo3; 
	private MultipartFile go_photofile4;
	private byte[] go_photo4; 
	private String photoname;
	private String go_gen;
	private String go_age;
	private String go_say;
	private String go_type;
	private int go_status;
	private int dday;
	private int s_num;
	
	private String td_nickname;
	private String td_password;
	private MultipartFile td_profilephoto;
	private byte[] td_profile;
	private Date td_reg_date;
	private int td_score;
	private Date td_modify_Date;
	private int td_gender;
	private String td_content;
	private String td_birth;
	private int good;
	private int bad;
	
	
	public int getS_num() {
		return s_num;
	}
	public void setS_num(int s_num) {
		this.s_num = s_num;
	}
	public int getDday() {
		return dday;
	}
	public void setDday(int dday) {
		this.dday = dday;
	}
	public String getPhotoname() {
		return photoname;
	}
	public void setPhotoname(String photoname) {
		this.photoname = photoname;
	}
	public int getGo_num() {
		return go_num;
	}
	public void setGo_num(int go_num) {
		this.go_num = go_num;
	}
	public int getGo_member() {
		return go_member;
	}
	public void setGo_member(int go_member) {
		this.go_member = go_member;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGo_startdate() {
		return go_startdate;
	}
	public void setGo_startdate(String go_startdate) {
		this.go_startdate = go_startdate;
	}
	public String getGo_enddate() {
		return go_enddate;
	}
	public void setGo_enddate(String go_enddate) {
		this.go_enddate = go_enddate;
	}
	public String getGo_deadline() {
		return go_deadline;
	}
	public void setGo_deadline(String go_deadline) {
		this.go_deadline = go_deadline;
	}
	public String getGo_area() {
		return go_area;
	}
	public void setGo_area(String go_area) {
		this.go_area = go_area;
	}
	public MultipartFile getGo_photofile1() {
		return go_photofile1;
	}
	public void setGo_photofile1(MultipartFile go_photofile1) throws IOException {
		this.go_photofile1 = go_photofile1;
		setGo_photo1(go_photofile1.getBytes());
	}
	public byte[] getGo_photo1() {
		return go_photo1;
	}
	public void setGo_photo1(byte[] go_photo1) {
		this.go_photo1 = go_photo1;
	}
	public MultipartFile getGo_photofile2() {
		return go_photofile2;
	}
	public void setGo_photofile2(MultipartFile go_photofile2) throws IOException {
		this.go_photofile2 = go_photofile2;
		setGo_photo2(go_photofile2.getBytes());
	}
	public byte[] getGo_photo2() {
		return go_photo2;
	}
	public void setGo_photo2(byte[] go_photo2) {
		this.go_photo2 = go_photo2;
	}
	public MultipartFile getGo_photofile3() {
		return go_photofile3;
	}
	public void setGo_photofile3(MultipartFile go_photofile3) throws IOException {
		this.go_photofile3 = go_photofile3;
		setGo_photo3(go_photofile3.getBytes());
	}
	public byte[] getGo_photo3() {
		return go_photo3;
	}
	public void setGo_photo3(byte[] go_photo3) {
		this.go_photo3 = go_photo3;
	}
	public MultipartFile getGo_photofile4() {
		return go_photofile4;
	}
	public void setGo_photofile4(MultipartFile go_photofile4) throws IOException {
		this.go_photofile4 = go_photofile4;
		setGo_photo4(go_photofile4.getBytes());
	}
	public byte[] getGo_photo4() {
		return go_photo4;
	}
	public void setGo_photo4(byte[] go_photo4) {
		this.go_photo4 = go_photo4;
	}
	public String getGo_gen() {
		return go_gen;
	}
	public void setGo_gen(String go_gen) {
		this.go_gen = go_gen;
	}
	public String getGo_age() {
		return go_age;
	}
	public void setGo_age(String go_age) {
		this.go_age = go_age;
	}
	public String getGo_say() {
		return go_say;
	}
	public void setGo_say(String go_say) {
		this.go_say = go_say;
	}
	public String getGo_type() {
		return go_type;
	}
	public void setGo_type(String go_type) {
		this.go_type = go_type;
	}
	public int getGo_status() {
		return go_status;
	}
	public void setGo_status(int go_status) {
		this.go_status = go_status;
	}
	public String getTd_nickname() {
		return td_nickname;
	}
	public void setTd_nickname(String td_nickname) {
		this.td_nickname = td_nickname;
	}
	public String getTd_password() {
		return td_password;
	}
	public void setTd_password(String td_password) {
		this.td_password = td_password;
	}
	

	public MultipartFile getTd_profilephoto() {
		return td_profilephoto;
	}
	public void setTd_profilephoto(MultipartFile td_profilephoto) {
		this.td_profilephoto = td_profilephoto;
	}
	public byte[] getTd_profile() {
		return td_profile;
	}
	public void setTd_profile(byte[] td_profile) {
		this.td_profile = td_profile;
	}
	public Date getTd_reg_date() {
		return td_reg_date;
	}
	public void setTd_reg_date(Date td_reg_date) {
		this.td_reg_date = td_reg_date;
	}
	public int getTd_score() {
		return td_score;
	}
	public void setTd_score(int td_score) {
		this.td_score = td_score;
	}
	public Date getTd_modify_Date() {
		return td_modify_Date;
	}
	public void setTd_modify_Date(Date td_modify_Date) {
		this.td_modify_Date = td_modify_Date;
	}
	public int getTd_gender() {
		return td_gender;
	}
	public void setTd_gender(int td_gender) {
		this.td_gender = td_gender;
	}
	public String getTd_content() {
		return td_content;
	}
	public void setTd_content(String td_content) {
		this.td_content = td_content;
	}
	public String getTd_birth() {
		return td_birth;
	}
	public void setTd_birth(String td_birth) {
		this.td_birth = td_birth;
	}
	public int getGood() {
		return good;
	}
	public void setGood(int good) {
		this.good = good;
	}
	public int getBad() {
		return bad;
	}
	public void setBad(int bad) {
		this.bad = bad;
	}
	@Override
	public String toString() {
		return "GowithCommand [go_num=" + go_num + ", go_member=" + go_member + ", email=" + email + ", go_startdate="
				+ go_startdate + ", go_enddate=" + go_enddate + ", go_deadline=" + go_deadline + ", go_area=" + go_area
				+ ", go_photofile1=" + go_photofile1 + ", go_photofile2=" + go_photofile2 + ", go_photofile3="
				+ go_photofile3 + ", go_photo3=" + Arrays.toString(go_photo3) + ", go_photofile4=" + go_photofile4
				+ ", photoname=" + photoname + ", go_gen=" + go_gen + ", go_age=" + go_age + ", go_say=" + go_say
				+ ", go_type=" + go_type + ", go_status=" + go_status + ", dday=" + dday + ", td_nickname="
				+ td_nickname + ", td_password=" + td_password + ", td_profilephoto=" + td_profilephoto
				+ ", td_reg_date=" + td_reg_date + ", td_score=" + td_score + ", td_modify_Date=" + td_modify_Date
				+ ", td_gender=" + td_gender + ", td_content=" + td_content + ", td_birth=" + td_birth + ", good="
				+ good + ", bad=" + bad + "]";
	}

	
	}
	

