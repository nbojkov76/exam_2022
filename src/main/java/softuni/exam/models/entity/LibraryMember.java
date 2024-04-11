package softuni.exam.models.entity;

import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "library_members")
public class LibraryMember extends BaseEntity{

    private String firstName;
    private String lastName;

    private String address;

    private String phoneNumber;

    private Set<BorrowingRecord> libraryRecords;



    public LibraryMember() {
    }
@Column(name = "first_name", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
@Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
@Column
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
@Column(name = "phone_number", unique = true, nullable = false)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
@OneToMany(mappedBy = "member")
    public Set<BorrowingRecord> getLibraryRecords() {
        return libraryRecords;
    }

    public void setLibraryRecords(Set<BorrowingRecord> libraryRecords) {
        this.libraryRecords = libraryRecords;
    }
}
