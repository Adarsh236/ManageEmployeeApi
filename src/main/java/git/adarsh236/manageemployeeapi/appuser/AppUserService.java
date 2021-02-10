package git.adarsh236.manageemployeeapi.appuser;

import git.adarsh236.manageemployeeapi.exception.UserNotFoundException;
import git.adarsh236.manageemployeeapi.registration.token.ConfirmationToken;
import git.adarsh236.manageemployeeapi.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Service
public class AppUserService implements UserDetailsService {

    private final IAppUser iAppUser;
    private final static  String USER_NOT_FOUND_MSG= "user with email %s not found";
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) {
        return iAppUser.findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpUser(AppUser appUser){
        boolean userExist = iAppUser.findByEmail((appUser.getEmail()))
                .isPresent();
        if(userExist){
            //TODO check of attributes are the same and
            //TODO if email not confirmed send confirmation email
            throw  new IllegalStateException("email already taken");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);
        iAppUser.save(appUser);

        String token = UUID.randomUUID().toString();

        //TODO: SEND confirmation token - done
        ConfirmationToken confirmationToken = new ConfirmationToken(
                      token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );

        confirmationTokenService.saveConfirmationToken(confirmationToken);
        //TODO: SEND EMAIL
        return token;
    }

    public int enableAppUser(String email) {
        return iAppUser.enableAppUser(email);
    }
}
