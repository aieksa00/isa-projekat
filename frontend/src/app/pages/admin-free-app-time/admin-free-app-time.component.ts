import { Component, OnInit } from '@angular/core';
import { BloodBankDto } from 'src/app/DTO/blood-bank-dto';
import { BloodBankService } from 'src/app/services/blood-bank.service';

@Component({
  selector: 'app-admin-free-app-time',
  templateUrl: './admin-free-app-time.component.html',
  styleUrls: ['./admin-free-app-time.component.css']
})
export class AdminFreeAppTimeComponent implements OnInit {

  public bloodBankDto: BloodBankDto = new BloodBankDto();
  public error: String = '';
  public timesList: String[] = [];
  public startTime: number = 0;
  public endTime: number = 24;
  //public dateTime:Date = Date.now();


  constructor(private bloodBankService: BloodBankService) { }

  ngOnInit(): void {
    this.bloodBankService.getBloodBankById(1).subscribe(res => {
      this.bloodBankDto = res;
      this.error = '';
      this.startTime = Number(this.bloodBankDto.bloodBankWorkingHours.split("-", 2)[0]);
      this.endTime = Number(this.bloodBankDto.bloodBankWorkingHours.split("-", 2)[1]);
    })
    var today = new Date().toISOString().split('T')[0];
    document.getElementsByName("date")[0].setAttribute('min', today);
    this.populateTimesList();
  }

  public populateTimesList():void {
    this.timesList = ["08:00", "08:30", "09:00", "09:30", "10:00", "10:30","11:00", "11:30","12:00", "12:30","13:00", "13:30","14:00", "14:30"]
  }

  public addFreeAppointmentTime(date: HTMLInputElement): void {
    var timeSelected = (<HTMLInputElement>document.getElementById("time")).value;
    if((Number(timeSelected.split(":", 2)[0]) >= this.startTime) && (Number(timeSelected.split(":", 2)[0]) <= this.endTime)){

    }else{
      alert("Bank is not working on chosen time")
    }
  }

}
