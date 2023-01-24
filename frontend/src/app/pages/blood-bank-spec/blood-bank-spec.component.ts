import { DatePipe } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { SortDTO } from 'src/app/DTO/sort-dto';
import { BloodBankService } from 'src/app/services/blood-bank.service';

@Component({
  selector: 'app-blood-bank-spec',
  templateUrl: './blood-bank-spec.component.html',
  styleUrls: ['./blood-bank-spec.component.css']
})
export class BloodBankSpecComponent implements OnInit {

  public bloodBankId : any = null;
  public bloodBank : any = null
  public appointments : any = [];
  public sortValue : any = null;
  public sortDTO : SortDTO = new SortDTO();

  constructor(private datePipe: DatePipe, public router: Router, private bloodBankService: BloodBankService, private http: HttpClient) {
    this.bloodBankId = localStorage.getItem("bloodBank");
    
  }

  public Schedule(appointment : any) : void {
    localStorage.setItem("appointmentId", appointment.id)
    this.router.navigate(['/questionnairePage'])
  }

  onSubmit() {
    this.sortDTO = {
      sortValue : this.sortValue,
      bloodBankId : this.bloodBankId,
      email : localStorage.getItem("email") as string
    }
    this.getSorted().subscribe(res => {
      this.appointments = res.filter((appointment : any) => {
        return appointment.free && appointment.scheduleDateTime > Date.now()
      })
    })
  }

  public Reset() :void{
    this.getBloodBank(this.bloodBankId).subscribe(res => {
      this.bloodBank = res;
      this.appointments = res.freeAppointments;
    })
    this.sortValue = "";
    this.sortValue = "";
  }

  public getSorted() : Observable<any> {
    return this.http.post<any>("http://localhost:9090/BloodBankController/getSortedAppointments", this.sortDTO);
  }

  public getBloodBank(bloodBankId : any) : Observable<any> {
    const email = localStorage.getItem("email") as string;
    return this.http.post<any>("http://localhost:9090/BloodBankController/BloodBankId/" + bloodBankId, email);
  }

  ngOnInit(): void {
    this.getBloodBank(this.bloodBankId).subscribe(res => {
      this.bloodBank = res;
      this.appointments = res.freeAppointments;
    })
  }

  public getWorkingHours(date : any): any {
    return this.datePipe.transform(date,'dd-MM-yyyy hh-mm-ss' );
  }

}
