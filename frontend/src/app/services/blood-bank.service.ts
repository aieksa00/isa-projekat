import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BloodBankDto } from '../DTO/blood-bank-dto';
import { BloodBanksDTO } from '../DTO/blood-banks-list-dto';
import { AppointmentDto } from 'src/app/DTO/appointment-time-dto';
import { CreateBloodBankDTO } from '../DTO/create-blood-bank-dto';
import { UpdateBloodBankDto } from '../DTO/update-blood-bank-dto';


@Injectable({
  providedIn: 'root'
})
export class BloodBankService {

  apiHost: string = 'http://localhost:9090/';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  getBloodBankById(id: number): Observable<BloodBankDto> {
    return this.http.get<BloodBankDto>(this.apiHost + 'BloodBankController/BloodBank/' + id, {headers: this.headers});
  }

  updateBloodBank(id: number, dto: UpdateBloodBankDto): Observable<BloodBankDto> {
    return this.http.put<BloodBankDto>(this.apiHost + 'BloodBankController/UpdateBloodBank/' + id, dto, {headers: this.headers});
  }

  public createBloodBank(CreateBloodBankDTO: CreateBloodBankDTO) :Observable<any> {
    return this.http.post<CreateBloodBankDTO>(this.apiHost + 'BloodBankController/addBloodBank',CreateBloodBankDTO,{headers: this.headers});
  }

  public getBloodBanks(): Observable<any>{
    return this.http.get<any>(this.apiHost + 'BloodBankController/getAllBloodBanks', {headers: this.headers});
  }

  public getAllBloodBanksWithFreeAppointment(AppointmentDto : String): Observable<any>{
    return this.http.post<any>(this.apiHost + 'BloodBankController/getAllBloodBanksWithFreeAppointment',AppointmentDto, {headers: this.headers});
  }
}
