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
	   
	   double sum_age_C = 0; //�¼���ġ C����
	   double sum_age_S = 0; //�¼���ġ S����
	   double sum_age_Q = 0; //�¼���ġ Q����
	   double sum_age_NULL = 0; //�¼���ġ null��
	   double temp_age = 0; // ���� �ӽ� ����
	   double cnt_C = 0; // C���� ī��Ʈ
	   double cnt_S = 0;// S���� ī��Ʈ
	   double cnt_Q = 0;// Q���� ī��Ʈ
	   double cnt_NULL = 0;// �¼���ġ null ī��Ʈ

	   
	   try  {
	      con = DriverManager.getConnection(url,user,pass);
	      con.setCatalog("20135174");
	      stmt = con.createStatement();

	      //Ÿ��Ÿ�� ���̺��� �������� ������ ��� ������
	      ResultSet result = stmt.executeQuery("SELECT * FROM Titanic where Survived = 1");
	
	      while(result.next())
	      {
	         String age = result.getString(6); // ����
	         String Embarked = result.getString(12); //�¼���ġ
	         
	         //null�� 0ó��
	         if(age.equals("null"))
	        	 continue;
	         else //age�� ���� �ִٸ� ���ڷ� ��ȯ
	        	 temp_age = Double.parseDouble(age);
	         
	         //�¼���ġ�� ���� ���� ó��
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
	         else { // �¼���ġ�� null�̸� ���� ó��
	        	 sum_age_NULL = sum_age_NULL + temp_age ;
	        	 cnt_NULL +=1;
	         }
	       
	        
	      }
	      System.out.println("�¼� ��ġ�� C�� �������� ��ճ��� : " +sum_age_C/cnt_C); // �Ҽ����� ���� ��� ���̸� ���Ѵٸ� (int)(sum_age_C/cnt_C) �̷��� ���� �ȴ�. 
	      System.out.println("�¼� ��ġ�� S�� �������� ��ճ��� : " +sum_age_S/cnt_S); // ������ ��� ���̴� �Ҽ������� ���� ��찡 �� ���Ƽ� �̴�� ���״�.
	      System.out.println("�¼� ��ġ�� Q�� �������� ��ճ��� : " +sum_age_Q/cnt_Q);
	      System.out.println("�¼� ��ġ�� NULL�� �������� ��ճ��� : " +sum_age_NULL/cnt_NULL);
	         con.close();
	         stmt.close();
	         
	         }catch(Exception ee) {System.out.println(ee);}
	}

}


		