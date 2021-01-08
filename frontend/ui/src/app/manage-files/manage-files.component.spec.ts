import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageFilesComponent } from './manage-files.component';

describe('ManageFilesComponent', () => {
  let component: ManageFilesComponent;
  let fixture: ComponentFixture<ManageFilesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ManageFilesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageFilesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
