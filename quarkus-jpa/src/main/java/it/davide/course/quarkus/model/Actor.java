package it.davide.course.quarkus.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "actor", schema = "sakila")
public class Actor {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "actor_id")
    private short actorId;

    @Basic
    @Column(name = "first_name")
    private String firstName;

    @Basic
    @Column(name = "last_name")
    private String lastName;

    @Basic
    @Column(name = "last_update")
    private Timestamp lastUpdate;

    @Setter
    @Getter
    @ManyToMany(mappedBy = "actors")
    private Set<Film> films = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Actor actor = (Actor) o;
        return actorId == actor.actorId && Objects.equals(firstName, actor.firstName) && Objects.equals(lastName, actor.lastName) && Objects.equals(lastUpdate, actor.lastUpdate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actorId, firstName, lastName, lastUpdate);
    }

}
