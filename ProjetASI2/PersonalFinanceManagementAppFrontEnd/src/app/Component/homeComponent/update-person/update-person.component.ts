import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Person } from 'src/app/Model/Person';
import { PersonService } from 'src/app/services/person.service';
import Swal from 'sweetalert2'

@Component({
  selector: 'app-update-person',
  templateUrl: './update-person.component.html',
  styleUrls: ['./update-person.component.css']
})
export class UpdatePersonComponent implements OnInit {

  person = new Person(null,"","", "", 0, 0, 0);
  id_person : string = "";
  token:string ='';
  formPerson = new FormGroup({
    firstname: new FormControl(""),
    lastName: new FormControl(""),
    totalBudget: new FormControl("")
  })

  get firstname(){
    return this.formPerson.get("firstname") as FormControl
  }

  get lastName(){
    return this.formPerson.get("lastName") as FormControl
  }


  get totalBudget(){
    return this.formPerson.get("totalBudget") as FormControl
  } 

  constructor(private personService:PersonService, private cd: ChangeDetectorRef, private router:Router){}

  ngOnInit(): void {
    this.id_person = localStorage.getItem("id_person") as string
    this.token = localStorage.getItem("token") as string
    this.getPersonById()
  }

  
  updatePersonInfo(){
    var updatePerson = new Person(this.person.id, this.firstname.value, this.lastName.value, 
      this.person.email, Number(this.totalBudget.value), 0, 0);

      this.personService.updatePerson(updatePerson,this.token).subscribe((data:Person)=>{
        this.person = data;
        console.log(data);
        Swal.fire({
          icon: "success",
          title: "Added",
          text: "Updated successfully !!",
        })
        this.router.navigate(['home/profile/overview'])
      }, err=>{
        Swal.fire({
          icon: "error",
          title: "Oops...",
          text: err.error.errorMessage,
        })
      })
  }


  getPersonById(){
    this.personService.getPersonById(this.id_person,this.token).subscribe((data:Person)=>{
      this.person = data;
      this.firstname.setValue(data.firstname);
      this.lastName.setValue(data.lastName);
      this.totalBudget.setValue(data.totalBudget);
      this.cd.detectChanges();
    },err=>{
      console.log(err)
    })
  }

}
