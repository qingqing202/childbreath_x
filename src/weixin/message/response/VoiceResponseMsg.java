package weixin.message.response;

import weixin.utility.MessageUtil;

public class VoiceResponseMsg extends ResponseMessage {
	public VoiceResponseMsg(){
		setMsgType(MessageUtil.RESP_MESSAGE_TYPE_VOICE);
	}
	private Voice voice = new Voice();
	
	public void setMediaId(String mediaId){
		this.voice.setMediaId(mediaId);
	}
}
