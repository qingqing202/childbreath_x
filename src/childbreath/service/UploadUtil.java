package childbreath.service;

import java.io.File;

public class UploadUtil {
	private static final String uploadUrl = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
	public static String UploadVideo( String filename){
		try{
			String url = uploadUrl.replace("ACCESS_TOKEN", AccessTokenMgr.getInstance().getAccessToken()).replace("TYPE", "video");
			String fileUrl = "http://partytime.duapp.com"+filename;
			File f= new File(filename);  
			long filesize = 0;
		    if (f.exists() && f.isFile()){  
		        filesize = f.length();  
		    }
		    //String param = String.format("filename=%s,filelength=%d,content-type=", args)
			//WeixinUtil.HttpRequest(url, "POST", param);
		    return null;
		}catch( Exception e ){
			e.printStackTrace();
			return null;
		}
	}
}
