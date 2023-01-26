import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable, catchError, throwError } from 'rxjs';
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

  public name: String = "";
  public surname: String = "";
  public address: String = "";
  public city: String = "";
  public country: String = "";
  public phoneNumber: String = "";
  public jmbg: String = "";
  public gender: String = "";
  public profession: String = "System administrator";
  public userType: Number = 3;

  public email: String = "";

  public administratorId: number = 0;
  public registreUserForm: FormGroup | any;
  public userDTO : UserDTO = new UserDTO();
  public userInfoAdded = false;

  constructor(private _userService: UserService,private _bloodbankService: BloodBankService, private router : Router, private http: HttpClient) { }

  ngOnInit(): void {
    this.registreUserForm = new FormGroup ({
      name : new FormControl(this.userDTO.name, [
        Validators.required,
      ]),
      surname : new FormControl(this.userDTO.surname, [
        Validators.required,
      ]),
      address : new FormControl(this.userDTO.address, [
        Validators.required,
      ]),
      city : new FormControl(this.userDTO.city, [
        Validators.required,
      ]),
      country : new FormControl(this.userDTO.country, [
        Validators.required,
      ]),
      phoneNumber : new FormControl(this.userDTO.phoneNumber, [
        Validators.required,
        Validators.minLength(6),
        Validators.maxLength(15)
      ]),
      jmbg : new FormControl(this.userDTO.jmbg, [
        Validators.required,
        Validators.minLength(13),
        Validators.maxLength(13)
      ]),
      gender : new FormControl(this.userDTO.gender, [
        Validators.required,
      ]),
      email : new FormControl(this.email, [
        Validators.required,
        Validators.email
      ])
    })
  }

  public onSubmit(){
    this.userDTO = {
      name : this.registreUserForm.get("name").value,
      surname : this.registreUserForm.get("surname").value,
      address : this.registreUserForm.get("address").value,
      city : this.registreUserForm.get("city").value,
      country : this.registreUserForm.get("country").value,
      phoneNumber : this.registreUserForm.get("phoneNumber").value,
      jmbg : this.registreUserForm.get("jmbg").value,
      gender : this.registreUserForm.get("gender").value,
      profession : this.profession,
      workplace : "Bloodbanks",
      userType : this.userType,
      id: 0
    }

    if(this.userInfoAdded==false){
      this.createUserInfo(this.userDTO).subscribe(res =>{
        if(res!=null){
          this.administratorId=res.userId;
          this.userInfoAdded = true;
          let userCredentials: UserCredentialsDTO = {
            email : this.registreUserForm.get("email").value,
            password : "ADMINISTRATOR",
            userId : this.administratorId
          }
          this.createAccount(userCredentials).subscribe(res => {
            Swal.fire({
              title: 'Success',
              text: 'Admin added successfully',
              icon: 'success'
            });
            this.router.navigate(['/bloodBanks'])
          });
        }
      })
    }
    else{
      let userCredentials: UserCredentialsDTO = {
        email : this.registreUserForm.get("email").value,
        password : "ADMINISTRATOR",
        userId : this.administratorId
      }
      this.createAccount(userCredentials).subscribe(res => {
        Swal.fire({
          title: 'Success',
          text: 'Admin added successfully',
          icon: 'success'
        });
        this.router.navigate(['/bloodBanks'])
      });
    }
  }

  createUserInfo(userDto:UserDTO) : Observable<any>{
    return this.http.post<UserDTO>("http://localhost:9090/userController/addAdmin", userDto).pipe(catchError(this.handleError));
  }

  createAccount(userCredentials:UserCredentialsDTO) : Observable<UserCredentialsDTO>{
    return this.http.post<UserCredentialsDTO>("http://localhost:9090/UserCredentialsController/addUserCredentials", userCredentials).pipe(catchError(this.handleError));
  }

  public handleError = (error: HttpErrorResponse) => {
    if(error.status == 400){
      Swal.fire({
        title: 'Warning',
        text: 'Please fill in all the fields',
        icon: 'warning'
      });
    }
    else if(error.status == 409){
      Swal.fire({
        title: 'Warning',
        text: 'Email already in use',
        icon: 'warning'
      });
      this.registreUserForm.get("email").setValue("");
    }
    return throwError(() => new Error('Something bad happened; please try again later.'));
}

}