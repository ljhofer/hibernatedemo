package perscholas.database.dao;

import perscholas.database.entity.Actor;
import perscholas.database.entity.Movie;
import perscholas.database.entity.MovieActor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class MovieActorDAO {

    private final String PERSISTENCE_UNIT_NAME = "moviesdb";

    private EntityManagerFactory emFactoryObj = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

    public MovieActor save(MovieActor movieActor) {
        EntityManager em = emFactoryObj.createEntityManager();
        em.getTransaction().begin();;

        em.persist(movieActor);

        em.getTransaction().commit();
        em.clear();

        return movieActor;
    }

    public MovieActor findById(Integer id) {
        EntityManager em = emFactoryObj.createEntityManager();

        String sql = "SELECT ma FROM MovieActor ma WHERE ma.id = :movieActorId";
        TypedQuery<MovieActor> query = em.createQuery(sql, MovieActor.class);
        query.setParameter("movieActorId", id);

        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public List<MovieActor> findMovieById(Integer id) {
        EntityManager em = emFactoryObj.createEntityManager();

        String sql = "SELECT ma FROM MovieActor ma WHERE movie_id = :movieId";
        TypedQuery<MovieActor> query = em.createQuery(sql, MovieActor.class);
        query.setParameter("movieId", id);

        List<MovieActor> result = query.getResultList();

        return result;

    }
}
