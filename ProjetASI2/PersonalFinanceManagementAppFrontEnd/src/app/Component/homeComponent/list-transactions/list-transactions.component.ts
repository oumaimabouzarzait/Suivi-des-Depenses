import { DatePipe } from '@angular/common';
import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Category } from 'src/app/Model/Category';
import { PaymentType } from 'src/app/Model/PaymentType';
import { Person } from 'src/app/Model/Person';
import { Transaction } from 'src/app/Model/Transaction';
import { TransactionDto } from 'src/app/Model/TransactionDto';
import { CategoryAndpaymentTypeService } from 'src/app/services/category-andpayment-type.service';
import { PersonService } from 'src/app/services/person.service';
import { TransactionService } from 'src/app/services/transaction.service';
import Swal from 'sweetalert2'


@Component({
  selector: 'app-list-transactions',
  templateUrl: './list-transactions.component.html',
  styleUrls: ['./list-transactions.component.css']
})
export class ListTransactionsComponent implements OnInit{

  id_person : string = "";
  token:string ='';
  categoryList : Category[] = [];
  PaymentTypeList : PaymentType[] = [];
  TransactionList : Transaction[] =[]
  currentsBudge:Number = 0;

  formTransaction = new FormGroup({
    id_category: new FormControl(""),
    id_typePayment: new FormControl(""),
    amount: new FormControl("")
  }) 
  $even: any;


  get id_category(){
    return this.formTransaction.get("id_category") as FormControl
  }

  get id_typePayment(){
    return this.formTransaction.get("id_typePayment") as FormControl
  }

  get amount(){
    return this.formTransaction.get("amount") as FormControl
  }

  constructor(private transactionService:TransactionService, private categoryPaymentTypeService:CategoryAndpaymentTypeService
    ,private cd: ChangeDetectorRef, private datePipe: DatePipe, private personService:PersonService){}

  ngOnInit(): void {
    this.id_person = localStorage.getItem("id_person") as string
    this.token = localStorage.getItem("token") as string
    this.getPersonById();
    this.getAllTransactionsByPersonId();
    this.getListOfCategorys();
    this.getListOfPaymentType();
  }

  filterTransactionByCategoryName(even:any){
    var value = even.value;
    if(value == "all"){
      this.getAllTransactionsByPersonId();
      this.cd.detectChanges()
    }else{
      this.getAllPersonTransactionsByCategory(value);
      this.cd.detectChanges()
    }
  }

  formatDate(dateString: string): string {
    const date = new Date(dateString);
    return this.datePipe.transform(date, 'dd/MM/yy HH:mm:ss') || '';
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

  getListOfCategorys(){
    this.categoryPaymentTypeService.getAllCategorys(this.token).subscribe((data:Category[])=>{
      this.categoryList = data;
      this.cd.detectChanges();
    },err=>{
      console.log(err);
    })
  }

  getListOfPaymentType(){
    this.categoryPaymentTypeService.getAllpaymentType(this.token).subscribe((data:PaymentType[])=>{
      this.PaymentTypeList= data;
      this.cd.detectChanges();
    },err=>{
      console.log(err);
    })
  }

  getAllTransactionsByPersonId(){
    this.transactionService.getAllTransactionsByPersonId(this.id_person,this.token).subscribe((data:Transaction[])=>{
      this.TransactionList = data;
      this.cd.detectChanges();
    },err=>{
      console.log(err);
    })
  }


  getAllPersonTransactionsByCategory(categoryName:string){
    this.transactionService.getAllPersonTransactionsByCategory(this.id_person,categoryName,this.token).subscribe((data:Transaction[])=>{
      this.TransactionList = data;
      this.cd.detectChanges();
    },err=>{
      console.log(err);
    })
  }

  saveTransaction(){
    var transactionDto = new TransactionDto(Number(this.id_person), Number(this.id_category.value), Number(this.id_typePayment.value), Number(this.amount?.value), null);
    this.transactionService.saveTransaction(transactionDto,this.token).subscribe((data:Transaction)=>{
      this.TransactionList.push(data);
      this.currentsBudge = data.person?.currenttotalBudget as number
      Swal.fire({
        icon: "success",
        title: "Added",
        text: "Transaction added successfully !!",
      })
      this.cd.detectChanges();
    },err=>{
      Swal.fire({
        icon: "error",
        title: "Oops...",
        text: err.error.errorMessage,
      })
    })
  } 
}
