package payback.ive2.register;

import org.springframework.stereotype.Service;

@Service
public class RegisterService {
  private final RegisterDao registerDao;

    public RegisterService(RegisterDao registerDao) {
        this.registerDao = registerDao;
    }

    public String registerUser(RegisterDto registerDto) {
        return registerDao.registerUser(registerDto);
    }
}
