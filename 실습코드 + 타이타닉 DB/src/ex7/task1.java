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
		
		
		double cnt_male=0; // ���� ��
		double cnt_female=0;// ���� ��

	   
	   try  {
	      con = DriverManager.getConnection(url,user,pass);
	      con.setCatalog("20135174");
	      stmt = con.createStatement();

	      // Ÿ��Ÿ�� ���̺��� ����� ������� ������ ������
	      ResultSet result = stmt.executeQuery("SELECT Sex FROM Titanic where Survived = 0");
	      
	
	      while(result.next())
	      {
	         String sex = result.getString(1); // sex
	         
	        
	        	 if(sex.equals("male")) { // ���� ī��Ʈ
	        		 cnt_male++;
	        		 
	        	 }else cnt_female++; // ���� ī��Ʈ
	      }
	      //���������� int������ ǥ���ϰ� �ʹٸ� (int)(cnt_male+cnt_female)�� �ڵ��ϸ� ������ �Ҽ������� ǥ���ϱ� ���ؼ� double�� ó��
	      System.out.println("����� �ο� �� : "+(cnt_male+cnt_female)+"��"); // ����� �� �ο�
	      System.out.println("����� ���� ���� : ���� "+((cnt_male/(cnt_male+cnt_female))*100)+"%, ����"+((cnt_female/(cnt_male+cnt_female))*100+"%")); // ����
	         con.close();
	         stmt.close();
	         }catch(Exception ee) {System.out.println(ee);}
	}

}

