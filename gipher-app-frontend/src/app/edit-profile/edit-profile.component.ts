import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from '../model/user.model';
import { UserService } from '../user.service';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {

  updateProfileForm !: FormGroup;
  infoMessage = ''
  errorMessage = ''
  

  constructor(private fb:FormBuilder,private userService:UserService) { }

  ngOnInit(): void {
    this.updateProfileForm  = this.fb.group({
      email : ['',[Validators.required,Validators.email]],
      name : ['',[Validators.required]],
      address  : ['',[Validators.required]],
      mobilenNumber : ['',[Validators.required,Validators.pattern('^[0-9]{10}$')]],

    });
    
  }

  updateProfile(){
    this.userService.updateUserProfile(this.updateProfileForm.value)
    .subscribe((response:any)=>{
      console.log(response)
      if(response.status == 200){
        this.infoMessage = response.body.message
        this.updateProfileForm.reset();
      }
    },error =>{
      this.errorMessage = error.error.message
    })
  }

}
