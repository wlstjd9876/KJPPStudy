package kr.spring.member.domain;

import java.io.IOException;
import java.sql.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

public class MemberCommand {
	private int t_auth;//0.일반회원, 1.스태프, 2.관리자, 3.탈퇴회원
	private int td_num;
	@Email
	@NotEmpty
	private String email;
	@NotEmpty
	private String td_nickname;
	@Size(min=4,max=10)
	private String td_password;
	private MultipartFile upload;//업로드 파일
	private byte[] td_profile;//DB에 저장된 파일 //byte는 ToString 할시 너무 느려짐
	private int td_score;
	private Date td_reg_date;
	private Date td_modify_date;
	@Range(min=1,max=2)//1은 남자, 2는 여자
	private int td_gender;
	@NotEmpty
	private String td_content;
	@NotEmpty
	private String td_birth;
	private int td_good;
	private int td_bad;

	private String old_passwd;

	//비밀번호 일치 여부 체크
	public boolean isCheckedPasswd(String userPasswd) {
		if(t_auth!=3 && td_password.equals(userPasswd)) {
			return true;
		}
		return false;
	}

	//MultipartFile -> byte[]변환
	public void setUpload(MultipartFile upload) throws IOException {
		this.upload = upload;
		//byte[] 데이터 저장
		setTd_profile(upload.getBytes());//byte배열로 바꿔서 저장
	}
	public MultipartFile getUpload() {
		return upload;
	}
	
	public byte[] getTd_profile() {
		return td_profile;
	}

	public void setTd_profile(byte[] td_profile) {
		this.td_profile = td_profile;
	}
	public int getT_auth() {
		return t_auth;
	}

	public void setT_auth(int t_auth) {
		this.t_auth = t_auth;
	}

	public int getTd_num() {
		return td_num;
	}

	public void setTd_num(int td_num) {
		this.td_num = td_num;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public int getTd_score() {
		return td_score;
	}

	public void setTd_score(int td_score) {
		this.td_score = td_score;
	}

	public Date getTd_reg_date() {
		return td_reg_date;
	}

	public void setTd_reg_date(Date td_reg_date) {
		this.td_reg_date = td_reg_date;
	}

	public Date getTd_modify_date() {
		return td_modify_date;
	}

	public void setTd_modify_date(Date td_modify_date) {
		this.td_modify_date = td_modify_date;
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

	public int getTd_good() {
		return td_good;
	}

	public void setTd_good(int td_good) {
		this.td_good = td_good;
	}

	public int getTd_bad() {
		return td_bad;
	}

	public void setTd_bad(int td_bad) {
		this.td_bad = td_bad;
	}

	public String getOld_passwd() {
		return old_passwd;
	}

	public void setOld_passwd(String old_passwd) {
		this.old_passwd = old_passwd;
	}
	
	//★주의 byte[]의 uploadfile은 제외!!! ★
	@Override
	public String toString() {
		return "MemberCommand [t_auth=" + t_auth  + ", email=" + email
				+ ", td_nickname=" + td_nickname + ", td_password=" + td_password + ", upload=" + upload + ", td_score="
				+ td_score + ", td_reg_date=" + td_reg_date + ", td_modify_date=" + td_modify_date + ", td_gender="
				+ td_gender + ", td_content=" + td_content + ", td_birth=" + td_birth + ", td_good=" + td_good
				+ ", td_bad=" + td_bad + ", old_passwd=" + old_passwd + "]";
	}
	//-> 바이트 배열 컬럼(uploadfile)은 toString() generate시 제외함, 배열에서 데이터를 꺼내느라 속도 느려지기 때문

}
