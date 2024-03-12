package edu.greenriver.sdev.movieapp.service;

import edu.greenriver.sdev.movieapp.db.MovieRepository;
import edu.greenriver.sdev.movieapp.domain.Movie;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/*
    This class has business logic and is marked
    as a stereotypical service layer.
 */
@Service
public class MovieService
{
    private MovieRepository repository;

    public MovieService(MovieRepository repository)
    {
        this.repository = repository;

        List<Movie> movies = new ArrayList<>(List.of(
                new Movie(0, "Inception", 2010, "Science Fiction", "PG-13", false),
                new Movie(0, "The Shawshank Redemption", 1994, "Drama", "R", false),
                new Movie(0, "The Dark Knight", 2008, "Action", "PG-13", false),
                new Movie(0, "Pulp Fiction", 1994, "Crime", "R", false),
                new Movie(0, "Titanic", 1997, "Drama", "PG-13", false),
                new Movie(0, "The Godfather", 1972, "Crime", "R", false),
                new Movie(0, "Avatar", 2009, "Action", "PG-13", true),
                new Movie(0, "The Lord of the Rings: The Return of the King", 2003, "Fantasy", "PG-13", false),
                new Movie(0, "Jurassic Park", 1993, "Science Fiction", "PG-13", false),
                new Movie(0, "Forrest Gump", 1994, "Drama", "PG-13", false)
        ));

        this.repository.saveAll(movies);
    }

    public Movie getRandomMovie()
    {
        Random generator = new Random();
        List<Movie> movies = repository.findAll();
        int index = generator.nextInt(movies.size());
        return movies.get(index);
    }

    public List<Movie> all()
    {
        //convert to read-only list
        List<Movie> movies = repository.findAll();
        return Collections.unmodifiableList(movies);
    }

    public Movie byTitle(String title)
    {
        List<Movie> results = new ArrayList<>();
        List<Movie> movies = repository.findAll();

        for (int i = 0; i < movies.size(); i++)
        {
            Movie next = movies.get(i);
            if (next.getTitle().equals(title))
            {
                return next;
            }
        }

        return null;
    }

    public List<Movie> byYear(int year)
    {
        List<Movie> results = new ArrayList<>();
        List<Movie> movies = repository.findAll();

        for (int i = 0; i < movies.size(); i++)
        {
            Movie next = movies.get(i);
            if (next.getReleaseYear() == year)
            {
                results.add(next);
            }
        }

        return results;
    }

    public Movie addMovie(Movie movie)
    {
        //this will result in an INSERT SQL statement
        return repository.save(movie);
    }

    public Movie updateMovie(Movie updatedMovie, int id)
    {
        Movie currentMovie = repository.findById(id).orElseThrow();

        currentMovie.setRating(updatedMovie.getRating());
        currentMovie.setInternational(updatedMovie.isInternational());
        currentMovie.setGenre(updatedMovie.getGenre());
        currentMovie.setReleaseYear(updatedMovie.getReleaseYear());
        currentMovie.setTitle(updatedMovie.getTitle());

        //this is add or update
        return repository.save(currentMovie);
    }

    public void deleteMovie(int id)
    {
        repository.deleteById(id);
    }

    public Movie byId(int id)
    {
        return repository.findById(id).orElse(null);
    }
}
