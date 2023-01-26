import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AppointmentReviewDto } from 'src/app/DTO/appointment-review-dto';
import { PenaltyPointDto } from 'src/app/DTO/penalty-point-dto';
import { QuestionnaireDTO } from 'src/app/DTO/questionnaireDTO';
import { UpdateBloodBankStorageDto } from 'src/app/DTO/update-blood-bank-storage-dto';
import { AppointmentService } from 'src/app/services/appointment.service';
import { BloodBankService } from 'src/app/services/blood-bank.service';
import { QuestionnaireService } from 'src/app/services/questionnaire.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-appointment-review-page',
  templateUrl: './appointment-review-page.component.html',
  styleUrls: ['./appointment-review-page.component.css']
})
export class AppointmentReviewPageComponent implements OnInit {
 
  public appointmentId?: number;
  public appointmentReviewDto?: AppointmentReviewDto;
  public appointmentForm: FormGroup | any;

  public questionnaireDto?: QuestionnaireDTO;
  step = 0;

  constructor(private router: Router, private route: ActivatedRoute, private fb: FormBuilder, private userService: UserService, private appointmentService: AppointmentService, private bloodBankService: BloodBankService, private questionnaireService: QuestionnaireService) { }

  ngOnInit(): void {
    this.appointmentId = Number(this.route.snapshot.paramMap.get('id'));
    this.appointmentForm = this.fb.group({
      bloodType: ['', Validators.required],
    });
    this.appointmentService.getAppointmentReviewDtoById(this.appointmentId).subscribe(res => {
      this.appointmentReviewDto = res;
      this.questionnaireService.getQuestionnaireByCustomerId(this.appointmentReviewDto?.customerId!).subscribe(res => {
      this.questionnaireDto = res;
      });
    });
    
  }

  setStep(index: number) {
    this.step = index;
  }

  nextStep() {
    this.step++;
  }

  prevStep() {
    this.step--;
  }

  endAppointment(){
    //this.appointmentService.FinishAppointment(this.appointmentId!).subscribe(res => {
      this.router.navigate(['/staffHomePage']);
    //});
  }

  addPenaltyPointToUser(){
    let dto: PenaltyPointDto = new PenaltyPointDto(this.appointmentReviewDto?.customerId);
    this.userService.addPenaltyPointToUser(dto).subscribe(res => {
      //this.appointmentService.FinishAppointment(this.appointmentId!).subscribe(res => {
        this.router.navigate(['/staffHomePage']);
      //});
    });
  }

  finishAppointment(){
    let dto: UpdateBloodBankStorageDto = new UpdateBloodBankStorageDto(this.appointmentReviewDto?.bloodBankId, this.appointmentForm.value.bloodType);
    this.bloodBankService.updateBloodBankStorage(dto).subscribe(res => {
      //this.appointmentService.FinishAppointment(this.appointmentId!).subscribe(res => {
        this.router.navigate(['/staffHomePage']);
      //});
    });
  }

}
