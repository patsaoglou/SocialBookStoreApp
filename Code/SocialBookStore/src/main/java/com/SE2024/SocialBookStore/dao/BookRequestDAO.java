package com.SE2024.SocialBookStore.dao;

import java.util.List;

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
    
    
    @Query("SELECT br FROM BookRequest br WHERE br.requestedBook = :book AND (br.requester = :requester OR br.status != :status)")
    List<BookRequest> findByRequestedBookAndNotStatusOrRequester(@Param("status") String status, @Param("book") Book book, @Param("requester") UserProfile requester);

    
    public void deleteByRequestedBook(Book book);
}
