package kr.spring.goapp.domain;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.web.multipart.MultipartFile;

public class GoappCommand {
	private int app_num;
	private String email;
	private int app_member;
	private String app_gen;
	private MultipartFile app_photofile;
	private byte[] app_photo;
	private String photoname;
	private String app_why;
	private int go_num;
	private int app_status;
	private int check;
	private int go_status;
	private String w_email; //동행글 작성자 이메일
	
	private String td_nickname;
	
	
	
	public String getW_email() {
		return w_email;
	}
	public void setW_email(String w_email) {
		this.w_email = w_email;
	}
	public void setApp_photofile(MultipartFile app_photofile) throws IOException {
		this.app_photofile = app_photofile;
		setApp_photo(app_photofile.getBytes());
	}
	public int getGo_status() {
		return go_status;
	}
	public void setGo_status(int go_status) {
		this.go_status = go_status;
	}
	public int getCheck() {
		return check;
	}

	public void setCheck(int check) {
		this.check = check;
	}
	
	public String getTd_nickname() {
		return td_nickname;
	}

	public void setTd_nickname(String td_nickname) {
		this.td_nickname = td_nickname;
	}

	public int getApp_num() {
		return app_num;
	}
	public void setApp_num(int app_num) {
		this.app_num = app_num;
	}
	public String getPhotoname() {
		return photoname;
	}
	public void setPhotoname(String photoname) {
		this.photoname = photoname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getApp_member() {
		return app_member;
	}
	public void setApp_member(int app_member) {
		this.app_member = app_member;
	}
	public String getApp_gen() {
		return app_gen;
	}
	public void setApp_gen(String app_gen) {
		this.app_gen = app_gen;
	}
	public MultipartFile getApp_photofile() {
		return app_photofile;
	}
	
	public byte[] getApp_photo() {
		return app_photo;
	}
	public void setApp_photo(byte[] app_photo) {
		this.app_photo = app_photo;
	}
	public String getApp_why() {
		return app_why;
	}
	public void setApp_why(String app_why) {
		this.app_why = app_why;
	}
	public int getGo_num() {
		return go_num;
	}
	public void setGo_num(int go_num) {
		this.go_num = go_num;
	}
	public int getApp_status() {
		return app_status;
	}
	public void setApp_status(int app_status) {
		this.app_status = app_status;
	}
	@Override
	public String toString() {
		return "GoappCommand [app_num=" + app_num + ", email=" + email + ", app_member=" + app_member + ", app_gen="
				+ app_gen + ", app_photofile=" + app_photofile + ", photoname=" + photoname + ", app_why=" + app_why
				+ ", go_num=" + go_num + ", app_status=" + app_status + ", check=" + check + ", go_status=" + go_status
				+ ", w_email=" + w_email + ", td_nickname=" + td_nickname + "]";
	}


}
