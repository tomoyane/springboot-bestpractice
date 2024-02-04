package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;

import java.util.UUID;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class RdbmsUserPersistentRepository implements UserPersistentRepository {
  private final JdbcTemplate jdbcTemplate;

  public RdbmsUserPersistentRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public String newId() {
    return UUID.randomUUID().toString();
  }

  @Override
  public User findByEmail(String email) {
    var sql = "SELECT * FROM users WHERE email = ?";
    return jdbcTemplate.queryForObject(sql, new DataClassRowMapper<>(User.class), email);
  }

  @Override
  public User findById(String id) {
    var sql = "SELECT * FROM users WHERE id = ?";
    return jdbcTemplate.queryForObject(sql, new DataClassRowMapper<>(User.class), id);
  }

  @Override
  public User insert(User user) {
    String sql = "INSERT INTO users (id, username, email, password) VALUES (?, ?, ?, ?)";

    try {
      jdbcTemplate.update(
          sql,
          user.getId(),
          user.getUsername(),
          user.getEmail(),
          user.getPassword()
      );
    } catch (org.springframework.dao.DuplicateKeyException e) {
      throw new Conflict(e);
    }
    return user;
  }

  @Override
  public User replace(String id, User user) {
    String updateSql = "UPDATE users SET username = ?, email = ?, password = ? WHERE id = ?";
    jdbcTemplate.update(
        updateSql,
        user.getUsername(),
        user.getEmail(),
        user.getPassword(),
        id
    );
    return user;
  }

  @Override
  public boolean removeById(String id) {
    String deleteSql = "DELETE FROM users WHERE id = ?";

    try {
      jdbcTemplate.update(deleteSql, id);
      return true;
    } catch (Exception ignored) {
      return false;
    }
  }
}
