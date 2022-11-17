import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserCredentialsDTO } from '../../DTO/user-credentials-dto';
import Swal from 'sweetalert2'


import { HttpClient, HttpErrorResponse, HttpStatusCode } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';

@Component({
  selector: 'app-registration-page',
  templateUrl: './registration-page.component.html',
  styleUrls: ['./registration-page.component.css']
})

export class RegistrationPageComponent implements OnInit {

  public email: String = "";
  public password: String = "";
  public confirmedPassword: String = "";
  public userCredentials : UserCredentialsDTO = new UserCredentialsDTO();


  constructor(private http: HttpClient, public router: Router) { }

  public onSubmit() {
    if(this.comparePasswords()) {
      const userCredentials: UserCredentialsDTO = {
        email : this.email,
        password : this.password,
        userId : 0
      }
      this.addUserCredentials(userCredentials).subscribe(res => {
          this.userCredentials = res;
          this.router.navigate([`/registrationUserPage/${res['id']}`])
      });
    } else {
      this.showWarningMessage();
      this.password = "";
      this.confirmedPassword = "";
    }

  }


  ngOnInit(): void {
  }

  addUserCredentials( userCredentials : UserCredentialsDTO):  Observable<UserCredentialsDTO> {
    return this.http.post<UserCredentialsDTO>("http://localhost:9090/UserCredentialsController/registreUserCredentials", userCredentials).pipe(catchError(this.handleError));
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

  public handleError = (error: HttpErrorResponse) => {
      Swal.fire({
        title: 'Warning',
        text: 'Email already in use',
        icon: 'warning'
      });
      this.email = "";
      this.password = "";
      this.confirmedPassword = "";
      return throwError(() => new Error('Something bad happened; please try again later.'));

  }
}
