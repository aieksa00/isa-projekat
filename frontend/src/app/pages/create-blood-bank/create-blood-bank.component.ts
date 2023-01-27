import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { identifierName } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable, catchError, throwError } from 'rxjs';
import { CreateBloodBankDTO } from 'src/app/DTO/create-blood-bank-dto';
import { UserCredentialsDTO } from 'src/app/DTO/user-credentials-dto';
import { BloodBankService } from 'src/app/services/blood-bank.service';
import { UserService } from 'src/app/services/user.service';
import Swal from 'sweetalert2';
import { UserDTO } from '../../DTO/user-dto';

@Component({
  selector: 'app-create-blood-bank',
  templateUrl: './create-blood-bank.component.html',
  styleUrls: ['./create-blood-bank.component.css']
})
export class CreateBloodBankComponent implements OnInit {

  public name: String = "";
  public description: String = "";
  public address: String = "";
  public city: String = "";
  public country: String = "";
  public workingHours: String = "";

  public BloodBankForm: FormGroup | any;
  public bloodBankDTO : CreateBloodBankDTO = new CreateBloodBankDTO();

  constructor(private _userService: UserService,private _bloodbankService: BloodBankService, private router : Router,private http: HttpClient) { }

  ngOnInit(): void {
    this.BloodBankForm = new FormGroup ({
      name : new FormControl(this.bloodBankDTO.name, [
        Validators.required,
      ]),
      description : new FormControl(this.bloodBankDTO.description, [
        Validators.required,
        Validators.minLength(10)
      ]),
      address : new FormControl(this.bloodBankDTO.address, [
        Validators.required,
      ]),
      city : new FormControl(this.bloodBankDTO.city, [
        Validators.required,
      ]),
      country : new FormControl(this.bloodBankDTO.country, [
        Validators.required,
      ]),
      workingHours : new FormControl(this.bloodBankDTO.workingHours, [
        Validators.required,
        Validators.pattern("(((0|1)[0-9])|(2[0-4]))(\-)(((0|1)[0-9])|(2[0-4]))")
      ])
    })
  }

  public onSubmit(){
    this.bloodBankDTO = {
      name : this.BloodBankForm.get("name").value,
      description : this.BloodBankForm.get("description").value,
      address : this.BloodBankForm.get("address").value,
      city : this.BloodBankForm.get("city").value,
      country : this.BloodBankForm.get("country").value,
      workingHours : this.BloodBankForm.get("workingHours").value,
    }
    this.createBloodBank(this.bloodBankDTO).subscribe(res =>{
      Swal.fire({
        title: 'Success',
        text: 'Bloodbank added successfully',
        icon: 'success'
      });
      this.router.navigate(['/bloodBanks'])
    })

  }
  createBloodBank(bloodBank:CreateBloodBankDTO) : Observable<CreateBloodBankDTO>{
    return this.http.post<CreateBloodBankDTO>("http://localhost:9090/BloodBankController/addBloodBank", bloodBank).pipe(catchError(this.handleError));
  }

  public handleError = (error: HttpErrorResponse) => {
    if(error.status == 400){
      Swal.fire({
        title: 'Warning',
        text: 'Please fill in all the fields',
        icon: 'warning'
      });
    }
    return throwError(() => new Error('Something bad happened; please try again later.'));
}
  
}
