package org.zhivko.todoListApi.entities;

import org.hibernate.annotations.Type;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity implements UserDetails {

    @NotBlank
    @Column(name = "username", nullable = false, unique = true, columnDefinition = "VARCHAR(255)")
    private String username = null;

    @NotBlank
    @Column(name = "password", nullable = false, columnDefinition = "VARCHAR(100)")
    private String password = null;

    @Type(type= "org.hibernate.type.NumericBooleanType")
    @Column(name = "is_enable", nullable = false, columnDefinition = "TINYINT(1)")
    private boolean isEnable = true;

    @Type(type= "org.hibernate.type.NumericBooleanType")
    @Column(name = "is_account_non_expired", nullable = false, columnDefinition = "TINYINT(1)")
    private boolean isAccountNonExpired = false;

    @Type(type= "org.hibernate.type.NumericBooleanType")
    @Column(name = "is_account_non_locked", nullable = false, columnDefinition = "TINYINT(1)")
    private boolean isAccountNonLocked = false;

    @Type(type= "org.hibernate.type.NumericBooleanType")
    @Column(name = "is_credentials_non_expired", nullable = false, columnDefinition = "TINYINT(1)")
    private boolean isCredentialsNonExpired = false;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> authorities = new HashSet<Role>();
//
//    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
//    private Profile profile = null;
//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "user_type", columnDefinition = "VARCHAR(20)", nullable = false)
//    private UserType userType = null;
//
//    @ManyToMany(mappedBy="users")
//    private Set<Group> groups = new HashSet<Group>();
//
//    @OneToMany(mappedBy = "note")
//    private Set<Note> notes = new HashSet<Note>();
//
//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    private Set<PasswordChangeRequest> passwordChangeRequests = new HashSet<>();


    public User() {
        super();
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnable;
    }

    public void setEnabled(boolean enable) {
        this.isEnable = enable;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.isAccountNonExpired = accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.isAccountNonLocked = accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.isCredentialsNonExpired = credentialsNonExpired;
    }

    @Override
    public Set<Role> getAuthorities() {
        return this.authorities;
    }

    public void setAuthorities(Set<Role> authorities) {
        this.authorities = authorities;
    }
}
