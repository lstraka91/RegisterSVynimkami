package register;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import register.exception.BadIndexException;
import register.exception.DuplicationException;

public class ListRegister implements Register, Serializable {

	private List<Person> persons;
	private File fileRegister = new File("register.bin");

	public ListRegister(int capacity) {
		persons = new ArrayList<>(capacity);
	}

	@Override
	public int getCount() {
		return persons.size();
	}

	@Override
	public int getSize() {
		return persons.size();
	}

	@Override
	public Person getPerson(int index) throws BadIndexException {
		try {
			return persons.get(index);
		} catch (IndexOutOfBoundsException e) {
			throw new BadIndexException();
		}
	}

	@Override
	public void addPerson(Person person) throws DuplicationException {

		if (persons.contains(person)) {
			throw new DuplicationException(
					"User already exists (with same name or phone number)");
		}
		persons.add(person);
		sortRegisterByName();

		try {
			save();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Person findPersonByName(String name) {
		name = name.toLowerCase();

		// --------traverse using Iterator-------
		Iterator<Person> i = persons.iterator(); // ziskam objekt iteratora
		while (i.hasNext()) { // zisti ci existuje nasledujuca polozka
			Person p = i.next(); // vrati nasledujucu polozku
			if (p.getName().toLowerCase().contains(name)) {

				return p;
			}
			// removal example
			// i.remove(); //safe element removal in a LOOP
		}

		// //--------traverse using the "enhanced for" loop--------
		// for(Person p : persons) {
		// if (p.getName().contains(name)) {
		// return p;
		// }
		// //removal example
		// persons.remove(p); //unsafe if used in a LOOP, throws
		// ConcurrentModificationException
		// }

		return null;
	}

	@Override
	public Person findPersonByPhoneNumber(String phoneNumber) {
		for (Person p : persons) {
			if (p.getPhoneNumber().equals(phoneNumber))
				return p;
		}
		return null;
	}

	@Override
	public void removePerson(Person person) {

		persons.remove(person);
		try {
			save();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void sortRegisterByName() {
		Collections.sort(persons);
	}

	public void removeAllBy(char firstLetter) {

		// for (Person person: persons){
		//
		// if(person.getName().toLowerCase().charAt(0) == firstLetter){
		// System.out.println(person.getName());
		// persons.remove(person);
		// }
		//
		// }
		Iterator<Person> i = persons.iterator(); // ziskam objekt iteratora
		while (i.hasNext()) { // zisti ci existuje nasledujuca polozka
			Person p = i.next(); // vrati nasledujucu polozku
			if (p.getName().toLowerCase().charAt(0) == firstLetter) {

				// removal example
				i.remove(); // safe element removal in a LOOP
			}

		}
		try {
			save();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void save() throws IOException {
		ObjectOutputStream output = null;

		try {
			FileOutputStream fout = new FileOutputStream(fileRegister);
			output = new ObjectOutputStream(fout);
			output.writeObject(this.persons);
			output.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void load() {
		ObjectInputStream objectInput = null;
		if (fileRegister.exists()) {

			try {
				FileInputStream streamIn = new FileInputStream(fileRegister);

				objectInput = new ObjectInputStream(streamIn);
				this.persons = (List<Person>) objectInput.readObject();
				objectInput.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			System.err.println("!!!! No such file " + fileRegister + " !!!!");
		}

	}

}
