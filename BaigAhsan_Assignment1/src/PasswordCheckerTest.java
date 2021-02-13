import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

class PasswordCheckerTest {
	ArrayList<String> passwordsArray;
	String password = "Hello";
	String passwordConfirm = "ooheiu";
	String allCaps = "ABCDEF";
	String withDigit = "Abcde9";

	@BeforeEach
	void setUp() throws Exception {
		String[] a = { "334455BB", "Im2cool4U", "Beautiful1!" };
		passwordsArray = new ArrayList<String>();
		passwordsArray.addAll(Arrays.asList(a));
	}

	@AfterEach
	void tearDown() throws Exception {
		passwordsArray = null;
	}

	@Test
	void testComparePasswords() {
		Throwable exception = assertThrows(UnmatchedException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				PasswordCheckerUtility.comparePasswords(password, passwordConfirm);
			}
		});

		assertEquals("The passwords do not match", exception.getMessage());
	}

	@Test
	void testComparePasswordsWithReturn() {
		assertFalse(PasswordCheckerUtility.comparePasswordsWithReturn(password, passwordConfirm));
		assertTrue(PasswordCheckerUtility.comparePasswordsWithReturn(password, password));
	}

	@Test
	void testUpperAlpha() {
		try {
			assertTrue(PasswordCheckerUtility.hasUpperAlpha("Beautiful"));
		} catch (NoUpperAlphaException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testLowerAlpha() {
		try {
			assertTrue(PasswordCheckerUtility.hasLowerAlpha("Beautiful"));
		} catch (NoLowerAlphaException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testHasDigit() {
		try {
			assertTrue(PasswordCheckerUtility.hasDigit("Beautiful1"));
		} catch (NoDigitException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testHasSpecialCharacter() {
		try {
			assertTrue(PasswordCheckerUtility.hasSpecialChar("Beautiful1!"));
		} catch (NoSpecialCharacterException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testHasNoInvalidSequence() {
		try {
			assertTrue(PasswordCheckerUtility.hasSameCharInSequence("Beautiful1!"));
		} catch (InvalidSequenceException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testWeakPasswordReturnsTrue() {
		try {
			assertTrue(PasswordCheckerUtility.isWeakPassword("Beautif"));
		} catch (WeakPasswordException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testIsValidPasswordReturnsTrue() {
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("Beautiful1!"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void NoUpperAlphaException() {
		Throwable exception = assertThrows(NoUpperAlphaException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				PasswordCheckerUtility.hasUpperAlpha("beautiful");
			}
		});
		assertEquals("The password must contain at least one uppercase alphabetic character", exception.getMessage());
	}
	
	@Test
	void NoLowerAlphaException() {
		Throwable exception = assertThrows(NoLowerAlphaException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				PasswordCheckerUtility.hasLowerAlpha("BEAUTIFUL");
			}
		});
		assertEquals("The password must contain at least one lowercase alphabetic character", exception.getMessage());
	}
	
	@Test
	void NoDigitException() {
		Throwable exception = assertThrows(NoDigitException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				PasswordCheckerUtility.hasDigit("BEAUTIFUL");
			}
		});
		assertEquals("The password must contain at least one digit", exception.getMessage());
	}
	
	@Test
	void NoSpecialCharacterException() {
		Throwable exception = assertThrows(NoSpecialCharacterException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				PasswordCheckerUtility.hasSpecialChar("BEAUTIFUL");
			}
		});
		assertEquals("The password must contain at least one special character", exception.getMessage());
	}
	
	@Test
	void InvalidSequenceException() {
		Throwable exception = assertThrows(InvalidSequenceException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				PasswordCheckerUtility.hasSameCharInSequence("BBBeAUTIFUL1$");
			}
		});
		assertEquals("Your password can't contain more than 2 letters in a sequence.", exception.getMessage());
	}
	
	@Test
	void WeakPasswordException() {
		Throwable exception = assertThrows(WeakPasswordException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				PasswordCheckerUtility.isWeakPassword("BeAUT1$");
			}
		});
		assertEquals("The password is OK but weak - it contains fewer than 10 characters.", exception.getMessage());
	}

	@Test
	void testUnmatchedException() {
		Throwable exception = assertThrows(UnmatchedException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				PasswordCheckerUtility.comparePasswords("Beautiful", allCaps);
			}
		});
		assertEquals("The passwords do not match", exception.getMessage());
	}

	@Test
	void testInValidLength() {
		Throwable exception = Assertions.assertThrows(LengthException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				PasswordCheckerUtility.isValidLength(password);
			}
		});
		assertEquals("The password must be at least 6 characters long", exception.getMessage());
	}

	@Test
	public void testGetInvalidPasswords() {
		ArrayList<String> results;
		results = PasswordCheckerUtility.getInvalidPasswords(passwordsArray);
		assertEquals(results.size(), 2);
		assertEquals(results.get(0), "334455BB The password must contain at least one lowercase alphabetic character");
		assertEquals(results.get(1), "Im2cool4U The password must contain at least one special character");
	}

}