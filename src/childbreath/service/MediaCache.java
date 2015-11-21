package childbreath.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class MediaCache {
	
	
	private MediaCache(){
		
	}
	
	private static MediaCache m_instance = new MediaCache();
	public static synchronized MediaCache getInstance(){
		return m_instance;
	}
	
	private void ClearTimeoutMedia( List<MediaInfo> medias){
		if( medias != null ){
			long nowtime = new Date().getTime();
			for( Iterator<MediaInfo> it = medias.iterator(); it.hasNext(); ){
				MediaInfo info = it.next();
				if( nowtime - info.getCreateTime() > 259200000 ){
					//超过3天
					it.remove();
				}else{
					break;
				}
			}
		}
	}
	
	//图片
	private List<MediaInfo> m_ImageMedias = new ArrayList<MediaInfo>();
	public void AddImage( String mediaId ){
		MediaInfo info = new MediaInfo();
		info.setCreateTime( new Date().getTime() );
		info.setMediaId(mediaId);
		m_ImageMedias.add( info );
	}
	public String GetRandImage(){
		ClearTimeoutMedia(m_ImageMedias);
		
		int count = m_ImageMedias.size();
		if( count == 0 )
			return null;
		Random rd = new Random( new Date().getTime() );
		int index = rd.nextInt(count);
		return m_ImageMedias.get( index ).getMediaId();
	}
	public String GetLastImage(){
		ClearTimeoutMedia(m_ImageMedias);
		if( m_ImageMedias.size() == 0 )
			return null;
		return m_ImageMedias.get( m_ImageMedias.size()-1 ).getMediaId() ;
	}
	
	//语音
	private List<MediaInfo> m_VoiceMedias = new ArrayList<MediaInfo>();
	public void AddVoice( String mediaId ){
		MediaInfo info = new MediaInfo();
		info.setCreateTime( new Date().getTime() );
		info.setMediaId(mediaId);
		m_VoiceMedias.add( info );
	}
	public String GetRandVoice(){
		ClearTimeoutMedia(m_VoiceMedias);
		
		int count = m_VoiceMedias.size();
		if( count == 0 )
			return null;
		Random rd = new Random( new Date().getTime() );
		int index = rd.nextInt(count);
		return m_VoiceMedias.get( index ).getMediaId();
	}
	public String GetLastVoice(){
		ClearTimeoutMedia(m_VoiceMedias);
		if( m_VoiceMedias.size() == 0 )
			return null;
		return m_VoiceMedias.get( m_VoiceMedias.size()-1 ).getMediaId() ;
	}
	
	//视频
	private List<MediaInfo> m_VideoMedias = new ArrayList<MediaInfo>();
	public void AddVideo( String mediaId, String fromUser ){
		MediaInfo info = new MediaInfo();
		info.setCreateTime( new Date().getTime() );
		info.setMediaId(mediaId);
		info.setTitle("测试视频");
		info.setDescription("由用户:"+fromUser+" 上传");
		m_VideoMedias.add( info );
	}
	public MediaInfo GetRandVideo(){
		ClearTimeoutMedia(m_VideoMedias);
		
		int count = m_VideoMedias.size();
		if( count == 0 )
			return null;
		Random rd = new Random( new Date().getTime() );
		int index = rd.nextInt(count);
		return m_VideoMedias.get( index );
	}
	public MediaInfo GetLastVideo(){
		ClearTimeoutMedia(m_VideoMedias);
		if( m_VideoMedias.size() == 0 )
			return null;
		return m_VideoMedias.get( m_VideoMedias.size()-1 ) ;
	}
}
