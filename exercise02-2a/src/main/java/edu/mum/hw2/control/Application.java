package edu.mum.hw2.control;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import edu.mum.hw2.domain.CD;
import edu.mum.hw2.domain.Order;
import edu.mum.hw2.domain.OrderLine;
import edu.mum.hw2.domain.Product;

public class Application {

	private static EntityManagerFactory emf;

	static {
		try {
			emf = Persistence.createEntityManagerFactory("cs544");
		} catch (Throwable ex) {
			ex.printStackTrace();
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static void main(String[] args) {

		addProducts();
		emf.close();
	}

	private static void addProducts() {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();

			Product p1 = new Product();

			p1.setName("Salon");
			p1.setDescription("Brown salon");

			Product p2 = new Product();

			p2.setName("T-shirt");
			p2.setDescription("White T-shirt");

			CD p3 = new CD();

			p3.setArtist("ABC");
			p3.setDescription("Test");
			p3.setName("Greatest Hits");

			OrderLine line1 = new OrderLine();
			line1.setProduct(p1);
			line1.setQuantity(100);

			OrderLine line2 = new OrderLine();
			line2.setProduct(p2);
			line2.setQuantity(1);

			OrderLine line3 = new OrderLine();
			line3.setProduct(p3);
			line3.setQuantity(1);

			Order order = new Order();
			order.setDate(new Date());
			order.getLines().add(line1);
			order.getLines().add(line2);
			order.getLines().add(line3);

			em.persist(order);

			tx.commit();

		} catch (Throwable e) {
			if ((tx != null) && (tx.isActive()))
				tx.rollback();
		} finally {
			if ((em != null) && (em.isOpen()))
				em.close();
		}
	}

}
