import { DatePipe } from '@angular/common';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, Observable, throwError } from 'rxjs';
import Swal from 'sweetalert2';

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
    return this.http.post<any>("http://localhost:9090/AppointmentController/unscheduleAppointment", appointment.id).pipe(catchError(this.handleError));
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

  public handleError = (error: HttpErrorResponse) => {
    if(error.status == 409){
      Swal.fire({
        title: 'Error',
        text: 'You can not unschedule appointment 24 hours before',
        icon: 'error'
      });
    }
    return throwError(() => new Error('Something bad happened; please try again later.'));
}

}
