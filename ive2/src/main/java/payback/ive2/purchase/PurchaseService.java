package payback.ive2.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PurchaseService {
    @Autowired
    private PurchaseDao purchaseDao;

    @Transactional
    public List<PurchaseDto> getBasketItems(String userId, String userName) {
    List<Map<String, Object>> basketItems = purchaseDao.getBasketItems(userId, userName);
    List<PurchaseDto> purchaseDtos = new ArrayList<>();

    for (Map<String, Object> basketItem : basketItems) {
        String productName = (String) basketItem.get("PRODUCTNAME");
        BigDecimal quantityBigDecimal = (BigDecimal) basketItem.get("QUANTITY");
        int quantity = quantityBigDecimal.intValue();
        PurchaseDto purchaseDto = new PurchaseDto(productName, quantity);
        purchaseDtos.add(purchaseDto);
    }

    return purchaseDtos;
}


@Transactional
public void purchaseItems(String userId, String userName, List<PurchaseDto> purchaseItems) {
    for (PurchaseDto item : purchaseItems) {
        String productName = item.getProductName();
        int quantityToBuy = item.getQuantity();

        // 메뉴 테이블에서 해당 제품의 재고 수량 조회
        int menuQuantity;
        try {
            System.out.println("메뉴 수량 조회 시작");
            menuQuantity = purchaseDao.getMenuQuantity(productName);
            System.out.println("메뉴 수량 조회 완료");
        } catch (Exception e) {
            throw new RuntimeException("제품 " + productName + "의 재고 수량을 가져오는 데 실패했습니다: " + e.getMessage());
        }

        if (menuQuantity < quantityToBuy) {
            throw new RuntimeException("제품 " + productName + "의 재고가 부족합니다. 재고 수량: " + menuQuantity + ", 구매 요청 수량: " + quantityToBuy);
        }

        try {
            System.out.println("재고 업데이트 및 구매 과정 시작");

            // 영수증에 추가
            purchaseDao.insertPurchaseReceipt(userId, userName, productName, quantityToBuy);
            System.out.println("영수증 추가 완료");

            // 재고 업데이트
            purchaseDao.updateMenuQuantity(productName, quantityToBuy);
            System.out.println("재고 업데이트 완료");

            // 장바구니에서 아이템 제거
            purchaseDao.deleteBasketItems(userId, userName, productName);
            System.out.println("장바구니 아이템 제거 완료");

        } catch (Exception e) {
            throw new RuntimeException("제품 " + productName + "의 구매 과정에서 오류가 발생했습니다: " + e.getMessage());
        }
    }
}


    // 다른 서비스 메서드 추가 가능
}
