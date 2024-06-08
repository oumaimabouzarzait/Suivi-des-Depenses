import { DatePipe } from '@angular/common';
import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { Person } from 'src/app/Model/Person';
import { Transaction } from 'src/app/Model/Transaction';
import { PersonService } from 'src/app/services/person.service';
import { TransactionService } from 'src/app/services/transaction.service';

@Component({
  selector: 'app-monthly-transaction',
  templateUrl: './monthly-transaction.component.html',
  styleUrls: ['./monthly-transaction.component.css']
})
export class MonthlyTransactionComponent implements OnInit {

  id_person : string = "";
  token:string ='';
  TransactionList : Transaction[] =[]
  currentsBudge:Number = 0;

  constructor(private transactionService:TransactionService, private datePipe: DatePipe, private cd: ChangeDetectorRef,
    private personService:PersonService){}

  ngOnInit(): void {
    this.id_person = localStorage.getItem("id_person") as string
    this.token = localStorage.getItem("token") as string
    this.getAllPersonTransactionsInThisMonth();
    this.getPersonById();
  }

  formatDate(dateString: string): string {
    const date = new Date(dateString);
    return this.datePipe.transform(date, 'dd/MM/yy HH:mm:ss') || '';
  }

  getAllPersonTransactionsInThisMonth(){
    this.transactionService.getAllPersonTransactionsInThisMonth(this.id_person,this.token).subscribe((data:Transaction[])=>{
      this.TransactionList = data;
      this.cd.detectChanges();
    },err=>{
      console.log(err);
    })
  }

  getPersonById(){
    this.personService.getPersonById(this.id_person,this.token).subscribe((data:Person)=>{
      console.log(data.currenttotalBudget);
      this.currentsBudge = data.currenttotalBudget;
      console.log(this.currentsBudge);
      this.cd.detectChanges();
    },err=>{
      console.log(err)
    })
  }

}
