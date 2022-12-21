import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-appointment-review-page',
  templateUrl: './appointment-review-page.component.html',
  styleUrls: ['./appointment-review-page.component.css']
})
export class AppointmentReviewPageComponent implements OnInit {
 
  step = 0;

  constructor() { }

  ngOnInit(): void {
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
    
  }

}
