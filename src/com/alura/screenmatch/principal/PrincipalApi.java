package com.alura.screenmatch.principal;

import com.alura.screenmatch.dto.TituloOmdbDto;
import com.alura.screenmatch.modelos.Titulo;
import com.alura.screenmatch.myexceptions.ErrorDuracionenMinutosException;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrincipalApi {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner teclado = new Scanner(System.in);
        List<Titulo> titulos = new ArrayList<>();
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)  //los campos inician com letra en Mayusculas
                .setPrettyPrinting()  //hace que el formato de json se vea ordenado y mas legible
                .create();

        while (true) {
            System.out.print("Teclea el nombre de una pelicula: ");
            String busqueda = teclado.nextLine();
            String uri = "http://www.omdbapi.com/?apikey=f098110&t=" + busqueda.replace(" ", "+") + "&plot=full";
            //se puede usar la clase URLEncoder, para el tratamiento de URL de mejor forma, en lugar de solo reemplazar el espacio en blanco

            if (busqueda.equalsIgnoreCase("salir")) {
                break;
            }
            try {
                //nuestro cliente
                HttpClient client = HttpClient.newHttpClient();
                //HttpRequest NO puede ser instanciado de forma directo pq es un etodo Abstracto, por eso e usa el PATRON builder
                //lo que vamos a pedir
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(uri))
                        .build();
                //lo que vamos a recibir
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                String json = response.body();
                System.out.println(json);

                //Convirtiendo de Json a Objetos : Serializacion: se puede usar: Jackson o Gson. en nuestro caso usaremos Gson.
                /*
                    de aqui se obtiene el jar de Gson:
                        https://mvnrepository.com/artifact/com.google.code.gson/gson
                        se baja el jar para agregarlo en el IntelliJ y que reconozca la dependencia de Gson
                        Menu --> Poject Structure --> Dependences  sino muestra el directorio de las dependencias, cerrar y abrir nuevamente IntelliJ.
                 */

                Gson gsonInicial = new Gson();
                Titulo mititulo = gsonInicial.fromJson(json, Titulo.class);
                System.out.println(mititulo);  //System.out.println("Titulo : " + mititulo.getNombre());

                //no regresa nada pq en el json de la API tiene title y year, mientras que en nuestra clase titulo tenemos nombre y fechaDeLanzamiento
                //por lo que necesitamos hacer un tipo de conversion para asociar title con nombre y year con fechaDeLanzamiento
                //esto se logra con anotaciones en la clase Titulo, mediante la anotacion: SerializedName

                /* que asaria si en lugar de usar la API de OMDb Movies, usamos IMDb movies o moviesAPI, es evidente que en cada API
                    los nombres que maneja en sus estructuras de json no seran los mismo, en uno puede ser title, en otro puede ser name, en otro nameMovie, etc.
                    esto quiere decir que para poder usar varias APIs y no depender de una sola, debemos usar algo para poder conectarnos a cualquier API y no depender de los nombres de sus elementos.
                    se hace mediante DTOs o Data Transfer Objects.

                    El DTO sera el responsalbe de entender el Json de una o de varias APIS y hacer la transformacion hacia lo que nosotros necesitamos
                    el DTO es una clase intermedia, que interpreta los resultados de cualquier API y de ahi ya podemos trasformar los datos a nuestra clase final, en este caso la clase Titulo.

                * */

                TituloOmdbDto mitituloOmdb = gsonInicial.fromJson(json, TituloOmdbDto.class);
                System.out.println(mitituloOmdb);

                /* se mueve la declaracion, para convertir la lista de peliculas en json
                Gson gson = new GsonBuilder()
                        .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)  //los campos inician com letra en Mayusculas
                        .create();
                */

                TituloOmdbDto miTituloOmdb = gson.fromJson(json, TituloOmdbDto.class);
                System.out.println(miTituloOmdb);

                //ya que logramos llevar la info del json del request al dto, ahora como le hacemos para mandar la info del DTO especifico a nuestra clase Titulo??

                //probar con las peliculas: Matrix, Bichos, Top gun
                //en top gun , debe ser tratado o convertido (incouding), en este caso el espacio, o caracteres especiales en otros caracteres que una url pueda interpretar de forma correcta.

                Titulo elTitulo = new Titulo(miTituloOmdb);  //creamos el constructor correspondiente
                System.out.println("Titulo ya convertido: " + elTitulo);

                /* al hacr la lista de peliculas que se van a uardar, este codio se mueve al final de capturar todas las peliculsa o series.
                //solo guarda una pelicula
                FileWriter file = new FileWriter("peliculas.txt");
                file.write(elTitulo.toString());
                file.close();
                */

                titulos.add(elTitulo);  //se agrega el titulo capturado.

            } catch (NumberFormatException e) {
                System.out.println("Ocurrió un error!");
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Ocurrió un error en la URI, verifique por favor!");
                System.out.println(e.getMessage());
            } catch (ErrorDuracionenMinutosException e) {
                System.out.println("Error personalizado!");
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Ocurrió un error inesperado!");
                System.out.println(e.getMessage());
            }
        }

        System.out.println(titulos);  //capturar Matrix, encanto, top gun, bichos

        FileWriter file = new FileWriter("titulos.json");
        file.write(gson.toJson(titulos));
        file.close();

        System.out.println("Finalizó la ejecución del programa");

        /*
        pelicula matriz:  {"Title":"Matrix","Year":"1993","Rated":"N/A","Released":"01 Mar 1993","Runtime":"60 min","Genre":"Action, Drama, Fantasy","Director":"N/A","Writer":"Grenville Case","Actors":"Nick Mancuso, Phillip Jarrett, Carrie-Anne Moss","Plot":"Steven Matrix is one of the underworld's foremost hitmen until his luck runs out, and someone puts a contract out on him. Shot in the forehead by a .22 pistol, Matrix \"dies\" and finds himself in \"The City In Between\", where he is shown the faces of all the men and women he's murdered and a sea of fire. He's informed that he will be given a second chance. He must earn a reprieve from Hell by helping others. He then wakes up in the hospital, after an apparent \"near death\" experience. In each episode, Matrix meets a new \"guide\" from the world beyond, and is given a new assignment, much in the manner of an unwilling guardian angel. Usually his guides give him little or no useful information about the job to come, and his methods of handling the cases are sometimes as brutal as the rules of his old profession, but he gets the job done.","Language":"English","Country":"Canada","Awards":"1 win total","Poster":"https://m.media-amazon.com/images/M/MV5BM2JiZjU1NmQtNjg1Ni00NjA3LTk2MjMtNjYxMTgxODY0NjRhXkEyXkFqcGc@._V1_SX300.jpg","Ratings":[{"Source":"Internet Movie Database","Value":"7.2/10"}],"Metascore":"N/A","imdbRating":"7.2","imdbVotes":"215","imdbID":"tt0106062","Type":"series","totalSeasons":"N/A","Response":"True"}
        pelicula bichos:  {"Title":"Bichos","Year":"2023","Rated":"N/A","Released":"08 Mar 2023","Runtime":"N/A","Genre":"Short, Family","Director":"Ares Sirvent","Writer":"Ares Sirvent","Actors":"Norberto Arribas, Alejandro Martinez, Antonio Reyes","Plot":"N/A","Language":"Spanish","Country":"Spain","Awards":"N/A","Poster":"https://m.media-amazon.com/images/M/MV5BNmU3NWM4MTItM2U4Mi00YmE5LWE2MmYtNGE4NDRiOWFiNjM2XkEyXkFqcGc@._V1_SX300.jpg","Ratings":[],"Metascore":"N/A","imdbRating":"N/A","imdbVotes":"N/A","imdbID":"tt22191036","Type":"movie","DVD":"N/A","BoxOffice":"N/A","Production":"N/A","Website":"N/A","Response":"True"}
        aqui runtime = N/A
         */

    }
}
