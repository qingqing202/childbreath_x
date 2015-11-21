package weixin.message.response;

import weixin.utility.MessageUtil;

public class MusicResponseMsg extends ResponseMessage {
	public MusicResponseMsg(){
		setMsgType(MessageUtil.RESP_MESSAGE_TYPE_MUSIC);
	}
	private Music music = new Music();
	
	public void setTitle(String title) {
		this.music.setTitle(title);
	}
	
	public void setDescription(String description) {
		this.music.setDescription(description);
	}
	
	public void setMusicUrl(String musicUrl) {
		this.music.setMusicUrl(musicUrl);
	}
	
	public void setHQMusicUrl(String hQMusicUrl) {
		this.music.setHQMusicUrl(hQMusicUrl);
	}
	
	public void setThumbMediaId(String thumbMediaId) {
		this.music.setThumbMediaId(thumbMediaId);
	}
}
