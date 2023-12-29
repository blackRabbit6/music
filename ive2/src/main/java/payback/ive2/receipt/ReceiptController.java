package payback.ive2.receipt;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReceiptController {
    @Autowired
    private ReceiptService receiptService;

    @GetMapping("/userReceipt")
    public String getUserReceipt(Model model, @RequestParam("userId") String userId) {
        List<ReceiptDto> receiptList = receiptService.getReceiptForUser(userId);
        model.addAttribute("receiptList", receiptList);
        return "userReceipt/userReceipt";
    }

    @GetMapping("/managerReceipt")
    public String getManagerReceipt(Model model) {
        List<ReceiptDto> receiptList = receiptService.getReceiptForManager();
        model.addAttribute("receiptList", receiptList);
        return "managerReceipt/managerReceipt";
    }
}
