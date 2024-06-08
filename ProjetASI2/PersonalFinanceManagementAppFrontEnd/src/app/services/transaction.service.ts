import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { Transaction } from '../Model/Transaction';
import { TransactionDto } from '../Model/TransactionDto';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {
  url= `${environment.backUrl}transaction/`;

  constructor(private http:HttpClient) { }

  getAllPersonTransactionsByCategory(personId:any, categoryName:String, token:string) : Observable<Transaction[]>{
    const headers = new HttpHeaders({
      'Authorization': "Bearer "+token
    });
    return this.http.get<Transaction[]>(`${this.url}getAllPersonTransactionsByCategory?personId=${personId}&categoryName=${categoryName}`, {headers});
  }

  getAllTransactionsByPersonId(personId:any,token:string) : Observable<Transaction[]>{
    const headers = new HttpHeaders({
      'Authorization': "Bearer "+token
    });
    return this.http.get<Transaction[]>(`${this.url}getAllTransactionsByPersonId?personId=${personId}`,{headers});
  }

  saveTransaction(transactionDto:TransactionDto, token:string) :Observable<Transaction>{
    const headers = new HttpHeaders({
      'Authorization': "Bearer "+token
    });
    return this.http.post<Transaction>(`${this.url}save`, transactionDto, {headers});
  }

  getAllPersonTransactionsInThisMonth(personId:any,token:string): Observable<Transaction[]>{
    const headers = new HttpHeaders({
      'Authorization': "Bearer "+token
    });
    return this.http.get<Transaction[]>(`${this.url}getAllPersonTransactionsInThisMonth?personId=${personId}`,{headers});
  }

}
