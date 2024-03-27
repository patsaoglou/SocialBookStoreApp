package com.SE2024.SocialBookStore.model;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "bookCategory")
public class BookCategory {

    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="bookCategoryId")
    private int id;

    @Column(name="categoryName")
	private String categoryName;
    
    @OneToMany
    @JoinTable(name = "bookIsCategory",
                joinColumns = @JoinColumn(name = "bookCategoryId", referencedColumnName = "bookCategoryId"),
                inverseJoinColumns = @JoinColumn(name = "bookId", referencedColumnName = "bookId"))
    private List<Book> books;
    
    public BookCategory() {
    }

    public int getId() {
        return id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }   

}
