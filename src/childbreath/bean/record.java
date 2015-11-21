package childbreath.bean;

import java.util.Date;

import org.springframework.stereotype.Component;

import codeman.util.DTF;

@Component
public class record {
	private String weixin_openid = null;
	private String name = null;
	private String admission_number = null;
	private String disease = null;
	private Date inTime = null;
	private long inDays = 0;
	private Date leaveTime = null;
	private long leaveDays = 0;
	
	public String getWeixin_openid() {
		return weixin_openid;
	}
	public void setWeixin_openid(String weixin_openid) {
		this.weixin_openid = weixin_openid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAdmission_number() {
		return admission_number;
	}
	public void setAdmission_number(String admission_number) {
		this.admission_number = admission_number;
	}
	public String getInTime() {
		return DTF.DateToString(inTime);
	}
	public void setInTime(Date inTime) {
		this.inTime = inTime;
		Date now = new Date();
		System.out.println("now:"+DTF.DateToString(now));
		System.out.println("inTime:"+DTF.DateToString(this.inTime));
		long total = now.getTime() - this.inTime.getTime();
		if( total >= 0 ){
			setInDays( total/(24*3600*1000) );
		}
	}
	public long getInDays() {
		return inDays;
	}
	public void setInDays(long inDays) {
		this.inDays = inDays;
	}
	public String getLeaveTime() {
		return DTF.DateToString(leaveTime);
	}
	public void setLeaveTime(Date leaveTime) {
		this.leaveTime = leaveTime;
		Date now = new Date();
		long total = now.getTime() - this.leaveTime.getTime();
		if( total >= 0 ){
			setLeaveDays( total/(24*3600*1000) );
		}
	}
	public long getLeaveDays() {
		return leaveDays;
	}
	public void setLeaveDays(long leaveDays) {
		this.leaveDays = leaveDays;
	}
	public String getDisease() {
		return disease;
	}
	public void setDisease(String disease) {
		this.disease = disease;
	}
}
