package register;

import java.io.Serializable;

import register.exception.*;

/**
 * register.Person.
 */
public class Person implements Comparable<Person>,Serializable {
	/** Name of this person. */
	private String name;

	/** Phone number of this person. */
	private String phoneNumber;

	/**
	 * Construct a person.
	 * 
	 * @param name
	 *            name of the person
	 * @param phoneNumber
	 *            phone number of the person
	 */
	public Person(String name, String phoneNumber) throws ValidationException, WrongFormatException {
		this.name = name;
		this.setPhoneNumber(phoneNumber);
	}

	/**
	 * Returns name of this person.
	 * 
	 * @return name of this person
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets name of this person.
	 * 
	 * @param nameNew
	 *            name of this person
	 */
	public void setName(String nameNew) throws WrongFormatException {
		if (name.length() == 0) {
			throw new WrongFormatException("Meno musi byt dlhsie ako 0");
		}
		name = nameNew;
	}

	/**
	 * Returns phone number of this person.
	 * 
	 * @return phone number of this person
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Sets phone number of this person.
	 * 
	 * @param phoneNumberNew
	 *            phone number of this person
	 */
	public void setPhoneNumber(String phoneNumberNew)
			throws WrongFormatException, ValidationException {
		if (phoneNumberNew.length() == 0) {
			throw new WrongFormatException(
					"Phone number must be longer than 0.");
		}
		if (phoneNumberNew.length() > 20) {
			throw new WrongFormatException(
					"Phone number must not be longer than 20 characters.");
		}
		if (isValidPhoneNumber(phoneNumberNew)) {
			phoneNumber = phoneNumberNew;
		}
	}

	/**
	 * Validates the phone number. Valid phone numbers contains only digits.
	 * 
	 * @param phoneNumber
	 *            phone number to validate
	 * @return <code>true</code> if phone number is valid, <code>false</code>
	 *         otherwise
	 */
	private boolean isValidPhoneNumber(String phoneNumber)
			throws ValidationException {

		for (int i = 0; i < phoneNumber.length(); i++) {
			if (!Character.isDigit(phoneNumber.charAt(i)))
				throw new ValidationException(
						"Phone number is not valid, only numbers are permitted");
		}

		return true;
	}

	/**
	 * Returns a string representation of the person.
	 * 
	 * @return string representation of the person.
	 */
	public String toString() {
		return name + " (" + phoneNumber + ")";
	}

	/**
	 * Compares two person's names lexicographically. The result is a positive
	 * integer if this Person's name lexicographically follows the argument
	 * Person's name. The result is zero if the names are equal;
	 */
	@Override
	public int compareTo(Person o) {
		return this.getName().compareTo(o.getName());
	}
}
