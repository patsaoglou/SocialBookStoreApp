package com.SE2024.SocialBookStore.service.books;

import java.util.List;

import com.SE2024.SocialBookStore.dtos.BookDTO;
import com.SE2024.SocialBookStore.dtos.SearchFormDTO;

public interface BookService {
    List<BookDTO> retrieveBookOffers(String username);
    BookDTO addBookOffer(BookDTO bookData, String username);
    void deleteBookOffer(String username, int bookId);

    BookDTO getBookById(int bookId);
    List<BookDTO> showAvailableBooksToUser(String username);

    List<BookDTO> searchBooks(SearchFormDTO searchForm, String username);
    List<BookDTO> recommendBooksByFavouriteCategories(String username);
    List<BookDTO> recommendBooksByFavouriteBookAuthors(String username);  
} 