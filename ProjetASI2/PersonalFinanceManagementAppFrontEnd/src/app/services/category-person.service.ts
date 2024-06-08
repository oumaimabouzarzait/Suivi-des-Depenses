import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment.prod';
import { CategoryPerson } from '../Model/CategoryPerson';
import { CategoryPersonDto } from '../Model/CategoryPersonDto';

@Injectable({
  providedIn: 'root'
})
export class CategoryPersonService {

  url= `${environment.backUrl}categoryPerson/`

  constructor(private http:HttpClient) { }

  AddCategoryToPerson(categoryPersonDto: CategoryPersonDto,token:string){
    const headers = new HttpHeaders({
      'Authorization': "Bearer "+token
    });
    return this.http.post<CategoryPerson>(`${this.url}AddCategoryToPerson`,categoryPersonDto, {headers})
  }

  UpdateMonthlyLimit(categoryPersonDto: CategoryPersonDto,token:string){
    const headers = new HttpHeaders({
      'Authorization': "Bearer "+token
    });
    return this.http.post<CategoryPerson>(`${this.url}UpdateMonthlyLimit`,categoryPersonDto, {headers})
  }

  getAllCategoryPersonById(personId:any, token:string){
    const headers = new HttpHeaders({
      'Authorization': "Bearer "+token
    });
    return this.http.get<CategoryPerson[]>(`${this.url}getAllCategoryPersonById?personId=${personId}`, {headers})
  }
}
