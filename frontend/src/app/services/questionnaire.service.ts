import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { QuestionnaireDTO } from '../DTO/questionnaireDTO';

@Injectable({
  providedIn: 'root'
})
export class QuestionnaireService {

  apiHost: string = 'http://localhost:9090/';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  public getQuestionnaireByCustomerId(id: number): Observable<QuestionnaireDTO> {
    return this.http.get<QuestionnaireDTO>(this.apiHost + 'questionnaireController/GetByCustomerId/' + id, {headers: this.headers});
  }

}