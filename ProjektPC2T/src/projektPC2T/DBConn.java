package projektPC2T;
import java.sql.Statement;
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
	            dbConnect = DriverManager.getConnection("jdbc:sqlite:knihovna.db");
	            createTable();
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


public static boolean createTable()
{
     if (dbConnect==null) {
           return false;
     }
     String sql = """
             CREATE TABLE IF NOT EXISTS Knihy(
             nazev VARCHAR(255) PRIMARY KEY,
             autor VARCHAR(255),
             rok INT,
             zanr VARCHAR(255), 
             dostupnost BOOLEAN,
             rocnik INT
             );""";
     
    try{
            Statement stmt = dbConnect.createStatement(); 
            stmt.execute(sql);
            return true;
    } 
    catch (SQLException e) {
    System.out.println(e.getMessage());
    }
    return false;
}
}