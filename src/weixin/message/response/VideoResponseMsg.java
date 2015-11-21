package weixin.message.response;

import weixin.utility.MessageUtil;

public class VideoResponseMsg extends ResponseMessage {
	public VideoResponseMsg(){
		setMsgType(MessageUtil.RESP_MESSAGE_TYPE_VIDEO);
	}
	private Video video = new Video();
	
	public void setMediaId(String mediaId) {
		this.video.setMediaId(mediaId);
	}

	public void setTitle(String title) {
		this.video.setTitle(title);
	}

	public void setDescription(String description) {
		this.video.setDescription(description);
	}
}
