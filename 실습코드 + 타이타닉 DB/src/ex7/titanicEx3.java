package ex7;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class titanicEx3 {
	
	public static void main(String[] args) {
		String url = "jdbc:inetdae7://210.115.229.77:2433";
		String user = "20135174";
		String pass = "req*u8534as";

		Connection con = null;
		Statement stmt = null;
		
		double cnt=0; // 카운트
		double firstClass=0; // 1등석에 앉은 사람

	   
	   try  {
	      con = DriverManager.getConnection(url,user,pass);
	      con.setCatalog("20135174");
	      stmt = con.createStatement();

	      // 타이타닉 테이블에서 생존자 중에 pclass 열을 가져온다
	      ResultSet result = stmt.executeQuery("SELECT Pclass FROM Titanic where Survived = 1");
	      
	
	      while(result.next())
	      {
	         String pclass = result.getString(1); // pclass
	         
	        
	        	 if(Double.parseDouble(pclass)==1) { // 1등석에 앉은 사람이라면 if문으로 처리
	        		 cnt++; // 사람 총 수
	        		 firstClass++; // 1등석 수
	        	 }else cnt++; // 사람 총수
	      }
	      System.out.println("생존자 중 1등석 탑승자 : "+(firstClass/cnt)*100+"%"); // 전체중에서 1등석 탄 사람을 퍼센트로 표시 
	         con.close();
	         stmt.close();
	         }catch(Exception ee) {System.out.println(ee);}
	}

}

