package com.bestpractice.api.infrastrucuture.persistent.rdbms;

import com.bestpractice.api.infrastrucuture.entity.User;
import com.bestpractice.api.infrastrucuture.persistent.UserPersistentRepository;

import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class RdbmsUserPersistentRepository implements UserPersistentRepository {
  private final JdbcTemplate jdbcTemplate;

  public RdbmsUserPersistentRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public User findByEmail(String email) {
    var sql = "SELECT * FROM users WHERE email = ?";
    return jdbcTemplate.queryForObject(sql, new DataClassRowMapper<>(User.class), email);
  }

  @Override
  public User findByIdAndUuid(long id, String uuid) {
    var sql = "SELECT * FROM users WHERE id = ? AND uuid = ?";
    return jdbcTemplate.queryForObject(sql, new DataClassRowMapper<>(User.class), id, uuid);
  }

  @Override
  public User insert(User user) {
    String sql = "INSERT INTO users (uuid, username, email, password) VALUES (?, ?, ?, ?)";
    jdbcTemplate.update(
        sql,
        user.getUuid(),
        user.getUsername(),
        user.getEmail(),
        user.getPassword()
    );
    return user;
  }

  @Override
  public User replace(long id, User user) {
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
  public boolean removeById(long id) {
    String deleteSql = "DELETE FROM users WHERE id = ?";

    try {
      jdbcTemplate.update(deleteSql, id);
      return true;
    } catch (Exception ignored) {
      return false;
    }
  }
}
