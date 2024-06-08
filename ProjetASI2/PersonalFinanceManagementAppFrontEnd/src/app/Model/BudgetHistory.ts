import { Person } from "./Person";

export class BudgetHistory{
    id:Number;
    person:Person | null;
    totalBudget: Number;
    oldBudget:Number;
    newBudget:Number;
    dateModified:Date| String;

    constructor(id:Number, person:Person, totalBudget: Number, oldBudget:Number, newBudget:Number, dateModified:Date| String){
        this.id= id;
        this.person = person;
        this.totalBudget = totalBudget;
        this.oldBudget = oldBudget;
        this.newBudget = newBudget;
        this.dateModified = dateModified;
    }

}