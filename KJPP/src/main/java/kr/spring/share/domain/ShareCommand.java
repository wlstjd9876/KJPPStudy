package kr.spring.share.domain;

import java.io.IOException;
import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class ShareCommand {
	private int num;
	private String title;
	private String content;
	private Date reg_date;
	private Date modify_date;
	private String email;
	private MultipartFile thumbfile;
	private byte[] thumb;
	private MultipartFile photofile2;
	private byte[] photo2;
	private MultipartFile photofile3;
	private byte[] photo3;
	private int s_num;
	private String startdate;
	
	private String td_nickname;
	
	private int re_cnt; //댓글 수

	
	
	
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getTd_nickname() {
		return td_nickname;
	}
	public void setTd_nickname(String td_nickname) {
		this.td_nickname = td_nickname;
	}
	//MultipartFile -> byte[]변환
	public void setThumbfile(MultipartFile thumbfile) throws IOException {
		this.thumbfile = thumbfile;
		//byte[] 데이터 저장
		setThumb(thumbfile.getBytes());//byte배열로 바꿔서 저장
	}
	public MultipartFile getThumbfile() {
		return thumbfile;
	}
	public byte[] getThumb() {
		return thumb;
	}

	public void setThumb(byte[] thumb) {
		this.thumb = thumb;
	}
	//MultipartFile -> byte[]변환
	public void setPhotofile2(MultipartFile photofile2) throws IOException {
		this.photofile2 = photofile2;
		//byte[] 데이터 저장
		setPhoto2(photofile2.getBytes());//byte배열로 바꿔서 저장
	}
	public MultipartFile getPhotofile2() {
		return photofile2;
	}
	public byte[] getPhoto2() {
		return photo2;
	}

	public void setPhoto2(byte[] photo2) {
		this.photo2 = photo2;
	}
	//MultipartFile -> byte[]변환
	public void setPhotofile3(MultipartFile photofile3) throws IOException {
		this.photofile3 = photofile3;
		//byte[] 데이터 저장
		setPhoto3(photofile3.getBytes());//byte배열로 바꿔서 저장
	}
	public MultipartFile getPhotofile3() {
		return photofile3;
	}
	public byte[] getPhoto3() {
		return photo3;
	}

	public void setPhoto3(byte[] photo3) {
		this.photo3 = photo3;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public Date getModify_date() {
		return modify_date;
	}
	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getS_num() {
		return s_num;
	}
	public void setS_num(int s_num) {
		this.s_num = s_num;
	}
	
	
	public int getRe_cnt() {
		return re_cnt;
	}
	public void setRe_cnt(int re_cnt) {
		this.re_cnt = re_cnt;
	}
	@Override
	public String toString() {
		return "ShareCommand [num=" + num + ", title=" + title + ", content=" + content + ", reg_date=" + reg_date
				+ ", modify_date=" + modify_date + ", email=" + email + ", thumbfile=" + thumbfile + ", photofile2="
				+ photofile2 + ", photofile3=" + photofile3 + ", s_num=" + s_num + ", re_cnt=" + re_cnt + "]";
	}
	
}
