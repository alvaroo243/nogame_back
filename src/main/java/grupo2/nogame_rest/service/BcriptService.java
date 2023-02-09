package grupo2.nogame_rest.service;

public interface BcriptService {
    public String hashPassword(String password);
    public Boolean verifyPassword(String password, String passwordVerify);
}
