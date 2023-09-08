package kr.ac.kopo.vo;

import org.springframework.stereotype.Component;

@Component
public class ExerciseVO {
	// 바꾸지 말고 추가할것
	private long eNo;
	private String userId;
	private String type;
	private int cnt;
	private int avgCnt;
	private int maxCnt;
	private String endTime;
	
	public long geteNo() {
		return eNo;
	}
	public void seteNo(long eNo) {
		this.eNo = eNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public int getAvgCnt() {
		return avgCnt;
	}
	public void setAvgCnt(int avgCnt) {
		this.avgCnt = avgCnt;
	}
	public int getMaxCnt() {
		return maxCnt;
	}
	public void setMaxCnt(int maxCnt) {
		this.maxCnt = maxCnt;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	@Override
	public String toString() {
		return "ExerciseVO [eNo=" + eNo + ", userId=" + userId + ", type=" + type + ", cnt=" + cnt + ", avgCnt=" + avgCnt
				+ ", maxCnt=" + maxCnt + ", endTime=" + endTime + "]";
	}
	
	
}
