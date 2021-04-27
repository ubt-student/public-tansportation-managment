package project.transportation.publictransportationmanager.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomAuthenticationKeyGenerator {
    private static final String CLIENT_ID = "client_id";
    private static final String SCOPE = "scope";
    private static final String USERNAME = "username";
    private static final String UUID_KEY = "uuid";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private HttpServletRequest request;

    private boolean isLoginRequest(HttpServletRequest request) {
        String s = request.getRequestURL().toString();
        Pattern pattern = Pattern.compile(".*/oauth/token.*");
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }

    private boolean isLogoutRequest(HttpServletRequest request) {
        String s = request.getRequestURL().toString();
        Pattern pattern = Pattern.compile(".*/logout.*");
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }

    private boolean isPasswordRequest(HttpServletRequest request) {
        String s = request.getRequestURL().toString();
        Pattern pattern = Pattern.compile(".*/password.*");
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }
}
