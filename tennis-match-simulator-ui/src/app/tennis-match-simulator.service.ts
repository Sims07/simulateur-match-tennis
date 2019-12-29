import { Injectable, NgZone } from '@angular/core';
import { Observable } from 'rxjs';

import { catchError, delay, shareReplay, tap, map, filter } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class TennisMatchSimulatorService {
  simulatedMatch$: Observable<any>

  baseUrl="http://localhost:8080/tennisMatchSimulation";
  
  constructor(private zone: NgZone) {
    this.simulateTennisMatch();
  }

  simulateTennisMatch(){
    console.log("simulate match")
    this.simulatedMatch$=null;
    this.simulatedMatch$ = Observable.create((observer) => {
      let eventSource = new EventSource(this.baseUrl);
        eventSource.onmessage = (event) => {
          this.zone.run(() => observer.next(JSON.parse(event.data)));
        };
        eventSource.onerror = (error) => {observer.error(error);eventSource.close();}
    })
    .pipe(    
      tap(data => {
        console.log(data);
      }),
      shareReplay()
    )
  }
  
}
