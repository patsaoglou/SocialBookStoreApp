package com.SE2024.SocialBookStore.dtos;


public class BookCategoryDTO {

	private String categoryName;
    
    public BookCategoryDTO() {
    }

    public BookCategoryDTO(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
