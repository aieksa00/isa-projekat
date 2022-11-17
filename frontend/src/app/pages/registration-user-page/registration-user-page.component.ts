import { Component, OnInit, } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserCredentialsDTO } from '../../DTO/user-credentials-dto';
import { CookieService } from 'ngx-cookie-service';


import { HttpClient, HttpErrorResponse, HttpStatusCode } from '@angular/common/http';
import { Observable, Subscription, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { UserDTO } from 'src/app/DTO/user-dto';
import { UpdateUserDTO } from 'src/app/DTO/update-user-dto';
import Swal from 'sweetalert2';
import { FormControl, FormGroup, Validators } from '@angular/forms';

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

  public registreUserForm: FormGroup | any;
  public userDTO : UserDTO = new UserDTO();

  constructor(private http: HttpClient, public router: Router, public cookieService: CookieService, private route: ActivatedRoute) { }

  public onSubmit() {
    this.userDTO = {
      name : this.registreUserForm.get("name").value,
      surname : this.registreUserForm.get("surname").value,
      address : this.registreUserForm.get("address").value,
      city : this.registreUserForm.get("city").value,
      country : this.registreUserForm.get("country").value,
      phoneNumber : this.registreUserForm.get("phoneNumber").value,
      jmbg : this.registreUserForm.get("jmbg").value,
      gender : this.registreUserForm.get("gender").value,
      profession : this.registreUserForm.get("profession").value,
      workplace : this.registreUserForm.get("workplace").value,
      userType : this.userType,
      id: this.userId
    }
    this.addUserInfo(this.userDTO).subscribe(res => {
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
    return this.http.post<UserDTO>("http://localhost:9090/userController/addRegisteredUser", userDTO).pipe(catchError(this.handleError));
  }

  updateUserCredentials( updateUserDTO : UpdateUserDTO) : Observable<UpdateUserDTO> {
    return this.http.put<UpdateUserDTO>("http://localhost:9090/UserCredentialsController/updateUserCredentials", updateUserDTO)
  }

  ngOnInit(): void {
    this.userId = Number(this.route.snapshot.paramMap.get('id'));
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
      profession : new FormControl(this.userDTO.profession, [
        Validators.required,
      ]),
      workplace : new FormControl(this.userDTO.workplace, [
        Validators.required,
      ])
    })
  }

  ngOnDestroy() {
    this.routeSub.unsubscribe();
  }

  public handleError = (error: HttpErrorResponse) => {
      Swal.fire({
        title: 'Warning',
        text: 'Please ckeck again all the fields some of them are empty',
        icon: 'warning'
      });
    return throwError(() => new Error('Something bad happened; please try again later.'));

    }
}

