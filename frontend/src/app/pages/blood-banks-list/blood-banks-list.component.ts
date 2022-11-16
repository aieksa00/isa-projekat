import { Component, OnInit } from '@angular/core';
import { BloodBanksDTO } from 'src/app/DTO/blood-banks-list-dto';
import { BloodBankService } from 'src/app/services/blood-bank.service';

@Component({
  selector: 'app-blood-banks-list',
  templateUrl: './blood-banks-list.component.html',
  styleUrls: ['./blood-banks-list.component.css']
})
export class BloodBanksListComponent implements OnInit {

  public bloodbanks: BloodBanksDTO[] = [];

  constructor(private bloodBankService: BloodBankService) { }

  ngOnInit(): void {
    this.bloodBankService.getBloodBanks().subscribe(res => {
      this.bloodbanks = res;
    })
  }

}
