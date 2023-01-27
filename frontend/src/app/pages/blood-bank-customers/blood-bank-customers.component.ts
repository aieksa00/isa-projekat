import { Component, OnInit } from '@angular/core';
import { UserDTO } from 'src/app/DTO/user-dto';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-blood-bank-customers',
  templateUrl: './blood-bank-customers.component.html',
  styleUrls: ['./blood-bank-customers.component.css']
})
export class BloodBankCustomersComponent implements OnInit {

  public allUsers : UserDTO[] = [];
  public filteredUsers : UserDTO[] = [];
  public sortParam : string = "";

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.userService.GetAllUsersForBloodBank().subscribe(res=>{
      this.allUsers = res;
      this.filteredUsers = this.allUsers;
    })
  }

  public search(){
    this.userService.GetAllFilteredUsersForBloodBank(this.sortParam).subscribe(res=>{
      this.filteredUsers = res;
    })
  }

  public reset(){
    this.filteredUsers = this.allUsers;
    this.sortParam = "";
  }

}
