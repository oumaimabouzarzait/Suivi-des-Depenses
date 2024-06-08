import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../services/auth.service';


@Injectable({
  providedIn: 'root'
})
export class HomeGuard implements CanActivate {
  constructor(private authService:AuthService,private router:Router){}
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    return  new Promise((resolve, rejct)=>{
      var token =  localStorage.getItem("token") as string;
      this.authService.validateToken(token).subscribe((data)=>{
       console.log(data)
       resolve(true)
    }, err=>{
      window.location.href = '/login'
      localStorage.clear();
      resolve(false)
     })
     });
  }

 
  
}
