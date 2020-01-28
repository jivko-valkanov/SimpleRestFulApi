package org.zhivko.todoListApi.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private long id = 0;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false)
    private Date createdAt = null;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at", nullable = false)
    private Date updatedAt = null;

    public BaseEntity() {
        super();
    }

    @PrePersist
    protected void persist() {
        Date current = new Date();
        this.setCreatedAt(current);
        this.setUpdatedAt(current);
    }

    @PreUpdate
    protected void update() {
        this.setUpdatedAt(new Date());
    }

    @PreRemove
    protected void remove() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        return prime * result + super.hashCode();
    }

//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//
//        if (obj == null) {
//            return false;
//        }
//
//        if (!getClass().equals(HibernateProxyHelper.getClassWithoutInitializingProxy(obj))) {
//            return false;
//        }
//
//        final BaseEntity other = (BaseEntity) obj;
//
//        if (getId() != other.getId()) {
//
//            if (!getId().equals(other.getId())) {
//                return false;
//            }
//        }
//
//        return true;
//    }
}
