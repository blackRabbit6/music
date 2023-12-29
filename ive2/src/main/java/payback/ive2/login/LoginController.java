package payback.ive2.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping("/login")
    public String showLoginPage() {
        return "login/login"; // login.html 페이지를 보여줍니다.
    }

    @PostMapping("/login")
    public String login(@RequestParam String id, @RequestParam String pw, Model model, HttpSession session) {
        String result = loginService.authenticateUser(id, pw, model, session);
        if ("USER".equals(result)) {
            String userName = (String) session.getAttribute("userName");
            model.addAttribute("userName", userName);
            return "userMenu/userMenu";
        } else if ("MANAGER".equals(result)) {
            String managerName = (String) session.getAttribute("managerName"); // 오타 수정
            model.addAttribute("managerName", managerName);
            return "managerMenu/managerMenu";
        } else {
            model.addAttribute("message", "로그인 실패: id 혹은 pw 오류");
            return "login/login"; // 다시 로그인 페이지로 리디렉션
        }
    }

}