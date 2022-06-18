import { Component, OnInit } from '@angular/core';
import { User } from '../model/user.model';
import { UserService } from '../user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  email = localStorage.getItem("email") ??  ''
  user ?:User ;

  constructor(private userService:UserService) { }

  showEditView=false;
  changePassword=false;

  editProfileToggle(){
    this.showEditView=!this.showEditView;
    this.changePassword = false;
  }
  changePasswordToggle(){
    this.changePassword=!this.changePassword;
    this.showEditView=false;
  }
  
  ngOnInit(): void {
    this.userService.getUserProfile(this.email)
    .subscribe((response:any)=>{
      console.log(response)
      this.user = response
      
    })
    
  }

}
