package test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.bson.Document;
import org.junit.Test;

import datahandle.MongoDAO;

public class TestMongoDB {
	@Test
	public void testInsertData(){
		MongoDAO dao = MongoDAO.GetInstance();

		long id = dao.GetId("questionId");
		System.out.println("new question id is "+id);
	}
}
