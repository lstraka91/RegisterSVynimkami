package register;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import register.exception.BadIndexException;
import register.exception.DuplicationException;

public class ListRegister implements Register {

	private List<Person> persons;

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
		if(persons.contains(person))
			throw new DuplicationException("User already exists (with same name or phone number)");
		
		persons.add(person);
		sortRegisterByName();
	}

	@Override
	public Person findPersonByName(String name) {
		name = name.toLowerCase();
		
		//--------traverse using Iterator-------
		Iterator<Person> i = persons.iterator(); //ziskam objekt iteratora
		while(i.hasNext()) { //zisti ci existuje nasledujuca polozka
			Person p = i.next(); //vrati nasledujucu polozku
			if (p.getName().toLowerCase().contains(name)) {
				
				return p;		
			}
			//removal example
			//i.remove(); //safe element removal in a LOOP
		}
		
//		//--------traverse using the "enhanced for" loop--------
//		for(Person p : persons) {
//			if (p.getName().contains(name)) {
//				return p;
//			}
//			//removal example
//			persons.remove(p); //unsafe if used in a LOOP, throws ConcurrentModificationException
//		}
		
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
	}

	@Override
	public void sortRegisterByName() {
		Collections.sort(persons);
	}
	
	public void removeAllBy(char firstLetter){
		
//		for (Person person: persons){
//			
//			if(person.getName().toLowerCase().charAt(0) == firstLetter){
//				System.out.println(person.getName());
//				persons.remove(person);
//			}
//			
//		}
		Iterator<Person> i = persons.iterator(); //ziskam objekt iteratora
		while(i.hasNext()) { //zisti ci existuje nasledujuca polozka
			Person p = i.next(); //vrati nasledujucu polozku
			if (p.getName().toLowerCase().charAt(0) ==firstLetter) {
				
				//removal example
				i.remove(); //safe element removal in a LOOP
			}
			
		}
	}

}
