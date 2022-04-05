import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestDatabaseReadData {

	public static void main(String[] args) {
		
		Connection c = null;
		
		// load the sqlite driver to your program
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:TestDB.db");
			
			System.out.println("Successfully connect to database");

			Statement statement = c.createStatement();
			
			ResultSet result = statement.executeQuery("select * from Company");
			
			while(result.next()) {
				int id = result.getInt("id");
				String name = result.getString("name");
				int age = result.getInt("age");
				String address = result.getString("address");
				double salary = result.getDouble("salary");
				
				// %d: integer, %s: string, %f: double/float, %.2f means display 2 decimal places
				System.out.printf("id: %d | name: %s | age: %d | address: %s | salary: %.2f\n", id, name, age, address, salary);
			}
			
			result.close();			
			statement.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(c != null) {
			try {
				c.close();
				System.out.println("Closed connection successfully.");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
