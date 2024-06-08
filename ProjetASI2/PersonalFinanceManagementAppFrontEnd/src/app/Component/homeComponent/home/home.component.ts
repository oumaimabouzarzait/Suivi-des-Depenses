import { Component, OnInit } from '@angular/core';
import { ScriptsService } from 'src/app/services/scripts.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{

  constructor(private scriptService:ScriptsService){}

  ngOnInit(): void {
    this.scriptService.loadScripts();
  }

}
