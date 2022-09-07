import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductHisComponent } from './product-his.component';

describe('ProductHisComponent', () => {
  let component: ProductHisComponent;
  let fixture: ComponentFixture<ProductHisComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProductHisComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProductHisComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
