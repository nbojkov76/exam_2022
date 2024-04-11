package softuni.exam.service;


import softuni.exam.models.entity.Book;

import java.io.IOException;
import java.util.Optional;

// TODO: Implement all methods
public interface BookService {

    boolean areImported();

    String readBooksFromFile() throws IOException;

	String importBooks() throws IOException;

    boolean isEntityExist(String title);


    Book findByTitle(String title);


}
