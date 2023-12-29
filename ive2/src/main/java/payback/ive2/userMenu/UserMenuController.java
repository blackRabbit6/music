package payback.ive2.userMenu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import payback.ive2.basket.BasketService;
import payback.ive2.cancel.CancelDto;
import payback.ive2.cancel.CancelService;
import payback.ive2.menu.MenuDto;
import payback.ive2.menu.MenuService;
import payback.ive2.purchase.PurchaseDto;
import payback.ive2.purchase.PurchaseService;
import payback.ive2.receipt.ReceiptDto;
import payback.ive2.receipt.ReceiptService;
import payback.ive2.show.ShowDto;
import payback.ive2.show.ShowService;

import org.springframework.ui.Model;

import java.util.List; 

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserMenuController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private BasketService basketService;
    @Autowired
    private ShowService showService;
    @Autowired
    private CancelService cancelService;
    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    private ReceiptService receiptService;

    @GetMapping("/userMenu")
    public String showMenu(Model model){
        List<MenuDto> menuItems = menuService.getMenuItems();
        model.addAttribute("menuItems", menuItems);
        return "menu/menu";
    }

    @PostMapping("/userMenu")
    public String handleRequest(@RequestParam String action, Model model, HttpServletRequest request) {
        // 메뉴판 보기 클릭시
        if (action.equals("메뉴판 보기")) {
            List<MenuDto> menuItems = menuService.getMenuItems();
            model.addAttribute("menuItems", menuItems);
            return "redirect:/menu"; // menu.html 반환
        }   else if (action.equals("장바구니 담기")) {
            // 장바구니 담기 로직 구현
            List<MenuDto> menuItems = menuService.getMenuItems();
            model.addAttribute("menuItems", menuItems);
            return "basket/basket";
        } else if(action.equals("장바구니 보기")){
            // 장바구니 보기 로직 구현
            String userId = (String) request.getSession().getAttribute("userId");
            List<ShowDto> showItems = showService.getShowItemsByUserId(userId);
            model.addAttribute("showItems", showItems);
            return "show/show";
        } else if(action.equals("충전")){
            
            return "charge/charge";
        } else if(action.equals("취소")){
            String userId = (String) request.getSession().getAttribute("userId");
            List<CancelDto> cancelItems = cancelService.getCancelItemsByUserId(userId);
            model.addAttribute("cancelItems", cancelItems);
            return "cancel/cancel";
        } else if (action.equals("구매")) {
            String userId = (String) request.getSession().getAttribute("userId");
            String userName = (String) request.getSession().getAttribute("userName");
            List<PurchaseDto> basketList = purchaseService.getBasketItems(userId,userName);
            model.addAttribute("basketList", basketList);
            return "purchase/purchase"; 
        }else if (action.equals("구매내역 보기")) {
            String userId = (String) request.getSession().getAttribute("userId");
            List<ReceiptDto> receiptList = receiptService.getReceiptForUser(userId);
            model.addAttribute("receiptList", receiptList);
            return "userReceipt/userReceipt";
        } 
        else if (action.equals("종료")) {
            request.getSession().invalidate(); // 세션 초기화
            return "redirect:/"; // 초기 페이지로 리다이렉트

        }  else {
            return "userMenu"; // 그밖의 action은 userMenu.html로 다시 돌아감
        }
    }
    
    @PostMapping("/addToBasket")
    public String addToBasket(@RequestParam("productNames") String[] productNames, @RequestParam("quantities") String[] quantityStrings, HttpServletRequest request) {
    // 사용자 정보(ID와 이름)를 세션에서 가져옵니다.
    String userId = (String) request.getSession().getAttribute("userId");
    String userName = (String) request.getSession().getAttribute("userName");
    
    for (int i = 0; i < productNames.length; i++) {
        String productName = productNames[i];
        String quantityString = quantityStrings[i];
        
        // 수량 문자열이 비어있지 않은 경우에만 파싱하고 업데이트
        if (!quantityString.isEmpty()) {
            int quantity = Integer.parseInt(quantityString);
            basketService.addToBasket(productName, quantity, userId, userName);
        }
    }
    
    return "basket/basketComplete";
}

}

