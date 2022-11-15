import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BloodBankDto } from '../DTO/blood-bank-dto';

@Injectable({
  providedIn: 'root'
})
export class BloodBankService {

  apiHost: string = 'http://localhost:8080/';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }
 
  getBloodBankById(id: number): Observable<BloodBankDto> {
    return this.http.get<BloodBankDto>(this.apiHost + 'BloodBankController/BloodBank/' + id, {headers: this.headers});
  }

  updateDescription(id: number, description: string): Observable<BloodBankDto> {
    return this.http.put<BloodBankDto>(this.apiHost + 'BloodBankController/UpdateDescription/' + id, description, {headers: this.headers});
  }
}
