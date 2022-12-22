import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { BloodBankService } from 'src/app/services/blood-bank.service';

@Component({
  selector: 'app-blood-bank-spec',
  templateUrl: './blood-bank-spec.component.html',
  styleUrls: ['./blood-bank-spec.component.css']
})
export class BloodBankSpecComponent implements OnInit {

  public bloodBankId : any = null;
  public bloodBank : any = null

  constructor(public router: Router, private bloodBankService: BloodBankService) {
    this.bloodBankId = localStorage.getItem("bloodBank");
    this.bloodBankService.getBloodBankById(this.bloodBankId).subscribe(res => {
      this.bloodBank = res;
    })
  }

  public Schedule(appointment : any) : void {
    localStorage.setItem("appointmentId", appointment.appointmentId)
    this.router.navigate(['/questionnairePage'])
  }

  ngOnInit(): void {
  }

}
