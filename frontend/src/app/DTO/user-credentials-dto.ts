export class UserCredentialsDTO {
    email: String = "";
    password: String = "";
    userId: number = 0;

    public constructor(obj?: any) {
        if (obj) {
            this.email = obj.email;
            this.password = obj.password;
            this.userId = obj.userId;
        }
    }
}
