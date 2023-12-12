package com.rmsoft.bookmanagementsystem.repository;

import com.rmsoft.bookmanagementsystem.entity.BorrowRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
    Page<BorrowRecord> findByBook_BookId(Long bookId, Pageable pageable);

    Optional<BorrowRecord> findTopByBook_BookIdOrderByBorrowDateDesc(Long bookId);

}
