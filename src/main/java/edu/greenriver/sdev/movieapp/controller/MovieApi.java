package edu.greenriver.sdev.movieapp.controller;

import edu.greenriver.sdev.movieapp.domain.Movie;
import edu.greenriver.sdev.movieapp.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//a web api to deliver movies...
@RestController
@RequestMapping("api/v1/movies")
@CrossOrigin(origins = "*")
public class MovieApi
{
    private MovieService service;

    public MovieApi(MovieService service)
    {
        this.service = service;
    }

    //respond to (GET) requests at localhost:3000/movies/random
    @GetMapping("random")
    public ResponseEntity<Movie> getRandom()
    {
        return new ResponseEntity<>(service.getRandomMovie(), HttpStatus.OK);
    }

    @GetMapping("all")
    public ResponseEntity<List<Movie>> all()
    {
        return new ResponseEntity<>(service.all(), HttpStatus.OK);
    }

    @GetMapping("title/{title}")
    public ResponseEntity<Movie> byTitle(@PathVariable String title)
    {
        return new ResponseEntity<>(service.byTitle(title), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Movie> byId(@PathVariable int id)
    {
        Movie movie = service.byId(id);
        if (movie == null)
        {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        else
        {
            return new ResponseEntity(movie, HttpStatus.OK);
        }
    }

    @GetMapping("year/{year}")
    public ResponseEntity<List<Movie>> byYear(@PathVariable int year)
    {
        return new ResponseEntity(service.byYear(year), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity addMovie(@RequestBody Movie newMovie)
    {
        return new ResponseEntity(service.addMovie(newMovie), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable int id,
                            @RequestBody Movie updatedMovie)
    {
        Movie movie = service.byId(id);
        if (movie == null)
        {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        else
        {
            return new ResponseEntity(service.updateMovie(updatedMovie, id),
                    HttpStatus.OK);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteMovie(@PathVariable int id)
    {
        Movie movie = service.byId(id);
        if (movie == null)
        {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        else
        {
            service.deleteMovie(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }
}
