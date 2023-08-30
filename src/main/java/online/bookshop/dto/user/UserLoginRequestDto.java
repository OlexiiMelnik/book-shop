package online.bookshop.dto.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import online.bookshop.validation.Email;

@Data
public class UserLoginRequestDto {
    @Email
    private String email;
    @NotNull
    @Size(min = 7, max = 60)
    private String password;
}
