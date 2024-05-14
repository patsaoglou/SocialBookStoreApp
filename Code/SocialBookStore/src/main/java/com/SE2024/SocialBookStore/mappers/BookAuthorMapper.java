package com.SE2024.SocialBookStore.mappers;

import com.SE2024.SocialBookStore.dtos.BookAuthorDTO;
import com.SE2024.SocialBookStore.model.BookAuthor;

public class BookAuthorMapper {

    public static BookAuthorDTO toDTO(BookAuthor bookAuthor) {
        return new BookAuthorDTO(bookAuthor.getFirstName(), bookAuthor.getLastName());
    }
}
