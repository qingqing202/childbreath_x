package weixin.message.request;

import weixin.utility.MessageUtil;

public class VoiceRequestMsg extends RequestMessage {
	public VoiceRequestMsg(){
		setMsgType(MessageUtil.REQ_MESSAGE_TYPE_VOICE);
	}
	/**
	 * 语音消息媒体id，可以调用多媒体文件下载接口拉取数据
	 */
	private String mediaId;
	/**
	 * 语音格式，如amr，speex等
	 */
	private String fromat;
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	public String getFromat() {
		return fromat;
	}
	public void setFromat(String fromat) {
		this.fromat = fromat;
	}
	
}
