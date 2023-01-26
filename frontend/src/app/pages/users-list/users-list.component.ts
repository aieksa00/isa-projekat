import { Component, OnInit } from '@angular/core';
import { UserDTO } from 'src/app/DTO/user-dto';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.css']
})
export class UsersListComponent implements OnInit {

  public allUsers : UserDTO[] = [];
  public filteredUsers : UserDTO[] = [];
  public searchName : string = "";
  public searchLastName : string = "";

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.userService.getUsers().subscribe(res=>{
      this.allUsers = res;
      this.filteredUsers = this.allUsers;
    })
  }

  public search(){
    this.userService.getFilteredUsers(this.searchName,this.searchLastName).subscribe(res=>{
      this.filteredUsers = res;
    })
  }

  public reset(){
    this.filteredUsers = this.allUsers;
    this.searchName = "";
    this.searchLastName = "";
  }

}
