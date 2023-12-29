package payback.ive2.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InventoryDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public InventoryDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String searchProduct(String productName) {
        String searchProductSql = "SELECT COUNT(*) FROM MENU WHERE PRODUCTNAME = ?";

        int productCount = jdbcTemplate.queryForObject(searchProductSql, Integer.class, productName);

        if (productCount > 0) {
            return "existing";
        } else {
            return "not_existing";
        }
    }
    
    public String searchProductById(Integer productId) {
        String searchProductSql = "SELECT COUNT(*) FROM MENU WHERE PRODUCTID = ?";

        int productCount = jdbcTemplate.queryForObject(searchProductSql, Integer.class, productId);

        if (productCount > 0) {
            return "existing";
        } else {
            return "not_existing";
        }
    }

    public String addQuantity(String productName, int productId, int quantity) {
        String searchProductSql = "SELECT COUNT(*) FROM MENU WHERE PRODUCTNAME = ?";
        int productCount = jdbcTemplate.queryForObject(searchProductSql, Integer.class, productName);

        if (productCount > 0) {
            // 제품이 이미 존재하면 수량 업데이트
            String updateQuantitySql = "UPDATE MENU SET QUANTITY = QUANTITY + ? WHERE PRODUCTNAME = ?";
            jdbcTemplate.update(updateQuantitySql, quantity, productName);
            return "success";
        } else {
            String checkProductIdSql = "SELECT COUNT(*) FROM MENU WHERE PRODUCTID = ?";
            int idCount = jdbcTemplate.queryForObject(checkProductIdSql, Integer.class, productId);

            if (idCount > 0) {
                return "existing_product_id";
            } else {
                // 제품이 존재하지 않고 제품 ID도 존재하지 않는 경우 제품 추가
                String insertProductSql = "INSERT INTO MENU(PRODUCTNAME, QUANTITY, PRODUCTID) VALUES (?, ?, ?)";
                jdbcTemplate.update(insertProductSql, productName, quantity, productId);
                return "success";
            }
        }
    }
}
