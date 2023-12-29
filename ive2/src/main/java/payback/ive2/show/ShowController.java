package payback.ive2.show;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowController {
    @Autowired
    private ShowService showService;

    @GetMapping("/show")
    public String showItems(Model model, HttpServletRequest request) {
        String userId = (String) request.getSession().getAttribute("userId");
        List<ShowDto> showItems = showService.getShowItemsByUserId(userId);
        model.addAttribute("showItems", showItems);
        return "show/show";
    }

    // 다른 핸들러 메서드를 필요에 따라 구현하세요.
}