package datahandle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import childbreath.bean.question;
import childbreath.bean.record;

@Component
public class QuestionDataHandle {
	// 输出日志文件
	private static Logger logger = Logger.getLogger(QuestionDataHandle.class);
	
	public question find( String id ){
		try{
			MongoDAO dao = MongoDAO.GetInstance();
			
			BasicDBObject filter = new BasicDBObject();
			filter.append("id", new BasicDBObject("$eq", Long.parseLong(id)));
			FindIterable<Document> result = dao.GetCollection("questions").find(filter);


			if( result.first() != null ){
				Document doc = result.first();
				question fol = new question();
				fol.setId(doc.getLong("id"));
				fol.setType( doc.getString("type"));
				fol.setSuitability( doc.getString("suitability"));
				fol.setQ( doc.getString("question"));
				fol.setOptions( (List<String>) doc.get("options"));
				
				return fol;
			} else {
				System.out.print("nothing returned from db");
				return null;
			}
		}catch( Exception e ){
			e.printStackTrace();
			logger.error(e.toString());
			return null;
		}
	}
	
	public List<question> findList(List<Long> ids){
		try{
			List<question> list = new ArrayList<question>();
			MongoDAO dao = MongoDAO.GetInstance();
			BasicDBObject cond = new BasicDBObject();
			cond.append("id", new BasicDBObject("$in",ids));
			FindIterable<Document> result = dao.GetCollection("questions").find(cond);
			MongoCursor<Document> it = result.iterator();
			while( it.hasNext() ){
				Document doc = it.next();
				question fol = new question();
				fol.setId(doc.getLong("id"));
				fol.setType( doc.getString("type"));
				fol.setSuitability( doc.getString("suitability"));
				fol.setQ( doc.getString("question"));
				fol.setOptions( (List<String>) doc.get("options"));
				list.add( fol );
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString());
			return null;
		}
	}
	
	public boolean create( question q ){
		try{
			MongoDAO dao = MongoDAO.GetInstance();

			Map<String, Object> docMap = new HashMap<String, Object>();
			docMap.put("id", dao.GetId("questionId"));
			docMap.put("type", q.getType());
			docMap.put("suitability", q.getSuitability());
			docMap.put("question", q.getQ());
			docMap.put("options", q.getOptions());
			
			dao.GetCollection("questions").insertOne(new Document(docMap));
			return true;
		}catch( Exception e ){
			e.printStackTrace();
			logger.error(e.toString());
			return false;
		}
	}
	
	public boolean edit( question q ){
		try{
			MongoDAO dao = MongoDAO.GetInstance();

			BasicDBObject filter = new BasicDBObject();
			filter.append("id", new BasicDBObject("$eq", q.getId()));
			BasicDBObject update = new BasicDBObject();
			BasicDBObject content = new BasicDBObject();
			content.append("type", q.getType());
			content.append("suitability", q.getSuitability());
			content.append("question", q.getQ());
			content.append("options", q.getOptions());
			update.append("$set", content);
			
			UpdateResult rs = dao.GetCollection("questions").updateOne(filter, update);
			if( 1 == rs.getModifiedCount() ){
				return true;
			}
			return false;
		}catch( Exception e ){
			e.printStackTrace();
			logger.error(e.toString());
			return false;
		}
	}
	
	public boolean delete( String id ){
		try{
			MongoDAO dao = MongoDAO.GetInstance();
			
			BasicDBObject filter = new BasicDBObject();
			filter.append("id", new BasicDBObject("$eq", Long.parseLong(id)));
			DeleteResult dr = dao.GetCollection("questions").deleteOne(filter);
			if( 1== dr.getDeletedCount() )
				return true;
			return false;
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString());
			return false;
		}
	}
	
	public List<question> findMulti(String type, String suit){
		try{
			List<question> list = new ArrayList<question>();
			MongoDAO dao = MongoDAO.GetInstance();
			BasicDBObject cond = new BasicDBObject();
			if( null != type && !type.equalsIgnoreCase("all"))
				cond.append("type", new BasicDBObject("$eq",type));
			if( null != suit && !suit.equalsIgnoreCase("all"))
				cond.append("suitability", new BasicDBObject("$eq",suit));
			FindIterable<Document> result = dao.GetCollection("questions").find(cond);
			MongoCursor<Document> it = result.iterator();
			while( it.hasNext() ){
				Document doc = it.next();
				question fol = new question();
				fol.setId(doc.getLong("id"));
				fol.setType( doc.getString("type"));
				fol.setSuitability( doc.getString("suitability"));
				fol.setQ( doc.getString("question"));
				fol.setOptions( (List<String>) doc.get("options"));
				list.add( fol );
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString());
			return null;
		}
		
	}
}
