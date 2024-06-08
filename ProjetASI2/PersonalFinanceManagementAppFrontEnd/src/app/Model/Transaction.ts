import { BudgetHistory } from "./BudgetHistory";
import { Category } from "./Category";
import { CategoryLimitHistory } from "./CategoryLimitHistory";
import { PaymentType } from "./PaymentType";
import { Person } from "./Person";

export class Transaction{
    id:Number;
    person :Person | null;
    amount:number;
    date: string;
    imageUrl: String | null;
    category:Category;
    budgetHistory: BudgetHistory;
    categoryLimitHistory: CategoryLimitHistory;
    paymentType: PaymentType|null;

    constructor(id:Number, person :Person | null, amount:number, date:string, imageUrl: String | null, category:Category, budgetHistory: BudgetHistory,
        categoryLimitHistory: CategoryLimitHistory , paymentType: PaymentType|null){
            this.id = id;
            this.person = person
            this.amount = amount;
            this.date = date;
            this.imageUrl = imageUrl;
            this.category = category;
            this.budgetHistory = budgetHistory;
            this.categoryLimitHistory = categoryLimitHistory;
            this.paymentType = paymentType;
        }



}