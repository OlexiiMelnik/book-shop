package online.bookshop.service.impl;

import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import online.bookshop.dto.user.UserRegistrationRequestDto;
import online.bookshop.dto.user.UserResponseDto;
import online.bookshop.exeption.RegistrationException;
import online.bookshop.mapper.UserMapper;
import online.bookshop.model.Role;
import online.bookshop.model.User;
import online.bookshop.repository.RoleRepository;
import online.bookshop.repository.UserRepository;
import online.bookshop.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new RegistrationException("Unable to complete registration");
        }
        User user = new User();
        user.setEmail(requestDto.getEmail());
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        user.setFirstName(requestDto.getFirstName());
        user.setLastName(requestDto.getLastName());
        user.setShippingAddress(requestDto.getShippingAddress());
        Role userRole = roleRepository.findRoleByRoleName(Role.RoleName.USER)
                .orElseThrow(() -> new RegistrationException("Can't find role by name"));
        Set<Role> defaultUserRoleSet = new HashSet<>();
        defaultUserRoleSet.add(userRole);
        user.setRoles(defaultUserRoleSet);
        User saveUser = userRepository.save(user);
        return userMapper.toDto(saveUser);
    }
}
