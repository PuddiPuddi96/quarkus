package it.davide.course.quarkus.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "film", schema = "sakila")
public class Film {

    public Film(short filmId, String title, short length) {
        this.filmId = filmId;
        this.title = title;
        this.length = length;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "film_id")
    private short filmId;

    @Basic
    @Column(name = "title")
    private String title;

    @Basic
    @Column(name = "description")
    private String description;

    @Basic
    @Column(name = "language_id")
    private short languageId;

    @Basic
    @Column(name = "original_language_id")
    private Short originalLanguageId;

    @Basic
    @Column(name = "rental_duration")
    private short rentalDuration;

    @Basic
    @Column(name = "rental_rate", columnDefinition = "decimal(4,2)")
    private Float rentalRate;

    @Basic
    @Column(name = "length")
    private Short length;

    @Basic
    @Column(name = "replacement_cost")
    private BigDecimal replacementCost;

    @Basic
    @Column(name = "rating", columnDefinition = "enum('G', 'PG', 'PG-13', 'R', 'NC-17')")
    private String rating;

    @Basic
    @Column(name = "special_features", columnDefinition = "set('Trailers', 'Commentaries', 'Deleted Scenes', 'Behind the Scenes')")
    private String specialFeatures;

    @Basic
    @Column(name = "last_update")
    private Timestamp lastUpdate;

    @Setter
    @Getter
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "film_actor",
            joinColumns = { @JoinColumn(name = "film_id") },
            inverseJoinColumns = { @JoinColumn(name = "actor_id") }
    )
    private List<Actor> actors = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return filmId == film.filmId && languageId == film.languageId && rentalDuration == film.rentalDuration && Objects.equals(title, film.title) && Objects.equals(description, film.description) && Objects.equals(originalLanguageId, film.originalLanguageId) && Objects.equals(rentalRate, film.rentalRate) && Objects.equals(length, film.length) && Objects.equals(replacementCost, film.replacementCost) && Objects.equals(rating, film.rating) && Objects.equals(specialFeatures, film.specialFeatures) && Objects.equals(lastUpdate, film.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filmId, title, description, languageId, originalLanguageId, rentalDuration, rentalRate, length, replacementCost, rating, specialFeatures, lastUpdate);
    }

}
