import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../user.service';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {

  

  constructor(public userService:UserService,private router:Router) { }

  ngOnInit(): void {
  }

  loggedOut(){
    this.userService.loggedOut()
    this.router.navigate(['/login'])
  }
 

}
