import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Route, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class LoginGuard implements CanActivate {
  constructor(private authService:AuthService,private router:Router){}
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    return new Promise((resolve, rejct)=>{
     var token =  localStorage.getItem("token") as string;
     if(token != null){
      this.authService.validateToken(token).subscribe((data)=>{
        window.location.href = '/home/transactionList'
        resolve(false)
      }, err=>{
        resolve(true)
      })
     }else{
      resolve(true)
     }
    })
  }
  
}
