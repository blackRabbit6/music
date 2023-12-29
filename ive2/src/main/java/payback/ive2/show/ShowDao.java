package payback.ive2.show;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ShowDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getShowItemsByUserId(String userId) {
        String sql = "SELECT PRODUCTNAME, QUANTITY FROM BASKET WHERE ID = ?";
        return jdbcTemplate.queryForList(sql, userId);
    }

    // 다른 메서드 필요에 따라 구현
}