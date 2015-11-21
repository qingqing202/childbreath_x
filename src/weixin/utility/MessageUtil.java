package weixin.utility;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import weixin.message.response.Article;
import weixin.message.response.ImageResponseMsg;
import weixin.message.response.MusicResponseMsg;
import weixin.message.response.NewsResponseMsg;
import weixin.message.response.ResponseMessage;
import weixin.message.response.TextResponseMsg;
import weixin.message.response.VideoResponseMsg;
import weixin.message.response.VoiceResponseMsg;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;

public class MessageUtil {
	
    public static final String RESP_MESSAGE_TYPE_TEXT = "text";  
  
    public static final String RESP_MESSAGE_TYPE_IMAGE = "image";
    
    public static final String RESP_MESSAGE_TYPE_VOICE = "voice";
    
    public static final String RESP_MESSAGE_TYPE_VIDEO = "video";
    
    public static final String RESP_MESSAGE_TYPE_MUSIC = "music";  
  
    public static final String RESP_MESSAGE_TYPE_NEWS = "news";  
  
    public static final String REQ_MESSAGE_TYPE_TEXT = "text";  
  
    public static final String REQ_MESSAGE_TYPE_IMAGE = "image";  
    
    public static final String REQ_MESSAGE_TYPE_VOICE = "voice";
    
    public static final String REQ_MESSAGE_TYPE_VIDEO = "video";
    
    public static final String REQ_MESSAGE_TYPE_LOCATION = "location";
  
    public static final String REQ_MESSAGE_TYPE_LINK = "link";      
  
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";  
  
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";  
  
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";    
    
    public static final String EVENT_TYPE_CLICK = "CLICK";

	private static XStream xstream = new XStream( new XppDriver (){
		@Override
		public HierarchicalStreamWriter createWriter(Writer out){
			return new PrettyPrintWriter(out){
				boolean cdata = false;
				
				@Override
				public void startNode(String name, Class clazz) {  
					if( !name.equals("xml") && !name.equals("item")){
						char[] arr = name.toCharArray();
						if( arr[0]>='a' && arr[0] <='z'){
							arr[0] = (char)(arr[0]-32);
						}
						name = new String(arr);
					}
                    super.startNode(name, clazz);  
                }  
				
				@Override
				public void setValue(String text) {
					if (text != null && !"".equals(text)) {
						// 浮点型判断
						Pattern patternInt = Pattern
								.compile("[0-9]*(\\.?)[0-9]*");
						// 整型判断
						Pattern patternFloat = Pattern.compile("[0-9]+");
						// 如果是整数或浮点数 就不要加[CDATA[]了
						if (patternInt.matcher(text).matches()
								|| patternFloat.matcher(text).matches()) {
							cdata = false;
						} else {
							cdata = true;
						}
					}
					super.setValue(text);
				}
  
				@Override
				protected void writeText(QuickWriter writer, String text) {
					if(cdata){
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					}else{
						writer.write(text);
					}
				}
			};
		}
	});
	
	public static Map<String, String> parseXml(HttpServletRequest request){		
		try {
			Map<String, String> map = new HashMap<String, String>();
			InputStream inputStream = request.getInputStream();
			SAXReader reader = new SAXReader();
			Document document = reader.read(inputStream);
			Element root = document.getRootElement();
			List<Element> eleList = root.elements();
			for( Element e : eleList ){
				map.put( e.getName(), e.getText() );
			}
			
			inputStream.close();
			inputStream = null;
			return map;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch ( Exception e ){
			e.printStackTrace();
			return null;
		}
	}
	
	public static String ResponseMessageToXml( ResponseMessage resp ){
		try{
			if( resp.getMsgType().equals(RESP_MESSAGE_TYPE_IMAGE))
				return ImageMessageToXml( (ImageResponseMsg) resp );
			if( resp.getMsgType().equals(RESP_MESSAGE_TYPE_MUSIC))
				return MusicMessageToXml( (MusicResponseMsg) resp );
			if( resp.getMsgType().equals(RESP_MESSAGE_TYPE_NEWS))
				return NewsMessageToXml( (NewsResponseMsg) resp );
			if( resp.getMsgType().equals(RESP_MESSAGE_TYPE_TEXT))
				return TextMessageToXml( (TextResponseMsg) resp );
			if( resp.getMsgType().equals(RESP_MESSAGE_TYPE_VIDEO))
				return VideoMessageToXml( (VideoResponseMsg) resp );
			if( resp.getMsgType().equals(RESP_MESSAGE_TYPE_VOICE))
				return VoiceMessageToXml( (VoiceResponseMsg) resp );
			return null;
		}catch( Exception e ){
			e.printStackTrace();
			return null;
		}
	}
	
	public static String TextMessageToXml( TextResponseMsg msg ){
		xstream.alias("xml", TextResponseMsg.class);
		return xstream.toXML( msg );
	}
	
	public static String ImageMessageToXml( ImageResponseMsg msg ){
		xstream.alias("xml", ImageResponseMsg.class);
		return xstream.toXML( msg );
	}
	
	public static String VoiceMessageToXml( VoiceResponseMsg msg ){
		xstream.alias("xml", VoiceResponseMsg.class);
		return xstream.toXML( msg );
	}
	
	public static String VideoMessageToXml( VideoResponseMsg msg ){
		xstream.alias("xml", VideoResponseMsg.class);
		return xstream.toXML( msg );
	}
	
	public static String MusicMessageToXml( MusicResponseMsg msg ){
		xstream.alias("xml", MusicResponseMsg.class);
		return xstream.toXML( msg );		
	}
	
	public static String NewsMessageToXml( NewsResponseMsg msg ){
		xstream.alias("xml", NewsResponseMsg.class);
		xstream.alias("item", Article.class);
		return xstream.toXML( msg );
	}
}
