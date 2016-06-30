package register;

/**
 * Created by jaro on 3.2.2014.
 */
public class Main {

	public static void main(String[] args) throws Exception {
		// Register register = new ArrayRegister(20);
//		Register register = new ListRegister(20);
//		register.load();
////		register.addPerson(new Person("Janko Hrasko", "0900123456"));
//
//		ConsoleUI ui = new ConsoleUI(register);
//
//		ui.run();
		
		
//		Person person= new Person("Blazej", "75346");
		
		Register register= new DatabaseRegister();
		ConsoleUI ui = new ConsoleUI(register);
		ui.run();
		
//		DatabaseRegister dr = new DatabaseRegister();
//		System.out.println(dr.getCount());
////		dr.addPerson(person);
//		System.out.println(dr.findPersonByName("Ander"));
//		System.out.println(dr.findPersonByPhoneNumber("123456"));
//		System.out.println(dr.getPerson(3));
	}
}
