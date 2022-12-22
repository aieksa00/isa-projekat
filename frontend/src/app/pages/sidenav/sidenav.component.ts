import { Component, OnInit } from '@angular/core';
import { navbarData } from './nav-data';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.css'],
  providers: [CookieService]
})
export class SidenavComponent implements OnInit {

  constructor(public router: Router, public cookieService: CookieService) { }
  collapsed = false;
  navData = navbarData;
  
  toogleCollapse() : void {
    this.collapsed = !this.collapsed;
  }

  closeSidenav() : void {
    this.collapsed = false;
  }

  onSignOut() {
    this.cookieService.deleteAll();
    localStorage.clear();
    this.router.navigate(['/']);
  }

  ngOnInit(): void {
  }

}
