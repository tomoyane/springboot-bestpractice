package com.bestpractice.api.infrastrucuture.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "infos")
public class Info extends SharedData {

    @Id
    @Column(name = "id")
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonIgnore
    @Column(nullable = false, name = "uuid")
    private String uuid;

    @NotNull
    @Column(nullable = false, name = "title")
    private String title;

    @NotNull
    @Column(nullable = false, name = "description")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
