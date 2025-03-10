import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MutualFundsCalculatorComponent } from './mutual-funds-calculator.component';

describe('MutualFundsCalculatorComponent', () => {
  let component: MutualFundsCalculatorComponent;
  let fixture: ComponentFixture<MutualFundsCalculatorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MutualFundsCalculatorComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MutualFundsCalculatorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
