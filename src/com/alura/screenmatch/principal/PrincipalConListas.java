package com.alura.screenmatch.principal;

import com.alura.screenmatch.modelos.Pelicula;
import com.alura.screenmatch.modelos.Serie;
import com.alura.screenmatch.modelos.Titulo;

import java.util.*;

public class PrincipalConListas {
    public static void main(String[] args) {
        Pelicula miPelicula = new Pelicula("Encanto", 2021);
        miPelicula.evalua(9);
        miPelicula.setDuracionEnMinutos(120);
        Pelicula otraPelicula = new Pelicula("Avatar", 2023);
        otraPelicula.evalua(6);
        otraPelicula.setDuracionEnMinutos(175);
        var peliculaDeBruno = new Pelicula("El señor de los anillos", 2001);
        peliculaDeBruno.evalua(10);
        peliculaDeBruno.setDuracionEnMinutos(210);

        Serie lost = new Serie("Lost", 2000);
        //para sacar la duracion en minutos de una serie, se necesitan los siguientes 3 parametros, con esto internamente calcula la duracion en Minutos
            lost.setTemporadas(3);
            lost.setEpisodiosPorTemporada(10);
            lost.setMinutosPorEpisodio(55);

        ArrayList<Titulo> lista = new ArrayList<>();  //para que pueda usar Peliculas y Series tiene que ser una lista de titulos.
        lista.add(miPelicula);
        lista.add(otraPelicula);
        lista.add(peliculaDeBruno);
        lista.add(lost);

        for(Titulo item: lista) {  //for-each
            System.out.println("item=" + item);

            if (item instanceof Pelicula) {
                //estas 2 siguientes lineas suponen que todo lo que trae lista son Peliculas, pero no es cierto pq tambien trae Series.
                Pelicula pelicula = (Pelicula) item;
                System.out.println("clasificación: " + pelicula.getClasificacion());
            }

            //y desde Java 14, se cmbio a:
            if (item instanceof Pelicula pelicula) {
                //estas 2 siguientes lineas suponen que todo lo que trae lista son Peliculas, pero no es cierto pq tambien trae Series.
                //--y se puede comentar este cast: Pelicula pelicula = (Pelicula) item;
                System.out.println("clasificación: " + pelicula.getClasificacion());
            }

            //y desde Java 17, se cmbio a:
            if (item instanceof Pelicula pelicula && pelicula.getClasificacion() > 3) {
                //estas 2 siguientes lineas suponen que todo lo que trae lista son Peliculas, pero no es cierto pq tambien trae Series.
                //--y se puede comentar este cast: Pelicula pelicula = (Pelicula) item;
                System.out.println("clasificación: " + pelicula.getClasificacion());
            }
        }


        //Manejo de listas ordenadas:

        ArrayList<String> listaDeArtistas = new ArrayList<>();
        listaDeArtistas.add("Penélope Cruz");
        listaDeArtistas.add("Antonio Banderas");
        listaDeArtistas.add("Ricardo Darín");
        System.out.println("Lista de artistas NO Ordenada: " + listaDeArtistas);

        Collections.sort(listaDeArtistas);
        System.out.println("Lista de artistas Ordenada: " + listaDeArtistas);


        //tratando de ordenar la lista de titulos por nombre
        System.out.println("Lista de titulos NO Ordenada: " + lista);

        Collections.sort(lista);
        System.out.println("Lista de titulos Ordenada: " + lista);

        //no solo comparar por nombre, tambien quiero tener la opcion de ordenarlo por fecha de lanzamiento
        lista.sort(Comparator.comparing(Titulo::getFechaDeLanzamiento));
        System.out.println("Lista Ordenada por Fecha de Lanzamiento: " + lista);

        //lista ordenada por duracion en minutos usando comparator
        lista.sort(Comparator.comparing(Titulo::getDuracionEnMinutos));
        System.out.println("Lista Ordenada por Duración en Minutos: " + lista);

        //lista ordenada por nombre usando comparator
        lista.sort(Comparator.comparing(Titulo::getNombre));
        System.out.println("Lista Ordenada por Nombre - usando Comparator: " + lista);

        //uSO DE List
        System.out.println("=====================  Usando List - ArrayList ===========================");
        List<String> listaDeArtistas_02 = new ArrayList<>();
        listaDeArtistas_02.add("Penélope Cruz");
        listaDeArtistas_02.add("Antonio Banderas");
        listaDeArtistas_02.add("Ricardo Darín");
        System.out.println("Lista de artistas NO Ordenada: " + listaDeArtistas_02);

        Collections.sort(listaDeArtistas_02);
        System.out.println("Lista de artistas Ordenada: " + listaDeArtistas_02);

        System.out.println("=====================  Usando List - LinkedList ===========================");
        List<String> listaDeArtistas_03 = new LinkedList<>();
        listaDeArtistas_03.add("Penélope Cruz");
        listaDeArtistas_03.add("Antonio Banderas");
        listaDeArtistas_03.add("Ricardo Darín");
        System.out.println("Lista de artistas NO Ordenada: " + listaDeArtistas_03);

        Collections.sort(listaDeArtistas_02);
        System.out.println("Lista de artistas Ordenada: " + listaDeArtistas_03);

        //Collection -> es la interfaz Padre de List, en collection NO tenemos un conjunto Ordenado, solo estan los items
        //Set -> es una interfaz, que es un conjunto de datos que no se pueden repetir los datos. ejemplo: un set de nmeros enteros, no se pueden repetir un numero entero
        //Map -> esta interfaz, es un conjunto de llave-valor, se busca una llave en el hashMap y se puede ver la descripcion de esa llave o el valor de la llave.

    }
}
