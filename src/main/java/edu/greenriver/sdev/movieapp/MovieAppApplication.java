package edu.greenriver.sdev.movieapp;

import edu.greenriver.sdev.movieapp.db.MovieRepository;
import edu.greenriver.sdev.movieapp.domain.Movie;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class MovieAppApplication
{
    public static void main(String[] args)
    {
        ApplicationContext context = SpringApplication.run(MovieAppApplication.class, args);
        MovieRepository dbRepo = context.getBean(MovieRepository.class);


    }

}
