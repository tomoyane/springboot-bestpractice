package com.bestpractice.api.infrastrucuture.persistent.mongo;

import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import com.bestpractice.api.infrastrucuture.persistent.mongo.entity.MongoInfoEntity;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public class MongoInfoPersistentRepository implements InfoPersistentRepository {

  private static final String COLLECTION_NAME = "infos";
  private final MongoClient mongoClient;
  private final MongoDatabase mongoDatabase;
  private final MongoCollection<MongoInfoEntity> collection;

  public MongoInfoPersistentRepository(MongoClient mongoClient, MongoDatabase mongoDatabase) {
    this.mongoClient = mongoClient;
    this.mongoDatabase = mongoDatabase;
    this.collection = this.mongoDatabase.getCollection(COLLECTION_NAME, MongoInfoEntity.class);
  }

  @Override
  public String newId() {
    return new ObjectId().toString();
  }

  @Override
  public List<Info> findAll() {
    FindIterable<MongoInfoEntity> find = this.collection.find();

    List<Info> data = new ArrayList<>();
    try (MongoCursor<MongoInfoEntity> iterator = find.iterator()) {
      while (iterator.hasNext()) {
        MongoInfoEntity next = iterator.next();
        data.add(next.convertTo());
      }
    } catch (Exception ex) {
      throw new InternalServerError("Failed to get data of range from database", ex);
    }
    return data;
  }

  @Override
  public Info findById(String id) {
    Bson filter = Filters.and(
        Filters.eq("_id", new ObjectId(id)));

    try {
      return Objects.requireNonNull(this.collection.find(filter).first()).convertTo();
    } catch (Exception ex) {
      throw new InternalServerError("Failed to get detail from database", ex);
    }
  }

  @Override
  public Info insert(Info info) {
    try {
      this.collection.insertOne(MongoInfoEntity.convertFrom(info));
      return info;
    } catch (Exception ex) {
      throw new InternalServerError("Failed to insert", ex);
    }
  }

  @Override
  public Info replace(String id, Info info) {
    MongoInfoEntity mongoInfoEntity = MongoInfoEntity.convertFrom(info);
    Bson filter = Filters.and(
        Filters.eq("_id", mongoInfoEntity.getId()));

    try {
      ReplaceOptions opts = new ReplaceOptions().upsert(true);
      UpdateResult result = this.collection.replaceOne(filter, mongoInfoEntity, opts);
      if (!result.wasAcknowledged()) {
        throw new RuntimeException("Failed to get Acknowledged on replace operation");
      }
      return info;
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
