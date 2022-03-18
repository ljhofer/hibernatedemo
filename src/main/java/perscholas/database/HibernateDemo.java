package perscholas.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import perscholas.database.dao.ActorDAO;
import perscholas.database.dao.MovieActorDAO;
import perscholas.database.dao.MovieDAO;
import perscholas.database.entity.Actor;
import perscholas.database.entity.Movie;
import perscholas.database.entity.MovieActor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class HibernateDemo {

    private ActorDAO actorDAO= new ActorDAO();
    private MovieDAO movieDAO = new MovieDAO();
    private MovieActorDAO movieActorDAO = new MovieActorDAO();

    public void work() {

//
//        getActorById();
        getActorByFirstName();
//        create();
//        deleteWithEntityManager();
//        deleteWithQuery();
//        updateMovie();
//        updateActor();
//        findByFirstNameAndLastName();
//        updateMovie();
//        addMovietoActor();
//        addActorToMovie();
//       queryOneToMany();
//        getActorsByMovie();

    }

    private void getActorById() {
        Actor actor = actorDAO.findById(1);
        System.out.println(actor.toString());
    }

    private void getActorByFirstName() {
        List<Actor> actors = actorDAO.findByFirstName("Tina");
        for ( Actor a : actors ) {
            System.out.println("Find by firstName : " + a);
        }
    }

    private void getActorsByMovie() {
        List<MovieActor> movieActor = movieActorDAO.findMovieById(1);

        for ( MovieActor ma : movieActor ) {
            String characterName = ma.getCharacterName();
            System.out.printf("%s played by %s %s\n", characterName, ma.getActor().getFirstName(), ma.getActor().getLastName());
        }

    }

    private void queryOneToMany() {
        MovieActor movieActor = movieActorDAO.findById(1);

        System.out.println(movieActor);
        System.out.println("Actor first name = " + movieActor.getActor().getFirstName());

    }

    private void addActorToMovie(){
        Movie movie = movieDAO.findById(1);
        Actor actor = actorDAO.findById(12);

        MovieActor movieActor = new MovieActor();
        movieActor.setActor(actor);
        movieActor.setMovie(movie);
        movieActor.setCharacterName("Naomi");

        movieActorDAO.save(movieActor);
    }

//    private void updateMovie() {
//        Movie movie = movieDAO.findById(1);
//        System.out.println("Before update :" + movie);
//
//        movie.setTitle("This is");
//        movie.setDescription("An Update");
//        movie.setReleaseDate(1990/09/24);
//
//        actorDAO.update(movie);
//        System.out.println("After update : " + movie);
//    }

    private void create() {
        Actor actor = new Actor();

        actor.setFirstName("FirstName");
        actor.setLastName("LastName");
        actor.setAge(100);

        System.out.println("Before save : " + actor);

        actorDAO.save(actor);

        System.out.println("After save : " + actor);


    }

    private void updateActor() {
        Actor actor = actorDAO.findById(1);
        System.out.println("Before update :" + actor);

        actor.setFirstName("First");
        actor.setLastName("Name");
        actor.setAge(1);

        actorDAO.update(actor);
        System.out.println("After update : " + actor);
    }

    private void deleteWithEntityManager() {
        Actor actor = actorDAO.findById(2);
        actorDAO.delete(actor);

    }

    public void deleteWithQuery() {
        Actor actor = actorDAO.findById(5);
        actorDAO.deleteById(5);
    }

    public void findByFirstNameAndLastName() {
        List<Actor> actors = actorDAO.findByFirstNameAndLastName("Harrison", "Ford");
        for ( Actor a : actors ) {
            System.out.println("Find by firstName and lastName : " + a);

        }

    }



    public static void main ( String[] args) {

       new HibernateDemo().work();
    }
}
