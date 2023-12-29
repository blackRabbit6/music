package payback.ive2.receipt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ReceiptService {
    @Autowired
    private ReceiptDao receiptDao;

    @Transactional
    public List<ReceiptDto> getReceiptForUser(String userId) {
        List<Map<String, Object>> receiptItems = receiptDao.getReceiptItemsByUserId(userId);
        List<ReceiptDto> receiptDtoList = new ArrayList<>();

        for (Map<String, Object> receiptItem : receiptItems) {
            ReceiptDto receiptDto = new ReceiptDto();
            receiptDto.setName((String) receiptItem.get("NAME"));
            receiptDto.setProductName((String) receiptItem.get("PRODUCTNAME"));

            // Convert QUANTITY to BigDecimal and then to int
            BigDecimal quantityBigDecimal = new BigDecimal(String.valueOf(receiptItem.get("QUANTITY")));
            int quantity = quantityBigDecimal.intValue();
            receiptDto.setQuantity(quantity);

            receiptDto.setBuyday((Date) receiptItem.get("BUYDAY"));
            receiptDto.setPurchaseId((String) receiptItem.get("PURCHASEID"));
            receiptDtoList.add(receiptDto);
        }

        return receiptDtoList;
    }

    @Transactional
    public List<ReceiptDto> getReceiptForManager() {
        List<Map<String, Object>> receiptItems = receiptDao.getReceiptItemsForManager();
        List<ReceiptDto> receiptDtoList = new ArrayList<>();

        for (Map<String, Object> receiptItem : receiptItems) {
            ReceiptDto receiptDto = new ReceiptDto();
            receiptDto.setName((String) receiptItem.get("NAME"));
            receiptDto.setProductName((String) receiptItem.get("PRODUCTNAME"));

            // Convert QUANTITY to BigDecimal and then to int
            BigDecimal quantityBigDecimal = new BigDecimal(String.valueOf(receiptItem.get("QUANTITY")));
            int quantity = quantityBigDecimal.intValue();
            receiptDto.setQuantity(quantity);

            receiptDto.setBuyday((Date) receiptItem.get("BUYDAY"));
            receiptDto.setPurchaseId((String) receiptItem.get("PURCHASEID"));
            receiptDtoList.add(receiptDto);
        }

        return receiptDtoList;
    }
}
