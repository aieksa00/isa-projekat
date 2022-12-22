import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserCredentialsDTO } from '../../DTO/user-credentials-dto';
import { CookieService } from 'ngx-cookie-service';
import { JwtHelperService } from '@auth0/angular-jwt';

import { HttpClient, HttpErrorResponse, HttpStatusCode } from '@angular/common/http';
import { Observable, Subscription, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { UserDTO } from 'src/app/DTO/user-dto';
import Swal from 'sweetalert2';
import { _countGroupLabelsBeforeOption } from '@angular/material/core';
import { MatDialogRef } from '@angular/material/dialog';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-log-in-page',
  templateUrl: './log-in-page.component.html',
  styleUrls: ['./log-in-page.component.css'],
  providers: [CookieService]
})
export class LogInPageComponent implements OnInit {

  public email: String = "";
  public password: String = "";
  public list :  Array<any> = [];
  public logInForm: FormGroup | any;
  public userCredentials : UserCredentialsDTO = new UserCredentialsDTO();
  private access_token = null;
  public role = "";
  private jwtEmail = "";
  private errorHappend : boolean = false;
  ;
  

  constructor(public router: Router, public cookieService: CookieService, private http: HttpClient,
    private dialogRef: MatDialogRef<LogInPageComponent>) {
  }

  onSubmit() {
    let notEmptyFields : boolean = false;
    this.userCredentials = {
      email : this.logInForm.get("email").value,
      password : this.logInForm.get("password").value,
      userId : 0
    }
    if(this.userCredentials.email == "" || this.userCredentials.password == "") {
      this.showEmptyFieldMessage();
    } else {
      notEmptyFields = true;
    }
    if(notEmptyFields) {
      this.checkUserCredentials(this.userCredentials).subscribe(res => {

        this.userCredentials = res;
        this.logIn(this.userCredentials).subscribe(res => {
          if(!this.errorHappend) {
            this.cookieService.set('LoggedIn', 'true' );
            this.access_token = res.accessToken;
            localStorage.setItem("jwt", res.accessToken);
            const helper = new JwtHelperService();
            const decodedToken = helper.decodeToken(res.accessToken);
            this.jwtEmail = decodedToken['sub'];
            this.role = decodedToken['Granted Authorities'];
            localStorage.setItem('email', this.jwtEmail);
            localStorage.setItem('role', this.role);
            if(this.role == "CUSTOMER") {
              this.router.navigate(['/bloodBanks']);
            } else if (this.role == "STAFF") {
              this.router.navigate(['/bloodBankInfo']) //staviti sta treba
            } else {
              //dodati admina
            }
          }
        });
      });
      this.closeDialog();
    }
    
  }

  logIn(userCredentialsDTO : UserCredentialsDTO) : Observable<any> {
    return this.http.post<any>("http://localhost:9090/UserCredentialsController/logIn", userCredentialsDTO).pipe(catchError(this.handleError));
  }

  checkUserCredentials(userCredentialsDTO : UserCredentialsDTO) : Observable<any> {
    return this.http.post<any>("http://localhost:9090/UserCredentialsController/checkCredeentials", userCredentialsDTO).pipe(catchError(this.handleError));
  }

  showEmptyFieldMessage(){
    return Swal.fire({
      title: 'Warning',
      text: 'Please fill in all the fields',
      icon: 'warning'
    })
  }

  public handleError = (error : HttpErrorResponse) => {
    if(error.status == 404) {
      Swal.fire({
        title: 'Warning',
        text: 'Bad credentials, please try again',
        icon: 'warning'
      });
    }
    if(error.status == 401) {
      Swal.fire({
        title: 'Error',
        text: 'User is not verified',
        icon: 'error'
      });
      this.errorHappend = true;
    }
    this.logInForm.get("email").setValue("");
    this.logInForm.get("password").setValue("");
    return throwError(() => new Error('Something bad happened; please try again later.'));
  }
  

  closeDialog(){
    this.dialogRef.close();
  }

  ngOnInit(): void {
    this.logInForm = new FormGroup ({
      email : new FormControl(this.userCredentials.email, [
        Validators.required,
        Validators.email
      ]),
      password : new FormControl(this.userCredentials.password, [
        Validators.required,
        Validators.minLength(5)
      ])
    })
  }

}
