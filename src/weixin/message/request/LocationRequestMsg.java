package weixin.message.request;

import weixin.utility.MessageUtil;

public class LocationRequestMsg extends RequestMessage {
	public LocationRequestMsg(){
		setMsgType(MessageUtil.REQ_MESSAGE_TYPE_LOCATION);
	}
	/**
	 * 地理位置维度
	 */
	private float location_X;
	/**
	 * 地理位置经度
	 */
	private float location_Y;
	/**
	 * 地图缩放大小
	 */
	private int scale;
	/**
	 * 地理位置信息
	 */
	private String label;
	public float getLocation_X() {
		return location_X;
	}
	public void setLocation_X(float location_X) {
		this.location_X = location_X;
	}
	public float getLocation_Y() {
		return location_Y;
	}
	public void setLocation_Y(float location_Y) {
		this.location_Y = location_Y;
	}
	public int getScale() {
		return scale;
	}
	public void setScale(int scale) {
		this.scale = scale;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
}
