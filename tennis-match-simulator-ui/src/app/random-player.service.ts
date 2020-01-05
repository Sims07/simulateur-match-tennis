import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RandomPlayerService {

  private baseUrl="http://localhost:4000/randomPlayers";


  constructor(private http:HttpClient) { }

  loadTwoRandomPlayers():Observable<any>{
    return this.http.get(this.baseUrl,{ params: {nbPlayers:'2' } });
  }
}
