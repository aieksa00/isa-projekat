import { BehaviorSubject, catchError, Observable, throwError } from "rxjs";
import { UserCredentialsDTO } from "../DTO/user-credentials-dto";
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from "@angular/core";
import { CookieService } from "ngx-cookie-service";

@Injectable({
    providedIn: 'root'
  })
export class AuthenticationService {

    constructor(private http: HttpClient, public cookieService: CookieService) { }

    isAuthenticatedSrc: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(this.cookieService.get('LoggedIn') === 'true');

    get isAuthenticated(): Observable<boolean> {
    return this.isAuthenticatedSrc.asObservable();
    }

    logIn(userCredentialsDTO : UserCredentialsDTO) : Observable<any> {
        return this.http.post<any>("http://localhost:9090/UserCredentialsController/logIn", userCredentialsDTO).pipe(catchError((error: any) => {
            this.isAuthenticatedSrc.next(false);
            return throwError(error);
          }));
    }
}

