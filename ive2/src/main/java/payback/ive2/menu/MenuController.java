package payback.ive2.menu;

import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {
   private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/menu")
    public String showMenu(Model model) {
        List<MenuDto> menuItems = menuService.getMenuItems();
        model.addAttribute("menuItems", menuItems);
        return "menu/menu"; // menu.html 반환
    }
}
