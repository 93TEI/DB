package ex7;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class titanicEx2 {
	
	public static void main(String[] args) {
		String url = "jdbc:inetdae7://210.115.229.77:2433";
		String user = "20135174";
		String pass = "req*u8534as";

		Connection con = null;
		Statement stmt = null;
		
		double cnt=0; // 카운트 변수
		double sum=0; // 합 변수
		double cnt_null=0; // null값만 카운트

	   
	   try  {
	      con = DriverManager.getConnection(url,user,pass);
	      con.setCatalog("20135174");
	      stmt = con.createStatement();

	      // 생존자 중에 여자인 사람의 나이를 가져옴
	      ResultSet result = stmt.executeQuery("SELECT Age FROM Titanic where Survived = 1 and Sex = 'female'");
	      
	      while(result.next())
	      {
	         String age = result.getString(1); // 나이
	         
	         if(age.equals("null")) // 나이가 null인 경우는 계산에 넣지 않음.
	         {
	        	 cnt_null++;
	        	 continue;
	         }
	         else //age에 값이 있다면 숫자로 변환
	         {
	        	 sum+=  Double.parseDouble(age); // 나이 모두 더해줌
		         cnt++; // 사람 수 카운트
	         }
	      }
	      System.out.println("여성 생존자 수 : "+(int)(cnt+cnt_null));
	      System.out.println("여성 생존자의 평균 나이 : "+sum/cnt); // 딱 떨어지는 나이를 원한다면 (int)(sum/cnt)를 사용하면 된다. 하지만 소수점으로 많이 사용하기 때문에 double로 처리했음
	         con.close();
	         stmt.close();
	         }catch(Exception ee) {System.out.println(ee);}
	}

}

