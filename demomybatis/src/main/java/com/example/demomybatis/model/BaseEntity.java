package com.example.demomybatis.model;

import javax.persistence.*;

@Entity
public class BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    @Transient
    private Integer page=1;
    @Transient
    private  Integer rows=10;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                ", page=" + page +
                ", rows=" + rows +
                '}';
    }
}
