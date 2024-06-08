import { Category } from "./Category";
import { Person } from "./Person";

export class CategoryLimitHistory{

    id:number;
    category:Category;
    person: Person | null;
    monthlyLimit:Number;
    totalSpent: Number;
    dateModified : Date | String;

    constructor(id:number, category:Category, person: Person | null, monthlyLimit:Number, totalSpent: Number, dateModified : Date| String){
        this.id = id;
        this.person = person;
        this.category = category;
        this.monthlyLimit = monthlyLimit;
        this.totalSpent = totalSpent;
        this.dateModified = dateModified;
    }

}