import { Component, OnInit } from '@angular/core';
import { TennisMatchSimulatorService } from 'src/app/tennis-match-simulator.service';

@Component({
  selector: 'game-score',
  templateUrl: './game-score.component.html',
  styleUrls: ['./game-score.component.css']
})
export class GameScoreComponent implements OnInit {

  score_1:any;
  score_2:any;
  score:any;

  constructor(private tennisMatchSimulator:TennisMatchSimulatorService) { }

  ngOnInit() {
    this.tennisMatchSimulator.simulatedMatch$.subscribe(
      (data)=> {
        this.score=data.gameScore.score
        this.updateScores();
      }
    )
  }

  updateScores(){
    this.score_1=this.score['_1'];
    this.score_2=this.score['_2'];
  }


}
