package connect_4;

import java.util.InputMismatchException;

public class Connect4 {

	public static void main(String[] args) {
		// Kreiramo brojac.
		int count = 0;
		// Kreiramo igraca.
		int player = 2;
		// Postavljamo uslov petlje.
		boolean condition = false;
		// Kreiramo tabelu i ispunjavamo je 0.
		int[][] table = new int[6][7];
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[i].length; j++) {
				table[i][j] = 0;
			}
		}

		while (!condition) {
			// Ispisujemo tabelu.
			printTable(table);
			// Postavljamo prvog igraca, svaki put se mijenja.
			player = player == 2 ? 1 : 2;
			// Metoda za upis u tabelu.
			userInput(table, player);
			// Povecavamo brojac poteza.
			count++;

			// Pozivamo metodu za provjeru da li je igrac pobjedio.
			condition = checkRow(table, player) || checkColum(table, player) || checkDiagonals(table, player);
			// Ako je broj poteza dostigao maksimum prekini igricu.
			if (count >= 42) {
				break;
			}
		}
		// Ispisuje poruku ako je neko od igraca pobjedio,
		// u suprotnom je rezultat nerijeseno.
		if (condition) {
			System.out.println("Player " + (player == 1 ? "red" : "yellow") + " wins");
		} else {
			System.out.println("Game is eaven");
		}
		printTable(table);
	}

	
	// Metoda koja stiti korisnikov unos.
	public static int checkInput() {
		java.util.Scanner input = new java.util.Scanner(System.in);
		// Kreiramo varijablu za unos.
		int num = 0;
		// Postavljamo uslov petlje.
		boolean condition = true;

		while (condition) {
			try {
				num = input.nextInt();
				if (num >= 0 && num <= 6) {
					// Ako je uneseno od 0 do 6 postavljamo uslov petlje
					// na false i izlazimo iz petlje.
					condition = false;
				} else {
					// U suprotnom ispisujemo poruku.
					System.out.println("You must enter from 0 to 6! Please try again.");
				}
			} catch (InputMismatchException e) {
				// Ispisuje poruku o netacnom tipu unosa.
				System.out.println("You not entered int value! Please try again.");
				input.nextLine();
			}
		}
		// Vraca ispravno unijetu vrijednost.
		return num;
	}

	
	// Metoda za korisnikov unos kolone od 0 do 6.
	public static void userInput(int[][] table, int player) {
		// Uslov petlje.
		boolean condition = true;
		while (condition) {
			System.out.println("Drop " + (player == 1 ? "red" : "yellow") + " disk at colum (0 - 6): ");
			// Pozivamo metodu za unos broja kolone.
			int colum = checkInput();
			// Petlja za unos u kolonu.
			for (int i = table.length - 1; i >= 0; i--) {
				// Ako se nalazi broj na mjestu kolone.
				if (table[0][colum] != 0) {
					System.out.println("You cannot select that colum, colum is used! Please try again!");
					break;
				}
				// Ako je vrijednost polja 0.
				if (table[i][colum] == 0) {
					// Dodajemo redni broj igraca i prekidamo petlju.
					table[i][colum] = player;
					condition = false;
					break;
				}
			}
		}
	}
	

	// Metoda za ispis tabele.
	public static void printTable(int[][] table) {
		System.out.println();
		for (int i = 0; i < table.length; i++) {
			// Ispisujemo liniju za redove.
			System.out.print(" |");
			for (int j = 0; j < table[i].length; j++) {
				/*
				 * Printamo vrijednosti tabele, ako je 0 prikazujemo prazno
				 * polje, ako je 1 prikazujemo R, ako je 2 prikazujemo Y.
				 */
				System.out.print((table[i][j] != 0 ? (table[i][j] == 1 ? " R " : " Y ") : "   ") + "|");
			}
			// Ispisujemo novi red.
			System.out.println();
		}
		// Ispisujemo liniju.
		System.out.print(" -----------------------------\n");
	}

	
	// Metoda provjerava redove.
	public static boolean checkRow(int[][] table, int player) {
		for (int i = 0; i < table.length; i++) {
			int count = 0;
			for (int j = 0; j < table[i].length; j++) {
				// Ako vrijednost polja nije jednaka broju igraca,
				// postavljamo brojac na 0,
				if (player != table[i][j]) {
					count = 0;
				} else { // u suprotnom uvecavamo brojac.
					count++;
				}
				// Ako se nalaze ista 4 broja u redu vraca true.
				if (count == 4) {
					return true;
				}
			}
		}
		return false;
	}
	

	// Metoda za provjeru kolona.
	public static boolean checkColum(int[][] table, int player) {
		for (int i = 0; i < table[0].length; i++) {
			int count = 0;
			for (int j = 0; j < table.length; j++) {
				// Ako vrijednost polja nije jednaka broju igraca,
				// postavljamo brojac na 0,
				if (player != table[j][i]) {
					count = 0;
				} else { // u suprotnom uvecavamo brojac.
					count++;
				}
				// Ako se nalaze ista 4 broja u redu vraca true.
				if (count == 4) {
					return true;
				}
			}
		}
		return false;
	}

	
	// Metoda za provjeru dijagonala.
	public static boolean checkDiagonals(int[][] table, int player) {
		// Postavljamo index reda.
		int i = 0;
		// Postavljamo index kolone.
		int j = 0;
		int cond = 0;
		for (int k = 0; k < 6; k++) {
			// Za svaku dijagonalu odredjujemo indekse niza i duzinu tog niza.
			if (k == 0) {
				i = j = 0;
				cond = 6;
			} else if (k == 1) {
				i = 0;
				j = 1;
			} else if (k == 2) {
				i = 0;
				j = 2;
				cond = 5;
			} else if (k == 3) {
				i = 0;
				j = 3;
				cond = 4;
			} else if (k == 4) {
				i = 1;
				j = 0;
				cond = 5;
			} else if (k == 5) {
				i = 2;
				j = 0;
				cond = 4;
			}
			// Kreiramo brojac.
			int count = 0;
			// Petlja za provjeru lijevih dijagonala.
			for (int m = 0; m < cond; m++, i++, j++) {
				// Ako vrijednost na polju nije jednaka broju igraca.
				if (player != table[i][j]) {
					// Resetujemo brojac.
					count = 0;
				} else {
					// Ako jest uvecavamo brojac.
					count++;
				}
				// Ako ima ista 4 broja vraca true.
				if (count == 4) {
					return true;
				}
			}
		}
		// Petlja za provjeru suprotne dijagonale
		for (int k = 0; k < 6; k++) {
			// Za svaku dijagonalu odredjujemo indekse niza i duzinu tog niza.
			if (k == 0) {
				i = 0;
				j = 3;
				cond = 4;
			} else if (k == 1) {
				i = 0;
				j = 4;
				cond = 5;
			} else if (k == 2) {
				i = 0;
				j = 5;
				cond = 6;
			} else if (k == 3) {
				i = 0;
				j = 6;
			} else if (k == 4) {
				i = 1;
				j = 6;
				cond = 5;
			} else if (k == 5) {
				i = 2;
				j = 6;
				cond = 4;
			}
			// Kreiramo brojac.
			int count = 0;
			for (int m = 0; m < cond; m++, i++, j--) {
				// Ako vrijednost na polju nije jednaka broju igraca.
				if (player != table[i][j]) {
					// Resetujemo brojac.
					count = 0;
				} else {
					// U suprotnom uvecavamo brojac.
					count++;
				}
				// Ako ima ista 4 broja vraca true.
				if (count == 4) {
					return true;
				}
			}
		}
		// Ako igrac nije pobjedio vraca false.
		return false;
	}

}
