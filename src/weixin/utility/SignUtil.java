package weixin.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class SignUtil {
	private static String token="childbreathweixin";
	
	public static boolean checkSignature( String signature, String timestamp, String nonce){
		String[] arr = { token, timestamp, nonce };
		Arrays.sort( arr );
		StringBuilder content = new StringBuilder();  
        for (int i = 0; i < arr.length; i++) {  
            content.append(arr[i]);  
        }
        MessageDigest md = null;
        String strDigested = null;
        
        try{
        	md = MessageDigest.getInstance("SHA-1");
        	
        	byte[] digest = md.digest( content.toString().getBytes() );
        	strDigested = byteToStr(digest);
        	strDigested = strDigested.toUpperCase();
        }catch(NoSuchAlgorithmException e){
        	e.printStackTrace();
        }
        content = null;
        return strDigested != null ? strDigested.equals(signature.toUpperCase()) : false;
	}
	
	private static String byteToStr( byte[] byteArray ){
		String strDigest = "";
		for( int i = 0; i < byteArray.length; i++ ){
			strDigest += byteToHexStr(byteArray[i]);
		}
		return strDigest;
	}
	
	private static String byteToHexStr( byte mByte ){
		char[] Digit =  { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char[] tempArr = new char[2];
		tempArr[0] = Digit[ (mByte >>> 4) & 0X0F ];
		tempArr[1] = Digit[ mByte & 0X0F ];
		
		String s = new String( tempArr );
		return s;
	}
}
