package kr.spring.advice.domain;

import org.hibernate.validator.constraints.NotEmpty;

public class AdviceReplyDetailCommand {
	private int ad_num;  //��� �� ���� �۹�ȣ
	@NotEmpty
	private int ad_code;  //������ �ڵ�
	@NotEmpty
	private int ad_day;  //�� ��
	@NotEmpty
	private String starttime;  //���۽ð�
	@NotEmpty
	private String endtime;  //����ð�
	private String ad_memo;  //�޸�
	private int ar_num;   //��� ��ȣ
	private int ad_money;  //���
	
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
	public int getAr_num() {
		return ar_num;
	}
	public void setAr_num(int ar_num) {
		this.ar_num = ar_num;
	}
	public int getAd_money() {
		return ad_money;
	}
	public void setAd_money(int ad_money) {
		this.ad_money = ad_money;
	}
	@Override
	public String toString() {
		return "AdviceReplyDetailCommand [ad_num=" + ad_num + ", ad_code=" + ad_code + ", ad_day=" + ad_day
				+ ", starttime=" + starttime + ", endtime=" + endtime + ", ad_memo=" + ad_memo + ", ar_num=" + ar_num
				+ ", ad_money=" + ad_money + "]";
	}
}
