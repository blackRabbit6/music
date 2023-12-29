package payback.ive2.cancel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CancelService {

    @Autowired
    private CancelDao cancelDao;

    public List<CancelDto> getCancelItemsByUserId(String userId) {
      // 여기에서 데이터베이스에서 특정 사용자의 장바구니 항목을 가져오는 코드를 작성해야 합니다.
      // userId를 사용하여 필터링하여 해당 사용자의 장바구니 항목만 가져와야 합니다.
      List<CancelDto> cancelItems = cancelDao.getCancelItemsByUserId(userId);
      return cancelItems;
  }

    public void cancelBasketItem(String productName) {
        cancelDao.cancelBasketItem(productName);
    }
}
