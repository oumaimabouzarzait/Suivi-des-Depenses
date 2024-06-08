

export class Person {
    id: number | null;
    firstname: string;
    lastName: string;
    email: string;
    totalBudget: number;
    totalBudgetSpend: number;
    currenttotalBudget: number;


    constructor(
        id: number | null,
        firstname: string,
        lastName: string,
        email: string,
        totalBudget: number,
        totalBudgetSpend: number,
        currenttotalBudget: number
    ) {
        this.id = id;
        this.firstname = firstname;
        this.lastName = lastName;
        this.email = email;
        this.totalBudget = totalBudget;
        this.totalBudgetSpend = totalBudgetSpend;
        this.currenttotalBudget = currenttotalBudget;
    }
}
