package projektPC2T;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConn {

	private static volatile Connection dbConnect;
	private DBConn() {}
	public static Connection getDBConnection() {
	    if (dbConnect == null) {
	      synchronized (DBConn.class) {
	        if (dbConnect == null) {
	          try {
	            Class.forName("org.sqlite.JDBC");
	            dbConnect = DriverManager.getConnection("jdbc:sqlite:db/knihy.db");
	          } catch (SQLException | ClassNotFoundException e) {
	            e.printStackTrace(); // log it
	          }
	        }
	      }
	    }
	    return dbConnect;
	  }
	
	 public static void closeConnection() {
		    try {
		      dbConnect.close();
		    } catch (SQLException e) {
		      e.printStackTrace();	
		    }
		  }

}
