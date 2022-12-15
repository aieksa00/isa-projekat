package isaapp.g3malt.dto;

import isaapp.g3malt.model.User;

public class NewUserDTO {

    UserDTO user;
    Integer userId;

    public NewUserDTO(UserDTO user, Integer userId) {
        this.user = user;
        this.userId = userId;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
