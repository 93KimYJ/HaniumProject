package kr.ac.kopo.vo;

import org.springframework.stereotype.Component;

@Component
public class UserVO {
	private String userId;
	private String password;
	private String email;
	private String name;
	private String phoneNum;
	private String birthDate;
	private String addr;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String id) {
		this.userId = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	@Override
	public String toString() {
		return "UserVO [id=" + userId + ", password=" + password + ", email=" + email + ", name=" + name + ", phoneNum="
				+ phoneNum + ", birthDate=" + birthDate + ", addr=" + addr + "]";
	}
	
}
