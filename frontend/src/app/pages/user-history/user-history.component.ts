import { DatePipe } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { SortHistoryDTO } from 'src/app/DTO/sortHistoryDTO';

@Component({
  selector: 'app-user-history',
  templateUrl: './user-history.component.html',
  styleUrls: ['./user-history.component.css']
})
export class UserHistoryComponent implements OnInit {

  public appointments : any = [];
  public email : any = "";
  public sortValue : any = null;
  public sortHistoryDTO : SortHistoryDTO = new SortHistoryDTO();

  constructor(private http: HttpClient, private datePipe: DatePipe) { }

  ngOnInit(): void {
    this.email = localStorage.getItem("email");
    this.getUserHistory(this.email).subscribe( res => {
      this.appointments = res;
    })
  }

  public getUserHistory(email : String) {
    return this.http.post<any>("http://localhost:9090/AppointmentController/getAppointmentHistory", email);
  }

  public getWorkingHours(date : any): any {
    return this.datePipe.transform(date,'dd-MM-yyyy hh-mm-ss' );
  }

  onSubmit() {
    this.sortHistoryDTO = {
      sortValue : this.sortValue,
      email : this.email
    }
    this.getSorted().subscribe(res => {
      this.appointments = res;
    })
  }

  public getSorted() : Observable<any> {
    return this.http.post<any>("http://localhost:9090/AppointmentController/getSortedAppointmentHistory", this.sortHistoryDTO);
  }

  public Reset() :void{
    this.getUserHistory(this.email).subscribe(res => {
      this.appointments = res;
    })
    this.sortValue = "";
  }

}
