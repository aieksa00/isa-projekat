package isaapp.g3malt.dto;

import isaapp.g3malt.model.User;

public class UpdateUserDTO {
    public User user;
    public int userId;

    public UpdateUserDTO(User user, int userId) {
        this.user = user;
        this.userId = userId;
    }
}
