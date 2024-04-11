package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.BorrowingRecordSeedDto;
import softuni.exam.models.dto.BorrowingRecordsRootDto;
import softuni.exam.models.entity.Book;
import softuni.exam.models.entity.BorrowingRecord;
import softuni.exam.models.entity.LibraryMember;
import softuni.exam.repository.BookRepository;
import softuni.exam.repository.BorrowingRecordRepository;
import softuni.exam.repository.LibraryMemberRepository;
import softuni.exam.service.BookService;
import softuni.exam.service.BorrowingRecordsService;
import softuni.exam.service.LibraryMemberService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BorrowingRecordsServiceImpl  implements BorrowingRecordsService {

    private static final String BORROW_PATH = "src/main/resources/files/xml/borrowing-records.xml";
    private final BorrowingRecordRepository borrowingRecordRepository;

    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final BookService bookService;

    private final LibraryMemberService libraryMemberService;

    private final BookRepository bookRepository;

    private final LibraryMemberRepository libraryMemberRepository;
    
    private final XmlParser xmlParser;

    public BorrowingRecordsServiceImpl(BorrowingRecordRepository borrowingRecordRepository, ModelMapper modelMapper, ValidationUtil validationUtil, BookService bookService, LibraryMemberService libraryMemberService, BookRepository bookRepository, LibraryMemberRepository libraryMemberRepository, XmlParser xmlParser) {
        this.borrowingRecordRepository = borrowingRecordRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.bookService = bookService;
        this.libraryMemberService = libraryMemberService;
        this.bookRepository = bookRepository;
        this.libraryMemberRepository = libraryMemberRepository;
        this.xmlParser = xmlParser;
    }

    @Override
    public boolean areImported() {
        return borrowingRecordRepository.count() > 0;
    }

    @Override
    public String readBorrowingRecordsFromFile() throws IOException {
        return Files.readString(Path.of(BORROW_PATH));
    }

    @Override
    public String importBorrowingRecords() throws IOException, JAXBException {
        
        StringBuilder sb = new StringBuilder();
        BorrowingRecordsRootDto borrowingRecordsRootDto = xmlParser.fromFile(BORROW_PATH, BorrowingRecordsRootDto.class);

        for (BorrowingRecordSeedDto borrow : borrowingRecordsRootDto.getBorrows()) {

            Optional<Book> optionalBook = bookRepository.findByTitle(borrow.getBook().getTitle());
            Optional<LibraryMember> optionalLibraryMember = libraryMemberRepository.findById(borrow.getMember().getId());

            if (!validationUtil.isValid(borrow) || optionalBook.isEmpty() || optionalLibraryMember.isEmpty()){
                sb.append("Invalid borrowing record\n");
                continue;
            }

            BorrowingRecord borrowingRecord = modelMapper.map(borrow, BorrowingRecord.class);
            borrowingRecord.setMember(optionalLibraryMember.get());
            borrowingRecord.setBook(optionalBook.get());
            this.borrowingRecordRepository.save(borrowingRecord);

            sb.append(String.format("Successfully imported borrowing record %s - %s", borrow.getBook().getTitle()
            ,borrow.getBorrowDate())).append(System.lineSeparator());

        }

        return sb.toString();
    }

    @Override
    public String exportBorrowingRecords() {
        return borrowingRecordRepository.findByBook_TitleAndBook_AuthorAndBorrowDateAndMember_FirstNameAndMember_LastNameOrBorrowDate()
                .stream()
                .map(enter -> String.format("\"Book title: %s\n" +
                        "\"*Book author: %s\n" +
                        "\"**Date borrowed: %s\n" +
                        "\"***Borrowed by: %s %s", enter.getBook().getTitle(), enter.getBook().getAuthor()
                ,enter.getBorrowDate(),enter.getMember().getFirstName(),enter.getMember().getLastName())).collect(Collectors.joining());


    }
}
