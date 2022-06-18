import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { catchError, throwError } from 'rxjs';
import { UserService } from '../user.service';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registrationForm !: FormGroup;
  infoMessage = ''
  errorMessage = ''
  

  constructor(private fb: FormBuilder, private userService :  UserService ,private router:Router) { }

  ngOnInit(): void {

    this.registrationForm = this.fb.group({
      email : ['',[Validators.required,Validators.email]],
      password : ['' , [Validators.required,Validators.minLength(6)]],
      name : ['',[Validators.required]],
      address  : ['',[Validators.required]],
      mobilenNumber : ['',[Validators.required,Validators.pattern('^[0-9]{10}$')]],

    });
  }

  register(){
    this.userService.registerUser(this.registrationForm.value)
    .subscribe((response:any)=>{
      console.log(response)
      if(response.status == 201){
        this.infoMessage = "user register succesfull"
        this.errorMessage = ''
        
        setTimeout(() => {
          this.router.navigate(['/login'])
      }, 3000);  

      }
    },error =>{
      this.errorMessage = error.error.message
    })

  }
  
  
  

  

}
