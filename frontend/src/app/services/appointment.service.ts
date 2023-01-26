import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AppointmentReviewDto } from '../DTO/appointment-review-dto';
import { FutureAppointmentDto } from '../DTO/future-appointment';

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {

  apiHost: string = 'http://localhost:9090/';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  public createAppointment(id:number, dto: FutureAppointmentDto): Observable<FutureAppointmentDto> {
    return this.http.post<FutureAppointmentDto>(this.apiHost + 'BloodBankController/CreateAppointment/'+ id, dto, {headers: this.headers});
  }

  public getAppointmentReviewDtoById(id:number): Observable<AppointmentReviewDto> {
    return this.http.get<AppointmentReviewDto>(this.apiHost + 'AppointmentController/GetAppointmentReviewDtoById/'+ id, {headers: this.headers});
  }

  public FinishAppointment(id: number){
    return this.http.put(this.apiHost + 'AppointmentController/FinishAppointment/'+ id, {headers: this.headers});
  }
  
}
