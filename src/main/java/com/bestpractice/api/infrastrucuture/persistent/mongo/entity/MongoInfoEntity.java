package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import com.bestpractice.api.infrastrucuture.entity.Info;
import org.bson.types.ObjectId;

public class MongoInfoEntity {
  private ObjectId id;
  private String title;
  private String description;

  public MongoInfoEntity() {
  }

  public MongoInfoEntity(ObjectId id, String title, String description) {
    this.id = id;
    this.title = title;
    this.description = description;
  }

  public void setId(ObjectId id) {
    this.id = id;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public ObjectId getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getDescription() {
    return description;
  }

  public static MongoInfoEntity convertFrom(Info info) {
    return new MongoInfoEntity(new ObjectId(info.getId()), info.getTitle(), info.getDescription());
  }

  public Info convertTo() {
    Info info = new Info();
    info.setId(this.id.toString());
    info.setTitle(this.title);
    info.setDescription(this.description);
    return info;
  }
}
