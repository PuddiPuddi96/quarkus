package it.davide.quarkus.starting;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class BookRepository {

    public List<Book> getAllBooks(){
        return List.of(
                new Book(1, "Title one", "Davide", 2025, "IT"),
                new Book(2, "Title two", "Luca", 2024, "IT"),
                new Book(3, "Title three", "Gennaro", 2023, "IT")
        );
    }

    public Optional<Book> getBook(int id){
        return getAllBooks().stream().filter(book -> book.id == id).findFirst();
    }
}
