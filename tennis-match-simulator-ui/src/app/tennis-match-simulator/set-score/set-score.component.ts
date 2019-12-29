import { Component, OnInit, Input } from '@angular/core';
import { TennisMatchSimulatorService } from 'src/app/tennis-match-simulator.service';

@Component({
  selector: 'set-score',
  templateUrl: './set-score.component.html',
  styleUrls: ['./set-score.component.scss']
})
export class SetScoreComponent implements OnInit {

  @Input()
  setIndex;
  @Input()
  score_1:any;
  @Input()
  score_2:any;

  constructor() { }

  ngOnInit() {
  }


}
