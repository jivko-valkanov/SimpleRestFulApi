package org.zhivko.todoListApi.entities;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tasks")
public class Task extends BaseEntity {

    @Column(name = "task_description", nullable = false, columnDefinition = "VARCHAR(255)")
    private String taskDescription = null;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "due_date", nullable = false)
    private Date dueDate = null;

    @Type(type= "org.hibernate.type.NumericBooleanType")
    @Column(name = "completed", nullable = false, columnDefinition = "TINYINT(1)")
    private boolean completed = false;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category = null;

    public Task() {
        super();
    }

    public String getTaskDescription() {
        return this.taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Date getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isCompleted() {
        return this.completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
