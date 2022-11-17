import { Component, OnInit } from '@angular/core';
import { QuestionnaireDTO } from 'src/app/DTO/questionnaireDTO';

@Component({
  selector: 'app-questionnaire-page',
  templateUrl: './questionnaire-page.component.html',
  styleUrls: ['./questionnaire-page.component.css']
})
export class QuestionnairePageComponent implements OnInit {
    public date : Date = new Date();
    public previousDonations: number = 0;
    public question1 : boolean = false;
    public question2 : boolean = false;
    public question3 : boolean = false;
    public question4 : boolean = false;
    public question5 : boolean = false;
    public question6 : boolean = false;
    public question7 : boolean = false;
    public question8 : boolean = false;
    public question9 : boolean = false;
    public question10 : boolean = false;
    public question11 : boolean = false;
    public question12 : boolean = false;

  constructor() { }

  public onSubmit() {
    const questionnaireDTO : QuestionnaireDTO = {
      date : this.date,
      previousDonations: this.previousDonations,
      question1 : this.question1,
      question2 : this.question2,
      question3 : this.question3,
      question4 : this.question4,
      question5 : this.question5,
      question6 : this.question6,
      question7 : this.question7,
      question8 : this.question8,
      question9 : this.question9,
      question10 : this.question10,
      question11 : this.question11,
      question12 : this.question12
    }
    console.log(questionnaireDTO);
    this.reset();

  }

  reset() {
    this.date = new Date();
    this.previousDonations = 0;
    this.question1 = false;
    this.question2 = false;
    this.question3 = false;
    this.question4 = false;
    this.question5 = false;
    this.question6 = false;
    this.question7 = false;
    this.question8 = false;
    this.question9 = false;
    this.question10 = false;
    this.question11 = false;
    this.question12 = false;
  }

  ngOnInit(): void {
  }

}
