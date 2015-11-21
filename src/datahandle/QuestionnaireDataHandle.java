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
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import childbreath.bean.questionnaire;

@Component
public class QuestionnaireDataHandle {
	private static Logger logger = Logger.getLogger(QuestionnaireDataHandle.class);
	
	public questionnaire find(String id){
		try{
			MongoDAO dao = MongoDAO.GetInstance();
			
			BasicDBObject filter = new BasicDBObject();
			filter.append("id", new BasicDBObject("$eq", Long.parseLong(id)));
			FindIterable<Document> result = dao.GetCollection("questionnaires").find(filter);
			
			if( result.first() != null ){
				Document doc = result.first();
				questionnaire q = new questionnaire();
				q.setId( doc.getLong("id"));
				q.setName( doc.getString("name"));
				q.setQuestions( (List<Long>)doc.get("questions"));
				return q;
			}
			return null;
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString());
			return null;
		}
	}
	
	public List<questionnaire> findAll(){
		try{
			List<questionnaire> list = new ArrayList<questionnaire>();
			
			MongoDAO dao = MongoDAO.GetInstance();
			FindIterable<Document> result = dao.GetCollection("questionnaires").find();
			MongoCursor<Document> it = result.iterator();
			while( it.hasNext() ){
				Document doc = it.next();
				questionnaire q = new questionnaire();
				q.setId( doc.getLong("id"));
				q.setName( doc.getString("name"));
				q.setQuestions( (List<Long>)doc.get("questions"));
				list.add(q);
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString());
			return null;
		}
	}
	
	public boolean create(questionnaire q){
		try{
			MongoDAO dao = MongoDAO.GetInstance();

			Map<String, Object> docMap = new HashMap<String, Object>();
			docMap.put("id", dao.GetId("questionnaireid"));
			docMap.put("name", q.getName());
			docMap.put("questions", q.getQuestions());
			
			dao.GetCollection("questionnaires").insertOne(new Document(docMap));
			return true;
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString());
			return false;
		}
	}
	
	public boolean edit(questionnaire q){
		try{
			MongoDAO dao = MongoDAO.GetInstance();

			BasicDBObject filter = new BasicDBObject();
			filter.append("id", new BasicDBObject("$eq", q.getId()));
			BasicDBObject update = new BasicDBObject();
			BasicDBObject content = new BasicDBObject();
			content.append("name", q.getName());
			content.append("questions", q.getQuestions());
			update.append("$set", content);
			
			UpdateResult rs = dao.GetCollection("questionnaires").updateOne(filter, update);
			if( 1 == rs.getModifiedCount() ){
				return true;
			}
			return false;
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString());
			return false;
		}
	}
	
	public boolean delete(String id){
		try{
			MongoDAO dao = MongoDAO.GetInstance();
			
			BasicDBObject filter = new BasicDBObject();
			filter.append("id", new BasicDBObject("$eq", Long.parseLong(id)));
			DeleteResult dr = dao.GetCollection("questionnaires").deleteOne(filter);
			if( 1== dr.getDeletedCount() )
				return true;
			return false;
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString());
			return false;
		}
	}
}
