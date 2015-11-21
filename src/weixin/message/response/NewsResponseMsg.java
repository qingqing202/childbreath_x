package weixin.message.response;

import java.util.ArrayList;
import java.util.List;

import weixin.utility.MessageUtil;

public class NewsResponseMsg extends ResponseMessage {
	public NewsResponseMsg(){
		setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
	}
	private int articleCount = 0;
	private List<Article> articles = new ArrayList<Article>();
	
	public void addArticle( Article article ){
		articles.add( article );
		articleCount = articles.size();
	}
}
