import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BloodBankDto } from '../DTO/blood-bank-dto';
import { BloodBanksDTO } from '../DTO/blood-banks-list-dto';
import { AppointmentDto } from 'src/app/DTO/appointment-time-dto';
import { CreateBloodBankDTO } from '../DTO/create-blood-bank-dto';
import { UpdateBloodBankDto } from '../DTO/update-blood-bank-dto';
import { UpdateBloodBankStorageDto } from '../DTO/update-blood-bank-storage-dto';
import { BloodBankAppointmentDto } from '../DTO/blood-bank-appointment-dto';
import { CreateAppointmentByPatientDTO } from '../DTO/create-appointment-by-patient-dto';


@Injectable({
  providedIn: 'root'
})
export class BloodBankService {

  apiHost: string = 'http://localhost:9090/';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json'});

  constructor(private http: HttpClient) { }

  public getBloodBankByUserEmail(): Observable<BloodBankDto> {
    return this.http.get<BloodBankDto>(this.apiHost + 'BloodBankController/BloodBank/' + localStorage.getItem("email"), {headers: this.headers});
  }

  public updateBloodBank(id: number, dto: UpdateBloodBankDto): Observable<BloodBankDto> {
    return this.http.put<BloodBankDto>(this.apiHost + 'BloodBankController/UpdateBloodBank/' + id, dto, {headers: this.headers});
  }

  public createBloodBank(CreateBloodBankDTO: CreateBloodBankDTO) :Observable<any> {
    return this.http.post<CreateBloodBankDTO>(this.apiHost + 'BloodBankController/addBloodBank',CreateBloodBankDTO,{headers: this.headers});
  }

  public getBloodBanks(): Observable<any>{
    return this.http.get<any>(this.apiHost + 'BloodBankController/getAllBloodBanks', {headers: this.headers});
  }

  public updateBloodBankStorage(dto: UpdateBloodBankStorageDto): Observable<UpdateBloodBankStorageDto> {
    return this.http.post<UpdateBloodBankStorageDto>(this.apiHost + 'BloodBankController/UpdateBloodBankStorage', dto, {headers: this.headers});
  }

  public getBloodBankById(id: number): Observable<BloodBankDto> {
    return this.http.get<BloodBankDto>(this.apiHost + 'BloodBankController/BloodBankById/' + id, {headers: this.headers});
  }

  public getAllBloodBanksWithFreeAppointment(AppointmentDto : String): Observable<any>{
    return this.http.post<any>(this.apiHost + 'BloodBankController/getAllBloodBanksWithFreeAppointment',AppointmentDto, {headers: this.headers});
  }

  public getCalenderEventsForBloodBank(id:any):Observable<any>{
    return this.http.get<any>(this.apiHost + 'BloodBankController/getAllAppointmentsForBloodBank/' + id, {headers: this.headers});
  }

  public getAppointmentFromBloodBank(bloodBankAppointmentDto: BloodBankAppointmentDto) {
    return this.http.post<any>(this.apiHost + 'BloodBankController/getAppointmentFromBloodBank',bloodBankAppointmentDto, {headers: this.headers});
  }

  public createAppointmentByPatient(createAppointmentByPatientDTO: CreateAppointmentByPatientDTO, id: any) {
    console.log(createAppointmentByPatientDTO)
    return this.http.post<any>(this.apiHost + 'AppointmentController/CreateAppointmentByPatient/'+ id, createAppointmentByPatientDTO, {headers: this.headers});
  }
}
