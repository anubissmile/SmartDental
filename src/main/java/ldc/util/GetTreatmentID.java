package ldc.util;
 
import java.sql.Connection; 
import java.sql.ResultSet;
import java.sql.Statement;

public class GetTreatmentID {
	DBConnect agent = new DBConnect();
	Connection conn = null;
	Statement Stmt = null; 
	ResultSet rs = null;
	
	public String Get_TreatmentID(String hn) throws Exception{ 
		String treatment_id = "";  
		 
		String sql = "SELECT "
				+"a.treatment_id " 
				+"FROM "
				+"patient a where ";
		
		if(!hn.equals("")) sql += "a.hn = '"+hn+"' ";
		  
		conn = agent.getConnectMYSql();
		Stmt = conn.createStatement();
		
		rs = Stmt.executeQuery(sql); 
		 
		while(rs.next()){ 
			if(rs.getString("treatment_id")!=null) treatment_id = rs.getString("treatment_id"); else treatment_id = "";
		}
		rs.close();
		Stmt.close(); 
	 

		return treatment_id;
	} 
	 
}
