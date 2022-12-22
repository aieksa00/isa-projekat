import { ThisReceiver } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { AppointmentDto } from 'src/app/DTO/appointment-time-dto';
import { BloodBanksDTO } from 'src/app/DTO/blood-banks-list-dto';
import { BloodBankService } from 'src/app/services/blood-bank.service';

@Component({
  selector: 'app-schedule-appointment',
  templateUrl: './schedule-appointment.component.html',
  styleUrls: ['./schedule-appointment.component.css']
})
export class ScheduleAppointmentComponent implements OnInit {

  public appointmentDto:AppointmentDto = new AppointmentDto();
  public error: String = '';
  public bloodBanks: BloodBanksDTO[] = [];

  constructor(private bloodBankService: BloodBankService) { }

  ngOnInit(): void {
  }

  public getBanksWithFreeAppointmentTime():void {
    this.bloodBankService.getAllBloodBanksWithFreeAppointment(this.appointmentDto.date + " " + this.appointmentDto.time + ":00").subscribe(res => {
      this.bloodBanks = res;
      console.log(this.bloodBanks)
    });
  }

}
