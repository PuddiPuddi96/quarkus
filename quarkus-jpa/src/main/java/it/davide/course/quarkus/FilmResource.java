package it.davide.course.quarkus;

import it.davide.course.quarkus.model.Film;
import it.davide.course.quarkus.repository.FilmRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.Optional;
import java.util.stream.Collectors;

@Path("/")
public class FilmResource {

    @Inject
    FilmRepository filmRepository;

    @GET
    @Path("/helloWorld")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(){
        return "Hello world";
    }

    @GET
    @Path("/film/{filmId}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getFilm(short filmId){
        Optional<Film> film = filmRepository.getFilm(filmId);
        return film.isPresent() ? film.get().getTitle() : "No film was found!";
    }

    @GET
    @Path("/pagedFilm/{page}/{minLength}")
    @Produces(MediaType.TEXT_PLAIN)
    public String paged(long page, short minLength){
        return filmRepository.paged(page, minLength)
                .map(film ->
                        String.format("%s (%d min)",
                                film.getTitle(),
                                film.getLength()))
                .collect(Collectors.joining("\n"));
    }

    @GET
    @Path("/actors/{startsWith}/{minLength}")
    @Produces(MediaType.TEXT_PLAIN)
    public String actors(String startsWith, short minLength){
        return filmRepository.actors(startsWith, minLength)
                .map(film ->
                        String.format("%s (%d min): %s",
                                film.getTitle(),
                                film.getLength(),
                                film.getActors().stream()
                                        .map(actor ->
                                            String.format("%s %s",
                                                actor.getFirstName(),
                                                actor.getLastName()))
                                        .collect(Collectors.joining(", "))))
                .collect(Collectors.joining("\n"));
    }


    
}
