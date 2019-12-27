import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TiebreakScoreComponent } from './tiebreak-score.component';

describe('TiebreakScoreComponent', () => {
  let component: TiebreakScoreComponent;
  let fixture: ComponentFixture<TiebreakScoreComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TiebreakScoreComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TiebreakScoreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
