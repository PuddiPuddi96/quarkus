import io.quarkus.test.junit.QuarkusTest;
import it.davide.course.quarkus.model.Film;
import it.davide.course.quarkus.repository.FilmRepository;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
class FilmRepositoryTest {

    @Inject
    FilmRepository filmRepository;

    @Test
    void test() {
        Optional<Film> film = filmRepository.getFilm((short) 5);
        assertTrue(film.isPresent());
        assertEquals("AFRICAN EGG", film.get().getTitle());
    }
}
