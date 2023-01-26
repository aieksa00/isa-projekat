import { Component, OnInit } from '@angular/core';
import { navbarData, navbarDataAdmin } from './nav-data';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.css'],
  providers: [CookieService]
})
export class SidenavComponent implements OnInit {

  constructor(public router: Router, public cookieService: CookieService, private authenticationService : AuthenticationService) { }
  collapsed = false;
  navData = navbarData;
  
  toogleCollapse() : void {
    this.collapsed = !this.collapsed;
  }

  closeSidenav() : void {
    this.collapsed = false;
  }

  onSignOut() {
    this.authenticationService.isAuthenticatedSrc.next(false);
    this.cookieService.deleteAll();
    localStorage.clear();
    this.router.navigate(['/']);
  }

  ngOnInit(): void {
    if(localStorage.getItem("role")=="ADMIN")
        this.navData = navbarDataAdmin
  }

}
