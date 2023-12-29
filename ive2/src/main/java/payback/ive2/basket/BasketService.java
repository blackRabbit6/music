package payback.ive2.basket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import payback.ive2.menu.MenuDto;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

@Service
public class BasketService {

    @Autowired
    private BasketDao basketDao;

    public List<MenuDto> getMenu() {
      List<MenuDto> menu = new ArrayList<>();
      List<Map<String, Object>> basketData = basketDao.getMenu();

      for (Map<String, Object> basketMap : basketData) {
          String productname = (String) basketMap.get("productname");
          int quantity = (int) basketMap.get("quantity");
          MenuDto menuDto = new MenuDto(productname, quantity);
          menu.add(menuDto);
      }

      return menu;
  }

  public void addToBasket(String productname, int quantity, String id, String name) {
    boolean productExistsInBasket = basketDao.checkProductInBasket(productname, id);

    if (productExistsInBasket) {
        // 제품이 이미 장바구니에 있는 경우 수량을 업데이트합니다.
        int currentQuantity = basketDao.getQuantityForProduct(productname, id);
        int newQuantity = currentQuantity + quantity;
        basketDao.updateBasketQuantity(productname, newQuantity, id);
    } else {
        basketDao.addToBasket(productname, quantity, id, name);
    }
}

    // 다른 비즈니스 로직 메서드를 필요에 따라 구현하세요.
}