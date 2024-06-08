import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
  sector!: string;
  role=""
  
  constructor(private route: ActivatedRoute){}
  ngOnInit(): void {
    this.role = localStorage.getItem("role") as string;
  }

  

}
