import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDatabaseExample1 {
	public static void main(String[] arg) {
		Connection c = null;
		
		try {
			Class.forName("org.sqlite.JDBC");	//loading the sqlite driver to your program
			c = DriverManager.getConnection("jdbc:sqlite:TestDB.db");
			System.out.println("Sucessfully connected to Database");
			
			//display all tables in database
			ResultSet result= c.getMetaData().getTables(null, null, null, null); //null means no value required
			while(result.next()) {
				System.out.println(result.getString("TABLE_NAME"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();// TODO: handle exception
		}
		
		
		
		if (c !=null) {
			try {
				c.close();
				System.out.println("Connection closed sucessfully !!!");
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
