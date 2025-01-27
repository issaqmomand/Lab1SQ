package com.ontariotechu.sofe3980U;

/**
 * Unsigned integer Binary variable
 *
 */
public class Binary {
	private String number = "0"; // string containing the binary value '0' or '1'

	/**
	 * A constructor that generates a binary object.
	 *
	 * @param number a String of the binary values. It should contain only zeros or ones with any length and order. otherwise, the value of "0" will be stored.   Trailing zeros will be excluded and empty string will be considered as zero.
	 */
	public Binary(String number) {
		if (number == null || number.isEmpty()) {
			this.number = "0"; // Default to "0" for null or empty input
			return;
		}

		// Validate the binary string (only '0' or '1' allowed)
		for (int i = 0; i < number.length(); i++) {
			char ch = number.charAt(i);
			if (ch != '0' && ch != '1') {
				this.number = "0"; // Default to "0" for invalid input
				return;
			}
		}

		// Remove leading zeros
		int beg;
		for (beg = 0; beg < number.length(); beg++) {
			if (number.charAt(beg) != '0') {
				break;
			}
		}

		// If all digits are '0', ensure number is "0"
		this.number = (beg == number.length()) ? "0" : number.substring(beg);

		if (this.number.isEmpty()) { // replace empty strings with a single zero
			this.number = "0";
		}
	}

	/**
	 * Return the binary value of the variable
	 *
	 * @return the binary value in a string format.
	 */
	public String getValue() {
		return this.number;
	}

	/**
	 * Adding two binary variables.
	 *
	 * @param num1 The first addend object
	 * @param num2 The second addend object
	 * @return A binary variable with a value of <i>num1+num2</i>.
	 */
	public static Binary add(Binary num1, Binary num2) {
		// the index of the first digit of each number
		int ind1 = num1.number.length() - 1;
		int ind2 = num2.number.length() - 1;
		// initial variable
		int carry = 0;
		String num3 = ""; // the binary value of the sum
		while (ind1 >= 0 || ind2 >= 0 || carry != 0) // loop until all digits are processed
		{
			int sum = carry; // previous carry
			if (ind1 >= 0) { // if num1 has a digit to add
				sum += (num1.number.charAt(ind1) == '1') ? 1 : 0; // convert the digit to int and add it to sum
				ind1--; // update ind1
			}
			if (ind2 >= 0) { // if num2 has a digit to add
				sum += (num2.number.charAt(ind2) == '1') ? 1 : 0; // convert the digit to int and add it to sum
				ind2--; // update ind2
			}
			carry = sum / 2; // the new carry
			sum = sum % 2; // the resultant digit
			num3 = ((sum == 0) ? "0" : "1") + num3; // convert sum to string and append it to num3
		}
		return new Binary(num3); // create a binary object with the calculated value.
	}

	/**
	 * Performs a bitwise OR operation between two binary numbers.
	 *
	 * @param b1 First binary number
	 * @param b2 Second binary number
	 * @return The result of bitwise OR as a new Binary object.
	 */
	public static Binary or(Binary b1, Binary b2) {
		StringBuilder result = new StringBuilder();
		String val1 = padZeros(b1.number, b2.number);
		String val2 = padZeros(b2.number, b1.number);

		for (int i = 0; i < val1.length(); i++) {
			result.append((val1.charAt(i) == '1' || val2.charAt(i) == '1') ? "1" : "0");
		}
		return new Binary(result.toString());
	}

	/**
	 * Performs a bitwise AND operation between two binary numbers.
	 *
	 * @param b1 First binary number
	 * @param b2 Second binary number
	 * @return The result of bitwise AND as a new Binary object.
	 */
	public static Binary and(Binary b1, Binary b2) {
		StringBuilder result = new StringBuilder();
		String val1 = padZeros(b1.number, b2.number);
		String val2 = padZeros(b2.number, b1.number);

		for (int i = 0; i < val1.length(); i++) {
			result.append((val1.charAt(i) == '1' && val2.charAt(i) == '1') ? "1" : "0");
		}
		return new Binary(result.toString());
	}

	/**
	 * Multiplies two binary numbers using repeated addition.
	 *
	 * @param b1 First binary number
	 * @param b2 Second binary number
	 * @return The result of multiplication as a new Binary object.
	 */
	public static Binary multiply(Binary b1, Binary b2) {
		Binary result = new Binary("0");
		for (int i = b2.number.length() - 1; i >= 0; i--) {
			if (b2.number.charAt(i) == '1') {
				Binary temp = new Binary(b1.number + "0".repeat(b2.number.length() - 1 - i));
				result = Binary.add(result, temp);
			}
		}
		return result;
	}

	/**
	 * Pads the shorter binary string with leading zeros to match the length of
	 * the longer string.
	 *
	 * @param str1 First string
	 * @param str2 Second string
	 * @return Padded string
	 */
	private static String padZeros(String str1, String str2) {
		int length = Math.max(str1.length(), str2.length());
		return "0".repeat(length - str1.length()) + str1;
	}
}
