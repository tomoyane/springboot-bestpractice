package com.bestpractice.api.infrastrucuture.persistent.cassandra;

import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.bindMarker;
import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.deleteFrom;
import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.insertInto;
import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.selectFrom;
import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.update;
import static com.datastax.oss.driver.api.querybuilder.update.Assignment.setColumn;

import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.querybuilder.delete.Delete;
import com.datastax.oss.driver.api.querybuilder.insert.RegularInsert;
import com.datastax.oss.driver.api.querybuilder.select.Select;
import com.datastax.oss.driver.api.querybuilder.update.Update;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CassandraInfoPersistentRepository implements InfoPersistentRepository {

  private final CqlSession session;
  private final Select selectInfo = selectFrom("infos")
      .all();

  private final Select selectInfoWithWhere = selectFrom("infos")
      .all().whereColumn("id").isEqualTo(bindMarker());

  private final RegularInsert insertInfo = insertInto("infos")
      .value("id", bindMarker())
      .value("title", bindMarker())
      .value("description", bindMarker());

  private final Update updateInfo = update("infos")
      .set(setColumn("title", bindMarker()),
          setColumn("description", bindMarker()))
      .whereColumn("id").isEqualTo(bindMarker());

  private final Delete deleteInfo = deleteFrom("infos")
      .whereColumn("id").isEqualTo(bindMarker());

  public CassandraInfoPersistentRepository(CqlSession session) {
    this.session = session;
  }

  @Override
  public String newId() {
    return UUID.randomUUID().toString();
  }

  @Override
  public List<Info> findAll() {
    // Limit is required if cassandra
    PreparedStatement preparedSelectInfo = session.prepare(selectInfo.build());
    ResultSet resultSet = session.execute(preparedSelectInfo.bind());

    List<Info> infos = new ArrayList<>();
    for (Row row : resultSet) {
      Info info = new Info();
      info.setId(row.getString("id"));
      info.setTitle(row.getString("title"));
      info.setDescription(row.getString("description"));
      infos.add(info);
    }
    return infos;
  }

  @Override
  public Info findById(String id) {
    PreparedStatement preparedSelectInfo = session.prepare(selectInfoWithWhere.build());
    ResultSet resultSet = session.execute(preparedSelectInfo.bind(id));

    Row row = resultSet.one();
    if (row == null) {
      return null;
    }

    Info info = new Info();
    info.setId(row.getString("id"));
    info.setTitle(row.getString("title"));
    info.setDescription(row.getString("description"));
    return info;
  }

  @Override
  public Info insert(Info info) {
    PreparedStatement preparedSelectInfo = session.prepare(insertInfo.build());
    ResultSet resultSet = session.execute(
        preparedSelectInfo.bind(info.getId(), info.getTitle(), info.getDescription()));

    if (!resultSet.wasApplied()) {
      return null;
    }
    return info;
  }

  @Override
  public Info replace(String id, Info info) {
    PreparedStatement preparedSelectInfo = session.prepare(updateInfo.build());
    ResultSet resultSet = session.execute(
        preparedSelectInfo.bind(info.getTitle(), info.getDescription(), info.getId()));

    if (!resultSet.wasApplied()) {
      return null;
    }
    return info;
  }

  @Override
  public boolean removeById(String id) {
    PreparedStatement preparedSelectInfo = session.prepare(deleteInfo.build());
    ResultSet resultSet = session.execute(
        preparedSelectInfo.bind(id));

    if (!resultSet.wasApplied()) {
      return false;
    }
    return true;
  }
}
