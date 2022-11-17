import { Component, OnInit, } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserCredentialsDTO } from '../../DTO/user-credentials-dto';
import { CookieService } from 'ngx-cookie-service';


import { HttpClient, HttpErrorResponse, HttpStatusCode } from '@angular/common/http';
import { Observable, Subscription, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { UserDTO } from 'src/app/DTO/user-dto';
import { UpdateUserDTO } from 'src/app/DTO/update-user-dto';

@Component({
  selector: 'app-registration-user-page',
  templateUrl: './registration-user-page.component.html',
  styleUrls: ['./registration-user-page.component.css']
})
export class RegistrationUserPageComponent implements OnInit {
  private routeSub: Subscription = new Subscription;

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
  public userType: number = 2;
  public userId : number = 0;

  constructor(private http: HttpClient, public router: Router, public cookieService: CookieService, private route: ActivatedRoute) { }

  public onSubmit() {
    const userDTO : UserDTO = {
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
      id: this.userId
    }
    this.addUserInfo(userDTO).subscribe(res => {
      const updateUserDTO : UpdateUserDTO = {
        user : res,
        userId : this.userId
      }
      this.updateUserCredentials(updateUserDTO).subscribe(res => {})
      this.cookieService.set('LoggedIn', 'true' );
      this.router.navigate(['/bloodBanks'])
    })
  }

  addUserInfo( userDTO : UserDTO) : Observable<UserDTO> {
    return this.http.post<UserDTO>("http://localhost:9090/userController/addRegisteredUser", userDTO);
  }

  updateUserCredentials( updateUserDTO : UpdateUserDTO) : Observable<UpdateUserDTO> {
    return this.http.put<UpdateUserDTO>("http://localhost:9090/UserCredentialsController/updateUserCredentials", updateUserDTO)
  }

  ngOnInit(): void {
    this.userId = Number(this.route.snapshot.paramMap.get('id'));
  }

  ngOnDestroy() {
    this.routeSub.unsubscribe();
  }

}
