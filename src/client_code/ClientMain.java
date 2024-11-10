package client_code;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import entity.Address;
import entity.Employee;

public class ClientMain {
	public static void main(String[] args) {
		Configuration cfg = new Configuration();
		cfg.configure().addAnnotatedClass(Employee.class).addAnnotatedClass(Address.class);

		SessionFactory sessionFactory = cfg.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		try {

			// Create Employee Object
			Employee emp1 = new Employee();
			emp1.setEmpName("RohitSingh");

			// Create Address Objects

			Address add1 = new Address("XYZ", "XYZ", "XYZ", "123");
			Address add2 = new Address("ABC", "ABC", "ABC", "456");
			Address add3 = new Address("PQR", "PQR", "PQR", "789");
			Address add4 = new Address("MNP", "MNP", "MNP", "0001");

			// Create HashSet Object
			Set<Address> addresses = new HashSet<>();
			addresses.add(add1);
			addresses.add(add2);
			addresses.add(add3);
			addresses.add(add4);

			// Set employee for each address
			for (Address address : addresses) {
				address.setEmployee(emp1);
			}

			// set addresses for employee
			emp1.setAddress(addresses);

			// save Employee, address will be automatically saved
			session.save(emp1);

			transaction.commit();

			session.close();

		} catch (Exception e) {
			e.printStackTrace();
			session.close();
		}
	}
}
