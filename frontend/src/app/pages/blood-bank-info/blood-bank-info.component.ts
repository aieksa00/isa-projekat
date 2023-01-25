import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FutureAppointmentDto } from 'src/app/DTO/future-appointment';
import { AllUserInfoDto } from 'src/app/DTO/all-user-info-dto';
import { BloodBankDto } from 'src/app/DTO/blood-bank-dto';
import { UpdateBloodBankDto } from 'src/app/DTO/update-blood-bank-dto';
import { BloodBankService } from 'src/app/services/blood-bank.service';
import { UserCredentialsService } from 'src/app/services/user-credentials.service';
import { AppointmentService } from 'src/app/services/appointment.service';

@Component({
  selector: 'app-blood-bank-info',
  templateUrl: './blood-bank-info.component.html',
  styleUrls: ['./blood-bank-info.component.css']
})
export class BloodBankInfoComponent implements OnInit {

  public bloodBankDto: BloodBankDto = new BloodBankDto();
  public updateBloodBankDto: UpdateBloodBankDto = new UpdateBloodBankDto();

  public allUserInfoDto: AllUserInfoDto = new AllUserInfoDto();

  public futureAppointmentDto: FutureAppointmentDto = new FutureAppointmentDto();

  public updateForm: FormGroup | any;
  public userForm: FormGroup | any;
  public appointmentForm: FormGroup | any;

  constructor(private bloodBankService: BloodBankService, private userCredentialsService: UserCredentialsService,private appointmentService: AppointmentService, private router: Router, private fb: FormBuilder) { }

  ngOnInit(): void {
    this.updateForm = this.fb.group({
      name: ['', Validators.required],
      street: ['', Validators.required],
      city: ['', Validators.required],
      country: ['', Validators.required],
      description: ['', [Validators.required, Validators.minLength(10)]]
    });
    this.userForm = this.fb.group({
      userName: ['', [Validators.required]],
      userSurname: ['', [Validators.required]],
      userStreet: ['', Validators.required],
      userCity: ['', Validators.required],
      userCountry: ['', Validators.required],
      userPhoneNumber: ['', [Validators.required]],
      userJmbg: ['', [Validators.required]],
      userGender: [0, [Validators.required]],
      userProfession: ['', [Validators.required]],
      userWorkplace: ['', [Validators.required]],
      userCredentialsEmail: ['', [Validators.required]],
      userCredentialsPassword: ['', [Validators.required]]
    });
    this.appointmentForm = this.fb.group({
      scheduleDateTime: ['', Validators.required],
      duration: ['', Validators.required],
      time: ['', Validators.required],
      medicalStaffOne: ['', Validators.required],
      medicalStaffTwo: [''],
      medicalStaffThree: ['']
    });
    this.bloodBankService.getBloodBankByUserEmail().subscribe(res => {
      this.bloodBankDto = res;
      this.updateForm.get('name').setValue(this.bloodBankDto.bloodBankName);
      this.updateForm.get('street').setValue(this.bloodBankDto.bloodBankStreet);
      this.updateForm.get('city').setValue(this.bloodBankDto.bloodBankCity);
      this.updateForm.get('country').setValue(this.bloodBankDto.bloodBankCountry);
      this.updateForm.get('description').setValue(this.bloodBankDto.bloodBankDescription);
    });
    this.userCredentialsService.GetLoggedUserByEmail().subscribe(res => {
      this.allUserInfoDto = res;
      this.userForm.get('userName').setValue(this.allUserInfoDto.userName);
      this.userForm.get('userSurname').setValue(this.allUserInfoDto.userSurname);
      this.userForm.get('userStreet').setValue(this.allUserInfoDto.userStreet);
      this.userForm.get('userCity').setValue(this.allUserInfoDto.userCity);
      this.userForm.get('userCountry').setValue(this.allUserInfoDto.userCountry);
      this.userForm.get('userPhoneNumber').setValue(this.allUserInfoDto.userPhoneNumber);
      this.userForm.get('userJmbg').setValue(this.allUserInfoDto.userJmbg);
      this.userForm.get('userGender').setValue(this.allUserInfoDto.userGender);
      this.userForm.get('userProfession').setValue(this.allUserInfoDto.userProfession);
      this.userForm.get('userWorkplace').setValue(this.allUserInfoDto.userWorkplace);
      this.userForm.get('userCredentialsEmail').setValue(this.allUserInfoDto.userCredentialsEmail);
      this.userForm.get('userCredentialsPassword').setValue(this.allUserInfoDto.userCredentialsPassword);
    });
  }

  updateDescription(){
    this.updateBloodBankDto.bloodBankName = this.updateForm.value.name;
    this.updateBloodBankDto.bloodBankStreet = this.updateForm.value.street;
    this.updateBloodBankDto.bloodBankCity = this.updateForm.value.city;
    this.updateBloodBankDto.bloodBankCountry = this.updateForm.value.country;
    this.updateBloodBankDto.bloodBankDescription = this.updateForm.value.description;
    this.bloodBankService.updateBloodBank(this.bloodBankDto.bloodBankId, this.updateBloodBankDto).subscribe(res => {
      this.router.navigate(['/bloodBankInfo']);
    });
  }
  updateUser(){
    this.allUserInfoDto.userName = this.userForm.value.userName;
    this.allUserInfoDto.userSurname = this.userForm.value.userSurname;
    this.allUserInfoDto.userStreet = this.userForm.value.userStreet;
    this.allUserInfoDto.userCity = this.userForm.value.userCity;
    this.allUserInfoDto.userCountry = this.userForm.value.userCountry;
    this.allUserInfoDto.userPhoneNumber = this.userForm.value.userPhoneNumber;
    this.allUserInfoDto.userJmbg = this.userForm.value.userJmbg;
    this.allUserInfoDto.userGender = this.userForm.value.userGender;
    this.allUserInfoDto.userProfession = this.userForm.value.userProfession;
    this.allUserInfoDto.userWorkplace = this.userForm.value.userWorkplace;
    this.allUserInfoDto.userCredentialsEmail = this.userForm.value.userCredentialsEmail;
    this.allUserInfoDto.userCredentialsPassword = this.userForm.value.userCredentialsPassword;
    this.userCredentialsService.UpdateLoggedUser(this.allUserInfoDto).subscribe(res => {
      this.router.navigate(['/bloodBankInfo']);
    });
  }
  createAppointment(){
    this.futureAppointmentDto.scheduleDateTime = this.appointmentForm.value.scheduleDateTime + " " + this.appointmentForm.value.time + ":00:00";
    this.futureAppointmentDto.duration = this.appointmentForm.value.duration;
    this.futureAppointmentDto.time = this.appointmentForm.value.time;
    this.futureAppointmentDto.medicalStaff.push(this.appointmentForm.value.medicalStaffOne);
    if(this.appointmentForm.value.medicalStaffTwo!='')
      this.futureAppointmentDto.medicalStaff.push(this.appointmentForm.value.medicalStaffTwo);
    if(this.appointmentForm.value.medicalStaffThree!='')
      this.futureAppointmentDto.medicalStaff.push(this.appointmentForm.value.medicalStaffThree);

    this.appointmentService.createAppointment(1, this.futureAppointmentDto).subscribe(res => {
      this.router.navigate(['/bloodBankInfo']);
    });
  }
}
