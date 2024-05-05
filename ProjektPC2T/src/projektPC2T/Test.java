package projektPC2T;
import java.util.Scanner;




public class Test {
	
	public static int pouzeCelaCisla(Scanner sc) {
			int cislo = 0;
			try {
				cislo = sc.nextInt();
				} catch (Exception e) {
					System.out.println("Nastala vyjimka typu " + e.toString());
					System.out.println("zadejte prosim cele cislo ");
					sc.nextLine();
      cislo = pouzeCelaCisla(sc);
    }
    return cislo;
		}
		
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean running = true;
		int prikaz;
				 while (running) {	 
					 System.out.println("Vyberte z nabidky moznosti");
				
	      System.out.println("1 .. ");
	      System.out.println("2 .. ");
	      System.out.println("3 .. ");
	      System.out.println("4 .. ");
	      System.out.println("5 .. ");
	      System.out.println("6 .. ");
	      System.out.println("7 .. ");
	      System.out.println("8 .. ukonceni aplikace"); 
	      prikaz = pouzeCelaCisla(sc);
				 switch (prikaz) {
				 case 1:
					 break;
				 case 2:
					 break;
				 case 3:
					 break;
				 case 4:
					 break;
				 case 5:
					 break;
				 case 6:
					 break;
				 case 7:
					 break;
				 case 8:
			          running = false;
			          DBConn.closeConnection();
			          break;
			        default:
			          running = false;
			          DBConn.closeConnection();
			          break;
				}
			}
		}
	}

