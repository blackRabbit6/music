package payback.ive2.addManager;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AddManagerDao {

  private final JdbcTemplate jdbcTemplate;

  public AddManagerDao(DataSource dataSource) {
    this.jdbcTemplate = new JdbcTemplate(dataSource);
  }

  public String addManager(AddManagerDto addManagerDto) {
    String checkUserSql = "SELECT COUNT(*) FROM (SELECT id FROM USERS UNION SELECT id FROM MANAGER) WHERE id=?";
    int managerCount = jdbcTemplate.queryForObject(checkUserSql, Integer.class, addManagerDto.getId());

    if (managerCount > 0) {
      return "managerMenu";
    } else {
      String insertSql = "INSERT INTO MANAGER(id, pw, name) VALUES (?, ?, ?)";
      int rowsAffected = jdbcTemplate.update(insertSql, addManagerDto.getId(), addManagerDto.getPw(),
          addManagerDto.getName());

      if (rowsAffected > 0) {
        return "complete";
      } else {
        return "managerMenu";
      }
    }
  }
}
