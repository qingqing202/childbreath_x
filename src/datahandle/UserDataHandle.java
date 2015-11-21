package datahandle;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;

import datahandle.MongoDAO;

@Component
public class UserDataHandle {
	// 输出日志文件
	private static Logger logger = Logger.getLogger(UserDataHandle.class);
		
	public boolean VerifyUser(String userName, String pwd){
		try{
			MongoDAO dao = MongoDAO.GetInstance();
			
			BasicDBObject cond = new BasicDBObject();
			cond.append("name", new BasicDBObject("$eq",userName));
			cond.append("pwd", new BasicDBObject("$eq", pwd));
			if( dao.GetCollection("mgn_user").count(cond) == 1 )
				return true;
			return false;
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString());
			return false;
		}
	}
}
