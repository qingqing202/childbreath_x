package childbreath.service;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import weixin.message.response.Article;
import weixin.message.response.ImageResponseMsg;
import weixin.message.response.MusicResponseMsg;
import weixin.message.response.NewsResponseMsg;
import weixin.message.response.ResponseMessage;
import weixin.message.response.TextResponseMsg;
import weixin.message.response.VideoResponseMsg;
import weixin.message.response.VoiceResponseMsg;
import weixin.utility.MessageUtil;

public class WeixinMessageProcess {
	public static String ProcessRequest(HttpServletRequest request){
		try{
			String respContent = "请求处理异常,请稍候再试";
			
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			
			String fromUserName = requestMap.get("FromUserName");
			
			String toUserName = requestMap.get("ToUserName");
			
			String msgType = requestMap.get("MsgType");
			
			
			ResponseMessage res = null;
			
			  
			// 文本消息  
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {  
                res = TextResp( requestMap ); 
            }  
            // 图片消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {  
            	res = ProcessImageRequest( requestMap );
            }
            //语音
            else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)){
            	res = ProcessVoiceRequest( requestMap );
            }
            //视频
            else if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)){
            	res = ProcessVideoRequest( requestMap );
            }
            // 地理位置消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {  
                respContent = "您发送的是地理位置消息";  
            }  
            // 链接消息  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {  
                respContent = "您发送的是链接消息";  
            }  
            
            // 事件推送  
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {  
                // 事件类型  
                String eventType = requestMap.get("Event");  
                // 订阅  
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {  
                	res = CreateTextResponse("感谢您的关注,本订阅号目前只用于开发调试.输入help查询支持的命令");
                }  
                // 取消订阅  
                else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {  
                    // 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息  
                }  
                // 自定义菜单点击事件  
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {  
                    // 自定义菜单权没有开放，暂不处理该类消息  
                }  
            }
            if( res == null )
            	return null;
            
            res.setToUserName(fromUserName);
            res.setFromUserName(toUserName);
            res.setCreatTime( new Date().getTime());
			return MessageUtil.ResponseMessageToXml(res);
		}catch(Exception e ){
			e.printStackTrace();
			return null;
		}
	}
	
	public static TextResponseMsg CreateTextResponse(String content){
		TextResponseMsg res = new TextResponseMsg();
		res.setContent(content);
		return res;
	}
	
	public static ResponseMessage TextResp(Map<String, String> requestMap){
		String reqContent = requestMap.get("Content");
		if( reqContent.equalsIgnoreCase("help")){
			TextResponseMsg res = CreateTextResponse("help-查询支持的命令\n"
					+ "photo-随机取得一张图片\n"
					+ "voice-随机取得一段语音\n"
					+ "music-听音乐\n"
					+ "news-查看新闻\n");
			return res;
		}
		if( reqContent.equalsIgnoreCase("photo") || reqContent.equalsIgnoreCase("photo -new")){
			String mediaId = null;
			if( reqContent.equalsIgnoreCase("photo") ){
				mediaId = MediaCache.getInstance().GetRandImage();
			}else if(reqContent.equalsIgnoreCase("photo -new")){
				mediaId = MediaCache.getInstance().GetLastImage();
			}
			if( mediaId != null ){
				ImageResponseMsg res = new ImageResponseMsg();
				res.setMediaId(mediaId);
				return res;
			}else{
				TextResponseMsg res = CreateTextResponse("目前服务器中没有图片,欢迎您分享图片");
				return res;
			}
		}
		if( reqContent.equalsIgnoreCase("voice") || reqContent.equalsIgnoreCase("voice -new")){
			String mediaId = null;
			if( reqContent.equalsIgnoreCase("voice") ){
				mediaId = MediaCache.getInstance().GetRandVoice();
			}else if(reqContent.equalsIgnoreCase("voice -new")){
				mediaId = MediaCache.getInstance().GetLastVoice();
			}
			if( mediaId != null ){
				VoiceResponseMsg res = new VoiceResponseMsg();
				res.setMediaId(mediaId);
				return res;
			}else{
				TextResponseMsg res = CreateTextResponse("目前服务器中没有语音,欢迎您发送语音");
				return res;
			}
		}
		if( reqContent.equalsIgnoreCase("video") || reqContent.equalsIgnoreCase("video -new")){
			MediaInfo media = null;
			if( reqContent.equalsIgnoreCase("video") ){
				media = MediaCache.getInstance().GetRandVideo();
			}else if(reqContent.equalsIgnoreCase("video -new")){
				media = MediaCache.getInstance().GetLastVideo();
			}
			if( media != null ){
				VideoResponseMsg res = new VideoResponseMsg();
				res.setMediaId(media.getMediaId());
				res.setTitle(media.getTitle());
				res.setDescription(media.getDescription());
				return res;
			}else{
				TextResponseMsg res = CreateTextResponse("目前服务器中没有视频,欢迎您发送视频");
				return res;
			}
		}
		if( reqContent.equalsIgnoreCase("music")){
			MusicResponseMsg res = new MusicResponseMsg();
			res.setMusicUrl("http://partytime.duapp.com/music/121367437219600128.mp3");
			res.setHQMusicUrl("http://partytime.duapp.com/music/121367437219600128.mp3");
			res.setTitle("后会无期");
			res.setDescription("电影<<后会无期>>插曲,邓紫棋演唱"); 
			//res.setThumbMediaId(MediaCache.getInstance().GetRandImage());
			return res;
		}
		if( reqContent.equalsIgnoreCase("news")){
			NewsResponseMsg res = new NewsResponseMsg();
			Article art = new Article();
			art.setTitle("IS宣称斩首1名日本人质 多国对其空袭数月无进展");
			art.setDescription("一段疑似由极端组织“伊斯兰国”上传互联网的视频称，日本人质汤川遥菜已经遇害。日本广播协会和时事通讯社报道，这段视频出现于日本时间24日23时左右。");
			art.setPicUrl("http://partytime.duapp.com/image/news1.jpg");
			art.setUrl("http://news.qq.com/a/20150125/003200.htm");
			res.addArticle(art);
			
			art = new Article();
			art.setTitle("中央政法工作会议新意：三个首提四大转变");
			art.setDescription("中央政法工作会议日前在北京召开，中共中央总书记、国家主席、中央军委主席习近平就政法工作作出重要指示。");
			art.setPicUrl("http://partytime.duapp.com/image/news2.jpg");
			art.setUrl("http://news.qq.com/a/20150123/040780.htm");
			res.addArticle(art);
			
			return res;
		}
		
		TextResponseMsg res = CreateTextResponse("已经收到您发送的消息.(输入help可查看支持的命令).");
		return res;
	}
	
	public static ResponseMessage ProcessImageRequest(Map<String, String> requestMap){
		String mediaId = requestMap.get("MediaId");
		MediaCache.getInstance().AddImage(mediaId);
		
		TextResponseMsg res = CreateTextResponse("已经收到您发送的图片.(输入photo可随机查看一张图片,输入photo -new可查看最新的一张图片)");
		return res;
	}
	
	public static ResponseMessage ProcessVoiceRequest(Map<String, String> requestMap){
		String mediaId = requestMap.get("MediaId");
		MediaCache.getInstance().AddVoice(mediaId);
		
		TextResponseMsg res = CreateTextResponse("已经收到您发送的语音.(输入voice可随机收听一段语音,输入voice -new可查看最新的一段语音)");
		return res;
	}
	
	public static ResponseMessage ProcessVideoRequest(Map<String, String> requestMap){
		MediaCache.getInstance().AddVideo(requestMap.get("MediaId"), requestMap.get("FromUserName"));
		
		TextResponseMsg res = CreateTextResponse("已经收到您发送的视频.(输入video可随机收看一段视频,输入video -new可查看最新的一段视频)");
		return res;
	}
}
