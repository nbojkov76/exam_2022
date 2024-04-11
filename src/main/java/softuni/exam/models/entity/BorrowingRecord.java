package softuni.exam.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "borrowing_records")
public class BorrowingRecord extends BaseEntity {

    private LocalDate borrowDate;
    private LocalDate returnDate;

    private String remarks;

    private Book book;

    private LibraryMember member;

    public BorrowingRecord() {
    }

    @Column(name = "borrow_date", nullable = false)
    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    @Column(name = "return_date", nullable = false)
    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    @Column(name = "remarks")
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @ManyToOne
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
@ManyToOne
    public LibraryMember getMember() {
        return member;
    }

    public void setMember(LibraryMember member) {
        this.member = member;
    }
}