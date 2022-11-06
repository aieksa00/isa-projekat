import { Component, OnInit } from '@angular/core';
import { UserDTO } from '../../DTO/user-dto';
import { UserCredentialsDTO } from '../../DTO/user-credentials-dto';
import Swal from 'sweetalert2'

@Component({
  selector: 'app-registration-page',
  templateUrl: './registration-page.component.html',
  styleUrls: ['./registration-page.component.css']
})

export class RegistrationPageComponent implements OnInit {

  public name: String = "";
  public surname: String = "";
  public address: String = "";
  public city: String = "";
  public country: String = "";
  public phoneNumber: String = "";
  public jmbg: String = "";
  public gender: String = "";
  public profession: String = "";
  public workplace: String = "";
  public userType: String = "";

  public email: String = "";
  public password: String = "";
  public confirmedPassword: String = "";

  constructor() { }

  public onSubmit() {
    const user: UserDTO = {
      name : this.name,
      surname : this.surname,
      address : this.address,
      city : this.city,
      country : this.country,
      phoneNumber : this.phoneNumber,
      jmbg : this.jmbg,
      gender : this.gender,
      profession : this.profession,
      workplace : this.workplace,
      userType : this.userType,
            
    }
    if(this.comparePasswords()) {
      const userCredentials: UserCredentialsDTO = {
        email : this.email,
        password : this.password,
      }
    console.log(userCredentials);
    } else {
      this.showWarningMessage();
      this.password = "";
      this.confirmedPassword = "";
    }

    console.log(user);
  }

  ngOnInit(): void {
  }

  comparePasswords() : boolean {
    if(this.password === this.confirmedPassword) {
      return true;
    }
    return false;
  }

  showWarningMessage(){
    return Swal.fire({
      title: 'Warning',
      text: 'Passwords must be the same',
      icon: 'warning'
    })
  }

}
