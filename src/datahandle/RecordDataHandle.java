package datahandle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.mongodb.client.MongoCollection;
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


	/** 插入新用户
	 *
 	 */
	private boolean insertRecord(record rec) {
		System.out.print("insertRecord begin");
		try {
			MongoDAO dao = MongoDAO.GetInstance();
			System.out.print("insertRecord : insertOne record");
			Map<String, Object> docMap = rec.getDocMap();
			docMap.put("inHospital", true);
			docMap.put("leaveHospital", false);
			docMap.put("followup", false);
			dao.GetCollection("records").insertOne(new Document(docMap));
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString());
			return false;
		}
		return true;
	}

	/** 更新用户信息
	 * @return 是否更新成功
	 */

	private boolean updateRecord(record exist_record, record new_record) {
		try {
			System.out.print("update record");
			MongoDAO dao = MongoDAO.GetInstance();
			System.out.print("update record1");

			//deleteOne not working!!!
			dao.GetCollection("records").deleteOne(new Document(exist_record.getDocMap()));

			dao.GetCollection("records").insertOne(new Document(new_record.getDocMap()));
			//dao.GetCollection("records").updateOne(new Document(exist_record.getDocMap()),
			//		new Document(new_record.getDocMap()));
			System.out.print("update record2");
		} catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString());
			return false;
		}
		return true;
	}


	/** 新用户注册
	 * @return
	 */
	public boolean addNewRecord(record rec) {
		try {
			System.out.print("add new record begin");

			if (this.isRecordNameExists(rec.getName())) {
				System.out.print("has record with same name");
				record exist_record = this.GetRecord(rec.getAdmission_number());
				if (exist_record != null) {
					System.out.print("has record with same id");
					return this.updateRecord(exist_record, rec);
				}
			}
			return this.insertRecord(rec);
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString());
			return false;
		}
	}

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
			cond.append("followup", new BasicDBObject("$eq", false));
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

	/** 是否有同名记录
	 * @param name 患儿姓名
	 * @return 住院记录
	 */
	public boolean isRecordNameExists(String name){
		try{
			MongoDAO dao = MongoDAO.GetInstance();
			BasicDBObject cond = new BasicDBObject();
			cond.append("name", new BasicDBObject("$eq", name));
			FindIterable<Document> result = dao.GetCollection("records").find(cond);
			MongoCursor<Document> it = result.iterator();
			if( it.hasNext() ){
				return true;
			}
			return false;
		}catch( Exception e ){
			e.printStackTrace();
			logger.error(e.toString());
			return false;
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
			cond.append("admission_number", new BasicDBObject("$eq", admissionNum));
			FindIterable<Document> result = dao.GetCollection("records").find(cond);
			MongoCursor<Document> it = result.iterator();
			if( it.hasNext() ){
				Document doc = it.next();
				record fol = new record();
				fol.setAdmission_number(doc.getString("admission_number"));
				fol.setName(doc.getString("name"));
				fol.setWeixin_openid(doc.getString("weixin_openid"));
				fol.setLeaveTime(doc.getDate("leaveTime"));
				fol.setInTime(doc.getDate("inTime"));
				fol.setDisease(doc.getString("disease"));
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
