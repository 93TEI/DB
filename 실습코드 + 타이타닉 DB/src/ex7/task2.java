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
		
		double cnt=0; // ī��Ʈ
		double age =0; //����
		File file = new File("task2.txt"); // ���� ����
        FileWriter writer = null;
        
	   try  {
	      con = DriverManager.getConnection(url,user,pass);
	      con.setCatalog("20135174");
	      stmt = con.createStatement();

	      //������� ������ ��� ������
	      ResultSet result = stmt.executeQuery("SELECT * FROM Titanic where Survived = 0");
	      
	
	      while(result.next())
	      {
	         String id = result.getString(1);
	         String sur = result.getString(2);
	         String pclass = result.getString(3); 
	         String name = result.getString(4); 
	         String sex = result.getString(5); 
	         String age_temp = result.getString(6); 
	       //null�� 0ó��
	         if(age_temp.equals("null"))
	        	 continue;
	         else //age�� ���� �ִٸ� ���ڷ� ��ȯ
	        	 age = Double.parseDouble(age_temp);

	         String sib = result.getString(7); 
	         String par = result.getString(8);
	         String tck = result.getString(9); 
	         String fare = result.getString(10); 
	         String cbn = result.getString(11); 
	         String emb = result.getString(12);
	         
	        
	        	 if(age<=20) { // Ÿ��Ÿ�� �������� age�� �� �������� ��¥ �������� �� �� ��� �� ���̷� �����ϰ� ����մϴ� 
	        		 try {
	        	            // ���� ������ ���뿡 �̾ ������ true��, ���� ������ ���ְ� ���� ������ false�� �����Ѵ�.
	        	            writer = new FileWriter(file, true);
	        	            writer.write(id+","+sur+","+pclass+","+name+","+sex+","+age_temp+","+sib+","+par+","+tck+","+fare+","+cbn+","+emb+"\n"); // ���ǿ� �´� ����ڸ� ���Ϸ� ���
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
	      System.out.println("����� �� �� 20�� ���� �ο� �� : "+cnt+"��");
	         con.close();
	         stmt.close();
	         writer.close();
	         }catch(Exception ee) {System.out.println(ee);}
	}

}

