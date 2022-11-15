import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CustomerDTO } from '../DTO/customer-dto';
import { Observable } from 'rxjs';
import { UserDTO } from '../DTO/user-dto';
import { UserCredentialsDTO } from '../DTO/user-credentials-dto';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  apiHost: string = 'http://localhost:9090/';
  headers: HttpHeaders = new HttpHeaders({'COntent-Type': 'application/json'})

  constructor(private http: HttpClient) { }

  getCustomer(id: number): Observable<CustomerDTO> {
    return this.http.get<CustomerDTO>(this.apiHost + 'userController/getCustomer/' + id, {headers: this.headers});
  }

  updateCustomer(customerDTO: any): Observable<any> {
    return this.http.put<any>(this.apiHost + 'userController/editCustomer/' + customerDTO.id, customerDTO, {headers: this.headers});
  }

  getUsers(): Observable<any>{
    return this.http.get<any>(this.apiHost + 'userController/getAllUsers',{headers: this.headers})
  }

  getUserCredentials():Observable<UserCredentialsDTO>{
    return this.http.get<any>('http://localhost:9090/UserCredentialsController/getAllUserCredentials',{headers: this.headers})
  }

  addUser(user : UserDTO): Observable<UserDTO> {
    return this.http.post<UserDTO>('http://localhost:9090/userController/addUser', user, {headers: this.headers});
  }

  addUserCredentials( userCredentials : UserCredentialsDTO):  Observable<UserCredentialsDTO> {
    return this.http.post<UserCredentialsDTO>(this.apiHost + 'UserCredentialsController/addUserCredentials', userCredentials,{headers: this.headers});
  }
}
