package payback.ive2.register;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {
  private final RegisterService registerService;

  public RegisterController(RegisterService registerService){
    this.registerService = registerService;
  }

  @PostMapping("/register")
  public String registerUser(RegisterDto registerDto, Model model){
    String message = registerService.registerUser(registerDto);
    if (message.equals("complete")) {
      model.addAttribute("message", "등록이 완료되었습니다.");
      return "register/complete";
  } else {
      model.addAttribute("message", message);
      return "register/register"; // 다시 등록 페이지로 돌아감
  }
  }
}
