import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router} from '@angular/router';
import { AuthGuardService } from './auth-guard.service';

@Injectable({
  providedIn: 'root'
})
export class RoleGuard implements CanActivate {

  constructor(private authService: AuthGuardService, private router: Router) { } 

  canActivate(route: ActivatedRouteSnapshot): boolean {
    // this will be passed from the route config
    // on the data property
    const expectedRole = route.data['expectedRole'];
    const role = localStorage.getItem('role');
    // decode the token to get its payload
    if (
      !this.authService.isLoggedIn() || 
      !expectedRole.includes(role)
    ) {
      this.router.navigate(['']);
      return false;
    }
    return true;
  }
  
}
