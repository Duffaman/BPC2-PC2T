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
		DBQueries dotaz = new DBQueries();
		while (running) {	 
		System.out.println("Vyberte z nabidky moznosti");	
			System.out.println("1 .. Pridani nove knihy");
			System.out.println("2 .. Uprava knihy");
			System.out.println("3 .. Smazani knihy");
			System.out.println("4 .. Oznaceni knihy");
			System.out.println("5 .. Vypis vsech knih");
			System.out.println("6 .. Vyhledani knihy");
			System.out.println("7 .. Vypis vsech knih autora");
			System.out.println("8 .. Vypis knih podle zanru");
			System.out.println("9 .. Vypis vypujcenych knih");
			System.out.println("10 .. ukonceni aplikace"); 
	      prikaz = pouzeCelaCisla(sc);
				 switch (prikaz) {
				 case 1:
					  System.out.println("1...pro pridani romanu");
						System.out.println("2...pro pridani ucebnice");
						int choise = pouzeCelaCisla(sc);
						switch (choise) { 
				 case 1:
					 System.out.println("Zadejte nazev knihy");
			          String nazev = sc.next();
			          if (dotaz.testIfExists(nazev)) {
			            nazev = "";
			            do {
			              System.out.println(
			                  "Kniha s timto nazvem jiz existuje");
			              nazev = sc.next();
			            } while (dotaz.testIfExists(nazev));
			        }
			          System.out.println("Zadejte autora");
			          String autor = sc.next();
			          System.out.println("Zadejte zanr");
			          String zanr = sc.next();
			          System.out.println("Zadejte rok");
			          int rok = pouzeCelaCisla(sc);
			          dotaz.vlozRoman(nazev, autor, zanr, rok);
			      break;
				 case 2:
					 System.out.println("Zadejte nazev knihy");
			         	nazev = sc.next();
			          if (dotaz.testIfExists(nazev)) {
			            nazev = "";
			            do {
			              System.out.println(
			                  "Kniha s timto nazvem jiz existuje");
			              nazev = sc.next();
			            } while (dotaz.testIfExists(nazev));
			        }
			          System.out.println("Zadejte autora");
			          autor = sc.next();
			          System.out.println("Zadejte rocnik");
			          int rocnik = pouzeCelaCisla(sc);
			          System.out.println("Zadejte rok");
			          rok = pouzeCelaCisla(sc);
			          dotaz.vlozUceb(nazev, autor, rocnik, rok);
			      break;
				}
						break;
				 case 2:
					 System.out.println("Zadejte nazev knihy");
			          String nazev = sc.next();
			          if (dotaz.testIfExists(nazev)) {
			        	  dotaz.zmenaDleNazvu(nazev);
			          }else {
			              System.out.println("Kniha s timto nazvem neexistuje"); 
			        }
					 break;
				 case 3:
					 System.out.println("Zadejte nazev knihy");
			          nazev = sc.next();
			          dotaz.smazKnihu(nazev);
					 break;
				 case 4:
					 System.out.println("Zadejte nazev knihy");
			          nazev = sc.next();
			          if (dotaz.testIfExists(nazev)) {
			        	  dotaz.zmenaDostupnosti(nazev);
			          }else {
			              System.out.println("Kniha s timto nazvem neexistuje"); 
			        }
					 break;
				 case 5:
					 dotaz.vypisAllKnih();
					 break;
				 case 6:
					 System.out.println("Zadejte nazev knihy");
			          nazev = sc.next();
			          if (dotaz.testIfExists(nazev)) {
			        	  dotaz.vypisKonkKnihy(nazev);
			          }else {
			              System.out.println("Kniha s timto nazvem neexistuje"); 
			        }
					 break;
				 case 7:
					 System.out.println("Zadejte autora");
			          String autor = sc.next();
			          dotaz.vypisAutora(autor);
					 break;
				 case 8:
					 System.out.println("Zadejte zanr romanu");
			          String zanr = sc.next();
			          dotaz.vypisZanru(zanr);
					 break;
				 case 9:
					 dotaz.vypisVupujKnih();
					 break;
				 case 10:
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

