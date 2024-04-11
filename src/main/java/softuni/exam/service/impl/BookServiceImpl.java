package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.BookSeedDto;
import softuni.exam.models.entity.Book;
import softuni.exam.repository.BookRepository;
import softuni.exam.service.BookService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final  BookRepository bookRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;

    private static final String BOOK_PATH ="src/main/resources/files/json/books.json";

    private final ValidationUtil validationUtil;

    public BookServiceImpl(BookRepository bookRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.bookRepository = bookRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }


    @Override
    public boolean areImported() {
        return bookRepository.count() > 0;
    }

    @Override
    public String readBooksFromFile() throws IOException {
        return Files.readString(Path.of(BOOK_PATH));
    }

    @Override
    public String importBooks() throws IOException {

        StringBuilder sb = new StringBuilder();

      Arrays.stream(gson.fromJson(readBooksFromFile(), BookSeedDto[].class))
              .filter(bookSeedDto -> {
             boolean isValid = validationUtil.isValid(bookSeedDto)
                     && !isEntityExist(bookSeedDto.getTitle());
                sb.append(isValid ? String.format("Successfully imported book %s - %s", bookSeedDto.getAuthor()
                        , bookSeedDto.getTitle()) : "Invalid book").append(System.lineSeparator());
                  return isValid;
              }).map(bookSeedDto -> modelMapper.map(bookSeedDto, Book.class)).forEach(bookRepository::save);


        return sb.toString();
    }
@Override
public boolean isEntityExist(String title) {
       return bookRepository.existsByTitle(title);

    }

    @Override
    public Book findByTitle(String title) {
        return bookRepository.findByTitle(title).orElse(null);
    }


}
