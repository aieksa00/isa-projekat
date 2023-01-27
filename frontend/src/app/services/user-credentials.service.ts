import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AllUserInfoDto } from '../DTO/all-user-info-dto';

@Injectable({
  providedIn: 'root'
})
export class UserCredentialsService {

  apiHost: string = 'http://localhost:9090/';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }


  GetLoggedUserByEmail(): Observable<AllUserInfoDto> {
    return this.http.get<AllUserInfoDto>(this.apiHost + 'UserCredentialsController/GetUserByEmail/' + localStorage.getItem('email'), {headers: this.headers});
  }

  UpdateLoggedUser(dto: AllUserInfoDto): Observable<AllUserInfoDto> {
    return this.http.put<AllUserInfoDto>(this.apiHost + 'UserCredentialsController/UpdateUserByEmail/' + localStorage.getItem('email'), dto, {headers: this.headers});
  }

  changeAdminPassword(newPass: String): Observable<any> {
    return this.http.put<any>(this.apiHost + 'UserCredentialsController/changePassword/' + newPass, {headers: this.headers});
  }
}
