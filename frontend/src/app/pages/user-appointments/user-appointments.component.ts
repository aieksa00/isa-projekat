import { DatePipe } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-user-appointments',
  templateUrl: './user-appointments.component.html',
  styleUrls: ['./user-appointments.component.css']
})
export class UserAppointmentsComponent implements OnInit {

  public appointments : any = []

  constructor(private datePipe: DatePipe, public router: Router, private http: HttpClient) { 
    this.getNotFree().subscribe(res => {
      this.appointments = res;
    })
  }

  public getNotFree() : Observable<any> {
    return this.http.get<any>("http://localhost:9090/AppointmentController/getScheduledAppointments");
  }

  public CancelAppointment(appointment : any) : Observable<any> {
    return this.http.post<any>("http://localhost:9090/AppointmentController/unscheduleAppointment", appointment.id);
  }

  ngOnInit(): void {
  }

  public Cancel(appointment : any) {
    this.CancelAppointment(appointment).subscribe(res => {
      this.appointments = res;
    });
  }

  public getWorkingHours(date : any): any {
    return this.datePipe.transform(date,'MM-dd-yyyy hh-mm-ss' );
  }

}
