package edu.mum.hw2.control;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import edu.mum.hw2.domain.Book;
import edu.mum.hw2.domain.CD;
import edu.mum.hw2.domain.DVD;


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

			CD p1 = new CD();

			p1.setName("Love Songs");
			p1.setDescription("Make you feel my love");
			p1.setArtist("Adele");

			DVD p2 = new DVD();

			p2.setName("Celine Dion favourite songs");
			p2.setDescription("Celine Dion favourite songs");
			p2.setGenre("Classic");

			Book p3 = new Book();

			p3.setTitle("ABC");
			p3.setName("ABC");
			p3.setDescription("Test");
			
			em.persist(p1);
			em.persist(p2);
			em.persist(p3);			

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
