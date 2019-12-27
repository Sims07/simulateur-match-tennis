import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TennisMatchSimulatorComponent } from './tennis-match-simulator.component';

describe('TennisMatchSimulatorComponent', () => {
  let component: TennisMatchSimulatorComponent;
  let fixture: ComponentFixture<TennisMatchSimulatorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TennisMatchSimulatorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TennisMatchSimulatorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
