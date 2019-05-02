package kr.spring.location.domain;

public class LocationCommand {

	private String mapx;
	private String mapy;
	public String getMapx() {
		return mapx;
	}
	public void setMapx(String mapx) {
		this.mapx = mapx;
	}
	public String getMapy() {
		return mapy;
	}
	public void setMapy(String mapy) {
		this.mapy = mapy;
	}
	@Override
	public String toString() {
		return "LocationCommand [mapx=" + mapx + ", mapy=" + mapy + "]";
	}
	
	
	
}
