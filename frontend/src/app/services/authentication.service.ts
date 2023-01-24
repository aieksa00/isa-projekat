import { BehaviorSubject, Observable, throwError } from "rxjs";
import { UserCredentialsDTO } from "../DTO/user-credentials-dto";
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root'
  })
export class AuthenticationService {

    constructor(private http: HttpClient) { }

    isAuthenticatedSrc: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

    get isAuthenticated(): Observable<boolean> {
    return this.isAuthenticatedSrc.asObservable();
    }

    logIn(userCredentialsDTO : UserCredentialsDTO) : Observable<any> {
        return this.http.post<any>("http://localhost:9090/UserCredentialsController/logIn", userCredentialsDTO);
    }
}

