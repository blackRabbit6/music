package payback.ive2.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
   @GetMapping("/login")
    public String loginPage() {
        return "login/login"; // "login.html" 템플릿을 렌더링
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register/register"; // "register.html" 템플릿을 렌더링
    }
}
