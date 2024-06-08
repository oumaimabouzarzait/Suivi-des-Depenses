import { Category } from "./Category";
import { Person } from "./Person";

export class CategoryPerson{
    id:Number;
    category :Category;
    person : Person | null;
    monthlyLimit : Number;
    totalSpent : Number;

    constructor(id:Number, category :Category, person : Person | null, monthlyLimit : Number, totalSpent : Number){
        this.id = id;
        this.category = category;
        this.person = person;
        this.monthlyLimit = monthlyLimit;
        this.totalSpent = totalSpent;
    }
}