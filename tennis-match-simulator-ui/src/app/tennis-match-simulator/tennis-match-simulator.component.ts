import { Component, OnInit, NgZone } from '@angular/core';
import { Observable } from 'rxjs';
import { TennisMatchSimulatorService } from '../tennis-match-simulator.service';
import { Router } from '@angular/router';
import { RandomPlayerService } from '../random-player.service';

@Component({
  selector: 'tennis-match-simulator',
  templateUrl: './tennis-match-simulator.component.html',
  styleUrls: ['./tennis-match-simulator.component.css']
})
export class TennisMatchSimulatorComponent implements OnInit {

  simulatedMatch$;
  randomPlayers$;

  constructor(private tennisMatchSimulator: TennisMatchSimulatorService
    ,private randomPlayerService:RandomPlayerService
    ,private router:Router) { }

  ngOnInit() {
    this.simulateTennisMatch();
    this.router.routeReuseStrategy.shouldReuseRoute = function(){
      return false;
    }
  }

  simulateTennisMatch(){
    this.randomPlayers$ = this.randomPlayerService.loadTwoRandomPlayers();
    this.simulatedMatch$ = this.tennisMatchSimulator.simulateTennisMatch();
  }
}
