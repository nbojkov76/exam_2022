package softuni.exam.models.dto;

import softuni.exam.models.entity.Book;
import softuni.exam.models.entity.LibraryMember;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlAccessorType(XmlAccessType.FIELD)
public class BorrowingRecordSeedDto {
@XmlElement(name = "borrow_date")
    private String borrowDate;
@XmlElement(name = "return_date")
    private String returnDate;
@XmlElement(name = "book")
    private BookTitleDto book;
@XmlElement(name = "member")
    private LibraryMemberIdDto member;
@XmlElement(name = "remarks")
private String remarks;

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public BookTitleDto getBook() {
        return book;
    }

    public void setBook(BookTitleDto book) {
        this.book = book;
    }

    public LibraryMemberIdDto getMember() {
        return member;
    }

    public void setMember(LibraryMemberIdDto member) {
        this.member = member;
    }
    @Size(min = 3,max = 100)
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
