package com.bestpractice.api.domain.model;

import com.bestpractice.api.infrastrucuture.entity.Info;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import javax.validation.constraints.NotNull;

public class InfoRequest  {

    @NotNull
    @JsonProperty("title")
    private String title;

    @NotNull
    @JsonProperty("description")
    private String description;

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

    public Info convert() {
        Info info = new Info();
        info.setId(UUID.randomUUID().toString());
        setData(info);
        return info;
    }

    public Info convert(String id) {
        Info info = new Info();
        info.setId(id);
        setData(info);
        return info;
    }

    private void setData(Info info) {
        info.setTitle(this.title);
        info.setDescription(this.description);
    }
}
