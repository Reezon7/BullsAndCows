import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http"

@Injectable()
export class LoginService{
    constructor(private http : HttpClient){
        
    }
    saveUser(nickname : String){
        return this.http.get('http://localhost:8080/users/save-user?nickname='+nickname);
    }

    getResults(nickname : String){
        return this.http.get(`http://localhost:8080/users/${nickname}`);
    }
}