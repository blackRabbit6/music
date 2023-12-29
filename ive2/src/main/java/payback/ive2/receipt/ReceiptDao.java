package payback.ive2.receipt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ReceiptDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getReceiptItemsByUserId(String userId) {
        String sql = "SELECT NAME, PRODUCTNAME, QUANTITY, BUYDAY, PURCHASEID FROM RECEIPT WHERE ID = ?";
        return jdbcTemplate.queryForList(sql, userId);
    }

    // 관리자에 따른 쿼리문
    public List<Map<String, Object>> getReceiptItemsForManager() {
      String managerSql = "SELECT NAME, PRODUCTNAME, QUANTITY, BUYDAY, PURCHASEID FROM RECEIPT";
      return jdbcTemplate.queryForList(managerSql);
  }
    // 다른 메서드 필요에 따라 구현
}
