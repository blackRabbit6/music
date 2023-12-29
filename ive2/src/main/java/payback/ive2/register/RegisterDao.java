package payback.ive2.register;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RegisterDao {
   private final JdbcTemplate jdbcTemplate;

    public RegisterDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public String registerUser(RegisterDto registerDto) {
        String checkUserSql = "SELECT COUNT(*) FROM (SELECT id FROM USERS UNION SELECT id FROM MANAGER) WHERE id=?";
        int userCount = jdbcTemplate.queryForObject(checkUserSql, Integer.class, registerDto.getId());

        if(userCount>0){
            return "userMenu";
        }
        else{
        String insertSql = "INSERT INTO USERS(id, pw, name) VALUES (?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(insertSql, registerDto.getId(), registerDto.getPw(), registerDto.getName());

        if (rowsAffected > 0) {
            return "complete";
        } else {
            return "userMenu";
        }
    }
    }
}
