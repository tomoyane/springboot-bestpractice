package com.bestpractice.api.infrastrucuture.persistent.mongo;

import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoUserEntity;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import java.util.Objects;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public class MongoUserPersistentRepository implements UserPersistentRepository {

  private static final String COLLECTION_NAME = "users";
  private final MongoClient mongoClient;
  private final MongoDatabase mongoDatabase;
  private final MongoCollection<MongoUserEntity> collection;

  public MongoUserPersistentRepository(MongoClient mongoClient, MongoDatabase mongoDatabase) {
    this.mongoClient = mongoClient;
    this.mongoDatabase = mongoDatabase;
    this.collection = this.mongoDatabase.getCollection(COLLECTION_NAME, MongoUserEntity.class);
  }

  @Override
  public String newId() {
    return new ObjectId().toString();
  }

  @Override
  public User findByEmail(String email) {
    Bson filter = Filters.and(
        Filters.eq("email", email));

    try {
      return Objects.requireNonNull(this.collection.find(filter).first()).convertTo();
    } catch (Exception ex) {
      throw new InternalServerError("Failed to get detail from database", ex);
    }
  }

  @Override
  public User findById(String id) {
    Bson filter = Filters.and(
        Filters.eq("_id", new ObjectId(id)));

    try {
      return Objects.requireNonNull(this.collection.find(filter).first()).convertTo();
    } catch (Exception ex) {
      throw new InternalServerError("Failed to get detail from database", ex);
    }
  }

  @Override
  public User insert(User user) {
    try {
      this.collection.insertOne(MongoUserEntity.convertFrom(user));
      return user;
    } catch (Exception ex) {
      throw new InternalServerError("Failed to insert", ex);
    }
  }

  @Override
  public User replace(String id, User user) {
    MongoUserEntity mongoUserEntity = MongoUserEntity.convertFrom(user);
    Bson filter = Filters.and(
        Filters.eq("_id", mongoUserEntity.getId()));

    try {
      ReplaceOptions opts = new ReplaceOptions().upsert(true);
      UpdateResult result = this.collection.replaceOne(filter, mongoUserEntity, opts);
      if (!result.wasAcknowledged()) {
        throw new RuntimeException("Failed to get Acknowledged on replace operation");
      }
      return user;
    } catch (Exception ex) {
      throw new InternalServerError("Failed to insert", ex);
    }
  }

  @Override
  public boolean removeById(String id) {
    ObjectId objectId = new ObjectId(id);
    Bson filter = Filters.and(
        Filters.eq("_id", objectId));
    try {
      DeleteResult result = this.collection.deleteOne(filter);
      return result.wasAcknowledged();
    } catch (Exception ex) {
      throw new InternalServerError("Failed to delete", ex);
    }
  }
}
