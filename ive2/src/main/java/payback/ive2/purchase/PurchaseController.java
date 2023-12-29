package payback.ive2.purchase;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import payback.ive2.users.User;

@Controller
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/purchase")
    public String showPurchasePage(HttpSession session, Model model) {
        // 사용자 정보를 가져와 사용자가 로그인하지 않은 경우 로그인 페이지로 리디렉션
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "login/login"; // 로그인 페이지로 리디렉션
        }

        // 사용자가 로그인한 경우, 장바구니 아이템을 가져와 모델에 추가
        String userId = user.getId();
        String userName = user.getName();
        List<PurchaseDto> basketList = purchaseService.getBasketItems(userId, userName);
        model.addAttribute("basketList", basketList);

        return "purchase/purchase"; // 구매 페이지로 이동
    }

    @PostMapping("/purchase")
public String processPurchase(HttpSession session, @RequestParam("basketList") String[] basketList, RedirectAttributes redirectAttributes) {
    System.out.println("구매 요청 시작");

    // 구매 로직 처리
    User user = (User) session.getAttribute("user");
    if (user == null) {
        System.out.println("사용자 정보 없음, 로그인 페이지로 리디렉션");
        return "login/login"; // 로그인 페이지로 리디렉션
    }

    String userId = user.getId();
    String userName = user.getName();

    if (basketList == null || basketList.length == 0) {
        System.out.println("선택된 상품 없음, 구매 페이지로 리디렉션");
        redirectAttributes.addFlashAttribute("errorMessage", "선택된 상품이 없습니다.");
        return "purchase/purchaseNo"; // 오류 메시지와 함께 구매 페이지로 리디렉션
    }

    try {
        System.out.println("구매 아이템 처리 시작");
        List<PurchaseDto> purchaseItems = new ArrayList<>();
        for (String item : basketList) {
            String[] itemDetails = item.split(",");
            String productName = itemDetails[0];
            int quantityToBuy = purchaseService.getBasketItems(userId, userName).stream()
            .filter(p -> p.getProductName().equals(productName))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("장바구니에 제품이 없습니다."))
            .getQuantity();
            purchaseItems.add(new PurchaseDto(productName, quantityToBuy));
        }
        

        // 구매 서비스를 통해 아이템을 구매하고 장바구니에서 제거
        System.out.println("구매 서비스 호출 시작");
        purchaseService.purchaseItems(userId, userName, purchaseItems);
        System.out.println("구매 서비스 호출 완료");

        System.out.println("구매 완료 페이지로 리디렉션");
        return "purchase/purchaseComplete"; // 구매 완료 페이지로 리디렉션
    } catch (RuntimeException e) {
        System.out.println("구매 과정에서 오류 발생: " + e.getMessage());
        redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        return "purchase/purchaseNo"; // 오류 메시지와 함께 구매 페이지로 리디렉션
    }
}
    // 다른 요청 처리 메서드도 추가
}
