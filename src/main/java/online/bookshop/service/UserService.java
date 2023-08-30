package online.bookshop.service;

import online.bookshop.dto.user.UserRegistrationRequestDto;
import online.bookshop.dto.user.UserResponseDto;
import online.bookshop.exeption.RegistrationException;

public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto userRegistrationRequestDto)
            throws RegistrationException;
}
