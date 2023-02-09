package grupo2.nogame_rest.service.impl;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import grupo2.nogame_rest.service.BcriptService;

@Service
public class BcriptServiceImpl implements BcriptService {

    @Override
    public String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    @Override
    public Boolean verifyPassword(String password, String passwordHashed) {
        return BCrypt.checkpw(password, passwordHashed);
    }
}
