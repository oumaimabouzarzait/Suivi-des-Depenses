import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Category } from 'src/app/Model/Category';
import { CategoryPerson } from 'src/app/Model/CategoryPerson';
import { CategoryPersonDto } from 'src/app/Model/CategoryPersonDto';
import { CategoryAndpaymentTypeService } from 'src/app/services/category-andpayment-type.service';
import { CategoryPersonService } from 'src/app/services/category-person.service';
import Swal from 'sweetalert2'

@Component({
  selector: 'app-category-list',
  templateUrl: './category-list.component.html',
  styleUrls: ['./category-list.component.css']
})
export class CategoryListComponent implements OnInit {

  categoryPersonList : CategoryPerson[] = [];
  id_person : string = "";
  token:string ='';
  categoryList : Category[] = [];

  formCategoryPerson = new FormGroup({
    monthlyLimit : new FormControl(""),
    id_category : new FormControl(""),
  })

  formUpdateMonthlyLimit= new FormGroup({
    categoryName: new FormControl(""),
    currentsManthlayLimit : new FormControl(""),
    newmonthlyLimit : new FormControl(""),
    id_category_updated:new FormControl("")
  })

  get categoryName(){
    return this.formUpdateMonthlyLimit.get("categoryName") as FormControl
  }

  get id_category_updated(){
    return this.formUpdateMonthlyLimit.get("id_category_updated") as FormControl
  }

  get currentsManthlayLimit(){
    return this.formUpdateMonthlyLimit.get("currentsManthlayLimit") as FormControl
  }
  get newmonthlyLimit(){
    return this.formUpdateMonthlyLimit.get("newmonthlyLimit") as FormControl
  }

  get monthlyLimit(){
    return this.formCategoryPerson.get("monthlyLimit") as FormControl
  }

  get id_category(){
    return this.formCategoryPerson.get("id_category") as FormControl
  }

  constructor(private categoryPeraonService:CategoryPersonService, private categoryService:CategoryAndpaymentTypeService
    ,private cd: ChangeDetectorRef){

  }

  ngOnInit(): void {
    this.id_person = localStorage.getItem("id_person") as string
    this.token = localStorage.getItem("token") as string
    this.getAllCategoryPersonById();
    this.getListOfCategorys();
  }

  openEditModel(line:number){
    this.currentsManthlayLimit.setValue(this.categoryPersonList[line].monthlyLimit);
    this.categoryName.setValue(this.categoryPersonList[line].category.name);
    this.id_category_updated.setValue(this.categoryPersonList[line].category.id)
    this.cd.detectChanges();
  }

  getListOfCategorys(){
    this.categoryService.getAllCategorys(this.token).subscribe((data:Category[])=>{
      this.categoryList = data;
      this.cd.detectChanges();
    },err=>{
      console.log(err);
    })
  }

  getAllCategoryPersonById(){
    this.categoryPeraonService.getAllCategoryPersonById(this.id_person, this.token).subscribe((data:CategoryPerson[])=>{
      this.categoryPersonList = data;
      this.cd.detectChanges();
    },err=>{
      console.log(err.error);
    })
  }

  addCategoryPerson(){
    var categoryPersonDto = new CategoryPersonDto(Number(this.monthlyLimit.value), Number(this.id_category.value), Number(this.id_person));
    this.categoryPeraonService.AddCategoryToPerson(categoryPersonDto,this.token).subscribe((data:CategoryPerson)=>{
      Swal.fire({
        icon: "success",
        title: "Added",
        text: "Category added successfully !!",
      })
      this.categoryPersonList.push(data);
      this.ngOnInit();
      this.cd.detectChanges();
    },err=>{
      var message = err.error.errorMessage == "Person Category already  exists." ? "You already has this Category!" : err.error.errorMessage;
      Swal.fire({
        icon: "error",
        title: "Oops...",
        text: message,
      })
    })
  }
  UpdateMonthlyLimit(){
    var categoryPersonDto = new CategoryPersonDto(Number(this.newmonthlyLimit.value), Number(this.id_category_updated.value), Number(this.id_person));
    this.categoryPeraonService.UpdateMonthlyLimit(categoryPersonDto,this.token).subscribe((data:CategoryPerson)=>{
      Swal.fire({
        icon: "success",
        title: "Added",
        text: "Monthly Limit successfully !!",
      })
      this.ngOnInit();
      this.cd.detectChanges();
    },err=>{
      var message = err.error.errorMessage == "Person Category already  exists." ? "You already has this Category!" : err.error.errorMessage;
      Swal.fire({
        icon: "error",
        title: "Oops...",
        text: message,
      })
    })
  }

}
