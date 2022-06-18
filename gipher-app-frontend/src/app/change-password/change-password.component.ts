import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Login } from '../model/login.model';
import { UserService } from '../user.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {

  changePasswordForm !: FormGroup
  email:string = localStorage.getItem("email") ??  ''
  login !:Login
  infoMessage = ''
  errorMessage = ''

  constructor(private fb:FormBuilder,private userService:UserService) { }

  ngOnInit(): void {
    this.changePasswordForm  = this.fb.group({
      
      oldPassword : ['',[Validators.required]],
      newPassword  : ['',[Validators.required]],
      

    });
  }

  changePassword(){
    const formValue = this.changePasswordForm.value;
    console.log(this.email,formValue.newPassword)
    
    
    const changePasswordDetails = {
      email : this.email,
      password :formValue.oldPassword,
      newPassword : formValue.newPassword,
     
    }
    this.userService.changePassword(changePasswordDetails)
    .subscribe((response:any)=>{
      console.log(response)
      if(response.status == 200){
        this.infoMessage = response.body.message
        this.errorMessage = ''
        this.changePasswordForm.reset()
      }
    },error =>{
      this.errorMessage = "error in changing password"
    })

  }

}
