package com.SE2024.SocialBookStore.dao;

import java.util.List;

import org.antlr.v4.runtime.atn.SemanticContext.AND;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.SE2024.SocialBookStore.model.Book;
import com.SE2024.SocialBookStore.model.BookRequest;
import com.SE2024.SocialBookStore.model.UserProfile;

@Repository
public interface BookRequestDAO extends JpaRepository<BookRequest, Integer> {
    public List<BookRequest> findByRequestedBook(Book book);

    public List<BookRequest> findByRequester(UserProfile requester);

    public BookRequest findByRequestedBookAndRequester(Book book, UserProfile requester);

    public BookRequest findByRequestId(int requestId);

    @Query(value = "SELECT * FROM user_request_book as br WHERE br.book_id = :book AND (br.user_id = :requester OR :status IN (SELECT request_status FROM user_request_book  WHERE book_id = :book))", nativeQuery = true)
    List<BookRequest> findByRequestedBookAndNotStatusOrRequester(@Param("status") String status,
            @Param("book") int bookId, @Param("requester") int userId);

    public void deleteByRequestedBook(Book book);
}
