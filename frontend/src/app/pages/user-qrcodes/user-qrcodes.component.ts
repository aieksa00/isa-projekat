import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { AllUserInfoDto } from 'src/app/DTO/all-user-info-dto';

@Component({
  selector: 'app-user-qrcodes',
  templateUrl: './user-qrcodes.component.html',
  styleUrls: ['./user-qrcodes.component.css']
})
export class UserQRCodesComponent implements OnInit {

  public email : any = "";
  public penalties : any = 0;

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.email = localStorage.getItem("email");
    this.getUserPenalty().subscribe( res => {
      this.penalties = res;
    })
  }

  public getUserPenalty(){
    return this.http.post<any>("http://localhost:9090/UserCredentialsController/getCustomerPenaltyByEmail", localStorage.getItem("email"));
  }

}
