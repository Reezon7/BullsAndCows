import { Component, EventEmitter, Output } from '@angular/core';
import { LoginService } from './login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  nickname : string = ""
  isEntered : Boolean = false
  results : any

  @Output() changeNickname = new EventEmitter<string>();

  constructor (private httpService : LoginService){

  }
  onNicknameChange() : void{
    this.isEntered = false
  }
  saveUser() : void {
    if(this.nickname.trim() == ''){
      alert("Никнейм должен быть не пустой!")
      return
    }
    this.isEntered = true
    this.httpService.saveUser(this.nickname).subscribe()
    this.changeNickname.emit(this.nickname)
  }

  getResults() : void {
    this.httpService.getResults(this.nickname).subscribe((data : any) =>{
      this.results = data
    })
  }
}
