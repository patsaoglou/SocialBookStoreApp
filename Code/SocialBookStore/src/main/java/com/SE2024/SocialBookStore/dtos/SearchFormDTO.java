package com.SE2024.SocialBookStore.dtos;

public class SearchFormDTO {
    private String searchKeyword;
    private int searchStrategy;
    private String authors;
    
    public SearchFormDTO() {
    }

    public SearchFormDTO(String searchKeyword, int searchStrategy, String authors) {
        this.searchKeyword = searchKeyword;
        this.searchStrategy = searchStrategy;
        this.authors = authors;
    }


    public String getSearchKeyword() {
        return searchKeyword;
    }
    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }
    public int getSearchStrategy() {
        return searchStrategy;
    }
    public void setSearchStrategy(int searchStrategy) {
        this.searchStrategy = searchStrategy;
    }
    public String getAuthors() {
        return authors;
    }
    public void setAuthors(String authors) {
        this.authors = authors;
    }
}
