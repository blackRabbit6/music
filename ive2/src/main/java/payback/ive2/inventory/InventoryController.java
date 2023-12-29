package payback.ive2.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/add")
    public String showInventoryForm() {
        return "inventory/inventory";
    }

    @PostMapping("/add")
    public String processInventoryForm(
            @RequestParam("productName") String productName,
            @RequestParam(value = "productId", required = false) Integer productId,
            @RequestParam(value = "quantity", required = false) Integer quantity,
            Model model,
            @RequestParam String action) {

        if ("search".equals(action)) {
            // "제품명 검색" 버튼을 클릭한 경우
            String searchResult = inventoryService.searchProduct(productName);

            if ("existing".equals(searchResult)) {
                // 이미 존재하는 제품 - 수량 입력 폼 표시
                model.addAttribute("idDuplicate", false);
                model.addAttribute("existing", searchResult);
                model.addAttribute("productName", productName);
                model.addAttribute("quantityInputVisible", true);
                return "inventory/inventory";
            } else if ("not_existing".equals(searchResult)) {
                // 존재하지 않는 제품 - ID 입력 폼 표시
                model.addAttribute("idDuplicate", false);
                model.addAttribute("existing", "not_existing");
                model.addAttribute("productName", productName);
                model.addAttribute("productIdInputVisible", true);
                return "inventory/inventory";
            }
        } else if ("idSearch".equals(action)) {
            // "제품 ID 검색" 버튼을 클릭한 경우
            if (productId == null) {
                // productId가 null인 경우
                // ID를 입력하지 않았을 때 처리를 추가합니다.
                model.addAttribute("idDuplicate", false);
                model.addAttribute("existing", "not_existing");
                model.addAttribute("productName", productName);
                model.addAttribute("error", "제품 ID를 입력하세요.");
                return "inventory/inventory";
            } else {
                // productId가 null이 아닌 경우
                String idCheckResult = inventoryService.searchProductById(productId);
                if ("existing".equals(idCheckResult)) {
                    // 이미 존재하는 제품 ID
                    model.addAttribute("idDuplicate", true);
                    model.addAttribute("existing", "not_existing");
                    model.addAttribute("productName", productName);
                    model.addAttribute("error", "이미 존재하는 제품 ID입니다. 다른 ID를 입력하세요.");
                    return "inventory/inventory";
                } else {
                    // 존재하지 않는 제품 ID
                    // 여기에서 "제품 ID 검색"에 대한 추가 로직을 구현합니다.
                    // 제품 ID를 저장하고 "제품 ID 검색" 버튼을 사라지게 만듭니다.
                    model.addAttribute("productId", productId);
                    model.addAttribute("productName", productName); // 제품명을 다시 추가
                    model.addAttribute("idSearchButtonVisible", false);
                    model.addAttribute("quantityInputVisible", true); // 수량 입력란 표시
                    model.addAttribute("addButtonVisible", true); // 추가 버튼 표시
                    return "inventory/inventory";
                }
            }
        }  else if ("add".equals(action)) {
            try {
                InventoryDto inventoryDto = new InventoryDto();
                inventoryDto.setProductName(productName);
                inventoryDto.setQuantity(quantity);

                if (productId != null) { // 제품 ID가 null이 아닐 때만 설정
                    inventoryDto.setProductId(productId);
                }

                String result = inventoryService.addOrUpdateProduct(inventoryDto);

                if ("success".equals(result)) {
                    // 업데이트가 성공한 경우 complete 페이지로 리다이렉트
                    return "inventory/complete";
                }
            } catch (Exception e) {
                System.out.println("에러 발생: " + e.getMessage());
                model.addAttribute("error", "에러 발생: " + e.getMessage());
                return "error";
            }
        }

        // 결과 페이지로 이동 (검색 또는 추가 폼)
        return "inventory/inventory";
    }
}
