package payback.ive2.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

@Service
public class LoginService {

    @Autowired
    private LoginDao loginDao;

    public String authenticateUser(String id, String pw, Model model, HttpSession session) {
        LoginDao.UserTypeAndName userTypeAndName = loginDao.getUserTypeAndName(id, pw);

        if (userTypeAndName != null) {
            if ("USER".equals(userTypeAndName.type)) {
                session.setAttribute("userType", "user");
                session.setAttribute("userName", userTypeAndName.name);
                session.setAttribute("userId", id);
                return "USER";
            } else if ("MANAGER".equals(userTypeAndName.type)) {
                session.setAttribute("managerType", "manager");
                session.setAttribute("managerName", userTypeAndName.name);
                session.setAttribute("managerId", id);
                return "MANAGER";
            }
        }

        // 로그인 실패 시 메시지 추가 및 로그인 페이지로 리디렉션
        model.addAttribute("message", "ID나 비밀번호가 잘못되었습니다. 확인하고 다시 입력하세요.");
        return "FAILED";
    }
}
