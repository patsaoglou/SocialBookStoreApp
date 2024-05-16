package com.SE2024.SocialBookStore;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.SE2024.SocialBookStore.dao.UserProfileDAO;
import com.SE2024.SocialBookStore.dtos.BookCategoryDTO;
import com.SE2024.SocialBookStore.dtos.BookDTO;
import com.SE2024.SocialBookStore.dtos.UserProfileDTO;
import com.SE2024.SocialBookStore.service.books.BookSeviceImpl;
import com.SE2024.SocialBookStore.service.userProfile.UserProfileServiceImpl;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
public class BookServiceTests {

    @Autowired
    private UserProfileServiceImpl userProfileService;

    @Autowired
    private BookSeviceImpl bookService;

    @BeforeEach
    public void setUp() {
        UserProfileDTO userProfileDTO = new UserProfileDTO();

        userProfileDTO.setUsername("TestUsername");
        userProfileDTO.setFirstName("TestFirstName");
        userProfileDTO.setLastName("TestLastName");
        userProfileDTO.setAddress("TestAddress 1");
        userProfileDTO.setAge(20);
        userProfileDTO.setTelephone("1234567890");

        UserProfileDTO newUser = userProfileService.registerUserProfileData(userProfileDTO);

        UserProfileDTO userProfileDTO2 = new UserProfileDTO();

        userProfileDTO2.setUsername("TestUsername2");
        userProfileDTO2.setFirstName("Test2FirstName");
        userProfileDTO2.setLastName("Tes2tLastName");
        userProfileDTO2.setAddress("Test2Address 1");
        userProfileDTO2.setAge(20);
        userProfileDTO2.setTelephone("1234567890");

        UserProfileDTO newUser2 = userProfileService.registerUserProfileData(userProfileDTO2);

    }

    @Test
    public void HappyaddBookOffer() {
        BookDTO bookDTO = new BookDTO();

        bookDTO.setBookTitle("TestBookTitle");
        bookDTO.setBookSummary("TestSummary");
        bookDTO.setAuthorsFromForm("TestAuthorFirstName TestAuthorLastName, TestAuthor2FirstName TestAuthor2LastName");
        bookDTO.setBookCategory(new BookCategoryDTO("TestCategory"));

        BookDTO newBookOffer = bookService.addBookOffer(bookDTO, "TestUsername");

        assertThat(bookService.retrieveBookOffers("TestUsername"))
                .extracting(BookDTO::getBookTitle)
                .contains("TestBookTitle");

    }

    @Test
    public void addBookOfferWithEmptyBookTitle() {
        BookDTO bookDTO = new BookDTO();

        // Book title is empty
        bookDTO.setBookSummary("TestSummary");
        bookDTO.setAuthorsFromForm("TestAuthorFirstName TestAuthorLastName, TestAuthor2FirstName TestAuthor2LastName");
        bookDTO.setBookCategory(new BookCategoryDTO("TestCategory"));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            bookService.addBookOffer(bookDTO, "TestUsername");
        });

        assertEquals("Book title is null", exception.getMessage());

    }

    @Test
    public void HappydeleteBookOffer() {

        BookDTO bookDTO = new BookDTO();
        bookDTO.setBookTitle("TestBookTitle");
        bookDTO.setBookSummary("TestSummary");
        bookDTO.setAuthorsFromForm("TestAuthorFirstName TestAuthorLastName, TestAuthor2FirstName TestAuthor2LastName");
        bookDTO.setBookCategory(new BookCategoryDTO("TestCategory"));

        BookDTO newBookOffer = bookService.addBookOffer(bookDTO, "TestUsername");
        bookService.deleteBookOffer("TestUsername", newBookOffer.getId());

        assertThat(bookService.retrieveBookOffers("TestUsername"))
                .extracting(BookDTO::getId)
                .doesNotContain(newBookOffer.getId());

    }

    @Test
    public void deleteBookOfferByUserThatNotBelongToUser() {

        BookDTO bookDTO = new BookDTO();
        bookDTO.setBookTitle("TestBookTitle");
        bookDTO.setBookSummary("TestSummary");
        bookDTO.setAuthorsFromForm("TestAuthorFirstName TestAuthorLastName, TestAuthor2FirstName TestAuthor2LastName");
        bookDTO.setBookCategory(new BookCategoryDTO("TestCategory"));

        BookDTO newBookOffer = bookService.addBookOffer(bookDTO, "TestUsername");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            bookService.deleteBookOffer("TestUsername2", newBookOffer.getId());
        });

        assertEquals("Book does not belong to user", exception.getMessage());

    }

    @Test
    public void deleteBookOfferThatNotExists() {

        BookDTO bookDTO = new BookDTO();
        bookDTO.setBookTitle("TestBookTitle");
        bookDTO.setBookSummary("TestSummary");
        bookDTO.setAuthorsFromForm("TestAuthorFirstName TestAuthorLastName, TestAuthor2FirstName TestAuthor2LastName");
        bookDTO.setBookCategory(new BookCategoryDTO("TestCategory"));

        BookDTO newBookOffer = bookService.addBookOffer(bookDTO, "TestUsername");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            bookService.deleteBookOffer("TestUsername", 0);
        });

        assertEquals("Book to delete doesn't exist", exception.getMessage());

    }

    @Test
    public void HappyretrieveBookOffers() {

        BookDTO bookDTO = new BookDTO();
        bookDTO.setBookTitle("TestBookTitle");
        bookDTO.setBookSummary("TestSummary");
        bookDTO.setAuthorsFromForm("TestAuthorFirstName TestAuthorLastName, TestAuthor2FirstName TestAuthor2LastName");
        bookDTO.setBookCategory(new BookCategoryDTO("TestCategory"));

        BookDTO newBookOffer = bookService.addBookOffer(bookDTO, "TestUsername");

        assertThat(bookService.retrieveBookOffers("TestUsername"))
                .extracting(BookDTO::getId)
                .contains(newBookOffer.getId());

    }

    @Test
    public void HappygetBookById() {

        BookDTO bookDTO = new BookDTO();
        bookDTO.setBookTitle("TestBookTitle");
        bookDTO.setBookSummary("TestSummary");
        bookDTO.setAuthorsFromForm("TestAuthorFirstName TestAuthorLastName, TestAuthor2FirstName TestAuthor2LastName");
        bookDTO.setBookCategory(new BookCategoryDTO("TestCategory"));

        BookDTO newBookOffer = bookService.addBookOffer(bookDTO, "TestUsername");

        assertEquals(bookService.getBookById(newBookOffer.getId()).getId(), newBookOffer.getId());

    }

    @Test
    public void showAvailableBooksToUser() {

        BookDTO bookDTO = new BookDTO();
        bookDTO.setBookTitle("TestBookTitle");
        bookDTO.setBookSummary("TestSummary");
        bookDTO.setAuthorsFromForm("TestAuthorFirstName TestAuthorLastName, TestAuthor2FirstName TestAuthor2LastName");
        bookDTO.setBookCategory(new BookCategoryDTO("TestCategory"));

        BookDTO newBookOffer = bookService.addBookOffer(bookDTO, "TestUsername");

        assertThat(bookService.showAvailableBooksToUser("TestUsername"))
                .extracting(BookDTO::getId)
                .doesNotContain(newBookOffer.getId());
    }
}
