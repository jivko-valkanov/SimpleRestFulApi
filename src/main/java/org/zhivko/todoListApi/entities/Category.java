package org.zhivko.todoListApi.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    @Column(name = "categoty_name", nullable = false, columnDefinition = "VARCHAR(255)")
    private String categoryName = null;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user = null;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private Set<Task> tasks = new HashSet<Task>();

    public Category() {
        super();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }
}
