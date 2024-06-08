import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { Category } from '../Model/Category';
import { PaymentType } from '../Model/PaymentType';

@Injectable({
  providedIn: 'root'
})
export class CategoryAndpaymentTypeService {
  
  paymentTypeUrl= `${environment.backUrl}paymentType/`;
  categoryUrl =  `${environment.backUrl}category/`;

  constructor(private http:HttpClient) { }

  getAllCategorys(token:string) : Observable<Category[]>{
    const headers = new HttpHeaders({
      'Authorization': "Bearer "+token
    });
    return this.http.get<Category[]>(`${this.categoryUrl}getAllCategorys`, {headers});
  }

  getAllpaymentType(token:string) : Observable<PaymentType[]>{
    const headers = new HttpHeaders({
      'Authorization': "Bearer "+token
    });
    return this.http.get<PaymentType[]>(`${this.paymentTypeUrl}getAllPaymentTypes`, {headers});
  }

}
