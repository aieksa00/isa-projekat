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
  public bloodbanksFiltered: BloodBanksDTO[] = [];
  public search: string = '';
  public SearchByName: string = '';
  public filterValue: string = '';
  public sortValue : String = "";

  constructor(private bloodBankService: BloodBankService) { }

  ngOnInit(): void {
    this.bloodBankService.getBloodBanks().subscribe(res => {
      this.bloodbanks = res;
      this.bloodbanksFiltered = this.bloodbanks;
    })
  }

  public Reset() :void{
    this.bloodbanksFiltered = this.bloodbanks;
    this.search = "";
    this.SearchByName = "";
    this.filterValue = "";
    this.sortValue = "";
  }

  public SearchBloodBank(): void{
    if(this.SearchByName == 'name'){
        this.bloodbanksFiltered = this.bloodbanks.filter(element => {
          return element.name.includes(this.search) || element.name.includes(this.search.toLowerCase())
        });
    }else{
      this.bloodbanksFiltered = this.bloodbanks.filter(element => {
        return element.city.includes(this.search) || element.city.includes(this.search.toLowerCase())
      });
    }
  }

  public Filter() :void{
    this.bloodbanksFiltered = this.bloodbanksFiltered.filter(element => {
      let value = element.rating >= this.filterValue
      return value;
    });
  }

  public Sort() : void {
    if(this.sortValue === "name") {
      this.bloodbanksFiltered = this.bloodbanksFiltered.sort((a,b) => {
        if (a.name > b.name) {
            return 1;
        }
    
        if (a.name < b.name) {
            return -1;
        }
    
        return 0;
      } )
    } else if(this.sortValue === "city") {
      this.bloodbanksFiltered = this.bloodbanksFiltered.sort((a,b) => {
        if (a.city > b.city) {
            return 1;
        }
    
        if (a.city < b.city) {
            return -1;
        }
    
        return 0;
      } )
    } else if(this.sortValue === "rating") {
      this.bloodbanksFiltered = this.bloodbanksFiltered.sort((a,b) => {
        if (a.rating > b.rating) {
            return 1;
        }
    
        if (a.rating < b.rating) {
            return -1;
        }
    
        return 0;
      } )
    }


  }

}
