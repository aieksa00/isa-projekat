package isaapp.g3malt.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="questionnaire")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Questionnaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "previousDonations", nullable = true)
    private Integer previousDonations;

    @Column(name = "userId", nullable = true)
    private Integer userId;

    @Column(name = "question1", nullable=true)
    private Boolean question1;

    @Column(name = "question2", nullable=true)
    private Boolean question2;

    @Column(name = "question3", nullable=true)
    private Boolean question3;

    @Column(name = "question4", nullable=true)
    private Boolean question4;

    @Column(name = "question5", nullable=true)
    private Boolean question5;

    @Column(name = "question6", nullable=true)
    private Boolean question6;

    @Column(name = "question7", nullable=true)
    private Boolean question7;

    @Column(name = "question8", nullable=true)
    private Boolean question8;

    @Column(name = "question9", nullable=true)
    private Boolean question9;

    @Column(name = "question10", nullable=true)
    private Boolean question10;
    @Column(name = "question11", nullable=true)
    private Boolean question11;

    @Column(name = "question12", nullable=true)
    private Boolean question12;

    @Column(name = "question13", nullable=true)
    private Boolean question13;

    @Column(name = "question14", nullable=true)
    private Boolean question14;

    @Column(name = "question15", nullable=true)
    private Boolean question15;

    @Column(name = "question16", nullable=true)
    private Boolean question16;

    public Questionnaire() {
        super();
    }

    public Questionnaire(Integer id, Integer previousDonations, Integer userId, Boolean question1, Boolean question2, Boolean question3, Boolean question4, Boolean question5, Boolean question6, Boolean question7, Boolean question8, Boolean question9, Boolean question10, Boolean question11, Boolean question12, Boolean question13, Boolean question14, Boolean question15, Boolean question16) {
        this.id = id;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
