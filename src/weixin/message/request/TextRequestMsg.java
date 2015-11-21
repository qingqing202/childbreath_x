package weixin.message.request;

import weixin.utility.MessageUtil;

public class TextRequestMsg extends RequestMessage {
	public TextRequestMsg(){
		setMsgType(MessageUtil.REQ_MESSAGE_TYPE_TEXT);
	}
	/**
	 * 文本消息内容
	 */
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
