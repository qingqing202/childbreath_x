package datahandle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.*;
import org.bson.Document;
import org.springframework.stereotype.*;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.result.DeleteResult;

import childbreath.bean.disease;

@Component
public class DiseaseDataHandle {
	private static Logger logger = Logger.getLogger(DiseaseDataHandle.class);
	
	public List<disease> findAll(){
		try{
			List<disease> list = new ArrayList<disease>();
			MongoDAO dao = MongoDAO.GetInstance();
			
			FindIterable<Document> result = dao.GetCollection("diseases").find();
			MongoCursor<Document> it = result.iterator();
			while( it.hasNext() ){
				Document doc = it.next();
				disease fol = new disease();
				fol.setName( doc.getString("name"));
				list.add( fol );
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString());
			return null;
		}
	}
	
	public boolean exist(String name){
		try{
			MongoDAO dao = MongoDAO.GetInstance();
			BasicDBObject filter = new BasicDBObject();
			filter.append("name", new BasicDBObject("$eq", name));
			if( 1 == dao.GetCollection("diseases").count(filter) )
				return true;
			return false;
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString());
			return false;
		}
	}
	
	public boolean create(disease d){
		try{
			MongoDAO dao = MongoDAO.GetInstance();

			Map<String, Object> docMap = new HashMap<String, Object>();
			docMap.put("name", d.getName());
			
			dao.GetCollection("diseases").insertOne(new Document(docMap));
			return true;
		}catch( Exception e ){
			e.printStackTrace();
			logger.error(e.toString());
			return false;
		}
	}
	
	public boolean delete(String name){
		try{
			MongoDAO dao = MongoDAO.GetInstance();
			BasicDBObject filter = new BasicDBObject();
			filter.append("name", new BasicDBObject("$eq", name));
			DeleteResult dr = dao.GetCollection("diseases").deleteOne(filter);
			if( 1== dr.getDeletedCount() )
				return true;
			return false;
		}catch( Exception e ){
			e.printStackTrace();
			logger.error(e.toString());
			return false;
		}
	}
}
