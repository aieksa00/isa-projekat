import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { FutureAppointmentDto } from '../DTO/future-appointment';
import { FreeAppointmentDTO } from '../DTO/free-appointment-dto';

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {

  apiHost: string = 'http://localhost:9090/';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  public createAppointment(id:number, dto: FutureAppointmentDto): Observable<FutureAppointmentDto> {
    return this.http.post<FutureAppointmentDto>(this.apiHost + 'BloodBankController/CreateAppointment/'+id, dto, {headers: this.headers});
  }

  public addFreeAppointmentTime(appointment: FreeAppointmentDTO):Observable<FreeAppointmentDTO> {
    return this.http.post<FreeAppointmentDTO>(this.apiHost + 'BloodBankController/CreateFreeAppointment/'+appointment.appointmentId, appointment, {headers: this.headers});
  }
}
