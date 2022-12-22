export class AppointmentReviewDto {
    bloodBankId?: number;
    customerId?: number;

    public constructor(bbId?: number, cId?: number) {
        if (bbId || cId) {
            this.bloodBankId = bbId;
            this.customerId = cId;
        }
    }
}