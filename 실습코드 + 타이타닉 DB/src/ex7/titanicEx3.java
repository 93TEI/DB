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
		
		double cnt=0; // ī��Ʈ
		double firstClass=0; // 1��� ���� ���

	   
	   try  {
	      con = DriverManager.getConnection(url,user,pass);
	      con.setCatalog("20135174");
	      stmt = con.createStatement();

	      // Ÿ��Ÿ�� ���̺��� ������ �߿� pclass ���� �����´�
	      ResultSet result = stmt.executeQuery("SELECT Pclass FROM Titanic where Survived = 1");
	      
	
	      while(result.next())
	      {
	         String pclass = result.getString(1); // pclass
	         
	        
	        	 if(Double.parseDouble(pclass)==1) { // 1��� ���� ����̶�� if������ ó��
	        		 cnt++; // ��� �� ��
	        		 firstClass++; // 1� ��
	        	 }else cnt++; // ��� �Ѽ�
	      }
	      System.out.println("������ �� 1� ž���� : "+(firstClass/cnt)*100+"%"); // ��ü�߿��� 1� ź ����� �ۼ�Ʈ�� ǥ�� 
	         con.close();
	         stmt.close();
	         }catch(Exception ee) {System.out.println(ee);}
	}

}

