export class CategoryPersonDto{
    monthlyLimit:Number;
    id_category:Number;
    id_person:Number;

    constructor(monthlyLimit:Number, id_category:Number, id_person:Number){
        this.monthlyLimit = monthlyLimit;
        this.id_category = id_category;
        this.id_person = id_person;
    }
}