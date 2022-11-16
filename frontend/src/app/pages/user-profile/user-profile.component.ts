import { validateHorizontalPosition } from '@angular/cdk/overlay';
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
  public error: String = '';

  constructor(private _userService: UserService) { }

  ngOnInit(): void {
    this._userService.getCustomer(1).subscribe(res => {
      this.user = res;
      this.error = '';
    })
  }

  public updateCustomer(): void {
    if(!this.IsValidInput()){
      this.error = 'Some fields are empty';
      return;
    }
    this.error = '';
    this._userService.updateCustomer(this.user).subscribe(res => {
      this.user = res;
    });
  }

  private IsValidInput():boolean {
    return this.user.name != '' && this.user.surname != '' && this.user.phoneNumber != '' && this.user.password != '' && this.user.jmbg != '';
  }

}
