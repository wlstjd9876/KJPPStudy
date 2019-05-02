package kr.spring.location.domain;

public class ChartCommand {
	
	//여행 스타일
	private int countstyle;
	private int s_style; //-> String으로 어떻게,,,?
	
	//여행 타입
	private String go_type;
	private int count;
	//여행 시기
	private String startdate;
	private int countmonth;
		
	public String getGo_type() {
		return go_type;
	}
	public void setGo_type(String go_type) {
		this.go_type = go_type;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
		
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public int getCountmonth() {
		return countmonth;
	}
	public void setCountmonth(int countmonth) {
		this.countmonth = countmonth;
	}
	
	public int getCountstyle() {
		return countstyle;
	}
	public void setCountstyle(int countstyle) {
		this.countstyle = countstyle;
	}
	public int getS_style() {
		return s_style;
	}
	public void setS_style(int s_style) {
		this.s_style = s_style;
	}
	
	@Override
	public String toString() {
		return "ChartCommand [countstyle=" + countstyle + ", s_style=" + s_style + ", go_type=" + go_type + ", count="
				+ count + ", startdate=" + startdate + ", countmonth=" + countmonth + "]";
	}
	
}
