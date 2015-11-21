package test;

import org.junit.Test;

import datahandle.UserDataHandle;

public class TestUserDataHandle {

	@Test
	public void TestVerifyUser(){
		UserDataHandle handle = new UserDataHandle();
		if(handle.VerifyUser("1", "1")){
			System.out.println("verify user 1, successed");
		}else{
			System.out.println("verify user 1, failed");
		}
	}
}
