package project.transportation.publictransportationmanager.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import project.transportation.publictransportationmanager.model.Useri;
import project.transportation.publictransportationmanager.repo.UserRepository;

@Service
public class AuthenticationService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

//	private static GoogleAuthenticator googleAuthenticator = new GoogleAuthenticator();

    private UserRepository userRepository;

    @Autowired
    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Method to get authenticated user
     *
     * @return the authenticated User
     */
    public Useri getAuthenticatedUser() {
        logger.info("To get the authenticated user.");
        FlashUserDetails zeusUserDetails;
        try {
            zeusUserDetails = (FlashUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            logger.error("Unauthorized Access.", e.getMessage());
            throw new AuthenticationException("Unauthorized Access.") {
            };
        }
        Useri user = userRepository.findById(zeusUserDetails.getId()).get();

//		if (!user.isActive()) {
//			logger.error("This user no longer exists in our system.");
//			throw new UnauthorizedUserException("This user no longer exists in our system.");
//		}
        logger.info("Returning the authenticated user.");
        return user;
    }

    /**
     * Method to get the QRCode string
     *
     * @param user
     *            the user
     *
     * @return the QRCode string
     */

    /**
     * Method to check if OTP is valid or not
     *
     * @param otp
     *            the otp
     *
     * @param secret
     *            the secret
     *
     * @return true if OTP is valid or false if OTP is invalid
     */
//	public boolean isOTPValid(Integer otp, String secret) {
//		logger.info("To check if OTP is Valid or not.");
//		return otp.equals(googleAuthenticator.getTotpPassword(secret));
//	}

    /**
     * Method to revoke the access-token
     *
     */
}
