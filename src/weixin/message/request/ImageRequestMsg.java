package weixin.message.request;

import weixin.utility.MessageUtil;

public class ImageRequestMsg extends RequestMessage {
	public ImageRequestMsg(){
		setMsgType(MessageUtil.REQ_MESSAGE_TYPE_IMAGE);
	}
	/**
	 * 图片链接
	 */
	private String picUrl;
	/**
	 * 图片消息媒体id，可以调用多媒体文件下载接口拉取数据
	 */
	private String mediaId;
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	
	
}
