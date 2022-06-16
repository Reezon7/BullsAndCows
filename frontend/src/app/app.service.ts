import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http"

@Injectable()
export class AppService{
    constructor(private http : HttpClient){
        
    }
    generateNumber(){
        return this.http.get('http://localhost:8080/games/generate-number');
    }

    saveGame(nickname : String, number : String, result : String, 
        steps : String[], timings : String[], totalTime : String, 
        attempts : number){
        const body = JSON.stringify({
            number,
            result,
            steps,
            timings,
            totalTime,
            attempts
        })

        return this.http.post('http://localhost:8080/games/save-game/'+nickname, body, {headers : new HttpHeaders({ 'Content-Type': 'application/json' })})
    }
}