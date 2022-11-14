import { Component, OnInit } from '@angular/core';
import { CustomerDTO } from 'src/app/DTO/customer-dto';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  public user: CustomerDTO = new CustomerDTO;

  constructor(private _userService: UserService) { }

  ngOnInit(): void {
    this._userService.getCustomer(1).subscribe(res => {
      this.user = res;
    })
  }

  public updateRoom(): void {
    this._userService.updateCustomer(this.user).subscribe(res => {
      this.user = res;
    });
  }

}
