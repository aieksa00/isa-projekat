package isaapp.g3malt.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class QuestionnaireDTO {

    public Integer appointmentId;

    public Integer previousDonations;

    public Integer userId;

    public Boolean question1;

    public Boolean question2;

    public Boolean question3;

    public Boolean question4;

    public Boolean question5;

    public Boolean question6;

    public Boolean question7;

    public Boolean question8;

    public Boolean question9;

    public Boolean question10;
    
    public Boolean question11;

    public Boolean question12;

    public Boolean question13;

    public Boolean question14;

    public Boolean question15;

    public Boolean question16;

    public QuestionnaireDTO() {}

    public QuestionnaireDTO(Integer appointmentId, Integer previousDonations, Integer userId, Boolean question1, Boolean question2, Boolean question3, Boolean question4, Boolean question5, Boolean question6, Boolean question7, Boolean question8, Boolean question9, Boolean question10, Boolean question11, Boolean question12, Boolean question13, Boolean question14, Boolean question15, Boolean question16) {
        this.appointmentId = appointmentId;
        this.previousDonations = previousDonations;
        this.userId = userId;
        this.question1 = question1;
        this.question2 = question2;
        this.question3 = question3;
        this.question4 = question4;
        this.question5 = question5;
        this.question6 = question6;
        this.question7 = question7;
        this.question8 = question8;
        this.question9 = question9;
        this.question10 = question10;
        this.question11 = question11;
        this.question12 = question12;
        this.question13 = question13;
        this.question14 = question14;
        this.question15 = question15;
        this.question16 = question16;
    }

    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Integer getPreviousDonations() {
        return previousDonations;
    }

    public void setPreviousDonations(Integer previousDonations) {
        this.previousDonations = previousDonations;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Boolean getQuestion1() {
        return question1;
    }

    public void setQuestion1(Boolean question1) {
        this.question1 = question1;
    }

    public Boolean getQuestion2() {
        return question2;
    }

    public void setQuestion2(Boolean question2) {
        this.question2 = question2;
    }

    public Boolean getQuestion3() {
        return question3;
    }

    public void setQuestion3(Boolean question3) {
        this.question3 = question3;
    }

    public Boolean getQuestion4() {
        return question4;
    }

    public void setQuestion4(Boolean question4) {
        this.question4 = question4;
    }

    public Boolean getQuestion5() {
        return question5;
    }

    public void setQuestion5(Boolean question5) {
        this.question5 = question5;
    }

    public Boolean getQuestion6() {
        return question6;
    }

    public void setQuestion6(Boolean question6) {
        this.question6 = question6;
    }

    public Boolean getQuestion7() {
        return question7;
    }

    public void setQuestion7(Boolean question7) {
        this.question7 = question7;
    }

    public Boolean getQuestion8() {
        return question8;
    }

    public void setQuestion8(Boolean question8) {
        this.question8 = question8;
    }

    public Boolean getQuestion9() {
        return question9;
    }

    public void setQuestion9(Boolean question9) {
        this.question9 = question9;
    }

    public Boolean getQuestion10() {
        return question10;
    }

    public void setQuestion10(Boolean question10) {
        this.question10 = question10;
    }

    public Boolean getQuestion11() {
        return question11;
    }

    public void setQuestion11(Boolean question11) {
        this.question11 = question11;
    }

    public Boolean getQuestion12() {
        return question12;
    }

    public void setQuestion12(Boolean question12) {
        this.question12 = question12;
    }

    public Boolean getQuestion13() {
        return question13;
    }

    public void setQuestion13(Boolean question13) {
        this.question13 = question13;
    }

    public Boolean getQuestion14() {
        return question14;
    }

    public void setQuestion14(Boolean question14) {
        this.question14 = question14;
    }

    public Boolean getQuestion15() {
        return question15;
    }

    public void setQuestion15(Boolean question15) {
        this.question15 = question15;
    }

    public Boolean getQuestion16() {
        return question16;
    }

    public void setQuestion16(Boolean question16) {
        this.question16 = question16;
    }
}
