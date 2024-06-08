import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { CommonModule, DatePipe } from '@angular/common'; 

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FooterComponent } from './Component/parts/footer/footer.component';
import { HeaderComponent } from './Component/parts/header/header.component';
import { SidebarComponent } from './Component/parts/sidebar/sidebar.component';

import { HttpClientModule } from '@angular/common/http';
import { VerifyCodeComponent } from './Component/Auhtentification/verify-code/verify-code.component';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './Component/Auhtentification/login/login.component';
import { SignUpComponent } from './Component/Auhtentification/sign-up/sign-up.component';
import { ListTransactionsComponent } from './Component/homeComponent/list-transactions/list-transactions.component';
import { HomeComponent } from './Component/homeComponent/home/home.component';
import { TransactionDetailsComponent } from './Component/homeComponent/transaction-details/transaction-details.component';
import { AddTransactionComponent } from './Component/homeComponent/add-transaction/add-transaction.component';
import { CategoryListComponent } from './Component/homeComponent/category-list/category-list.component';
import { MonthlyTransactionComponent } from './Component/homeComponent/monthly-transaction/monthly-transaction.component';
import { ProfileComponent } from './Component/homeComponent/profile/profile.component';
import { UpdatePersonComponent } from './Component/homeComponent/update-person/update-person.component';

@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    HeaderComponent,
    SidebarComponent,
    VerifyCodeComponent,
    LoginComponent,
    SignUpComponent,
    ListTransactionsComponent,
    HomeComponent,
    TransactionDetailsComponent,
    AddTransactionComponent,
    CategoryListComponent,
    MonthlyTransactionComponent,
    ProfileComponent,
    UpdatePersonComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    CommonModule
  ],
  providers: [
    DatePipe
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
