package git.adarsh236.manageemployeeapi.registration.token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

    private final IConfirmationToken iConfirmationToken;

    public void saveConfirmationToken(ConfirmationToken token){
        iConfirmationToken.save(token);
    }

    public Optional<ConfirmationToken> getToken(String token) {
        return iConfirmationToken.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return iConfirmationToken.updateConfirmedAt(
                token, LocalDateTime.now());
    }
}
