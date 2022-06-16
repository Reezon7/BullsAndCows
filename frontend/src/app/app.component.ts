import { Component, DoCheck} from '@angular/core';
import { AppService } from './app.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements DoCheck {
  currentUser : string = ""
  isActive: Boolean = false
  answer : string = ""
  gameNumber: string = ""
  shot : string = ""
  steps : String[] = []
  answers : String[] = []
  result : String = ""
  gameDifficulty : number = 2
  timings : String[] = []
  attempts : number = 0
  totalAttempts : number = 15
  maxTime : number = 5
  time : String = "00:00"
  sec : number = 0
  min : number = 0
  interval : any

  constructor(private httpService : AppService){

  }

  ngDoCheck() {
    if (this.gameDifficulty == 1 && this.min >= this.maxTime){
      this.stopGame()
      if(this.steps.length != 0){
        this.result = "Проигрыш"
        this.time = "0" + this.min + ":" + (this.sec > 9 ? this.sec : "0" + this.sec)
        this.httpService.saveGame(this.currentUser, this.gameNumber, this.result, this.steps, this.timings, this.time, this.attempts).subscribe(() =>
          this.min = 0)
      }
      
    }
  }

  logNickname(nickname : string){
    this.currentUser = nickname
  }

  startGame(){
    if (this.currentUser === ""){
      alert("Введите никнейм!")
      return
    }
    this.shot = ""
    this.time = "00:00"
    this.sec = 0
    this.min = 0
    

    this.isActive = true
    this.steps = []
    this.answers = []
    this.timings = []
    this.attempts = 0
    this.answer = ""
    this.httpService.generateNumber().subscribe((data: any) => {
     this.gameNumber = data.toString().substring(0, 4)
     this.gameDifficulty = data.toString().charAt(4)
     console.log(this.gameNumber)
    })
    this.startTimer()
  }

  checkShot(){
    var bulls : number = 0
    var cows : number = 0
    if(this.shot.length !=4){
      alert("Количество символов должно быть 4!")
      return
    }

    this.timings.push(this.time)
    var userSymbols : string[] = []
    for (var i = 0; i < 4; i++){
      if (this.shot.charAt(i) === this.gameNumber.charAt(i)){
          bulls++
          userSymbols.push(this.shot.charAt(i))
      }else{
        if (this.gameNumber.includes(this.shot.charAt(i)) && 
            !userSymbols.includes(this.shot.charAt(i))){
          cows++
          userSymbols.push(this.shot.charAt(i))
        }
      }
    }

    this.answer = bulls.toString() + "Б" + cows.toString() + "К"
    this.answers.push(this.answer)
    this.steps.push(this.shot)
    

    if(this.answer === "4Б0К"){
      this.endTimer()
      this.answer = "Вы отгадали!!!"
      this.shot = ""
      this.isActive = false
      this.result = "Победа"
      this.httpService.saveGame(this.currentUser, this.gameNumber, this.result, this.steps, this.timings, this.time, this.attempts).subscribe()
    }else{
      if (this.gameDifficulty == 2){
        this.attempts++
        if (this.attempts == 15){
          this.stopGame()
          if(this.steps != []){
            this.time = "0" + this.min + ":" + (this.sec > 9 ? this.sec : "0" + this.sec)
            this.result = "Проигрыш"
            this.httpService.saveGame(this.currentUser, this.gameNumber, this.result, this.steps, this.timings, this.time, this.attempts).subscribe()
          }
        }
      }
    }

  }

  stopGame(){
    this.endTimer()
    if (this.gameDifficulty == 1){
      alert("Время закончилось :( \n Загаданное число: " +this.gameNumber)
    }
    if (this.gameDifficulty == 2){
      alert("Попытки закончились :( \n Загаданное число: " +this.gameNumber)
    }
    this.isActive = false
  }

  startTimer(){
    this.interval = setInterval(() => {
      this.sec++
      if (this.sec == 60){
        this.min++
        this.sec = 0
      }
      this.time = "0" + this.min + ":" + (this.sec > 9 ? this.sec : "0" + this.sec)
    }, 1000)
  }
  endTimer(){
    clearInterval(this.interval)
  }
}
