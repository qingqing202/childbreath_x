package datahandle;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;

public class MongoDAO {
	
	// 输出日志文件
	private static Logger logger = Logger.getLogger(MongoDAO.class);
		

	private MongoDAO(){
		
	}
	
	protected void finalize(){
		if( m_client != null ){
			m_client.close();
		}
	}
	
	public static MongoDAO m_instance = null;
	
	public static MongoDAO GetInstance(){
		if( m_instance == null ){
			m_instance = new MongoDAO();
			Connect();
		}
		return m_instance;
	}
	
	private static MongoClient m_client = null;
	private static MongoDatabase m_database = null;
	
	public static boolean Connect(){
		try{
			if( m_client == null ){
				m_client = new MongoClient("127.0.0.1", 27017);
				m_database = m_client.getDatabase("childbreath");
			}
			
			return true;
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString());
			return false;
		}
	}
	
	public MongoCollection<Document> GetCollection(String collectionName){
		try{
			return m_database.getCollection(collectionName);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString());
			return null;
		}
	}
	
	public long GetId(String idname){
		try{
			MongoDAO dao = MongoDAO.GetInstance();
			
			BasicDBObject cond = new BasicDBObject();
			cond.append("$inc", new BasicDBObject("id", (long)1));
			BasicDBObject cond4 = new BasicDBObject();
			cond4.append("name", new BasicDBObject("$eq", idname));
			FindOneAndUpdateOptions opt = new FindOneAndUpdateOptions();
			opt.upsert(true);
			opt.returnDocument(ReturnDocument.AFTER);
			Document doc = dao.GetCollection("ids").findOneAndUpdate(cond4, cond, opt);
			if( doc == null )
				return -1;
			return doc.getLong("id");
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString());
			return -1;
		}
	}

}


