package payback.ive2.basket;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import payback.ive2.menu.MenuDto;
import payback.ive2.menu.MenuService;

@Controller
public class BasketController {

    @Autowired
    private BasketService basketService;
    @Autowired
    private MenuService menuService;

    @GetMapping("/basket")
    public String showBasket(Model model, HttpServletRequest request) {
        // 사용자 정보(ID와 이름)를 세션에서 가져옵니다.
        String userId = (String) request.getSession().getAttribute("userId");
        String userName = (String) request.getSession().getAttribute("userName");
        
        List<MenuDto> menuItems = menuService.getMenuItems();
        model.addAttribute("menuItems", menuItems);
        model.addAttribute("userId", userId); // 사용자 ID를 모델에 추가
        model.addAttribute("userName", userName); // 사용자 이름을 모델에 추가
        return "basket";
    }

    @PostMapping("/basket")
    public String addToBasket(@RequestParam Map<String, String> requestParams, HttpServletRequest request){ 
        String userId = (String) request.getSession().getAttribute("userId");
        String userName = (String) request.getSession().getAttribute("userName");
    
        for (Map.Entry<String, String> entry : requestParams.entrySet()) {
            String productName = entry.getKey();
            int quantity = Integer.parseInt(entry.getValue());
    
            basketService.addToBasket(productName, quantity, userId, userName);
    }
    return "basket/basket";
}
}