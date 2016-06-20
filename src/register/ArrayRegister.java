package register;

import java.io.Serializable;

import register.exception.BadIndexException;
import register.exception.DuplicationException;

/**
 * register.Person register.
 */
public class ArrayRegister implements Register, Serializable {
	/** register.Person array. */
	private Person[] persons;

	/** Number of persons in this register. */
	private int count;

	/**
	 * Constructor creates an empty register with maximum size specified.
	 * 
	 * @param size
	 *            maximum size of the register
	 */
	public ArrayRegister(int size) {
		persons = new Person[size];
		count = 0;
	}

	/* (non-Javadoc)
	 * @see register.Register#getCount()
	 */
	@Override
	public int getCount() {
		return count;
	}

	/* (non-Javadoc)
	 * @see register.Register#getSize()
	 */
	@Override
	public int getSize() {
		return persons.length;
	}

	/* (non-Javadoc)
	 * @see register.Register#getPerson(int)
	 */
	@Override
	public Person getPerson(int index) throws BadIndexException {
		return persons[index];
	}

	/* (non-Javadoc)
	 * @see register.Register#addPerson(register.Person)
	 */
	@Override
	public void addPerson(Person person) throws DuplicationException {

		if (isInRegister(person))
			throw new DuplicationException("User already exists (with same name or phone number)");

		persons[count] = person;
		count++;
		
		sortRegisterByName();
	}

	/**
	 * Returns true, if person is in register, otherwise returns false;
	 * 
	 * @param person
	 * @return
	 */
	private boolean isInRegister(Person person) {

		for (int i = 0; i < count; i++) {

			Person comparingPerson = persons[i];

			if (comparingPerson.getName().equals(person.getName())
					|| comparingPerson.getPhoneNumber().equals(person.getPhoneNumber()))
				return true;

		}

		return false;
	}

	/* (non-Javadoc)
	 * @see register.Register#findPersonByName(java.lang.String)
	 */
	@Override
	public Person findPersonByName(String name) {
		name = name.toLowerCase();

		for (Person person : persons) {
			if (person.getName().toLowerCase().contains(name))
				return person;
		}

		return null;
	}

	/* (non-Javadoc)
	 * @see register.Register#findPersonByPhoneNumber(java.lang.String)
	 */
	@Override
	public Person findPersonByPhoneNumber(String phoneNumber) {

		for (Person person : persons) {
			if (person.getPhoneNumber().equals(phoneNumber))
				return person;
		}

		return null;
	}

	/* (non-Javadoc)
	 * @see register.Register#removePerson(register.Person)
	 */
	@Override
	public void removePerson(Person person) {
		for (int i = 0; i < count; i++) {
			if (persons[i].equals(person)) {

				count--;
				for (int j = i; j < count; j++) {
					persons[j] = persons[j + 1];
				}
			}
		}
	}

	@Override
	public void sortRegisterByName() {
		
		Person tempPerson;
		
		for(int i = 0; i < count-1; i++){
			for(int j = i+1; j < count; j++)
			{
				if(persons[i].compareTo(persons[j]) > 0){
					tempPerson = persons[i];
					persons[i]=persons[j];
					persons[j] = tempPerson;
				}
			}
		}
		
	}

	@Override
	public void removeAllBy(char firstLetter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		
	}

}
