//wait until the page loads
window.onload = async function (){
    //fetch the data
    const uri = "http://localhost:3000/api/v1/movies/all";
    const config = {
        method: 'get'
    }

    const response = await fetch(uri, config);
    console.log(response);

    const data = await response.json();
    showMovies(data);

    //select the form button and handle the submission
    const button = document.querySelector("button");
    button.onclick = addMovie();


   /* fetch(uri, config)
        .then(function (response) {
            console.log(response);

            //returns another promise
            return response.json();
        })
        .then(function (data){
            console.log(data);

            showMovies(data);
        });*/
}

async function addMovie(event){
    //stop the form from submitting
    event.preventDefault();

    const newMovie = {
        title: document.querySelector("#title").value,
        genre: document.querySelector("#genre").value
    }

    const uri = "http://localhost:3000/api/v1/movies";
    const config = {
        method: 'post',
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(newMovie)
    }


    const response = await fetch(uri, config);
    const movie = await response.json();

    const section = document.querySelector("#movies");
    addMovieSection(section, movie);



}

function showMovies(movies){
    const section = document.querySelector("#movies");

    //section.innerHTML = "<p>Hello, it worked</p>";

    //loop over movies and display them
    for (let i = 0; i < movies.length; i++) {
        const movie = movies[i];

        addMovieSection(section, movie);
    }
}

function addMovieSection(section, movie){
    section.innerHTML += `<div class="movie">
                                <h2>${movie.title}</h2>
                                <p>ID #${movie.id}</p>
                                <p>Genre: ${movie.genre}</p>
                            </div>`;
}