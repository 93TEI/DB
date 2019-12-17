package ex7;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class task1 {
	public static void main(String[] args) {
		String url = "jdbc:inetdae7://210.115.229.77:2433";
		String user = "20135174";
		String pass = "req*u8534as";

		Connection con = null;
		Statement stmt = null;
		
		
		double cnt_male=0; // 남자 수
		double cnt_female=0;// 여자 수

	   
	   try  {
	      con = DriverManager.getConnection(url,user,pass);
	      con.setCatalog("20135174");
	      stmt = con.createStatement();

	      // 타이타닉 테이블에서 사망한 사람들의 성별을 가져옴
	      ResultSet result = stmt.executeQuery("SELECT Sex FROM Titanic where Survived = 0");
	      
	
	      while(result.next())
	      {
	         String sex = result.getString(1); // sex
	         
	        
	        	 if(sex.equals("male")) { // 남자 카운트
	        		 cnt_male++;
	        		 
	        	 }else cnt_female++; // 여자 카운트
	      }
	      //마찬가지로 int형으로 표시하고 싶다면 (int)(cnt_male+cnt_female)로 코딩하면 되지만 소수점까지 표시하기 위해서 double로 처리
	      System.out.println("사망자 인원 수 : "+(cnt_male+cnt_female)+"명"); // 사망한 총 인원
	      System.out.println("사망자 남녀 비율 : 남자 "+((cnt_male/(cnt_male+cnt_female))*100)+"%, 여자"+((cnt_female/(cnt_male+cnt_female))*100+"%")); // 비율
	         con.close();
	         stmt.close();
	         }catch(Exception ee) {System.out.println(ee);}
	}

}

