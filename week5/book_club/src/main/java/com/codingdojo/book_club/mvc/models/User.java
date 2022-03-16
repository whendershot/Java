package com.codingdojo.book_club.mvc.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at", updatable = false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;

    @Column(name = "updated_at")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;

    @NotEmpty(message = "A username is required!")
    @Size(min = 3, max = 255, message = "Username must be between 3 and 255 characters")
    private String name;

    @NotEmpty(message = "A valid email address is required!")
    @Email(message = "Please enter a valid email address!")
    private String email;

    @Column(name = "password_hash")
    @NotEmpty(message = "A password is required!")
    @Size(min = 8, max = 255, message = "Password must be between 8 and 255 characters")
    private String password;

    @Transient
    @NotEmpty(message = "Please confirm the password entered!")
    @Size(min = 8, max = 255, message = "The comfirm password must be between 8 and 255 characters")
    private String confirm;
    
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Book> books;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<BookThought> booksThoughts;
    
    public User() {}

    public User(
            @NotEmpty(message = "A username is required!") @Size(min = 3, max = 255, message = "Username must be between 3 and 255 characters") String name,
            @NotEmpty(message = "A valid email address is required!") @Email(message = "Please enter a valid email address!") String email) {
        this.name = name;
        this.email = email;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
        this.updatedAt = this.createdAt;
    }

    @PreUpdate()
    protected void onUpdate() {
        this.updatedAt = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<BookThought> getBooksThoughts() {
        return booksThoughts;
    }

    public void setBooksThoughts(List<BookThought> booksThoughts) {
        this.booksThoughts = booksThoughts;
    }

    @Override
    public String toString() {
        return "User [confirm=" + confirm + ", createdAt="
                + createdAt + ", email=" + email + ", id=" + id + ", name=" + name + ", password=" + password
                + ", updatedAt=" + updatedAt + "]";
    }

    
}