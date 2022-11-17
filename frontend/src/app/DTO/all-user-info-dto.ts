export class AllUserInfoDto {
    userName: String = "";
    userSurname: String = "";
    userStreet: String = "";
    userCity: String = "";
    userCountry: String = "";
    userPhoneNumber: String = "";
    userJmbg: String = "";
    userGender: number = 0;
    userProfession: String = "";
    userWorkplace: String = "";
    userId: number = 0;

    userCredentialsEmail: String = "";
    userCredentialsPassword: String = "";

    public constructor(obj?: any) {
        if (obj) {
          this.userName = obj.userName;
          this.userSurname = obj.userSurname;
          this.userStreet = obj.userStreet;
          this.userCity = obj.userCity;
          this.userCountry = obj.userCountry;    
          this.userPhoneNumber = obj.userPhoneNumber;    
          this.userJmbg = obj.userCountry;    
          this.userGender = obj.userGender;    
          this.userProfession = obj.userProfession;    
          this.userWorkplace = obj.userWorkplace;    
          this.userId = obj.userId;    
          this.userCredentialsEmail = obj.userCredentialsEmail;    
          this.userCredentialsPassword = obj.userCredentialsPassword;    
        }
    }
}