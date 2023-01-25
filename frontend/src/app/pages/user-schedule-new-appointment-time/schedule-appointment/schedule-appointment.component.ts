import { ThisReceiver } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppointmentDto } from 'src/app/DTO/appointment-time-dto';
import { BloodBankAppointmentDto } from 'src/app/DTO/blood-bank-appointment-dto';
import { BloodBanksDTO } from 'src/app/DTO/blood-banks-list-dto';
import { BloodBanksDTO2 } from 'src/app/DTO/blood-banks-list-dto2';
import { BloodBankService } from 'src/app/services/blood-bank.service';

@Component({
  selector: 'app-schedule-appointment',
  templateUrl: './schedule-appointment.component.html',
  styleUrls: ['./schedule-appointment.component.css']
})
export class ScheduleAppointmentComponent implements OnInit {

  public appointmentDto:AppointmentDto = new AppointmentDto();
  public error: String = '';
  public bloodBanks: BloodBanksDTO2[] = [];
  public bloodBanksSorted: BloodBanksDTO2[] = [];
  public bloodBank: BloodBanksDTO2 = new BloodBanksDTO2;
  public appointmentTime: String = '';
  public bloodBankAppointmentDto: BloodBankAppointmentDto = new BloodBankAppointmentDto();

  constructor(public router: Router, private bloodBankService: BloodBankService) { }

  ngOnInit(): void {
  }

  public getBanksWithFreeAppointmentTime():void {
    this.appointmentTime = this.appointmentDto.date + " " + this.appointmentDto.time + ":00";
    this.bloodBankService.getAllBloodBanksWithFreeAppointment(this.appointmentDto.date + " " + this.appointmentDto.time + ":00").subscribe(res => {
      this.bloodBanks = res;
      this.bloodBanksSorted = res;
      console.log(this.bloodBanks)
    });
  }

  public SelectBank(bloodBank: BloodBanksDTO2): void{
    this.bloodBank = bloodBank;
  }

  public SortByRating():void{
    this.bloodBanksSorted = this.bloodBanks.sort((n1,n2) => Number(n2.rating) - Number(n1.rating))
  }

  public Schedule() : void {
    this.bloodBankAppointmentDto.bloodBankId = this.bloodBank.id;
    this.bloodBankAppointmentDto.appointmentTime = this.appointmentTime;
    let id;
    this.bloodBankService.getAppointmentFromBloodBank(this.bloodBankAppointmentDto).subscribe(res => {
      id = res;
      localStorage.setItem("appointmentId", id)
    });
    this.router.navigate(['/questionnairePage'])
  }

}
