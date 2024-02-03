package com.bestpractice.api.infrastrucuture.entity;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class Info extends SharedData {

    @Column(name = "id")
    private String id;

    @NotNull
    @Column(nullable = false, name = "title")
    private String title;

    @NotNull
    @Column(nullable = false, name = "description")
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
