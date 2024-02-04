package com.bestpractice.api.infrastrucuture.persistent.mongo.entity;

import com.bestpractice.api.infrastrucuture.entity.User;
import org.bson.types.ObjectId;

public class MongoUserEntity {
  private ObjectId  id;
  private String username;
  private String email;
  private String password;

  public MongoUserEntity() {
  }

  public MongoUserEntity(ObjectId id, String username, String email, String password) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.password = password;
  }

  public void setId(ObjectId id) {
    this.id = id;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public ObjectId getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public static MongoUserEntity convertFrom(User user) {
    return new MongoUserEntity(new ObjectId(user.getId()), user.getUsername(), user.getEmail(), user.getPassword());
  }

  public User convertTo() {
    User user = new User();
    user.setId(this.id.toString());
    user.setUsername(this.username);
    user.setPassword(this.password);
    return user;
  }
}
