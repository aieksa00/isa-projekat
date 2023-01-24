import { LIVE_ANNOUNCER_ELEMENT_TOKEN } from '@angular/cdk/a11y';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { catchError, Observable, throwError } from 'rxjs';
import { QuestionnaireDTO } from 'src/app/DTO/questionnaireDTO';
import { UserService } from 'src/app/services/user.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-questionnaire-page',
  templateUrl: './questionnaire-page.component.html',
  styleUrls: ['./questionnaire-page.component.css']
})
export class QuestionnairePageComponent implements OnInit {
    public appointmentId: any = 0;
    public previousDonations: number = 0;
    public userId : number = 0;
    public question1 : boolean = false;
    public question2 : boolean = false;
    public question3 : boolean = false;
    public question4 : boolean = false;
    public question5 : boolean = false;
    public question6 : boolean = false;
    public question7 : boolean = false;
    public question8 : boolean = false;
    public question9 : boolean = false;
    public question10 : boolean = false;
    public question11 : boolean = false;
    public question12 : boolean = false;
    public question13 : boolean = false;
    public question14 : boolean = false;
    public question15 : boolean = false;
    public question16 : boolean = false;

    public email : any = localStorage.getItem("email");

  constructor(public router: Router, private http: HttpClient) {
    this.getUserIdByEmail(this.email).subscribe(res => {
      this.userId = res;
    })
    this.appointmentId = localStorage.getItem("appointmentId");
  }

  getUserIdByEmail(email : String) : Observable<any> {
    return this.http.post<any>("http://localhost:9090/UserCredentialsController/getUserIdByEmail", email);
  }

  public onSubmit() {
    const questionnaireDTO : QuestionnaireDTO = {
      appointmentId : this.appointmentId,
      previousDonations: this.previousDonations,
      userId : this.userId,
      question1 : this.question1,
      question2 : this.question2,
      question3 : this.question3,
      question4 : this.question4,
      question5 : this.question5,
      question6 : this.question6,
      question7 : this.question7,
      question8 : this.question8,
      question9 : this.question9,
      question10 : this.question10,
      question11 : this.question11,
      question12 : this.question12,
      question13 : this.question13,
      question14 : this.question14,
      question15 : this.question15,
      question16 : this.question16
    }
    this.save(questionnaireDTO).subscribe(res => {});
    this.reset();
    this.router.navigate(['/bloodBanks'])
  }

  save(questionnaireDTO : QuestionnaireDTO) : Observable<any> {
    return this.http.post<any>("http://localhost:9090/AppointmentController/scheduleAppointment", questionnaireDTO).pipe(catchError(this.handleError));
  }

  reset() {
    this.appointmentId = 0;
    this.previousDonations = 0;
    this.userId = 0;
    this.question1 = false;
    this.question2 = false;
    this.question3 = false;
    this.question4 = false;
    this.question5 = false;
    this.question6 = false;
    this.question7 = false;
    this.question8 = false;
    this.question9 = false;
    this.question10 = false;
    this.question11 = false;
    this.question12 = false;
    this.question13 = false;
    this.question14 = false;
    this.question15 = false;
    this.question16 = false;
  }

  public handleError = (error : HttpErrorResponse) => {
    if(error.status == 404) {
      Swal.fire({
        title: 'Warning',
        text: 'Appointment is already taken, please try another one',
        icon: 'warning'
      });
      this.router.navigate(['/bloodBankSpec'])
    }
    return throwError(() => new Error('Something bad happened; please try again later.'));
  }

  cancel() : void {
    this.reset();
    localStorage.removeItem("appointmentId")
    this.router.navigate(['/bloodBankSpec'])
  }

  ngOnInit(): void {
  }

}
