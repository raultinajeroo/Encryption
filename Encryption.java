import java.util.Scanner;

public class Encryption {
	public static void main(String []args) {
		Scanner scan = new Scanner(System.in);
		String  alphabet = "abcdefghijklmnopqrstuvwxyz";
		int path;
		String key;

		// Lets the user decide which path they wanna take with the program
		do {
			System.out.print("Would you like to [1] Encrypt or [2] Decrypt? ");
			path = scan.nextInt();
		} 
		while (path != 1 && path != 2);

		scan.nextLine();

		// Gets the key from the user and verifies if it's a valid key
		do {
			System.out.print("Key: ");
			key = scan.nextLine();
		} 
		while (!keyVerifier(key));

		// Gets the text the user want to operate from
		System.out.print("Your text: ");
		String text = scan.nextLine();

		scan.close();

		// Action path
		if (path == 1) {
			System.out.print("Ciphered Message: " + encrypt(key, alphabet, text, 0));
		}
		else if (path == 2) {
			System.out.print("Deciphered Message: " + decrypt(key, alphabet, text, 0));
		}

		System.out.println();
	}

	public static String encrypt(String key, String alphabet, String text, int index) {
		// Returns the ciphered string to the previous functions, and ultimately, the main function.
		if (index >= text.length()) {
			return text;
		}

		for (int i = 0; i < alphabet.length(); i++) {
			char currentChar = alphabet.charAt(i);
			char keyChar = key.charAt(i);

			// checks to find the char of the alphabet that matches the text char.
			// This part was purposely made to avoid changing any character that is not a letter.
			if (text.charAt(index) == currentChar) { // changes the lower case letters
				if (index == 0) {
					text = keyChar + text.substring(index + 1);
					break;
				}
				else {
					text = text.substring(0, index) + keyChar + text.substring(index + 1);
					break;
				}
			} 
			else if ((text.charAt(index) == (char)(currentChar - 32))) { // changes the upper case letters 
				if (index == 0) {
					text = (char)(keyChar - 32) + text.substring(index + 1);
					break;
				}
				else {
					text = text.substring(0, index) + (char)(keyChar - 32) + text.substring(index + 1);
					break;
				}
			}
		}

		return encrypt(key, alphabet, text, ++index);
	}

	public static String decrypt(String key, String alphabet, String text, int index) {
		// Returns the ciphered string to the previous functions, and ultimately, the main function.
		if (index >= text.length()) {
			return text;
		}

		for (int i = 0; i < alphabet.length(); i++) { // checks the lower case letters
			char currentChar = key.charAt(i);
			char alphabetChar = alphabet.charAt(i);

			// checks to find the char of the key that matches the text char.
			if (text.charAt(index) == currentChar) { // changes the lower case letters
				if (index == 0) {
					text = alphabetChar + text.substring(index + 1);
					break;
				}
				else {
					text = text.substring(0, index) + alphabetChar + text.substring(index + 1);
					break;
				}
			}
			else if ((text.charAt(index) == (char)(currentChar - 32))) { // changes the upper case letters 
				if (index == 0) {
					text = (char)(alphabetChar - 32) + text.substring(index + 1);
					break;
				}
				else {
					text = text.substring(0, index) + (char)(alphabetChar - 32) + text.substring(index + 1);
					break;
				}
			}
		}

		return decrypt(key, alphabet, text, ++index);
	}

	public static boolean dupeFinder(String key, int keyLength) {
		// the matches variable allows the program to identify if any letter has been written more than once
		int matches = 0;
		key = key.toLowerCase();

		// Compares all of the letters
		for (int i = 0; i < keyLength; i++) {
			for (int j = 0; j < keyLength; j++) {
				if (key.charAt(i) == key.charAt(j)) {
					matches++;
				}
			}
		}

		// if there are not 26 matches, the program will ask for another key
		if (matches == 26)
			return true;
		else
			return false;
	}

	/* Verifies the key, according to this criteria:
	 * 1. Length = 26 characters
	 * 2. Only letters can be found in the key
	 * 3. No letters are found more than once
	 */
	public static boolean keyVerifier(String key) {
		int keyLength = key.length();
		int letterCount = 0;
		// Length = 26 characters
		if (keyLength != 26) {
			return false;
		}

		// Only letters can be found in the key
		for (int i = 0; i < key.length(); i++) {
			if (key.charAt(i) >= 'A' && key.charAt(i) <= 'Z') {
				letterCount++;
				continue;
			}
			else if (key.charAt(i) >= 'a' && key.charAt(i) <= 'z') {
				letterCount++;
				continue;
			}
		}

		if (letterCount != 26) {
			return false;
		}

		// No letters are found more than once
		return dupeFinder(key, keyLength);
	}
}