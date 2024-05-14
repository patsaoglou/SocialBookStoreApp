package com.SE2024.SocialBookStore.dtos;


public class BookRequestDTO {
    private int requestId;
    private BookDTO requestedBook;
    private UserProfileDTO requester;
    private String status;
    
    public int getRequestId() {
        return requestId;
    }
    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }
    public BookDTO getRequestedBook() {
        return requestedBook;
    }
    public void setRequestedBook(BookDTO requestedBook) {
        this.requestedBook = requestedBook;
    }
    public UserProfileDTO getRequester() {
        return requester;
    }
    public void setRequester(UserProfileDTO requester) {
        this.requester = requester;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
