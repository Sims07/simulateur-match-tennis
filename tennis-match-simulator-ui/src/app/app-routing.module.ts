import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TennisMatchSimulatorComponent } from './tennis-match-simulator/tennis-match-simulator.component';

const routes: Routes = [ 
  { path: '', redirectTo:'/home', pathMatch:'full' },
  { path: 'home', component: TennisMatchSimulatorComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
