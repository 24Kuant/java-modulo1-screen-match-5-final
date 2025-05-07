package com.alura.screenmatch.dto;

public record TituloOmdbDto(String title, String year, String runtime) {
    /*el record es un tipo de estructura que nos permite colocar datos de forma rapida.
      es un tipo de clase, pero mas sencilla.
       para este endpoint:  http://www.omdbapi.com/?i=tt3896198&apikey=f098110
       el resultado es:
       {"Title":"Guardians of the Galaxy Vol. 2","Year":"2017","Rated":"PG-13","Released":"05 May 2017","Runtime":"136 min","Genre":"Action, Adventure, Comedy","Director":"James Gunn","Writer":"James Gunn, Dan Abnett, Andy Lanning","Actors":"Chris Pratt, Zoe Saldaña, Dave Bautista","Plot":"The Guardians struggle to keep together as a team while dealing with their personal family issues, notably Star-Lord's encounter with his father, the ambitious celestial being Ego.","Language":"English","Country":"United States","Awards":"Nominated for 1 Oscar. 15 wins & 60 nominations total","Poster":"https://m.media-amazon.com/images/M/MV5BNWE5MGI3MDctMmU5Ni00YzI2LWEzMTQtZGIyZDA5MzQzNDBhXkEyXkFqcGc@._V1_SX300.jpg","Ratings":[{"Source":"Internet Movie Database","Value":"7.6/10"},{"Source":"Rotten Tomatoes","Value":"85%"},{"Source":"Metacritic","Value":"67/100"}],"Metascore":"67","imdbRating":"7.6","imdbVotes":"792,363","imdbID":"tt3896198","Type":"movie","DVD":"N/A","BoxOffice":"$389,813,101","Production":"N/A","Website":"N/A","Response":"True"}
       para este llamado: http://www.omdbapi.com/?t=matrix&apikey=f098110
       el rsultado es:
       {"Title":"Matrix","Year":"1993","Rated":"N/A","Released":"01 Mar 1993","Runtime":"60 min","Genre":"Action, Drama, Fantasy","Director":"N/A","Writer":"Grenville Case","Actors":"Nick Mancuso, Phillip Jarrett, Carrie-Anne Moss","Plot":"Hitman Steven Matrix is shot, experiences afterlife, gets second chance by helping others. Wakes up, meets guides assigning cases where he aids people using unorthodox methods from past profession.","Language":"English","Country":"Canada","Awards":"1 win total","Poster":"https://m.media-amazon.com/images/M/MV5BM2JiZjU1NmQtNjg1Ni00NjA3LTk2MjMtNjYxMTgxODY0NjRhXkEyXkFqcGc@._V1_SX300.jpg","Ratings":[{"Source":"Internet Movie Database","Value":"7.2/10"}],"Metascore":"N/A","imdbRating":"7.2","imdbVotes":"215","imdbID":"tt0106062","Type":"series","totalSeasons":"N/A","Response":"True"}
         -----            ----                                                 ------- los 3 son strings
       El record, internamente ya tiene los SETTERS y GETTERS y ToString que corresponden.

    OJO: nosotros declaramos en minusculas y la respuesta inicia en Mayusculas, por eso reresa nulos, PERO
    en lugar de cambiar String title, String year, String runtime  a estos : String Title, String Year, String Runtime, lo que implica que no estamos cumpliendo con las buenas practicas, mejor usaremos POLITICAS:
    Gson tiene un mecanismo que nos permite comprender automaticamente que los valores que vamos a recibir en el json empiezan con letra mayuscula y de forma automatica las convertira esa letra inicial en minusculas

    en el punto: JSON Field Naming Support, encontraremos como se ace:
    
     */
}
