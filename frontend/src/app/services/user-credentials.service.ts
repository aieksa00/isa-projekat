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

  getLoggedUser(): Observable<AllUserInfoDto> {
    return this.http.get<AllUserInfoDto>(this.apiHost + 'UserCredentialsController/GetUser/' + 4, {headers: this.headers});
  }

  updateLoggedUser(dto: AllUserInfoDto): Observable<AllUserInfoDto> {
    return this.http.put<AllUserInfoDto>(this.apiHost + 'UserCredentialsController/UpdateUser/' + 4, dto, {headers: this.headers});
  }
}
