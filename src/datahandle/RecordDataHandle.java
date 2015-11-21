package datahandle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.bson.Document;
import org.springframework.stereotype.Component;

import childbreath.bean.questionanswer;
import childbreath.bean.record;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.result.UpdateResult;

/**
 * @author Neil
 *
 */
@Component
public class RecordDataHandle {
	// 输出日志文件
	private static Logger logger = Logger.getLogger(RecordDataHandle.class);
	
	
	/**	取得正在住院的用户
	 * @return
	 */
	public List<record> GetInhospitalRecords(){
		try{
			List<record> list = new ArrayList<record>();
			MongoDAO dao = MongoDAO.GetInstance();
			BasicDBObject cond = new BasicDBObject();
			cond.append("inHospital", new BasicDBObject("$eq",true));
			cond.append("leaveHospital", new BasicDBObject("$eq",false));
			FindIterable<Document> result = dao.GetCollection("records").find(cond);
			MongoCursor<Document> it = result.iterator();
			while( it.hasNext() ){
				Document doc = it.next();
				record fol = new record();
				fol.setAdmission_number( doc.getString("admission_number"));
				fol.setName( doc.getString("name"));
				fol.setWeixin_openid( doc.getString("weixin_openid"));
				fol.setInTime( doc.getDate("inTime") );
				list.add( fol );
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString());
			return null;
		}
	}
	
	/**	取得已出院未回访的用户
	 * @return
	 */
	public List<record> GetleavehospitalRecords(){
		try{
			List<record> list = new ArrayList<record>();
			MongoDAO dao = MongoDAO.GetInstance();
			BasicDBObject cond = new BasicDBObject();
			cond.append("leaveHospital", new BasicDBObject("$eq",true));
			cond.append("followup", new BasicDBObject("$eq",false));
			FindIterable<Document> result = dao.GetCollection("records").find(cond);
			MongoCursor<Document> it = result.iterator();
			while( it.hasNext() ){
				Document doc = it.next();
				record fol = new record();
				fol.setAdmission_number( doc.getString("admission_number"));
				fol.setName( doc.getString("name"));
				fol.setWeixin_openid( doc.getString("weixin_openid"));
				fol.setLeaveTime( doc.getDate("leaveTime") );
				fol.setDisease(doc.getString("disease"));
				list.add( fol );
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString());
			return null;
		}
	}
	
	/** 取得指定住院号的记录
	 * @param admissionNum 住院号
	 * @return 住院记录
	 */
	public record GetRecord(String admissionNum){
		try{
			MongoDAO dao = MongoDAO.GetInstance();
			BasicDBObject cond = new BasicDBObject();
			cond.append("admission_number", new BasicDBObject("$eq",admissionNum));
			FindIterable<Document> result = dao.GetCollection("records").find(cond);
			MongoCursor<Document> it = result.iterator();
			if( it.hasNext() ){
				Document doc = it.next();
				record fol = new record();
				fol.setAdmission_number( doc.getString("admission_number"));
				fol.setName( doc.getString("name"));
				fol.setWeixin_openid( doc.getString("weixin_openid"));
				fol.setLeaveTime( doc.getDate("leaveTime") );
				fol.setInTime(doc.getDate("inTime"));
				fol.setDisease( doc.getString("disease") );
				return fol;
			}
			return null;
		}catch( Exception e ){
			e.printStackTrace();
			logger.error(e.toString());
			return null;
		}
	}
	
	public boolean leaveHospital(String admissionNum){
		try{
			MongoDAO dao = MongoDAO.GetInstance();
			BasicDBObject cond = new BasicDBObject();
			cond.append("admission_number", new BasicDBObject("$eq",admissionNum));
			BasicDBObject update = new BasicDBObject();
			BasicDBObject content = new BasicDBObject();
			content.append("leaveTime", new Date());
			content.append("leaveHospital", true);
			content.append("followup", false);
			update.append("$set", content);
			
			UpdateResult rs = dao.GetCollection("records").updateOne(cond, update);
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
	
	public boolean followup(String admissionNum, long questionnaireID){
		try{
			MongoDAO dao = MongoDAO.GetInstance();
			BasicDBObject cond = new BasicDBObject();
			cond.append("admission_number", new BasicDBObject("$eq",admissionNum));
			BasicDBObject update = new BasicDBObject();
			BasicDBObject content = new BasicDBObject();
			content.append("questionnaireID", questionnaireID);
			content.append("followup", true);
			content.append("answerFollowup", false);
			content.append("followupTime", new Date());
			update.append("$set", content);
			
			UpdateResult rs = dao.GetCollection("records").updateOne(cond, update);
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
	
	public boolean answerFollowup(String admissionNum, List<questionanswer> qas){
		try{
			MongoDAO dao = MongoDAO.GetInstance();
			BasicDBObject cond = new BasicDBObject();
			cond.append("admission_number", new BasicDBObject("$eq",admissionNum));
			BasicDBObject update = new BasicDBObject();
			BasicDBObject content = new BasicDBObject();
			content.append("answerFollowup", true);
			content.append("answerFollowupTime", new Date());
			content.append("answers", qas);
			update.append("$set", content);
			
			UpdateResult rs = dao.GetCollection("records").updateOne(cond, update);
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
}
