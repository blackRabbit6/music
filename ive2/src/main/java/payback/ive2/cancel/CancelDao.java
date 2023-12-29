package payback.ive2.cancel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CancelDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<CancelDto> getCancelItemsByUserId(String userId) {
        String sql = "SELECT PRODUCTNAME, QUANTITY FROM BASKET WHERE ID = ?";
        Object[] params = { userId };

        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, params);

        List<CancelDto> cancelItems = new ArrayList<>();

        for (Map<String, Object> row : rows) {
            CancelDto cancelDto = new CancelDto();
            cancelDto.setProductName((String) row.get("PRODUCTNAME"));
            BigDecimal quantityBigDecimal = (BigDecimal) row.get("QUANTITY");
            int quantity = quantityBigDecimal.intValue(); // BigDecimal를 int로 변환
            cancelDto.setQuantity(quantity);
            // 필요한 다른 필드에 대한 매핑 작업 추가

            cancelItems.add(cancelDto);
        }

        return cancelItems;
    }

    public void cancelBasketItem(String productName) {
        String sql = "DELETE FROM BASKET WHERE PRODUCTNAME = ?";
        jdbcTemplate.update(sql, productName);
    }
}
