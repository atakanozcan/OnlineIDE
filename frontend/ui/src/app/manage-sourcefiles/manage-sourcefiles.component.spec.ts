import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageSourcefilesComponent } from './manage-sourcefiles.component';

describe('ManageSourcefilesComponent', () => {
  let component: ManageSourcefilesComponent;
  let fixture: ComponentFixture<ManageSourcefilesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ManageSourcefilesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageSourcefilesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
