import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MonthlyTransactionComponent } from './monthly-transaction.component';

describe('MonthlyTransactionComponent', () => {
  let component: MonthlyTransactionComponent;
  let fixture: ComponentFixture<MonthlyTransactionComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MonthlyTransactionComponent]
    });
    fixture = TestBed.createComponent(MonthlyTransactionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
