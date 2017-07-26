package edu.mum.hw2.control;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import edu.mum.hw2.domain.Actor;
import edu.mum.hw2.domain.Movie;

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

		addMovies();
		printMoviesReport();
		emf.close();
	}

	private static void printMoviesReport() {
		// TODO Auto-generated method stub

	}

	private static void addMovies() {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();

			Actor actor1 = new Actor();
			Actor actor2 = new Actor();

			actor1.setName("Hong");
			actor1.setCharacter("character1");
			actor1.setRating(10);

			actor2.setName("Ha");
			actor2.setCharacter("character2");
			actor2.setRating(10);

			Movie movie = new Movie();

			movie.setName("movie1");
			movie.setCover(displayCover("overview.png"));
			movie.setCategory("Discovery");
			movie.getComments().add("Great movie");
			movie.getComments().add("Interesting movie");
			movie.setRating(10);
			movie.getActors().add(actor1);
			movie.getActors().add(actor2);

			em.persist(movie);

			tx.commit();

		} catch (Throwable e) {
			if ((tx != null) && (tx.isActive()))
				tx.rollback();
		} finally {
			if ((em != null) && (em.isOpen()))
				em.close();
		}
	}

	private static byte[] displayCover(String filename) {
		Path p = FileSystems.getDefault().getPath(filename);
		byte[] fileData = null;
		try {
			fileData = Files.readAllBytes(p);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileData;
	}

}
