import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BloodBankDto } from '../DTO/blood-bank-dto';
import { BloodBanksDTO } from '../DTO/blood-banks-list-dto';
import { CreateBloodBankDTO } from '../DTO/create-blood-bank-dto';
import { UpdateBloodBankDto } from '../DTO/update-blood-bank-dto';


@Injectable({
  providedIn: 'root'
})
export class BloodBankService {

  apiHost: string = 'http://localhost:9090/';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json','Authorization': `Bearer ${localStorage.getItem("jwt")}`});

  constructor(private http: HttpClient) { }

  getBloodBankById(id: number): Observable<BloodBankDto> {
    return this.http.get<BloodBankDto>(this.apiHost + 'BloodBankController/BloodBank/' + id, {headers: this.headers});
  }

  updateBloodBank(id: number, dto: UpdateBloodBankDto): Observable<BloodBankDto> {
    return this.http.put<BloodBankDto>(this.apiHost + 'BloodBankController/UpdateBloodBank/' + id, dto, {headers: this.headers});
  }

  public createBloodBank(CreateBloodBankDTO: CreateBloodBankDTO) :Observable<any> {
    let headers = {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${localStorage.getItem("jwt")}`,
    };
    return this.http.post<CreateBloodBankDTO>(this.apiHost + 'BloodBankController/addBloodBank',CreateBloodBankDTO,{headers: headers});
  }

  public getBloodBanks(): Observable<any>{
    let headers = {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${localStorage.getItem("jwt")}`,
    };
    return this.http.get<any>(this.apiHost + 'BloodBankController/getAllBloodBanks', {headers: headers});
  }

  public getCalenderEventsForBloodBank(id:any):Observable<any>{
    let headers = {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${localStorage.getItem("jwt")}`,
    };
    return this.http.get<any>(this.apiHost + 'BloodBankController/getAllAppointmentsForBloodBank/' + id, {headers: headers});
  }
}
