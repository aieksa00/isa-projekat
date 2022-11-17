import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AllUserInfoDto } from 'src/app/DTO/all-user-info-dto';
import { BloodBankDto } from 'src/app/DTO/blood-bank-dto';
import { UpdateBloodBankDto } from 'src/app/DTO/update-blood-bank-dto';
import { BloodBankService } from 'src/app/services/blood-bank.service';
import { UserCredentialsService } from 'src/app/services/user-credentials.service';

@Component({
  selector: 'app-blood-bank-info',
  templateUrl: './blood-bank-info.component.html',
  styleUrls: ['./blood-bank-info.component.css']
})
export class BloodBankInfoComponent implements OnInit {

  public bloodBankDto: BloodBankDto = new BloodBankDto();
  public updateBloodBankDto: UpdateBloodBankDto = new UpdateBloodBankDto();
  public allUserInfoDto: AllUserInfoDto = new AllUserInfoDto();
  public updateForm: FormGroup | any;
  public userForm: FormGroup | any;
  
  constructor(private bloodBankService: BloodBankService, private userCredentialsService: UserCredentialsService, private router: Router, private fb: FormBuilder) { }

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
    this.bloodBankService.getBloodBankById(1).subscribe(res => {
      this.bloodBankDto = res;
      this.updateForm.get('name').setValue(this.bloodBankDto.bloodBankName);
      this.updateForm.get('street').setValue(this.bloodBankDto.bloodBankStreet);
      this.updateForm.get('city').setValue(this.bloodBankDto.bloodBankCity);
      this.updateForm.get('country').setValue(this.bloodBankDto.bloodBankCountry);
      this.updateForm.get('description').setValue(this.bloodBankDto.bloodBankDescription);
    });
    this.userCredentialsService.getLoggedUser().subscribe(res => {
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


}
