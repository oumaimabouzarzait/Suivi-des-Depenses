import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './Component/Auhtentification/login/login.component';
import { SignUpComponent } from './Component/Auhtentification/sign-up/sign-up.component';
import { HomeComponent } from './Component/homeComponent/home/home.component';
import { ListTransactionsComponent } from './Component/homeComponent/list-transactions/list-transactions.component';
import { CategoryListComponent } from './Component/homeComponent/category-list/category-list.component';
import { MonthlyTransactionComponent } from './Component/homeComponent/monthly-transaction/monthly-transaction.component';
import { ProfileComponent } from './Component/homeComponent/profile/profile.component';
import { UpdatePersonComponent } from './Component/homeComponent/update-person/update-person.component';
import { LoginGuard } from './guard/login.guard';
import { HomeGuard } from './guard/home.guard';

const routes: Routes = [
  {path:'login', component: LoginComponent, canActivate: [LoginGuard]},
  {path:'signUp', component: SignUpComponent},
  {path: 'home', component:HomeComponent , children:[
    {path: 'transactionList', component:ListTransactionsComponent},
    {path:'categoryList', component:CategoryListComponent},
    {path: 'profile', children:[
      {path:'overview', component:ProfileComponent},
      {path:'Setting', component:UpdatePersonComponent}
    ]},
    {path:'MonthlyTransaction', component:MonthlyTransactionComponent}
  ],canActivate: [HomeGuard]},
  { path: '', redirectTo: '/login', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
