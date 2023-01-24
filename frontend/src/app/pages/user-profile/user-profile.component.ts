import { validateHorizontalPosition } from '@angular/cdk/overlay';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { CustomerDTO } from 'src/app/DTO/customer-dto';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  public user: CustomerDTO = new CustomerDTO;
  public error: String = '';
  public email: any = localStorage.getItem("email");
  public userId : number = 0;

  constructor(private _userService: UserService, private http: HttpClient) { }

  ngOnInit(): void {
    this.getUserIdByEmail(this.email).subscribe(res => {
      this.userId = res;
      this._userService.getCustomer(this.userId).subscribe(res => {
        this.user = res;
        this.error = '';
      })
    })

  }

  public updateCustomer(): void {
    if(!this.IsValidInput()){
      this.error = 'Some fields are empty';
      return;
    }
    this.error = '';
    this._userService.updateCustomer(this.user).subscribe(res => {
      this.user = res;
    });
  }

  private IsValidInput():boolean {
    return this.user.name != '' && this.user.surname != '' && this.user.phoneNumber != '' && this.user.password != '' && this.user.jmbg != '';
  }

  getUserIdByEmail(email : String) : Observable<any> {
    return this.http.post<any>("http://localhost:9090/UserCredentialsController/getUserIdByEmail", email);
  }

}
