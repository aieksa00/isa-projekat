import { Component, OnInit } from '@angular/core';
import { UserCredentialsDTO } from '../../DTO/user-credentials-dto';

@Component({
  selector: 'app-log-in-page',
  templateUrl: './log-in-page.component.html',
  styleUrls: ['./log-in-page.component.css']
})
export class LogInPageComponent implements OnInit {

  public email: String = "";
  public password: String = "";

  constructor() { }

  onSubmit() { //TODO proci kroz bazu i pronaci jel kredincijali odgovaraju nekom korisniku
    const userCredentials : UserCredentialsDTO = {
      email : this.email,
      password : this.password,
    }
    console.log(userCredentials);
  }

  ngOnInit(): void {
  }

}
