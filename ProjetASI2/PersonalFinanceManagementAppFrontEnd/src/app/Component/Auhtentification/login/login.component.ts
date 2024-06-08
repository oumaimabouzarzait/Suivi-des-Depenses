import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginResponseDto } from 'src/app/Model/LoginResponseDto';
import { UserDto } from 'src/app/Model/UserDto';
import { AuthService } from 'src/app/services/auth.service';
import Swal from 'sweetalert2'

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private authService:AuthService, private router:Router){}
  ngOnInit(): void {
  }

  formLogin = new FormGroup({
    email : new FormControl(""),
    password : new FormControl("")
  })

  get email(){
    return this.formLogin.get("email") as FormControl
  }

  get password(){
    return this.formLogin.get("password") as FormControl
  }

  login(){
    var userDto = new UserDto(this.email.value, this.password.value, null,null);
    this.authService.login(userDto).subscribe((loginResponseDto :LoginResponseDto)=>{
      localStorage.setItem("token", loginResponseDto.token as string);
      localStorage.setItem("id_person", loginResponseDto.user?.id as string);
      localStorage.setItem("email", loginResponseDto.user?.email as string);
      localStorage.setItem("role", loginResponseDto.user?.role as string);
      this.router.navigate(['home/transactionList']);
    }, err=>{
      Swal.fire({
        icon: "error",
        title: "Oops...",
        text: err.error.errorMesage
      });
    })
  }


  

}
