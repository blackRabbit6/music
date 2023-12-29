package payback.ive2.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class PurchaseDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getBasketItems(String userId, String userName) {
        String sql = "SELECT productname, quantity FROM BASKET WHERE id = ? AND name = ?";
        return jdbcTemplate.queryForList(sql, userId, userName);
    } 
    
    public int getMenuQuantity(String productName) {
        String sql = "SELECT quantity FROM MENU WHERE productname = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, productName);
    }
    
    public void updateBasketItem(PurchaseEntity item) {
        String sql = "UPDATE BASKET SET quantity = ? WHERE id = ? AND name = ? AND productname = ?";
        jdbcTemplate.update(sql, item.getQuantity(), item.getUserId(), item.getUserName(), item.getProductName());
    }

    public void insertPurchaseReceipt(String userId, String userName, String productName, int quantity) {
        String sql = "INSERT INTO RECEIPT (id, name, productname, quantity, buyday, purchaseid) " +
                     "VALUES (?, ?, ?, ?, CURRENT_DATE, SEQUENCE1.NEXTVAL)";
        jdbcTemplate.update(sql, userId, userName, productName, quantity);
    }

    public void updateMenuQuantity(String productName, int quantityToBuy) {
        String sql = "UPDATE MENU SET quantity = quantity - ? WHERE productname = ? AND quantity >= ?";
        jdbcTemplate.update(sql, quantityToBuy, productName, quantityToBuy);
    }

    public void deleteBasketItems(String userId, String userName, String productName) {
        String sql = "DELETE FROM BASKET WHERE id = ? AND name = ? AND productname = ?";
        jdbcTemplate.update(sql, userId, userName, productName);
    }

    // 다른 데이터베이스 액세스 메서드도 추가

    // 예를 들어, 장바구니 비우기 메서드 추가 가능
}
