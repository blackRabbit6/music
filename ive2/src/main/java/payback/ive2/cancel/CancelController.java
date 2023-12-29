package payback.ive2.cancel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import java.util.List; 

import javax.servlet.http.HttpServletRequest;

@Controller
public class CancelController {

    @Autowired
    private CancelService cancelService;

    @GetMapping("/cancel")
    public String showCancelItems(Model model, HttpServletRequest request) {
       // 장바구니 목록을 불러오는 코드
       String userId = (String) request.getSession().getAttribute("userId");
       List<CancelDto> cancelItems = cancelService.getCancelItemsByUserId(userId);
       model.addAttribute("cancelItems", cancelItems);
        return "cancel/cancel";
    }

    @PostMapping("/cancel")
    public String handleCancelRequest(@RequestParam("selectedItems") String[] selectedItems) {
        // 선택된 장바구니 항목들을 삭제하는 코드를 작성합니다.
        // selectedItems에는 선택된 항목들의 productName 또는 다른 식별자가 포함됩니다.
        for (String selectedItem : selectedItems) {
            cancelService.cancelBasketItem(selectedItem);
        }

        // 삭제 후에는 새로 업데이트된 장바구니 목록을 다시 표시할 수 있습니다.
        return "cancel/cancelComplete";
    }
}
