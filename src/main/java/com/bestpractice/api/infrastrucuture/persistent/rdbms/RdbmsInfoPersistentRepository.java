package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.UUID;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class RdbmsInfoPersistentRepository implements InfoPersistentRepository {
  private final JdbcTemplate jdbcTemplate;

  public RdbmsInfoPersistentRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public String newId() {
    return UUID.randomUUID().toString();
  }

  @Override
  public List<Info> findAll() {
    var sql = "SELECT * FROM infos";
    return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Info.class));
  }

  @Override
  public Info findById(String id) {
    var sql = "SELECT * FROM users WHERE id = ?";
    return jdbcTemplate.queryForObject(sql, new DataClassRowMapper<>(Info.class), id);
  }

  @Override
  public Info insert(Info info) {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    String sql = "INSERT INTO infos (id, title, description) VALUES (?, ?, ?)";

    try {
      jdbcTemplate.update(connection -> preparedStatement(connection, sql, info), keyHolder);
    } catch (org.springframework.dao.DuplicateKeyException e) {
      throw new Conflict(e);
    }
    return info;
  }

  @Override
  public Info replace(String id, Info info) {
    String sql = "UPDATE infos SET title = ?, description = ? WHERE id = ?";
    jdbcTemplate.update(sql, info.getTitle(), info.getDescription(), id);
    return info;
  }

  @Override
  public boolean removeById(String id) {
    String deleteSql = "DELETE FROM infos WHERE id = ?";
    try {
      jdbcTemplate.update(deleteSql, id);
      return true;
    } catch (Exception ignored) {
      return false;
    }
  }

  private PreparedStatement preparedStatement(Connection connection, String sql, Info info) {
    try {
      PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      ps.setString(1, info.getId());
      ps.setString(2, info.getTitle());
      ps.setString(3, info.getDescription());
      return ps;
    } catch (SQLException e) {
      throw new InternalServerError("Failed to run sql.");
    }
  }
}
