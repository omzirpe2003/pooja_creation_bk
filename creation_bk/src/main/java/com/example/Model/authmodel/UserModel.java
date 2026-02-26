package com.example.Model.authmodel;

import java.time.LocalDateTime;
import java.util.List;

import com.example.Model.orders.Order;
import com.example.Model.roles.Roles;

import jakarta.persistence.*;

@Entity
@Table(
    name = "users",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = "email_id"),
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "mobile_no")
    }
)
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name")
    private String fullName;

   
    @Column(name = "mobile")
    private String mobileNo;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders;
    
    private String username;

    
    @Column(name = "email")
    private String emailId;

    
    private String password;

    // ❌ Not stored in DB (used only for validation)
    @Transient
    private String confirmPassword;

    // ✅ Many users → one role
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    private Roles role;

    @Column(updatable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // ✅ Auto timestamps
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // ===== Constructors =====

    public UserModel() {
    }

    public UserModel(
            Long id,
            String fullName,
            String mobileNo,
            String username,
            String emailId,
            String password,
            Roles role
    ) {
        this.id = id;
        this.fullName = fullName;
        this.mobileNo = mobileNo;
        this.username = username;
        this.emailId = emailId;
        this.password = password;
        this.role = role;
    }

    // ===== Getters & Setters =====

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    // ⚠️ Always store ENCODED password
    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", username='" + username + '\'' +
                ", emailId='" + emailId + '\'' +
                ", role=" + (role != null ? role.getId() : null) +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", Password="+password+
                '}';
    }
}
