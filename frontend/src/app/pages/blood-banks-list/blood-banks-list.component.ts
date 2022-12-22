import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { BloodBanksDTO } from 'src/app/DTO/blood-banks-list-dto';
import { SearchDTO } from 'src/app/DTO/search-dto';
import { BloodBankService } from 'src/app/services/blood-bank.service';

@Component({
  selector: 'app-blood-banks-list',
  templateUrl: './blood-banks-list.component.html',
  styleUrls: ['./blood-banks-list.component.css']
})
export class BloodBanksListComponent implements OnInit {

  public search: string = '';
  public searchByName: string = '';
  public filterValue: string = '';
  public sortValue : String = "";
  public searchDTO : SearchDTO = new SearchDTO();
  public bloodBanksFiltered :  BloodBanksDTO[] = [];

  constructor(public router: Router, private bloodBankService: BloodBankService, private http: HttpClient) { }

  ngOnInit(): void {
    this.bloodBankService.getBloodBanks().subscribe(res => {
      this.bloodBanksFiltered = res
    })
  }

  onSubmit() {
    this.searchDTO = {
      search : this.search,
      searchByName : this.searchByName,
      filterValue : this.filterValue,
      sortValue : this.sortValue
    }

    this.getFiltered().subscribe(res => {
      this.bloodBanksFiltered = res;
    })
  }

  getFiltered() : Observable<any> {
    return this.http.post<any>("http://localhost:9090/BloodBankController/getFilteredBloodBanks", this.searchDTO);
  }

  public Reset() :void{
    this.bloodBankService.getBloodBanks().subscribe(res => {
      this.bloodBanksFiltered = res
    })
    this.search = "";
    this.searchByName = "";
    this.filterValue = "";
    this.sortValue = "";
  }

  public Visit(bloodBank : any) : void {
    localStorage.setItem("bloodBank", bloodBank.id)
    this.router.navigate(['/bloodBankSpec'])
  }

}
