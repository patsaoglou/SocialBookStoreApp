package com.SE2024.SocialBookStore.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_request_book",
    uniqueConstraints = @UniqueConstraint(columnNames = {"bookId", "userId"})
)
public class BookRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer requestId;

    @ManyToOne
    @JoinColumn(name = "bookId", referencedColumnName = "bookId")
    private Book requestedBook;
    
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userProfileId")
    private UserProfile requester;

    @Column(name = "requestStatus")
    private String status;
    
    public BookRequest() {
        this.status = "PENDING";
    }
    public BookRequest(UserProfile requester, Book requestedBook) {
        this.requester = requester;
        this.requestedBook = requestedBook;
        this.status = "PENDING";
    }
    public Book getRequestedBook() {
        return requestedBook;
    }
    public UserProfile getRequester() {
        return requester;
    }
    public String getStatus() {
        return status;
    }
    public void setRequestedBook(Book requestedBook) {
        this.requestedBook = requestedBook;
    }
    public void setRequester(UserProfile requester) {
        this.requester = requester;
    }
    public void setStatus(String status) {
        this.status = status;
    }    

}
