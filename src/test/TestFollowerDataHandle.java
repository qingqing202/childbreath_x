package test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.alibaba.fastjson.JSON;

import childbreath.bean.disease;
import childbreath.bean.question;
import childbreath.bean.questionnaire;
import childbreath.bean.record;
import datahandle.DiseaseDataHandle;
import datahandle.MongoDAO;
import datahandle.QuestionDataHandle;
import datahandle.QuestionnaireDataHandle;
import datahandle.RecordDataHandle;

public class TestFollowerDataHandle {

	@Test
	public void TestGetIncompleteFollower(){
		DiseaseDataHandle handle = new DiseaseDataHandle();
		
		disease q = new disease();
		q.setName("感冒");
		if( handle.exist("感冒") ){
			System.out.println("successed");
		}else
			System.out.println("failed");
	}
}
