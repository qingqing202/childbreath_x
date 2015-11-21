package weixin.message.request;

import weixin.utility.MessageUtil;

public class VideoRequestMsg extends RequestMessage {
	public VideoRequestMsg(){
		setMsgType(MessageUtil.REQ_MESSAGE_TYPE_VIDEO);
	}
	/**
	 * 视频消息媒体id，可以调用多媒体文件下载接口拉取数据
	 */
	private String mediaId;
	/**
	 * 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据
	 */
	private String thumbMediaId;
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	public String getThumbMediaId() {
		return thumbMediaId;
	}
	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}
	
}
