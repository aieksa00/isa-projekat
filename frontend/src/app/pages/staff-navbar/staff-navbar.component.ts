import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-staff-navbar',
  templateUrl: './staff-navbar.component.html',
  styleUrls: ['./staff-navbar.component.css']
})
export class StaffNavbarComponent implements OnInit {

  constructor(private router: Router, private cookieService: CookieService, private authenticationService : AuthenticationService) { }

  ngOnInit(): void {
  }

  LogOut(){
    this.authenticationService.isAuthenticatedSrc.next(false);
    this.cookieService.deleteAll();
    localStorage.clear();
    this.router.navigate(['/']);
  }

}
