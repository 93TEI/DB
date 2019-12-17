package ex7;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.BufferedReader;
import java.io.BufferedWriter; 
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class task2 {
	public static void main(String[] args) {
		String url = "jdbc:inetdae7://210.115.229.77:2433";
		String user = "20135174";
		String pass = "req*u8534as";

		Connection con = null;
		Statement stmt = null;
		
		double cnt=0; // 카운트
		double age =0; //나이
		File file = new File("task2.txt"); // 파입 생성
        FileWriter writer = null;
        
	   try  {
	      con = DriverManager.getConnection(url,user,pass);
	      con.setCatalog("20135174");
	      stmt = con.createStatement();

	      //사망자의 정보를 모두 가져옴
	      ResultSet result = stmt.executeQuery("SELECT * FROM Titanic where Survived = 0");
	      
	
	      while(result.next())
	      {
	         String id = result.getString(1);
	         String sur = result.getString(2);
	         String pclass = result.getString(3); 
	         String name = result.getString(4); 
	         String sex = result.getString(5); 
	         String age_temp = result.getString(6); 
	       //null값 0처리
	         if(age_temp.equals("null"))
	        	 continue;
	         else //age에 값이 있다면 숫자로 변환
	        	 age = Double.parseDouble(age_temp);

	         String sib = result.getString(7); 
	         String par = result.getString(8);
	         String tck = result.getString(9); 
	         String fare = result.getString(10); 
	         String cbn = result.getString(11); 
	         String emb = result.getString(12);
	         
	        
	        	 if(age<=20) { // 타이타닉 데이터의 age가 만 나이인지 진짜 나이인지 알 수 없어서 만 나이로 가정하고 계산합니다 
	        		 try {
	        	            // 기존 파일의 내용에 이어서 쓰려면 true를, 기존 내용을 없애고 새로 쓰려면 false를 지정한다.
	        	            writer = new FileWriter(file, true);
	        	            writer.write(id+","+sur+","+pclass+","+name+","+sex+","+age_temp+","+sib+","+par+","+tck+","+fare+","+cbn+","+emb+"\n"); // 조건에 맞는 사망자를 파일로 출력
	        	            writer.flush();
	        	            
	        	        } catch(IOException e) {
	        	            e.printStackTrace();
	        	        } finally {
	        	            try {
	        	                if(writer != null) writer.close();
	        	            } catch(IOException e) {
	        	                e.printStackTrace();
	        	            }
	        	        } 
	        		  
	        		 cnt++;
	        	 }else cnt++;
	      }
	      System.out.println("사망자 중 만 20세 이하 인원 수 : "+cnt+"명");
	         con.close();
	         stmt.close();
	         writer.close();
	         }catch(Exception ee) {System.out.println(ee);}
	}

}

