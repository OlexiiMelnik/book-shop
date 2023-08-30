package online.bookshop.mapper;

import online.bookshop.config.MapperConfig;
import online.bookshop.dto.user.UserRegistrationRequestDto;
import online.bookshop.dto.user.UserResponseDto;
import online.bookshop.model.User;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserResponseDto toDto(User user);

    User toModel(UserRegistrationRequestDto userRegistrationRequestDto);
}
