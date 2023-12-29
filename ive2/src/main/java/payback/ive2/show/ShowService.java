package payback.ive2.show;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ShowService {

    @Autowired
    private ShowDao showDao;

    public List<ShowDto> getShowItemsByUserId(String userId) {
        List<Map<String, Object>> result = showDao.getShowItemsByUserId(userId);
        List<ShowDto> showItems = new ArrayList<>();

         for (Map<String, Object> row : result) {
          String productName = (String) row.get("PRODUCTNAME");
          BigDecimal quantityBigDecimal = (BigDecimal) row.get("QUANTITY");
          int quantity = quantityBigDecimal.intValue(); // BigDecimal를 int로 변환
          ShowDto showItem = new ShowDto(productName, quantity, userId);
          showItems.add(showItem);
        }

        return showItems;
    }

    // 다른 비즈니스 로직 메서드를 필요에 따라 구현하세요.
}