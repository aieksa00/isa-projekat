import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { BloodBankDto } from 'src/app/DTO/blood-bank-dto';
import { BloodBankService } from 'src/app/services/blood-bank.service';

@Component({
  selector: 'app-blood-bank-info',
  templateUrl: './blood-bank-info.component.html',
  styleUrls: ['./blood-bank-info.component.css']
})
export class BloodBankInfoComponent implements OnInit {

  public bloodBankDto: BloodBankDto = new BloodBankDto();
  public descriptionForm: FormGroup | any;
  
  constructor(private bloodBankService: BloodBankService, private router: Router, private fb: FormBuilder) { }

  ngOnInit(): void {
    this.bloodBankService.getBloodBankById(1).subscribe(res => {
      this.bloodBankDto = res;
      this.descriptionForm.get('description').setValue(this.bloodBankDto.bloodBankDescription);
    });
    this.descriptionForm = this.fb.group({
      description: [this.bloodBankDto.bloodBankDescription, [Validators.required, Validators.minLength(10)]]
    });
  }

  updateDescription(){
    this.bloodBankService.updateDescription(this.bloodBankDto.bloodBankId, this.descriptionForm.value.description).subscribe(res => {
      this.router.navigate(['/bloodBankInfo']);
    });
  }


}
