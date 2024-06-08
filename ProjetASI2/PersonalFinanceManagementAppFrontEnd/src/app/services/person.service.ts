import { HttpClient, HttpHandler, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment.prod';
import { Person } from '../Model/Person';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PersonService {

  url= `${environment.backUrl}person/`

  constructor(private http:HttpClient) { }

  updatePerson(person:Person, token: string) : Observable<Person>{
    const headers = new HttpHeaders({
      'Authorization': "Bearer "+token
    });
    return this.http.post<Person>(`${this.url}updatePerson`,person, {headers});
  }

  getPersonById(personId:any, token:string) : Observable<Person>{
    const headers = new HttpHeaders({
      'Authorization': "Bearer "+token
    });
    return this.http.get<Person>(`${this.url}getPersonById?parsonId=${personId}`,{headers})
  }
  
}
