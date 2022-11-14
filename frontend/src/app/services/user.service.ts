import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CustomerDTO } from '../DTO/customer-dto';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  apiHost: string = 'http://localhost:8080';
  headers: HttpHeaders = new HttpHeaders({'COntent-Type': 'application/json'})

  constructor(private http: HttpClient) { }

  getCustomer(id: number): Observable<CustomerDTO> {
    return this.http.get<CustomerDTO>(this.apiHost + 'userController/getCustomer/' + id, {headers: this.headers});
  }

  updateCustomer(customerDTO: any): Observable<any> {
    return this.http.put<any>(this.apiHost + 'userController/editCustomer/' + customerDTO.id, customerDTO, {headers: this.headers});
  }
}
