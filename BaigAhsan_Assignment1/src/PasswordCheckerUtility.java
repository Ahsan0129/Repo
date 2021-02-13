import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordCheckerUtility {
/**
 * The comparePasswords method checks if the user inputed two of the same passwords
 * @param password
 * @param passwordConfirm
 * @throws UnmatchedException if the password entered is similar to one before
 */
	static void comparePasswords(String password, String passwordConfirm) throws UnmatchedException {
		if (!password.equals(passwordConfirm)) {
			throw new UnmatchedException();
		}

	}
/**
 * 
 * @param password
 * @param passwordConfirm
 * @return the password if it doesn't equal another password
 */
	static boolean comparePasswordsWithReturn(String password, String passwordConfirm) {
		return password.equals(passwordConfirm);
	}
/**
 * This method checks if the password is valid and if it is it checks if its between 6 and 9 characters
 * @param password
 * @return return the password if it is between 6 and 9 characters as a weak password
 */
	static boolean hasBetweenSixandNineChars(String password) {
		return password.length() >= 6 && password.length() <= 9;
	}
/**
 * Checks if the password has atleast 1 digit
 * @param password
 * @return
 * @throws NoDigitException if the password doesnt contain a single digit
 */
	static boolean hasDigit(String password) throws NoDigitException {
		for (int i = 0; i < password.length(); i++) {
			char pt = password.charAt(i);
			if (Character.isDigit(pt)) {
				return true;
			}
		}
		throw new NoDigitException();
	}
/**
 * Checks if the password has atleast 1 lower case
 * @param password
 * @return true if the password has an lower case
 * @throws NoLowerAlphaException if the password doesnt have an lower case letter
 */
	static boolean hasLowerAlpha(String password) throws NoLowerAlphaException {
		for (int i = 0; i < password.length(); i++) {
			char pt = password.charAt(i);
			if (Character.isLowerCase(pt)) {
				return true;
			}
		}
		throw new NoLowerAlphaException();
	}
/**
 * Checks if the password contains atleast 1 uppercase leter
 * @param password
 * @return true if the password has an upper case letter
 * @throws NoUpperAlphaException if the password doesnt contain an uppercase letter
 */
	static boolean hasUpperAlpha(String password) throws NoUpperAlphaException {
		for (int i = 0; i < password.length(); i++) {
			char pt = password.charAt(i);
			if (Character.isUpperCase(pt)) {
				return true;
			}
		}
		throw new NoUpperAlphaException();
	}
/**
 * Checks if the password has atleast 1 special character
 * @param password
 * @return true if the password has a special character
 * @throws NoSpecialCharacterException if the password doesnt contain a special character
 */
	public static boolean hasSpecialChar(String password) throws NoSpecialCharacterException {
		Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
		Matcher matcher = pattern.matcher(password);
		if (!matcher.matches()) {
			return true;
		}
		throw new NoSpecialCharacterException();
	}
/**
 * Checks if the password is greater than or equal to 6
 * @param password
 * @return true if the password is 6 chracters or more
 * @throws LengthException if the password is less than 6 characters
 */
	public static boolean isValidLength(java.lang.String password) throws LengthException {
		if (!(password.length() >= 6)) {
			throw new LengthException();
		}
		return true;
	}
/**
 * Checks if the password has the same character in the i+1 and the i+2
 * @param password
 * @return true if the password has 2 or less characters in the same sequence
 * @throws InvalidSequenceException if the password contains more than 2 of the same letter in a sequence
 */
	public static boolean hasSameCharInSequence(String password) throws InvalidSequenceException {
		for (int i = 0; i < password.length() - 2; i++) {
			if (password.charAt(i) == password.charAt(i + 1) && password.charAt(i) == password.charAt(i + 2)) {
				throw new InvalidSequenceException();
			}
		}
		return true;
	}
/**
 * This checks the validity of the whole password but running through every exception
 * @param password
 * @return true if the password is valid
 * @throws LengthException
 * @throws NoDigitException
 * @throws NoUpperAlphaException
 * @throws NoLowerAlphaException
 * @throws InvalidSequenceException
 * @throws NoSpecialCharacterException
 */
	public static boolean isValidPassword(String password) throws LengthException, NoDigitException,
			NoUpperAlphaException, NoLowerAlphaException, InvalidSequenceException, NoSpecialCharacterException {

		return isValidLength(password) && hasDigit(password) && hasUpperAlpha(password) && hasLowerAlpha(password)
				&& hasSpecialChar(password) && hasSameCharInSequence(password);

	}
/**
 * Checks if the password is between 6 and 9 characters
 * @param password
 * @return true if the password is more than 9 characters
 * @throws WeakPasswordException if the password is between 6 to 9 characters
 */
	public static boolean isWeakPassword(String password) throws WeakPasswordException {
		try {
			if (password.length() >= 6 && password.length() <= 9 && isValidPassword(password)) {
				throw new WeakPasswordException();

			}
		} catch (LengthException | NoDigitException | NoUpperAlphaException | NoLowerAlphaException
				| InvalidSequenceException | NoSpecialCharacterException e) {
			return true;
		}
		return true;

	}
/**
 * Stores all the invalid passwords(not the weak ones)
 * @param passwords
 * @return the invalid passwords
 */
	public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords) {
		ArrayList<String> invalid = new ArrayList<String>();
		for (int i = 0; i < passwords.size(); i++) {
			String pass = passwords.get(i);
			try {
				isValidPassword(pass);
			} catch (LengthException | NoDigitException | NoUpperAlphaException | NoLowerAlphaException
					| InvalidSequenceException | NoSpecialCharacterException e) {
				invalid.add(pass + " " + e.getMessage());
			}
		}
		return invalid;
	}


}