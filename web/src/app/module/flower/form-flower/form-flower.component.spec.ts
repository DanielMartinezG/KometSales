import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormFlowerComponent } from './form-flower.component';

describe('FormCamionComponent', () => {
  let component: FormFlowerComponent;
  let fixture: ComponentFixture<FormFlowerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormFlowerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormFlowerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
