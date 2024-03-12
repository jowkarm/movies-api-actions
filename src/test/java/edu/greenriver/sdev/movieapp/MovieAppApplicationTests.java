package edu.greenriver.sdev.movieapp;

import edu.greenriver.sdev.movieapp.domain.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MovieAppApplicationTests
{
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate rest;

    @Test
    public void contextLoads()
    {
    }


    @Test
    public void getAllMoviesTest(){
        String url = "http://localhost:" + port + "/api/v1/movies/all";

        HttpEntity request = new HttpEntity(new HttpHeaders());
        ResponseEntity<Movie[]> response = rest.exchange(url, HttpMethod.GET,
                request, Movie[].class);

        Movie[] movies = response.getBody();

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(movies);
        assertTrue(movies.length > 0);
    }

}
