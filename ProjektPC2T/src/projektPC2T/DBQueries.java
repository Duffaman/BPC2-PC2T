package projektPC2T;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DBQueries {
	 /*
	  * 
	  * insert queries 
	  *
	  *
	  *
	  *test pro novou knihu (zdali jiz neni nazev knihy obsazen)
	  */
	public boolean testIfExists(String nazev) {
		 if (nazev == null) {
		      throw new NullPointerException("Musis zadat nazev knihy!");
		    } else if (nazev == "") {
		      throw new IllegalArgumentException("Nazev knihy nesmi byt prazdny!");
		    }

	    Connection conn = DBConn.getDBConnection();
	    String testUserExistence = "SELECT * FROM Knihy WHERE nazev = ?";

	    try (PreparedStatement prStmt = conn.prepareStatement(testUserExistence);) {
	      prStmt.setString(1, nazev);
	      ResultSet rs = prStmt.executeQuery();
	      if (rs.next())
	        return true;
	      else
	        return false;
	    } catch (SQLException e) {
	      e.printStackTrace();
	      return false;
	    }
	  }
	public void vlozRoman(String nazev, String autor, String zanr, int rok) {
	    if (nazev == null || autor == null || zanr == null || rok == 0)
	      throw new NullPointerException("Nezadal jsi nazev, autora a zanr romanu!");

	    Connection conn = DBConn.getDBConnection();

	    String knihaVloz = "INSERT INTO knihy " + "(nazev,autor,zanr,rok,dostupnost)" + "VALUES(?,?,?,?,?)";

	    try (PreparedStatement prStmt = conn.prepareStatement(knihaVloz)) {
	      prStmt.setString(1, nazev);
	      prStmt.setString(2, autor);
	      prStmt.setString(3, zanr);
	      prStmt.setInt(4, rok);
	      prStmt.setBoolean(5, true);

	      prStmt.executeUpdate();

	      System.out.println("Byl pridan roman");
	    } catch (SQLException e) {
	      System.out.println("Neco se pokazilo, zkus znova nebo je spatne kod");
	    }
	  }
	
	
	public void vlozUceb(String nazev, String autor, int rocnik, int rok) {
	    if (nazev == null || autor == null || rocnik == 0 || rok == 0)
	      throw new NullPointerException("Nezadal jsi nazev, autora a rocnik ucebnice!");

	    Connection conn = DBConn.getDBConnection();

	    String knihaVloz = "INSERT INTO knihy " + "(nazev,autor,rocnik,rok,dostupnost)" + "VALUES(?,?,?,?,?)";

	    try (PreparedStatement prStmt = conn.prepareStatement(knihaVloz)) {
	      prStmt.setString(1, nazev);
	      prStmt.setString(2, autor);
	      prStmt.setInt(3, rocnik);
	      prStmt.setInt(4, rok);
	      prStmt.setBoolean(5, true);
	      prStmt.executeUpdate();

	      System.out.println("Byla pridana ucebnice");
	    } catch (SQLException e) {
	      System.out.println("Neco se pokazilo, zkus znova nebo je spatne kod");
	    }
	  }
	 /*
	  * 
	  * delete queries 
	  * 
	  */
	 public void smazKnihu(String nazev) {
		    if (nazev == null) {
		      throw new NullPointerException("Musis zadat nazev knihy!");
		    } else if (nazev == "") {
		      throw new IllegalArgumentException("Nazev knihy nesmi byt prazdny!");
		    }
		    Connection conn = DBConn.getDBConnection();

		    String knihaDelete = "DELETE FROM knihy WHERE nazev = ?";

		    try (PreparedStatement prStmt = conn.prepareStatement(knihaDelete);) {
		      prStmt.setString(1, nazev);
		      prStmt.executeUpdate();
		      System.out.println("smazal jsi knihu!");
		    } catch (SQLException e) {
		      e.printStackTrace();
		    }
		  }	
	 /*
	  * 
	  * update queries 
	  * 
	  * */
	 public void zmenaDleNazvu(String nazev) {
		 if (nazev == null) {
		      throw new NullPointerException("Musis zadat nazev knihy!");
		    } else if (nazev == "") {
		      throw new IllegalArgumentException("Nazev knihy nesmi byt prazdny!");
		    }
		 Connection conn = DBConn.getDBConnection();
		 System.out.println("1...pro zmenu autora");
		 System.out.println("2...pro zmenu roku vydani");
		 System.out.println("3...pro zmenu dostupnosti");
		 Scanner sd = new Scanner(System.in);
		 int choise = Test.pouzeCelaCisla(sd);
		 switch (choise) {
		 case 1:
			 System.out.println("Zadej jmeno autora");
			 String newAutor = sd.next();
			 if (newAutor == null) {
			      throw new NullPointerException("Musis zadat autora!");
			    } else if (newAutor == "") {
			      throw new IllegalArgumentException("Jmeno autora nesmi byt prazdne!");
			    }
			 String updateAutor = "UPDATE knihy SET autor = ? WHERE nazev = ?";

		    try (PreparedStatement prStmt = conn.prepareStatement(updateAutor);) {
		      prStmt.setString(1, newAutor);
		      prStmt.setString(2, nazev);
		      prStmt.executeUpdate();
		      System.out.println("Zmenilo se jmeno autora");
		    } catch (SQLException e) {
		      e.printStackTrace();
		    }
		 
	
			 break;
		 case 2:
			 System.out.println("Zadej novy rok");
			int newRok = Test.pouzeCelaCisla(sd);
			 String updateRok = "UPDATE knihy SET rok = ? WHERE nazev = ?";

		    try (PreparedStatement prStmt = conn.prepareStatement(updateRok);) {
		      prStmt.setInt(1, newRok);
		      prStmt.setString(2, nazev);
		      prStmt.executeUpdate();
		      System.out.println("Zmenil se rok vydani knihy");
		    } catch (SQLException e) {
		      e.printStackTrace();
		    }
			 break;
		 case 3:
			 System.out.println("1...pro zmenu na vypujcene");
			 System.out.println("2...pro zmenu na dostupne");
			 choise = Test.pouzeCelaCisla(sd);
			if (choise == 1) {
				 String updateDost = "UPDATE knihy SET dostupnost = FALSE WHERE nazev = ?";

				    try (PreparedStatement prStmt = conn.prepareStatement(updateDost);) {
				      prStmt.setString(1, nazev);
				      prStmt.executeUpdate();
				      System.out.println("Zmenila se dostupnost knihy");
				    } catch (SQLException e) {
				      e.printStackTrace();
				    }}
			
				    else if (choise == 2) {
				   String updateDost = "UPDATE knihy SET dostupnost = TRUE WHERE nazev = ?";

				    try (PreparedStatement prStmt = conn.prepareStatement(updateDost);) {
				    	  prStmt.setString(1, nazev);
				      prStmt.executeUpdate();
				      System.out.println("Zmenila se dostupnost knihy");
				    } catch (SQLException e) {
				      e.printStackTrace();
				    }
			}
		 }
	 }
	 public void zmenaDostupnosti(String nazev) {
		 if (nazev == null) {
		      throw new NullPointerException("Musis zadat nazev knihy!");
		    } else if (nazev == "") {
		      throw new IllegalArgumentException("Nazev knihy nesmi byt prazdny!");
		    }
		    Connection conn = DBConn.getDBConnection();
		    System.out.println("1...pro zmenu na vypujcene");
			System.out.println("2...pro zmenu na dostupne");
			Scanner sd = new Scanner(System.in);
			int choise = Test.pouzeCelaCisla(sd);
			String updateDost;
			switch (choise) {
			case 1:
				 updateDost = "UPDATE knihy SET dostupnost = FALSE WHERE nazev = ?";

				    try (PreparedStatement prStmt = conn.prepareStatement(updateDost);) {
				      prStmt.setString(1, nazev);
				      prStmt.executeUpdate();
				      System.out.println("Zmenila se dostupnost knihy");
				    } catch (SQLException e) {
				      e.printStackTrace();
				    }
				    break;
			
			case 2:  
				   updateDost = "UPDATE knihy SET dostupnost = TRUE WHERE nazev = ?";

				    try (PreparedStatement prStmt = conn.prepareStatement(updateDost);) {
				    	  prStmt.setString(1, nazev);
				      prStmt.executeUpdate();
				      System.out.println("Zmenila se dostupnost knihy");
				    } catch (SQLException e) {
				      e.printStackTrace();
				    }
			break;
		 }
		  }
	 /*
	  * 
	  * Select queries 
	  * 
	  *
	  */
	  public void vypisAllKnih() {
		    Connection conn = DBConn.getDBConnection();
		    String selectAllKnihy =
		        "SELECT * FROM knihy ORDER BY nazev ASC";
		    try (PreparedStatement prStmt = conn.prepareStatement(selectAllKnihy);) {
		      ResultSet rs = prStmt.executeQuery();
		      while (rs.next()) {
		    	  if (rs.getString("zanr") == null) {
		    		  if(rs.getBoolean("dostupnost")) {
		  		        System.out.println(rs.getString("nazev") + ", " + rs.getString("autor") + ", Rocnik "
		  		            + rs.getInt("rocnik") + ", " + rs.getInt("rok") + ", " + "dostupne");}else {
		  		            	  System.out.println(rs.getString("nazev") + ", " + rs.getString("autor") + ", Rocnik "
		  			  		            + rs.getInt("rocnik") + ", " + rs.getInt("rok") + ", " + "nedostupne");
		  		            }  
		    	  }
		    	  else {
		    		  if(rs.getBoolean("dostupnost")) {
		        System.out.println(rs.getString("nazev") + ", " + rs.getString("autor") + ", "
		            + rs.getString("zanr") + ", " + rs.getInt("rok") + ", " + "dostupne");}else {
		            	  System.out.println(rs.getString("nazev") + ", " + rs.getString("autor") + ", "
		      		            + rs.getString("zanr") + ", " + rs.getInt("rok") + ", " + "nedostupne");
		            } 
		      }  
		      }
		    } catch (SQLException e) {
		      e.printStackTrace();
		    }
		  }
	  
	  public void vypisVupujKnih() {
		    Connection conn = DBConn.getDBConnection();
		    String selectVypujKnihy =
		        "SELECT * FROM knihy WHERE dostupnost = FALSE";
		    try (PreparedStatement prStmt = conn.prepareStatement(selectVypujKnihy);) {
		      ResultSet rs = prStmt.executeQuery();
		      while (rs.next()) {
		    	  if (rs.getString("zanr") == null) {
		  		      System.out.println(rs.getString("nazev") + ", " + rs.getString("autor") + ", Ucebnice");
		  		            }
		    	  else {
		        System.out.println(rs.getString("nazev") + ", " + rs.getString("autor") + ", Roman");		            
		      }  
		      }
		    } catch (SQLException e) {
		      e.printStackTrace();
		    }
		  }
	  
	  public void vypisZanru(String zanr) {
		  if (zanr == null) {
		      throw new NullPointerException("Musis zadat zanr romanu!");
		    } else if (zanr == "") {
		      throw new IllegalArgumentException("Nazev zanru nesmi byt prazdny!");
		    }
		    Connection conn = DBConn.getDBConnection();
		    String selectZanr =
		        "SELECT * FROM knihy WHERE zanr = ?";
		    try (PreparedStatement prStmt = conn.prepareStatement(selectZanr);) {
		      prStmt.setString(1, zanr);
		      ResultSet rs = prStmt.executeQuery();
		      while (rs.next()) {
		    	  System.out.println(rs.getString("nazev") + ", " + rs.getString("autor") + ", " + rs.getInt("rok"));
		      }
		    } catch (SQLException e) {
		      e.printStackTrace();
		    }
		  }
	  
	  public void vypisKonkKnihy(String nazev) {
		  if (nazev == null) {
		      throw new NullPointerException("Musis zadat nazev knihy!");
		    } else if (nazev == "") {
		      throw new IllegalArgumentException("Nazev knihy nesmi byt prazdny!");
		    }
		    Connection conn = DBConn.getDBConnection();
		    String selectKonkKnihy =
		        "SELECT * FROM knihy WHERE nazev = ?";
		    try (PreparedStatement prStmt = conn.prepareStatement(selectKonkKnihy);) {
		    	 prStmt.setString(1, nazev);
		      ResultSet rs = prStmt.executeQuery();
		      while (rs.next()) {
		    	  if (rs.getString("zanr") == null) {
		    		  if(rs.getBoolean("dostupnost")) {
		  		        System.out.println(rs.getString("nazev") + ", " + rs.getString("autor") + ", Rocnik "
		  		            + rs.getInt("rocnik") + ", " + rs.getInt("rok") + ", " + "dostupne");}else {
		  		            	  System.out.println(rs.getString("nazev") + ", " + rs.getString("autor") + ", Rocnik "
		  			  		            + rs.getInt("rocnik") + ", " + rs.getInt("rok") + ", " + "nedostupne");
		  		            }  
		    	  }
		    	  else {
		    		  if(rs.getBoolean("dostupnost")) {
		        System.out.println(rs.getString("nazev") + ", " + rs.getString("autor") + ", "
		            + rs.getString("zanr") + ", " + rs.getInt("rok") + ", " + "dostupne");}else {
		            	  System.out.println(rs.getString("nazev") + ", " + rs.getString("autor") + ", "
		      		            + rs.getString("zanr") + ", " + rs.getInt("rok") + ", " + "nedostupne");
		            } 
		      }  
		      }
		    } catch (SQLException e) {
		      e.printStackTrace();
		    }
		  }
	  
	  public void vypisAutora(String autor) {
		  if (autor == null) {
		      throw new NullPointerException("Musis zadat jmeno autora!");
		    } else if (autor == "") {
		      throw new IllegalArgumentException("Jmeno autora nesmi byt prazdne!");
		    }
		    Connection conn = DBConn.getDBConnection();
		    String selectAutor =
		        "SELECT * FROM knihy WHERE autor = *?* ORDER BY rok ASC";
		    try (PreparedStatement prStmt = conn.prepareStatement(selectAutor);) {
		    	 prStmt.setString(1, autor);
		      ResultSet rs = prStmt.executeQuery();
		      while (rs.next()) {
		    	  System.out.println(rs.getString("autor")  + ": " + rs.getString("nazev") + ": " + rs.getInt("rok"));
		      }
		    } catch (SQLException e) {
		      e.printStackTrace();
		    }
		  }
}
