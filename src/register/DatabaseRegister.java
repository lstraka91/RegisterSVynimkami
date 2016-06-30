package register;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import register.exception.BadIndexException;
import register.exception.DuplicationException;
import register.exception.ValidationException;
import register.exception.WrongFormatException;

public class DatabaseRegister implements Register {
	// public static final String DRIVER_CLASS =
	// "org.apache.derby.jdbc.ClientDriver";
	public static final String URL = "jdbc:oracle:thin:@//localhost:1521/XE";
	public static final String USER = "strakal";
	public static final String PASSWORD = "p4ssw0rd";
	public static final String CREATETABLE = "CREATE TABLE register (id INT PRIMARY KEY, name VARCHAR(32) NOT NULL, phoneNumber INTEGER NOT NULL)";
	public static final String INSERT = "INSERT INTO register (id, name, phoneNumber) VALUES (idseq.nextVal, ?, ?)";
	Connection connected;
	Statement stmt;

	public DatabaseRegister() throws Exception {
		// Class.forName(DRIVER_CLASS);
		this.connected = DriverManager.getConnection(URL, USER, PASSWORD);
	}

	// public static void main(String[] args) throws Exception {
	// Class.forName(DRIVER_CLASS);
	// Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
	// Statement stmt = con.createStatement();
	// stmt.executeUpdate(CREATETABLE);
	// stmt.close();
	// con.close();
	// }

	@Override
	public int getCount() {
		int count = -1;
		try {
			stmt = connected.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT count(*)  as count FROM register");
			while (rs.next()) {
				count = rs.getInt(1);
			}

			rs.close();
			stmt.close();
			// connected.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return Integer.MAX_VALUE;
	}

	private int getIndex() {

		int index = -1;

		try {
			stmt = connected.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT max(id) FROM register");
			rs.next();
			index = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return index;
	}

	private ArrayList<Person> getAllPersons() {
		ArrayList<Person> persons = new ArrayList<>();
		Person person;
		try {
			stmt = connected.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT name, phonenumber FROM register");
			while (rs.next()) {
				rs.getString(1);
				rs.getString(2);
				person = new Person(rs.getString(1), rs.getString(2));
				persons.add(person);
			}
			rs.close();
			stmt.close();

		} catch (SQLException | ValidationException | WrongFormatException e) {

			e.printStackTrace();
		}
		return persons;

	}

	@Override
	public Person getPerson(int index) throws BadIndexException {
		ArrayList<Person> persons = getAllPersons();
		return persons.get(index);
	}

	@Override
	public void addPerson(Person person) throws DuplicationException {
		PreparedStatement stmt;
		try {
			stmt = connected.prepareStatement(INSERT);
			// stmt.setInt(1, getIndex()+1);
			stmt.setString(1, person.getName());
			stmt.setString(2, person.getPhoneNumber());
			stmt.executeUpdate();
			// stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public Person findPersonByName(String name) {

		Person person = null;
		try {
			stmt = connected.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT name, phonenumber FROM register where name like '"
							+ name + "'");
			while (rs.next()) {
				rs.getString(1);
				rs.getInt(2);
				person = new Person(rs.getString(1), rs.getString(2));
			}

			rs.close();
			stmt.close();
			// connected.close();
		} catch (SQLException | ValidationException | WrongFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return person;
	}

	@Override
	public Person findPersonByPhoneNumber(String phoneNumber) {
		Person person = null;
		try {
			stmt = connected.createStatement();
			ResultSet rs = stmt
					.executeQuery("SELECT name, phonenumber FROM register where phonenumber ="
							+ phoneNumber);
			while (rs.next()) {
				rs.getString(1);
				rs.getInt(2);
				person = new Person(rs.getString(1), rs.getString(2));
			}

			rs.close();
			stmt.close();
			// connected.close();
		} catch (SQLException | ValidationException | WrongFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return person;
	}

	public static void updatePerson(Person personToUpdate, Person personUpdt) {
		Connection con;
		String update = "UPDATE register " + "SET name = '"
				+ personUpdt.getName() + "', phoneNumber= '"
				+ personUpdt.getPhoneNumber() + "'  WHERE phonenumber="
				+ personToUpdate.getPhoneNumber();
		try {
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			Statement stmt = con.createStatement();
			stmt.executeQuery(update);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void removePerson(Person person) {

		String delete = "DELETE from register WHERE phonenumber="
				+ person.getPhoneNumber();
		try {

			Statement stmt = connected.createStatement();
			stmt.executeQuery(delete);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void removeAllBy(char firstLetter) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sortRegisterByName() {
		// TODO Auto-generated method stub

	}

	@Override
	public void save() throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void load() {
		// TODO Auto-generated method stub

	}
}
