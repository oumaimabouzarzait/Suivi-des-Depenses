import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormRecord } from '@angular/forms';
import { Person } from 'src/app/Model/Person';
import { PersonService } from 'src/app/services/person.service';


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  ngOnInit(): void {
    this.id_person = localStorage.getItem("id_person") as string
    this.token = localStorage.getItem("token") as string
    this.getPersonById()
  }

  person = new Person(null,"","", "", 0, 0, 0);
  id_person : string = "";
  token:string ='';

  constructor(private personService:PersonService, private cd: ChangeDetectorRef){}

  
  getPersonById(){
    this.personService.getPersonById(this.id_person,this.token).subscribe((data:Person)=>{
      this.person = data;
      this.cd.detectChanges();
    },err=>{
      console.log(err)
    })
  }

}
