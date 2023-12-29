package payback.ive2.managerMenu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import payback.ive2.menu.MenuDto;
import payback.ive2.menu.MenuService;
import payback.ive2.receipt.ReceiptDto;
import payback.ive2.receipt.ReceiptService;

import org.springframework.ui.Model;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ManagerMenuController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private ReceiptService receiptService;


    @GetMapping("/managerMenu")
     public String showMenu(Model model){
        List<MenuDto> menuItems = menuService.getMenuItems();
        model.addAttribute("menuItems", menuItems);
        return "menu/menu";
    }

    @PostMapping("/managerMenu")
    // /manager 에서 어떤 action 을 선택하냐에 따라 작동되는 로직이 다름
    public String handleRequest(@RequestParam String action, Model model, HttpServletRequest request) {

        // 메뉴판 보기 클릭시
        if (action.equals("메뉴판 보기")) {
            List<MenuDto> menuItems = menuService.getMenuItems();
            model.addAttribute("menuItems", menuItems);
            return "redirect:/menu"; // menu.html 반환
        }   else if(action.equals("구매내역 보기")){
            List<ReceiptDto> receiptList = receiptService.getReceiptForManager();
            model.addAttribute("receiptList", receiptList);
            return "managerReceipt/managerReceipt";
        }   else if(action.equals("재고수량 추가")){
            //로직추가
            return "inventory/inventory";
        }else if(action.equals("관리자 추가")){
            return "addManager/addManager";
        }
            else if (action.equals("종료")) {
            request.getSession().invalidate(); // 세션 초기화
            return "redirect:/"; // 초기 페이지로 리다이렉트

        } else {
            return "managerMenu/managerMenu"; // 그밖의 action은 managerMenu.html, 구매내역 보기, 관리자 추가 로직 필요함
        }
    }
}
