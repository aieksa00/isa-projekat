package isaapp.g3malt.dto;

import isaapp.g3malt.model.User;

public class UpdateUserDTO {
    public UserDTO user;
    public int userId;

    public UpdateUserDTO(UserDTO user, int userId) {
        this.user = user;
        this.userId = userId;
    }

    public UpdateUserDTO() {}

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
