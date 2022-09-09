package org.pbm.authentication.service;

import lombok.RequiredArgsConstructor;
import org.pbm.authentication.config.JwtTokenUtil;
import org.pbm.authentication.domain.dto.CreateUserRequest;
import org.pbm.authentication.domain.dto.UserView;
import org.pbm.authentication.domain.model.Users;
import org.pbm.authentication.mapper.UserMapper;
import org.pbm.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.validation.ValidationException;
import java.math.BigInteger;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final JwtTokenUtil jwtTokenUtil;

    @Transactional
    public UserView create(CreateUserRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new ValidationException("Username exists!");
        }

        Users user = userMapper.create(request);
        user.setAdmin(true);
        user = userRepository.save(user);

        String jwtToken = jwtTokenUtil.generateAccessToken(user);
        UserView userView = userMapper.toUserView(user);
        userView.setJwt(jwtToken);
        userView.setAdmin(user.getAdmin());
        return userView;
    }
}
