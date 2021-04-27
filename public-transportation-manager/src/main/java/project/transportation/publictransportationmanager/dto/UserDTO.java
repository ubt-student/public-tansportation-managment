package project.transportation.publictransportationmanager.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import project.transportation.publictransportationmanager.model.Useri;

@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class UserDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public UserDTO(Useri user) {
    }
}
