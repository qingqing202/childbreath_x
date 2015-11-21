package weixin.message.response;

import weixin.utility.MessageUtil;

public class TextResponseMsg extends ResponseMessage {
	public TextResponseMsg(){
		setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
	}
	/**
	 * 回复的消息内容
	 */
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
