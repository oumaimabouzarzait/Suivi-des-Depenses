import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { Person } from 'src/app/Model/Person';
import { PersonService } from 'src/app/services/person.service';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit  {

  firstName = "";
  lastName = "";
  id_person : string = "";
  token:string ='';
 
  constructor(private personService:PersonService, private cd: ChangeDetectorRef){}
  ngOnInit(): void {
    this.id_person = localStorage.getItem("id_person") as string
    this.token = localStorage.getItem("token") as string
    this.getPersonById();
  }

  getPersonById(){
    this.personService.getPersonById(this.id_person,this.token).subscribe((data:Person)=>{
      console.log(data);
      this.firstName = data.firstname;
      this.lastName = data.lastName;
      this.cd.detectChanges();
    },err=>{
      console.log(err)
    })
  }
  logout(){
    localStorage.clear()
    window.location.href ='/'
  }
}
