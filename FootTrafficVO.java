
public class FootTrafficVO {
	
	
	private int room;
	private int visitorNo;
	private String visitorStatus;
	private int inOutTime;
	private int duration;
	
	public FootTrafficVO(){
		
	}
	
	public FootTrafficVO(int room,int visitorNo,String visitorStatus,int  inOutTime){
		this.room=room;
		this.visitorNo=visitorNo;
		this.visitorStatus=visitorStatus;
		this.inOutTime=inOutTime;
	}
	/**
	 * @return the room
	 */
	public int getRoom() {
		return room;
	}
	/**
	 * @param room the room to set
	 */
	public void setRoom(int room) {
		this.room = room;
	}
	/**
	 * @return the visitorNo
	 */
	public int getVisitorNo() {
		return visitorNo;
	}
	/**
	 * @param visitorNo the visitorNo to set
	 */
	public void setVisitorNo(int visitorNo) {
		this.visitorNo = visitorNo;
	}
	/**
	 * @return the visitorStatus
	 */
	public String getVisitorStatus() {
		return visitorStatus;
	}
	/**
	 * @param visitorStatus the visitorStatus to set
	 */
	public void setVisitorStatus(String visitorStatus) {
		this.visitorStatus = visitorStatus;
	}
	/**
	 * @return the inOutTime
	 */
	public int getInOutTime() {
		return inOutTime;
	}
	/**
	 * @param inOutTime the inOutTime to set
	 */
	public void setInOutTime(int inOutTime) {
		this.inOutTime = inOutTime;
	}
	/**
	 * @return the duration
	 */
	public int getDuration() {
		return duration;
	}
	/**
	 * @param duration the duration to set
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "FootTrafficVO [room=" + room + ", visitorNo=" + visitorNo
				+ ", visitorStatus=" + visitorStatus + ", inOutTime="
				+ inOutTime + ", duration=" + duration + "]";
	}

	 
}
