import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-verify-code',
  templateUrl: './verify-code.component.html',
  styleUrls: ['./verify-code.component.css']
})
export class VerifyCodeComponent implements OnInit {
  code:any
  verfiecode:any = localStorage.getItem('code')
  constructor(private router:Router){}

  ngOnInit(): void {
    var body:any = document.getElementById('kt_app_body')
    body.style = " background-image: url('../../../../assets/media/auth/bg6.jpg')"
    }

  msgError:string =''
  move(e:any, p:any, c:any, n:any){
    if(e.key >= 0 && e.key <=9){
      if(n != ""){
        c.value=e.key;
        c.setAttribute('data-code',e.key)
        setTimeout(() => n.focus(), 10)
      }else{
        c.value=e.key;
        c.setAttribute('data-code',e.key)
      }
      }else if(e.key === "Backspace"){
      if(p!= ""){
        if(c.getAttribute('data-code') !=''){
          c.focus();
          c.setAttribute('data-code','');
        }else{
          c.setAttribute('data-code','');
          setTimeout(() => p.focus(), 10)
        }
      }
    }
  }

  verifyForm= new FormGroup({
    nb1:new FormControl('',[Validators.required,Validators.maxLength(1)]),
    nb2:new FormControl('',[Validators.required,Validators.maxLength(1)]),
    nb3:new FormControl('',[Validators.required,Validators.maxLength(1)]),
    nb4:new FormControl('',[Validators.required,Validators.maxLength(1)]),
    nb5:new FormControl('',[Validators.required,Validators.maxLength(1)]),
    nb6:new FormControl('',[Validators.required,Validators.maxLength(1)])
  })

  verify(){
    this.code=+`${this.verifyForm.value.nb1}${this.verifyForm.value.nb2}${this.verifyForm.value.nb3}${this.verifyForm.value.nb4}${this.verifyForm.value.nb5}${this.verifyForm.value.nb6} `
    this.verfiecode=parseInt(this.verfiecode)
    this.code=parseInt(this.code)
    console.log(this.verfiecode)
    console.log(this.code)
    if(this.code == this.verfiecode){
      localStorage.removeItem('virefyCode')
      localStorage.setItem('changePassWord',"true")
      this.router.navigate(['/changePassword'])
    }else{
      this.msgError='incorrect code';
    }
    this.code=''
  }

}
