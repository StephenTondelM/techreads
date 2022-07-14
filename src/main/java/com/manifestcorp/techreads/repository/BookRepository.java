package com.manifestcorp.techreads.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.manifestcorp.techreads.model.Book;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Modifying
    @Query("DELETE FROM Book WHERE title = :title")
    void deleteByTitle(@Param("title") String title);

    @Query(value = "SELECT * FROM Book WHERE title = :title", nativeQuery = true)
    List<Book> findByTitle(@Param("title") String title);
}
