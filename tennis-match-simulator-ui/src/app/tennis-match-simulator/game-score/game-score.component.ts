import { Component, OnInit, Input } from '@angular/core';
import { TennisMatchSimulatorService } from 'src/app/tennis-match-simulator.service';

@Component({
  selector: 'game-score',
  templateUrl: './game-score.component.html',
  styleUrls: ['./game-score.component.css']
})
export class GameScoreComponent implements OnInit {

  @Input()
  score_1:any;
  @Input()
  score_2:any;
  score:any;

  constructor(private tennisMatchSimulator:TennisMatchSimulatorService) { }

  ngOnInit() {
  }

  updateScores(){
    this.score_1=this.score['_1'];
    this.score_2=this.score['_2'];
  }


}
