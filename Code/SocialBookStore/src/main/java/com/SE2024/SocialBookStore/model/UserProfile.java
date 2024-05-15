package com.SE2024.SocialBookStore.model;


import java.util.*;

import jakarta.persistence.*;

@Entity
@Table(name="profiles")
public class UserProfile {
    
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="userProfileId")
    private int id;

    @Column(name="username")
	private String username;

    @Column(name="firstName")
	private String firstName;

    @Column(name="lastName")
	private String lastName;

    @Column(name="address")
	private String address;

    @Column(name="age")
	private int age;

    @Column(name="telephone")
	private String telephone;
    
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "userOffersBook",
               joinColumns = @JoinColumn(name = "userId", referencedColumnName = "userProfileId"),
               inverseJoinColumns = @JoinColumn(name = "bookId", referencedColumnName = "bookId"))
    private List <Book> bookOffers;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "userPrefersAuthor",
                joinColumns = @JoinColumn(name = "userId", referencedColumnName = "userProfileId"),
                inverseJoinColumns = @JoinColumn(name = "bookAuthorId", referencedColumnName = "bookAuthorId"))
    private  Set<BookAuthor> favouriteBookAuthors;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "userPrefersCategory",
                joinColumns = @JoinColumn(name = "userId", referencedColumnName = "userProfileId"),
                inverseJoinColumns = @JoinColumn(name = "bookCategoryId", referencedColumnName = "bookCategoryId"))
    private  Set<BookCategory> favouriteBookCategories;

    public UserProfile() {
        this.favouriteBookAuthors = new HashSet<>();
        this.bookOffers = new ArrayList<>();
        this.favouriteBookCategories = new HashSet<>();
    }

	public List<Book> getBookOffers() {
        return bookOffers;
    }

    public void setBookOffers(List<Book> bookOffers) {
        this.bookOffers = bookOffers;
    }

    public Set<BookAuthor> getFavouriteBookAuthors() {
        return favouriteBookAuthors;
    }

    public void setFavouriteBookAuthors(Set<BookAuthor> favouriteBookAuthors) {
        this.favouriteBookAuthors = favouriteBookAuthors;
    }

    
    public void addFavouriteBookAuthor(BookAuthor favouriteBookAuthor) {
        this.favouriteBookAuthors.add(favouriteBookAuthor);
    }

    public Set<BookCategory> getFavouriteBookCategories() {
        return favouriteBookCategories;
    }

    public void setFavouriteBookCategories(Set<BookCategory> favouriteBookCategories) {
        this.favouriteBookCategories = favouriteBookCategories;
    }

    public void addFavouriteBookCategory(BookCategory favouriteBookCategory) {
        favouriteBookCategories.add(favouriteBookCategory);
    }
    

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public int getAge() {
        return age;
    }

    public String getTelephone() {
        return telephone;
    }

    public void addBookToBookOffersList(Book newBook){
        bookOffers.add(newBook);
    }

    public Boolean deleteOfferFromUser(Book bookToDelete){
        return bookOffers.remove(bookToDelete);
    }

    public Boolean isBookInUserBookOffers(Book book){
        return bookOffers.contains(book);
    }
}
