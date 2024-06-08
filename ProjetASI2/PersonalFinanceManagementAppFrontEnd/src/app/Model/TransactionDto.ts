export class TransactionDto{
    person_id : Number;
    id_category: Number;
    id_typePayment : Number;
    amount:Number;
    imageUrl : String | null;

    constructor(person_id : Number, id_category: Number, id_typePayment : Number, amount:Number ,imageUrl : String | null){
        this.person_id = person_id;
        this.id_category = id_category;
        this.id_typePayment = id_typePayment;
        this.amount = amount;
        this.imageUrl = imageUrl;
    }
}