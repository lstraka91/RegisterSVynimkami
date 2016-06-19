package register;

import register.exception.BadIndexException;
import register.exception.DuplicationException;

public interface Register {

	/**
	 * Returns the number of persons in this register.
	 * 
	 * @return the number of persons in this register
	 */
	int getCount();

	/**
	 * Returns the maximum number of persons in this register.
	 * 
	 * @return the maximum number of persons in this register.
	 */
	int getSize();

	/**
	 * Returns the person at the specified position in this register.
	 * 
	 * @param index
	 *            index of the person to return
	 * @return person the person at the specified position in this register
	 */
	Person getPerson(int index) throws BadIndexException;

	/**
	 * Appends the specified person to the end of this register.
	 * 
	 * @param person
	 *            person to append to this register
	 */
	void addPerson(Person person) throws DuplicationException;

	/**
	 * Returns the person with specified name in this register or
	 * <code>null</code>, if match can not be found.
	 * 
	 * @param name
	 *            name of a person to search for
	 * @return person with specified phone number
	 */
	Person findPersonByName(String name);

	/**
	 * Returns the person with specified phone number in this register or
	 * <code>null</code>, if match can not be found.
	 * 
	 * @param phoneNumber
	 *            phone number of a person to search for
	 * @return person with specified phone number
	 */
	Person findPersonByPhoneNumber(String phoneNumber);

	/**
	 * Removes the specified person from the register.
	 * 
	 * @param person
	 *            person to remove
	 */
	void removePerson(Person person);
	
	void removeAllBy(char firstLetter);
	
	void sortRegisterByName();

}