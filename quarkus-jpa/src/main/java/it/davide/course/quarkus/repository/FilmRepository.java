package it.davide.course.quarkus.repository;

import com.speedment.jpastreamer.application.JPAStreamer;
import com.speedment.jpastreamer.projection.Projection;
import com.speedment.jpastreamer.streamconfiguration.StreamConfiguration;
import it.davide.course.quarkus.model.Film;
import it.davide.course.quarkus.model.Film$;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.Optional;
import java.util.stream.Stream;

@ApplicationScoped
public class FilmRepository {

    private final JPAStreamer jpaStreamer;

    @Inject
    public FilmRepository(JPAStreamer jpaStreamer) {
        this.jpaStreamer = jpaStreamer;
    }

    private static final int PAGE_SIZE = 20;

    public Optional<Film> getFilm(short filmId) {
        return jpaStreamer.stream(Film.class)
                .filter(Film$.filmId.equal(filmId))
                .findFirst();
    }

    public Stream<Film> getFilms(short minLength) {
        return jpaStreamer.stream(Film.class)
                .filter(Film$.length.greaterThan(minLength))
                .sorted(Film$.length);
    }

    public Stream<Film> paged(long page, short minLength) {
        //We need a constructor with tihis three fields
        return jpaStreamer.stream(Projection.select(Film$.filmId, Film$.title, Film$.length))
                .filter(Film$.length.greaterThan(minLength))
                .sorted(Film$.length)
                .skip(page * PAGE_SIZE)
                .limit(PAGE_SIZE);
    }

    public Stream<Film> actors(String startsWith, short minLength) {
        final StreamConfiguration<Film> sc =
                StreamConfiguration.of(Film.class).joining(Film$.actors);
        return jpaStreamer.stream(sc)
                .filter(Film$.title.startsWith(startsWith).and(Film$.length.greaterThan(minLength)))
                .sorted(Film$.length.reversed());
    }

    @Transactional
    public void updateRentalRate(short minLength, Float rentalRate) {
        jpaStreamer.stream(Film.class)
                .filter(Film$.length.greaterThan(minLength))
                .forEach(film -> film.setRentalRate(rentalRate));
    }

}
