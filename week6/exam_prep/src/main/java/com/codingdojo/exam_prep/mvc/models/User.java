package com.codingdojo.exam_prep.mvc.models;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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

    @Column(name = "first_name")
    @NotEmpty(message = "A first name is required!")
    @Size(min = 3, max = 255, message = "First must be between 3 and 255 characters")
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty(message = "A last name is required!")
    @Size(min = 3, max = 255, message = "Last name must be between 3 and 255 characters")
    private String lastName;

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

    @OneToMany(mappedBy = "teamLead", fetch = FetchType.LAZY)
    private List<Project> myProjectsLead;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "team_members", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private List<Project> projectsJoined;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Task> myTasks;

    public User() {}

    @Override
    public boolean equals (Object o) {
        
        if (this == o) { return true; }

        if (o == null || getClass() != o.getClass()) { return false; }

        User that = (User) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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

    public List<Task> getMyTasks() {
        return myTasks;
    }

    public void setMyTasks(List<Task> myTasks) {
        this.myTasks = myTasks;
    }

    public List<Project> getProjectsJoined() {
        return projectsJoined;
    }

    public void setProjectsJoined(List<Project> projectsJoined) {
        this.projectsJoined = projectsJoined;
    }

    public List<Project> getMyProjectsLead() {
        return myProjectsLead;
    }

    public void setMyProjectsLead(List<Project> myProjectsLead) {
        this.myProjectsLead = myProjectsLead;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "User [confirm=" + confirm + ", createdAt="
                + createdAt + ", email=" + email + ", id=" + id + ", first_name=" + firstName + ", last_name=" + lastName + ", password=" + password
                + ", updatedAt=" + updatedAt + "]";
    }

    
}
