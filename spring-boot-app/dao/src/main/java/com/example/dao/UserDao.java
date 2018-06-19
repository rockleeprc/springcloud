package com.example.dao;

import com.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public int getDepartmentCount(Integer departmentId) {
        String sql = "select count(*) from user where department_id=:deptId";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("deptId", departmentId);
        Integer count = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
        return count;
    }

    /**
     * 根据id查询
     *
     * @param userid
     * @return
     */
    public User getUser(Integer userid) {
        String sql = "select * from user where id=?";
        User user = jdbcTemplate.queryForObject(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getInt("department_id"));
                return user;
            }
        }, userid);
        return user;
    }

    public List<User> getUserByDepartmentId(Integer departmentId) {
        String sql = "select * from user where department_id=?";
        List<User> userList = jdbcTemplate.query(sql, new UserRowMapper(), departmentId);
        return userList;
    }

    public boolean update(User user) {
        String sql = "update user set name=?,department_id=? where id=?";
        int result = jdbcTemplate.update(sql, user.getName(), user.getDepartmentId(), user.getId());
        return result > 0;
    }

    public int save(User user) {
        String sql = "insert into user (name,department_id) value (?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
                ps.setString(1, user.getName());
                ps.setInt(2, user.getDepartmentId());
                return ps;
            }
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = new User(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("department_id"),
                    resultSet.getDate("create_time"));
            return user;
        }
    }
}
