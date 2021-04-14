package project.transportation.publictransportationmanager.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import project.transportation.publictransportationmanager.exception.UserNotFoundException;
import project.transportation.publictransportationmanager.model.Useri;
import project.transportation.publictransportationmanager.token.ConfirmationToken;
import project.transportation.publictransportationmanager.token.ConfirmationTokenService;
import project.transportation.publictransportationmanager.repo.UserRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private static String USER_NOT_FOUND = "user with email %s not found";
    private  final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new
                UserNotFoundException(String.format(USER_NOT_FOUND, email)));
    }

    public String signUpUser(Useri user){
        user.setUserCode(UUID.randomUUID().toString());
        boolean userExists = userRepository.findByEmail(user.getEmail())
                .isPresent();

        if(userExists){
            throw  new IllegalStateException("you've already registered!");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);

        userRepository.save(user);
        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );
        confirmationTokenService.saveConfirmationToken(confirmationToken);

        // TODO: SEND EMAIL

        return token;
    }


    public Useri addUser(Useri useri){
        useri.setUserCode(UUID.randomUUID().toString());
        return userRepository.save(useri);
    }
    public List<Useri> findAllUsers(){
        return userRepository.findAll();
    }
    public Useri updateUser(Useri useri){
        return userRepository.save(useri);
    }
    public Useri findUserById(Long id){
        return userRepository.findUserById(id).orElseThrow(() ->
                new UserNotFoundException("User by id " + id + " was not found"));
    }

    public void deleteUser(Long id){
        userRepository.deleteUserById(id);
    }


    public int enableAppUser(String email) {
        return userRepository.enableAppUser(email);
    }
}
