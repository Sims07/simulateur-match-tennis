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
  
  score_1:any;
  score_2:any;

  constructor(private tennisMatchSimulator:TennisMatchSimulatorService) { }

  ngOnInit() {
    this.tennisMatchSimulator.simulatedMatch$.subscribe(
      (data)=> {
        this.updateScores(data);
      }
    )
  }

  updateScores(data){
    this.score_1=data.setScores[this.setIndex]?data.setScores[this.setIndex].score['_1']:"-";
    this.score_2=data.setScores[this.setIndex]?data.setScores[this.setIndex].score['_2']:"-";
  }

}
