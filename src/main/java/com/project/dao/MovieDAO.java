package com.project.dao;



import  com.project.config.HibernateUtil;
import com.project.entity.Movie;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class MovieDAO {
    public void addMovie(Movie movie) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(movie);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public List<Movie> getAllMovies() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Movie", Movie.class).list();
        }
    }

    public Movie getMovieById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Movie.class, id);
        }
    }

    public void updateMovie(Movie movie) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(movie);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public void deleteMovie(int id) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Movie movie = session.get(Movie.class, id);
            if (movie != null) {
                session.remove(movie);
                tx.commit();
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }
}

