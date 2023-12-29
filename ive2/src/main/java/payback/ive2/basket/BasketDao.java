package payback.ive2.basket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class BasketDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getMenu() {
        String query = "SELECT productname, quantity, id, name FROM BASKET";
        return jdbcTemplate.queryForList(query);
    }

    public boolean checkProductInBasket(String productname, String id) {
        String query = "SELECT COUNT(*) FROM BASKET WHERE productname = ? AND id = ?";
        int count = jdbcTemplate.queryForObject(query, Integer.class, productname, id);
        return count > 0;
    }
    
    public void updateBasketQuantity(String productname, int quantity, String id) {
        String query = "UPDATE BASKET SET quantity = ? WHERE productname = ? AND id = ?";
        jdbcTemplate.update(query, quantity, productname, id);
    }

    public void addToBasket(String productname, int quantity, String id, String name) {
        String query = "INSERT INTO BASKET (productname, quantity, id, name) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(query, productname, quantity, id, name);
    }
    
    public int getQuantityForProduct(String productname, String id) {
        String query = "SELECT quantity FROM BASKET WHERE productname = ? AND id = ?";
        return jdbcTemplate.queryForObject(query, Integer.class, productname, id);
    }
    
    // 다른 DAO 메서드를 필요에 따라 구현하세요.
}