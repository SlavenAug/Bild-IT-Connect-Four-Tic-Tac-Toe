package tic_tac_toe;

import java.util.InputMismatchException;

public class TicTacToe {

	public static void main(String[] args) {
		// Kreiramo tabelu 3x3 sa vrijednostima 0.
		int[][] table = { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };
		// Postavljamo uslov petlje.
		boolean condition = false;
		// Kreiramo varijabli za igrace 1 i 2.
		int player = 2;
		// Postavljamo brojac polja, max 9.
		int count = 0;

		while (!condition) {
			// Ispisujemo tabelu.
			printTable(table);
			// Pocinjemo sa prvim igracem, svaki put se mijenja igrac.
			player = player == 2 ? 1 : 2;
			// Pozivamo metodu za unos u tabelu.
			table(table, player);
			// Povecavamo brojac polja.
			count++;

			// Provjeravamo da li je igrac pobijedio, tako sto provjeravamo
			// redove, kolone i diagonale.
			condition = checkRows(table, player) || checkColums(table, player) || checkDiagonal(table, player);

			// Ako smo ispunili polja prekidamo igru.
			if (count > 9) {
				break;
			}
		}

		// Ispisujemo rezultat ako je neko pobijedio ili je bilo nerijeseno.
		if (condition) {
			System.out.println("Player " + player + " wins!");
		} else {
			System.out.println("Game is eaven!");
		}
		// Printamo tabelu.
		printTable(table);

	}

	// Metoda printa tabelu.
	public static void printTable(int[][] table) {
		// Printamo tabelu.
		System.out.println("     0     1     2");
		System.out.println("   ------------------");
		for (int i = 0; i < table.length; i++) {

			System.out.print(i + " |");
			for (int j = 0; j < table.length; j++) {
				/*
				 * Printamo vrijednosti tabele, ako je 0 prikazujemo prazno
				 * polje, ako je 1 prikazujemo X, ako je 2 prikazujemo O.
				 */
				System.out.print((table[i][j] != 0 ? "  " + (table[i][j] == 1 ? "X" : "O") + " " : "    ") + " |");
			}
			System.out.print("\n  ------------------\n");
		}
	}

	// Metoda koja stiti korisnikov unos.
	public static int userInput() {
		java.util.Scanner input = new java.util.Scanner(System.in);
		// Kreiramo varijablu za unos.
		int num = 0;
		// Postavljamo uslov petlje.
		boolean condition = true;

		while (condition) {
			try {
				num = input.nextInt();
				if (num >= 0 && num < 3) {
					// Ako je uneseno 0, 1, ili 2 postavljamo uslov petlje
					// na false i izlazimo iz petlje.
					condition = false;
				} else {
					// U suprotnom ispisujemo poruku.
					System.out.println("You must enter from 0 to 2! Please try again.");
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

	// Metoda za unos polja u tabelu.
	public static void table(int[][] table, int player) {
		// Postavljamo uslov petlje.
		boolean condition = true;

		while (condition) {
			// Korisnikov unos za red.
			System.out.println("Player " + player + " enter row: ");
			int row = userInput();
			// Korisnikov unos za kolonu.
			System.out.println("Player " + player + " enter colum: ");
			int colum = userInput();

			// Ako je polje zauzeto ispisuje gresku.
			if (table[row][colum] != 0) {
				System.out.println("You cannot play that field! Please try another field: ");
				printTable(table);
			} else {
				// U suprotnom prihvata korisnikov unos.
				table[row][colum] = player;
				condition = false;
			}
		}
	}

	// Metoda za provjeru redova.
	public static boolean checkRows(int[][] table, int player) {
		for (int i = 0; i < table.length; i++) {
			int count = 0;
			// Petlja prolazi kroz redove tabele.
			for (int j = 0; j < table.length; j++) {
				// Ako je vrijednost tabele jednaka broju igraca,
				// onda uvecavamo brojac.
				if (table[i][j] == player) {
					count++;
				}
			}
			// Ako je brojac jedank 3, vracamo true, igrac je pobjedio.
			if (count == 3) {
				return true;
			}
		}
		// U suprotnom vracamo false, igrac nije pobjedio.
		return false;
	}

	// Metoda za provjeru kolona.
	public static boolean checkColums(int[][] table, int player) {
		for (int i = 0; i < table.length; i++) {
			int count = 0;
			// Petlja prolazi kroz kolone tabele.
			for (int j = 0; j < table.length; j++) {
				// Ako je vrijednost tabele jednaka broju igraca,
				// onda uvecavamo brojac.
				if (table[j][i] == player) {
					count++;
				}
			}
			// Ako je brojac jedank 3, vracamo true, igrac je pobjedio.
			if (count == 3) {
				return true;
			}
		}
		// U suprotnom vracamo false, igrac nije pobjedio.
		return false;
	}

	// Metoda za provjeru dijagonala tabele.
	public static boolean checkDiagonal(int[][] table, int player) {
		int count = 0;
		// Petlja za provjeru prve diagonale.
		for (int i = 0; i < table.length; i++) {
			if (table[i][i] == player) {
				count++;
			}
		}

		// Ako je brojac jedank 3, vracamo true, igrac je pobjedio.
		if (count == 3) {
			return true;
		}

		// Petlja za provjeru druge diagonale.
		for (int i = 0; i < table.length; i++) {
			// Ako sadrzaj polja nije jedank igracevim brojem,
			// vraca false, a u suprotnom vraca true.
			if (table[i][table.length - 1 - i] != player) {
				return false;
			}
		}
		return true;
	}

}
