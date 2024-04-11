package softuni.exam.models.dto;

import softuni.exam.models.entity.BorrowingRecord;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "borrowing_records")
@XmlAccessorType(XmlAccessType.FIELD)
public class BorrowingRecordsRootDto {
    @XmlElement(name = "borrowing_record")
    private List<BorrowingRecordSeedDto> borrows;

    public List<BorrowingRecordSeedDto> getBorrows() {
        return borrows;
    }

    public void setBorrows(List<BorrowingRecordSeedDto> borrows) {
        this.borrows = borrows;
    }
}
