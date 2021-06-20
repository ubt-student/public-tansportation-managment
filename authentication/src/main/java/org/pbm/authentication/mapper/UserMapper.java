package org.pbm.authentication.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.pbm.authentication.config.JwtTokenUtil;
import org.pbm.authentication.domain.dto.CreateUserRequest;
import org.pbm.authentication.domain.dto.UserView;
import org.pbm.authentication.domain.model.Users;
import org.pbm.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static java.util.stream.Collectors.toSet;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Autowired
    private UserRepository userRepository;
    private JwtTokenUtil jwtTokenUtil;

    public abstract UserView toUserView(Users user);

    public abstract List<UserView> toUserView(List<Users> users);

    public abstract Users create(CreateUserRequest createUserRequest);

    @AfterMapping
    protected void afterCreate(CreateUserRequest request, @MappingTarget Users user) {
        user.setPassword(jwtTokenUtil.getSHA512(request.getPassword()));
    }
}
