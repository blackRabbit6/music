package payback.ive2.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {
    private final InventoryDao inventoryDao;

    @Autowired
    public InventoryService(InventoryDao inventoryDao) {
        this.inventoryDao = inventoryDao;
    }

    public String searchProduct(String productName) {
        // 이 메서드는 DAO를 통해 데이터베이스에서 제품을 검색합니다.
        return inventoryDao.searchProduct(productName);
    }

    public String searchProductById(Integer productId) {
      return inventoryDao.searchProductById(productId);
  }

    public String addOrUpdateProduct(InventoryDto inventoryDto) {
        // 이 메서드는 제품 정보를 추가하거나 업데이트합니다.
        String productName = inventoryDto.getProductName();
        int productId = inventoryDto.getProductId();
        int quantity = inventoryDto.getQuantity();

        return inventoryDao.addQuantity(productName, productId, quantity);
    }
}
