import { Component, OnInit, NgZone } from '@angular/core';
import { Observable } from 'rxjs';
import { TennisMatchSimulatorService } from '../tennis-match-simulator.service';
import { Router } from '@angular/router';

@Component({
  selector: 'tennis-match-simulator',
  templateUrl: './tennis-match-simulator.component.html',
  styleUrls: ['./tennis-match-simulator.component.css']
})
export class TennisMatchSimulatorComponent implements OnInit {

  simulatedMatch$;

  constructor(private tennisMatchSimulator: TennisMatchSimulatorService,private router:Router) { }

  ngOnInit() {
    this.tennisMatchSimulator.simulateTennisMatch();
    this.simulatedMatch$= this.tennisMatchSimulator.simulatedMatch$;
    this.router.routeReuseStrategy.shouldReuseRoute = function(){
      return false;
  }
  
  }
}
