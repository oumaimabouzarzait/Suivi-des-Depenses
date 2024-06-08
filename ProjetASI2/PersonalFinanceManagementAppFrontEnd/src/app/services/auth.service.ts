import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment.prod';
import { UserDto } from '../Model/UserDto';
import { LoginResponseDto } from '../Model/LoginResponseDto';



@Injectable({
  providedIn: 'root'
})
export class AuthService {

  url= `${environment.backUrl}auth/`

  constructor(private http:HttpClient) { }


  Register(userDto:UserDto) : Observable<UserDto>{
    return this.http.post<UserDto>(`${this.url}singUp`, userDto);
  }

  login(userDto:UserDto)  : Observable<LoginResponseDto>{
    return this.http.post<LoginResponseDto>(`${this.url}login`, userDto);
  }

  validateToken(token:string) : Observable<string>{
    console.log(token)
    const headers = new HttpHeaders({
      'Authorization': "Bearer "+token
    });
    console.log("Bearer "+token)
    return this.http.get<string>(`${this.url}validateToken`, {headers});
  }

}
