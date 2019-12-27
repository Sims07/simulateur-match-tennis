import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { FlexLayoutModule } from '@angular/flex-layout';
import { TennisMatchSimulatorComponent } from './tennis-match-simulator.component';
import { SetScoreComponent } from './set-score/set-score.component';
import { GameScoreComponent } from './game-score/game-score.component';
import { TiebreakScoreComponent } from './tiebreak-score/tiebreak-score.component';

@NgModule({
  declarations: [
    SetScoreComponent,
    TennisMatchSimulatorComponent,
    GameScoreComponent,
    TiebreakScoreComponent
  ],
  exports: [
               TennisMatchSimulatorComponent
             ],
  imports: [
    BrowserModule,
    FlexLayoutModule
  ],
  providers: []
})
export class TennisMatchSimulatorModule { }
