import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { AuthenticationService } from 'src/app/services/authentication.service';

import { LogInPageComponent } from '../log-in-page/log-in-page.component';

@Component({
  selector: 'app-start-page',
  templateUrl: './start-page.component.html',
  styleUrls: ['./start-page.component.css']
})
export class StartPageComponent implements OnInit {

  constructor(private dialogRef : MatDialog, private router: Router, private cookieService: CookieService, private authenticationService : AuthenticationService) {
  }

  public isLoggedIn: boolean = false;
  public role: string = localStorage.getItem('role')!;

  openLogInDialog() {
    this.dialogRef.open(LogInPageComponent, {
      width: "500px",
    });
  }

  closeLogInDialog() {
    this.dialogRef.closeAll();
  }

  ngOnInit(): void {
    this.isLoggedIn = this.cookieService.get('LoggedIn') === 'true';
  }

  LogOut(){
    this.authenticationService.isAuthenticatedSrc.next(false);
    this.cookieService.deleteAll();
    localStorage.clear();
    this.router.navigate(['/']);
    window.location.reload();
  }

}
