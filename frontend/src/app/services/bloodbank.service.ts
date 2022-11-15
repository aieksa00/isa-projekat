import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CreateBloodBankDTO } from '../DTO/create-blood-bank-dto';

@Injectable({
  providedIn: 'root'
})
export class BloodbankService {

  apiHost: string = 'http://localhost:9090/';
  headers: HttpHeaders = new HttpHeaders({'COntent-Type': 'application/json'})

  constructor(private http: HttpClient) { }

  public createBloodBank(CreateBloodBankDTO: CreateBloodBankDTO) :Observable<any> {
    return this.http.post<CreateBloodBankDTO>(this.apiHost + 'bloodBankController/addBloodBank',CreateBloodBankDTO,{headers: this.headers});
  }
}
