package com.SE2024.SocialBookStore;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.SE2024.SocialBookStore.dtos.BookCategoryDTO;
import com.SE2024.SocialBookStore.dtos.BookDTO;
import com.SE2024.SocialBookStore.dtos.BookRequestDTO;
import com.SE2024.SocialBookStore.dtos.UserProfileDTO;
import com.SE2024.SocialBookStore.service.books.BookRequestService;
import com.SE2024.SocialBookStore.service.books.BookService;
import com.SE2024.SocialBookStore.service.userProfile.UserProfileService;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
public class BookRequestServiceTest {
    
    @Autowired
    private BookRequestService bookRequestService;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserProfileService userProfileService;

    private BookDTO addedBook;

    @BeforeEach
    void setUp(){

        UserProfileDTO userProfileDTO = new UserProfileDTO();
        
        userProfileDTO.setUsername("TestUser1");
        userProfileDTO.setFirstName("TestFirstName1");
        userProfileDTO.setLastName("TestLastName1");
        userProfileDTO.setAddress("TestAddress 1");
        userProfileDTO.setAge(20);
        userProfileDTO.setTelephone("1234567890");

        userProfileService.registerUserProfileData(userProfileDTO);

        userProfileDTO.setUsername("TestUser2");
        userProfileDTO.setFirstName("TestFirstName2");
        userProfileDTO.setLastName("TestLastName2");
        userProfileDTO.setAddress("TestAddress 2");
        userProfileDTO.setAge(20);
        userProfileDTO.setTelephone("1234567890");

        userProfileService.registerUserProfileData(userProfileDTO);

        // Adding book to user 1
        BookDTO bookDTO = new BookDTO();

        bookDTO.setBookTitle("TestBookTitle");
        bookDTO.setBookSummary("TestSummary");
        bookDTO.setAuthorsFromForm("TestAuthorFirstName TestAuthorLastName, TestAuthor2FirstName TestAuthor2LastName");
        bookDTO.setBookCategory(new BookCategoryDTO("TestCategory"));

        addedBook = bookService.addBookOffer(bookDTO, "TestUser1");

    }

    @Test
    public void happyDayRequestBook(){
        bookRequestService.requestBook(addedBook.getId(), "TestUser2");
        
        var bookRequests = bookRequestService.retrieveBookRequests("TestUser2");

        // check that requester has book in book requests
        assertThat(bookRequests)
            .extracting(bookRequest -> bookRequest.getRequestedBook().getId())
            .contains(addedBook.getId());
    }


    @Test
    public void userRequestOwnedBookRequestBook(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            bookRequestService.requestBook(addedBook.getId(), "TestUser1");
        });

        assertEquals("User requests from his/her book offers", exception.getMessage());

    }

    @Test
    public void bookIdNotExistsRequestBook(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            bookRequestService.requestBook(0, "TestUser2");
        });

        assertEquals("Book with bookId does not exist", exception.getMessage());

    }

    @Test
    public void happyDayDeleteBookRequest(){
        bookRequestService.requestBook(addedBook.getId(), "TestUser2");

        bookRequestService.deleteBookRequest(addedBook.getId(), "TestUser2");

        var bookRequests = bookRequestService.retrieveBookRequests("TestUser2");

        assertThat(bookRequests)
            .extracting(bookRequest -> bookRequest.getRequestedBook().getId())
            .doesNotContain(addedBook.getId());

    }

    @Test
    public void bookRequestDoesNotExistDeleteBookRequest(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            bookRequestService.deleteBookRequest(addedBook.getId(), "TestUser2");
        });

        assertEquals("Book Request Specified does not exist", exception.getMessage());
    }

    @Test
    public void requestingBookDoesNotExistDeleteBookRequest(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            bookRequestService.deleteBookRequest(0, "TestUser2");
        });

        assertEquals("Book with bookId does not exist", exception.getMessage());
    }


    @Test
    public void happyDayRetrieveRequesingUsers(){
        bookRequestService.requestBook(addedBook.getId(), "TestUser2");

        List<BookRequestDTO> bookRequests = bookRequestService.retrieveRequestingUsers(addedBook.getId());
        
        assertThat(bookRequests)
            .extracting(bookRequest -> bookRequest.getRequester().getUsername())
            .contains("TestUser2");
    
    
    }

    @Test
    public void happyDaySelectRequester(){
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setUsername("TestUser3");
        userProfileDTO.setFirstName("TestFirstName3");
        userProfileDTO.setLastName("TestLastName3");
        userProfileDTO.setAddress("TestAddress 3");
        userProfileDTO.setAge(20);
        userProfileDTO.setTelephone("1234567890");

        userProfileService.registerUserProfileData(userProfileDTO);


        bookRequestService.requestBook(addedBook.getId(), "TestUser2");
        bookRequestService.requestBook(addedBook.getId(), "TestUser3");


        List<BookRequestDTO> bookRequests = bookRequestService.retrieveRequestingUsers(addedBook.getId());
        
        // say that i select first requester from the list/ TestUser2
        bookRequestService.selectRequester(bookRequests.get(0).getRequestId());

        bookRequests = bookRequestService.retrieveRequestingUsers(addedBook.getId());

        assertEquals(bookRequests.get(0).getStatus(), "ACCEPTED");
        assertEquals(bookRequests.get(1).getStatus(), "DECLINED");

    }
}
