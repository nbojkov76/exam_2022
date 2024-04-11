package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.BorrowingRecord;

import java.util.List;

@Repository
public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {

    List<BorrowingRecord> findByBook_TitleAndBook_AuthorAndBorrowDateAndMember_FirstNameAndMember_LastNameOrBorrowDate();


}
