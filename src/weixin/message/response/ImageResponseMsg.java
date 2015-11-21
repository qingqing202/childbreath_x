package weixin.message.response;

import weixin.utility.MessageUtil;

public class ImageResponseMsg extends ResponseMessage {
	public ImageResponseMsg(){
		setMsgType(MessageUtil.RESP_MESSAGE_TYPE_IMAGE);
	}
	/**
	 * 图片对象属性
	 */
	private Image image = new Image();
	
	public void setMediaId(String mediaId){
		this.image.setMediaId(mediaId);
	}
	
}
