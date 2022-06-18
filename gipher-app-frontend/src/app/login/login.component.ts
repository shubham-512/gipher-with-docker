import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Login } from '../model/login.model';
import { UserService } from '../user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm !: FormGroup;
  infoMessage = '';
  errorMessage = ''
  

  constructor(private fb:FormBuilder,private userService:UserService,private router:Router) { }

  ngOnInit(): void {
   
    this.loginForm = this.fb.group({
      email : ['',[Validators.required,Validators.email]],
      password : ['' , [Validators.required,Validators.minLength(6)]]
    });
  }

  login(){
    
    this.userService.loginUser( this.loginForm.value)
    .subscribe((response:any)=>{
      console.log(response)
      if(response.status == 200){
        localStorage.setItem("token",response.body.token)
        localStorage.setItem("email" , this.loginForm.value.email)
        this.router.navigate([''])
    
      }
    },error =>{
      this.errorMessage = error.error.message
    })

  }

}
