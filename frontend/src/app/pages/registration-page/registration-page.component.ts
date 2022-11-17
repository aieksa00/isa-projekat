import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserCredentialsDTO } from '../../DTO/user-credentials-dto';
import Swal from 'sweetalert2'


import { HttpClient, HttpErrorResponse, HttpStatusCode } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-registration-page',
  templateUrl: './registration-page.component.html',
  styleUrls: ['./registration-page.component.css']
})

export class RegistrationPageComponent implements OnInit {

  public email: String = "";
  public password: String = "";
  public confirmedPassword: String = "";
  public registreForm: FormGroup | any;
  public userCredentials : UserCredentialsDTO = new UserCredentialsDTO();

  constructor(private http: HttpClient, public router: Router) { }

  public onSubmit() {
    if(this.comparePasswords()) {
      this.userCredentials = {
        email : this.registreForm.get("email").value,
        password : this.registreForm.get("password").value,
        userId : 0
      }
      this.addUserCredentials(this.userCredentials).subscribe(res => {
          this.userCredentials = res;
          this.router.navigate([`/registrationUserPage/${res['id']}`])
      });
    } else {
      this.showWarningMessage();
      this.registreForm.get("email").setValue("");
      this.registreForm.get("password").setValue("");
      this.registreForm.get("confirmedPassword").setValue("");
    }

  }


  ngOnInit(): void {
    this.registreForm = new FormGroup ({
      email : new FormControl(this.userCredentials.email, [
        Validators.required,
        Validators.email
      ]),
      password : new FormControl(this.userCredentials.password, [
        Validators.required,
        Validators.minLength(5)
      ]),
      confirmedPassword : new FormControl(this.userCredentials.password, [
        Validators.required,
        Validators.minLength(5)
      ])
    })
  }

  addUserCredentials( userCredentials : UserCredentialsDTO):  Observable<UserCredentialsDTO> {
    return this.http.post<UserCredentialsDTO>("http://localhost:9090/UserCredentialsController/registreUserCredentials", userCredentials).pipe(catchError(this.handleError));
  }

  comparePasswords() : boolean {
    if(this.registreForm.get("password").value === this.registreForm.get("confirmedPassword").value) {
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
        this.registreForm.get("email").setValue("");
        this.registreForm.get("password").setValue("");
        this.registreForm.get("confirmedPassword").setValue("");
      }
      return throwError(() => new Error('Something bad happened; please try again later.'));
  }
}
