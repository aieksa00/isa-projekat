import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserCredentialsDTO } from '../../DTO/user-credentials-dto';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-log-in-page',
  templateUrl: './log-in-page.component.html',
  styleUrls: ['./log-in-page.component.css'],
  providers: [CookieService]
})
export class LogInPageComponent implements OnInit {

  public email: String = "";
  public password: String = "";

  constructor(public router: Router, public cookieService: CookieService) {
  }

  onSubmit() { //TODO proci kroz bazu i pronaci jel kredincijali odgovaraju nekom korisniku
    const userCredentials : UserCredentialsDTO = {
      email : this.email,
      password : this.password,
    }
    console.log(userCredentials);
    this.cookieService.set('LoggedIn', 'true' );
    this.router.navigate(['/bloodBanks']);
  }

  ngOnInit(): void {
  }

}
