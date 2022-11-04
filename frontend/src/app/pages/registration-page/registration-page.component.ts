import { Component, OnInit } from '@angular/core';

interface User {
  name: String;
  /*surname: String;
  address: String;
  city: String;
  country: String;
  phoneNumber: String;
  jmbg: String;
  gender: String;
  profession: String;
  workplace: String;
  userType: String;*/
}

@Component({
  selector: 'app-registration-page',
  templateUrl: './registration-page.component.html',
  styleUrls: ['./registration-page.component.css']
})

export class RegistrationPageComponent implements OnInit {

  public name: String = "";

  constructor() { }

  public onSubmit() {
    const user: User = {
      name : this.name
    }
    console.log(user);
  }

  ngOnInit(): void {
  }

}
