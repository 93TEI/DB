package ex7;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class titanicEx1 {
	public static void main(String[] args) {
		String url = "jdbc:inetdae7://210.115.229.77:2433";
		String user = "20135174";
		String pass = "req*u8534as";

		Connection con = null;
		Statement stmt = null;
	   
	   double sum_age_C = 0; //승선위치 C구역
	   double sum_age_S = 0; //승선위치 S구역
	   double sum_age_Q = 0; //승선위치 Q구역
	   double sum_age_NULL = 0; //승선위치 null값
	   double temp_age = 0; // 나이 임시 변수
	   double cnt_C = 0; // C구역 카운트
	   double cnt_S = 0;// S구역 카운트
	   double cnt_Q = 0;// Q구역 카운트
	   double cnt_NULL = 0;// 승선위치 null 카운트

	   
	   try  {
	      con = DriverManager.getConnection(url,user,pass);
	      con.setCatalog("20135174");
	      stmt = con.createStatement();

	      //타이타닉 테이블에서 생존자의 정보를 모두 가져옴
	      ResultSet result = stmt.executeQuery("SELECT * FROM Titanic where Survived = 1");
	
	      while(result.next())
	      {
	         String age = result.getString(6); // 나이
	         String Embarked = result.getString(12); //승선위치
	         
	         //null값 0처리
	         if(age.equals("null"))
	        	 continue;
	         else //age에 값이 있다면 숫자로 변환
	        	 temp_age = Double.parseDouble(age);
	         
	         //승선위치에 따라서 각각 처리
	         if(Embarked.equals("C")) {
	        	 sum_age_C = sum_age_C + temp_age ;
	        	 cnt_C +=1;
	         }
	         else if(Embarked.equals("S")) {
	        	 sum_age_S = sum_age_S + temp_age ;
	        	 cnt_S +=1;
	         }
	         else if(Embarked.equals("Q")) {
	        	 sum_age_Q = sum_age_Q + temp_age ;
	        	 cnt_Q +=1;
	         }
	         else { // 승선위치가 null이면 따로 처리
	        	 sum_age_NULL = sum_age_NULL + temp_age ;
	        	 cnt_NULL +=1;
	         }
	       
	        
	      }
	      System.out.println("승선 위치가 C인 생존자의 평균나이 : " +sum_age_C/cnt_C); // 소수점이 없는 평균 나이를 원한다면 (int)(sum_age_C/cnt_C) 이렇게 쓰면 된다. 
	      System.out.println("승선 위치가 S인 생존자의 평균나이 : " +sum_age_S/cnt_S); // 하지만 평균 나이는 소수점으로 쓰는 경우가 더 많아서 이대로 놔뒀다.
	      System.out.println("승선 위치가 Q인 생존자의 평균나이 : " +sum_age_Q/cnt_Q);
	      System.out.println("승선 위치가 NULL인 생존자의 평균나이 : " +sum_age_NULL/cnt_NULL);
	         con.close();
	         stmt.close();
	         
	         }catch(Exception ee) {System.out.println(ee);}
	}

}


		