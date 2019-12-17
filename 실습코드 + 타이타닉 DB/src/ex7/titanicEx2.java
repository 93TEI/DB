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
		
		double cnt=0; // ī��Ʈ ����
		double sum=0; // �� ����
		double cnt_null=0; // null���� ī��Ʈ

	   
	   try  {
	      con = DriverManager.getConnection(url,user,pass);
	      con.setCatalog("20135174");
	      stmt = con.createStatement();

	      // ������ �߿� ������ ����� ���̸� ������
	      ResultSet result = stmt.executeQuery("SELECT Age FROM Titanic where Survived = 1 and Sex = 'female'");
	      
	      while(result.next())
	      {
	         String age = result.getString(1); // ����
	         
	         if(age.equals("null")) // ���̰� null�� ���� ��꿡 ���� ����.
	         {
	        	 cnt_null++;
	        	 continue;
	         }
	         else //age�� ���� �ִٸ� ���ڷ� ��ȯ
	         {
	        	 sum+=  Double.parseDouble(age); // ���� ��� ������
		         cnt++; // ��� �� ī��Ʈ
	         }
	      }
	      System.out.println("���� ������ �� : "+(int)(cnt+cnt_null));
	      System.out.println("���� �������� ��� ���� : "+sum/cnt); // �� �������� ���̸� ���Ѵٸ� (int)(sum/cnt)�� ����ϸ� �ȴ�. ������ �Ҽ������� ���� ����ϱ� ������ double�� ó������
	         con.close();
	         stmt.close();
	         }catch(Exception ee) {System.out.println(ee);}
	}

}

