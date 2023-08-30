package online.bookshop.dto.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import online.bookshop.validation.Email;
import online.bookshop.validation.FieldMatch;

@Data
@FieldMatch(
        field = "password",
        fieldMatch = "repeatPassword",
        message = "Password and repeat password shouldn't be empty and should be equal"
)
public class UserRegistrationRequestDto {
    @Email
    private String email;
    @NotNull
    @Size(min = 7, max = 60)
    private String password;
    @NotNull
    @Size(min = 7, max = 60)
    private String repeatPassword;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private String shippingAddress;
}
