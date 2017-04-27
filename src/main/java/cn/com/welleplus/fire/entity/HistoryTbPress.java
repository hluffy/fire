package cn.com.welleplus.fire.entity;

public class HistoryTbPress {

	private int station;
	//	private float PressCurrent;
	private float Press;
	private float Battvoltage;
	private int status;
	private String tm;

	public HistoryTbPress() {
		super();
	}

	public HistoryTbPress(int station, float press, float battvoltage, int status, String tm) {
		super();
		this.station = station;
		//		PressCurrent = pressCurrent;
		Press = press;
		Battvoltage = battvoltage;
		this.status = status;
		this.tm = tm;
	}

	public int getStation() {
		return station;
	}

	public void setStation(int station) {
		this.station = station;
	}

	//	public float getPressCurrent() {
	//		return PressCurrent;
	//	}
	//
	//	public void setPressCurrent(float pressCurrent) {
	//		PressCurrent = pressCurrent;
	//	}

	public float getPress() {
		return Press;
	}

	public void setPress(float press) {
		Press = press;
	}

	public float getBattvoltage() {
		return Battvoltage;
	}

	public void setBattvoltage(float battvoltage) {
		Battvoltage = battvoltage;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTm() {
		return tm;
	}

	public void setTm(String tm) {
		this.tm = tm;
	}

	@Override
	public String toString() {
		return "{\"station\":" + station + ", \"Press\":" + Press
				+ ", \"Battvoltage\":" + Battvoltage + ", \"status\":" + status + ", \"tm\":\"" + tm + "\"}";
	}

}
