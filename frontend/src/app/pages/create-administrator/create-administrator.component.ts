import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CreateBloodBankDTO } from 'src/app/DTO/create-blood-bank-dto';
import { UserCredentialsDTO } from 'src/app/DTO/user-credentials-dto';
import { UserDTO } from 'src/app/DTO/user-dto';
import { BloodBankService } from 'src/app/services/blood-bank.service';
import { UserService } from 'src/app/services/user.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-create-administrator',
  templateUrl: './create-administrator.component.html',
  styleUrls: ['./create-administrator.component.css']
})
export class CreateAdministratorComponent implements OnInit {

  public firstname: String = "";
  public surname: String = "";
  public u_address: String = "";
  public u_city: String = "";
  public u_country: String = "";
  public phoneNumber: String = "";
  public jmbg: String = "";
  public gender: String = "";
  public profession: String = "System administrator";
  public userType: Number = 3;

  public email: String = "";

  public existingEmails: any;
  public administratorId: number = 0;

  constructor(private _userService: UserService,private _bloodbankService: BloodBankService, private router : Router) { }

  ngOnInit(): void {
    this._userService.getUserCredentials().subscribe(res=>{
      this.existingEmails = res;
    })
  }

  public onSubmit(){
    if(!this.allFieldsAreValid()){
          this.showEmptyFieldsWarningMessage();
          return;
    }
    if(this.emailInUse()){
      this.showEmailInUsesWarningMessage();
      return;
    }

    let administrator : UserDTO = {
      name : this.firstname,
      surname : this.surname,
      address : this.u_address,
      city : this.u_city,
      country : this.u_country,
      phoneNumber : this.phoneNumber,
      jmbg : this.jmbg,
      gender : this.gender,
      profession : this.profession,
      workplace : "Bloodbanks",
      userType : this.userType,
      id : 0
    }

    this._userService.addAdmin(administrator).subscribe(res =>{
      if(res!=null){
        this.administratorId=res.userId;
        
        this.createAccount()

      }
    })
      

}

private createAccount(){
    let userCredentials: UserCredentialsDTO = {
      email : this.email,
      password : "ADMINISTRATOR",
      userId : this.administratorId
    }
    this._userService.addUserCredentials(userCredentials).subscribe(res=>{})
}

  allFieldsAreValid() : boolean{
    if(this.firstname==""||this.surname==""||this.u_address==""||this.u_city==""||this.u_country==""||this.jmbg==""||
      this.phoneNumber==""||this.gender==""||this.email=="")
        return false
    return true
  }

  emailInUse(){
    let tf = false
    this.existingEmails.forEach((email: String) => {
        if(email==this.email)
            tf=true
    });
    return tf;
  }

  showEmptyFieldsWarningMessage(){
    return Swal.fire({
      title: 'Warning',
      text: 'All fields must be filled',
      icon: 'warning'
    })
  }

  showEmailInUsesWarningMessage(){
    return Swal.fire({
      title: 'Warning',
      text: 'This email address is already in use',
      icon: 'warning'
    })
  }
}