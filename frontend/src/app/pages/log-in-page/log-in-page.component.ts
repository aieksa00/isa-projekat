import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserCredentialsDTO } from '../../DTO/user-credentials-dto';
import { CookieService } from 'ngx-cookie-service';

import { HttpClient, HttpErrorResponse, HttpStatusCode } from '@angular/common/http';
import { Observable, Subscription, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { UserDTO } from 'src/app/DTO/user-dto';
import Swal from 'sweetalert2';
import { _countGroupLabelsBeforeOption } from '@angular/material/core';
import { MatDialogRef } from '@angular/material/dialog';

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

  constructor(public router: Router, public cookieService: CookieService, private http: HttpClient,
    private dialogRef: MatDialogRef<LogInPageComponent>) {
  }

  onSubmit() {
    let notEmptyFields : boolean = false;
    const userCredentials : UserCredentialsDTO = {
      email : this.email,
      password : this.password,
      userId : 0
    }
    if(this.email == "" || this.password == "") {
      this.showEmptyFieldMessage();
    } else {
      notEmptyFields = true;
    }
    if(notEmptyFields) {
      this.getUsersCredentials().subscribe(res => {
        this.list = res;
  
        const foundEmail = this.list.find((element) => element.email === this.email);
        if(!foundEmail) {
          this.showWrongEmailMessage();
          this.email = "";
          this.password = "";
          return;
        }
        if(foundEmail.password === this.password) {
          this.cookieService.set('LoggedIn', 'true' );
          this.router.navigate(['/bloodBanks']);
          this.closeDialog();
        } else {
          this.showWrongPasswordMessage();
          this.password = "";
        }
      })
    }
    
  }

  getUsersCredentials() : Observable<any> {
    return this.http.get<any>("http://localhost:9090/UserCredentialsController/getAllUsersCredentials");
  }

  showEmptyFieldMessage(){
    return Swal.fire({
      title: 'Warning',
      text: 'Please fill in all the fields',
      icon: 'warning'
    })
  }

  showWrongPasswordMessage(){
    return Swal.fire({
      title: 'Warning',
      text: 'Wrong password',
      icon: 'warning'
    })
  }

  showWrongEmailMessage(){
    return Swal.fire({
      title: 'Warning',
      text: 'Wrong email',
      icon: 'warning'
    })
  }

  closeDialog(){
    this.dialogRef.close();
  }

  ngOnInit(): void {
  }

}
