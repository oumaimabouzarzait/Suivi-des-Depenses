import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';
import { Person } from '../../../Model/Person';
import { UserDto } from 'src/app/Model/UserDto';
import { Router } from '@angular/router';
import Swal from 'sweetalert2'


@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent {

  constructor(private authservice:AuthService, private router:Router){
  }

  signUpForm = new FormGroup({
    email: new  FormControl(""),
    password: new FormControl(""),
    firstname: new FormControl(""),
    lastName: new FormControl(""),
    role: new FormControl("ROLE_USER")
  })

  get email(){
    return this.signUpForm.get("email") as FormControl
  }
  get password(){
    return this.signUpForm.get("password") as FormControl
  }
  get firstname(){
    return this.signUpForm.get("firstname") as FormControl
  }
  get lastName(){
    return this.signUpForm.get("lastName") as FormControl
  }
  get role(){
    return this.signUpForm.get("lastName") as FormControl
  }


  Register(){
    var person = new Person(null,this.firstname.value, this.lastName.value, this.email.value, 0,0,0);
   
    var userDto = new UserDto(this.email.value,this.password.value, person,"ROLE_USER")
    this.authservice.Register(userDto).subscribe((data:UserDto)=>{
      Swal.fire({
        icon: "success",
        title: "Success",
        text: "Register successfully !!",
      });
      this.router.navigate(['login']);
    }, err=>{
      var errorMesage = (err.error.errorMesage == "Person with email black@gmail.com already exists.") ? "Account already Exist": err.error.errorMesage;
      Swal.fire({
        icon: "error",
        title: "Oops...",
        text: errorMesage,
      });
    })

  }


}
