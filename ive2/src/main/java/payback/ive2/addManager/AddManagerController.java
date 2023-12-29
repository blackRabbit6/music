package payback.ive2.addManager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddManagerController {

  private final AddManagerService addManagerService;

  public AddManagerController(AddManagerService addManagerService){
    this.addManagerService = addManagerService;
  }
  @PostMapping("/addManager")
  public String addManager(AddManagerDto addManagerDto, Model model){
    String message = addManagerService.addManager(addManagerDto);
    if (message.equals("complete")) {
      model.addAttribute("message", "등록이 완료되었습니다.");
      return "addManager/complete";
  } else {
      model.addAttribute("message", message);
      return "addManager/addManager"; // 다시 등록 페이지로 돌아감
  }
  }
}
