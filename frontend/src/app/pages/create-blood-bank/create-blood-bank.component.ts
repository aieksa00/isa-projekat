import { identifierName } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CreateBloodBankDTO } from 'src/app/DTO/create-blood-bank-dto';
import { UserCredentialsDTO } from 'src/app/DTO/user-credentials-dto';
import { BloodBankService } from 'src/app/services/blood-bank.service';
import { UserService } from 'src/app/services/user.service';
import Swal from 'sweetalert2';
import { UserDTO } from '../../DTO/user-dto';

@Component({
  selector: 'app-create-blood-bank',
  templateUrl: './create-blood-bank.component.html',
  styleUrls: ['./create-blood-bank.component.css']
})
export class CreateBloodBankComponent implements OnInit {

  public name: String = "";
  public description: String = "";
  public address: String = "";
  public city: String = "";
  public country: String = "";
  public workingHours: String = "";

  public firstname: String = "";
  public surname: String = "";
  public u_address: String = "";
  public u_city: String = "";
  public u_country: String = "";
  public phoneNumber: String = "";
  public jmbg: String = "";
  public gender: String = "";
  public profession: String = "Blood bank administrator";
  public userType: Number = 1;

  public email: String = "";
  public password: String = "";
  public confirmedPassword: String = "";

  public existingUsers: UserCredentialsDTO[] = [];
  public administratorId: number = 0;

  constructor(private _userService: UserService,private _bloodbankService: BloodBankService, private router : Router) { }

  ngOnInit(): void {
    this._userService.getUserCredentials().subscribe(res=>{
      this.existingUsers = Object.values(JSON.parse(JSON.stringify(res)));;
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

    if(!this.passwordsMatch()){
      this.showPasswordWarningMessage();
      this.password = "";
      this.confirmedPassword = "";
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
      workplace : this.name,
      userType : this.userType,
      id : 0
    }

    this._userService.addUser(administrator).subscribe(res =>{
      if(res!=null){
        this.administratorId=res.id;
        
        this.createAccount()
        this.createBloodBank()

      }
    })
      

}

private createAccount(){
    let userCredentials: UserCredentialsDTO = {
      email : this.email,
      password : this.password,
      userId : this.administratorId
    }
    this._userService.addUserCredentials(userCredentials).subscribe(res=>{})
}
  private createBloodBank(){
    let bloodbank : CreateBloodBankDTO = {
      name: this.name,
      description: this.description,
      address: this.address,
      city: this.city,
      country: this.country,
      workingHours: this.workingHours,
      administratorId: this.administratorId
    };
    this._bloodbankService.createBloodBank(bloodbank).subscribe(res=>{
      this.router.navigate(['bloodBanks']);
    })

  }

  allFieldsAreValid() : boolean{
    if(this.name==""||this.description==""||this.address==""||this.city==""||this.country==""||this.workingHours==""||
      this.firstname==""||this.surname==""||this.u_address==""||this.u_city==""||this.u_country==""||this.jmbg==""||
      this.phoneNumber==""||this.gender==""||this.email==""||this.password==""||this.confirmedPassword=="")
        return false
    return true
  }

  emailInUse(){
    let tf = false
    this.existingUsers.forEach(user => {
        if(user.email==this.email)
            tf=true
    });
    return tf;
  }

  passwordsMatch() : boolean {
    if(this.password === this.confirmedPassword) {
      return true;
    }
    return false;
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

  showPasswordWarningMessage(){
    return Swal.fire({
      title: 'Warning',
      text: 'Passwords do not match',
      icon: 'warning'
    })
  }
}
