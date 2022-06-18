import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Login } from './model/login.model';
import { User } from './model/user.model';
const URL = 'http://localhost:8888/api/v1/auth';
const httpOptions = {
  observe: 'response' as const,
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
  
};

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http:HttpClient ) { }

  registerUser(user:User):Observable<any>{
    return this.http.post<User>(URL+"/register",user,httpOptions)

  }

  updateUserProfile(user:User):Observable<any>{
    return this.http.put<User>(URL+"/updateprofile",user,httpOptions)
  }

  changePassword(login:Login):Observable<any>{
    return this.http.put<Login>(URL+"/changepassword",login,httpOptions)
  }

  loginUser(login:Login):Observable<any>{
    return this.http.post<Login>(URL+"/login",login,httpOptions)
  }

  getUserProfile(email:string):Observable<any>{
    return this.http.get<User>(URL+`/userprofile/${email}`)
  }

  loggedIn():boolean{
    return !!localStorage.getItem('token')
  }

  loggedOut(){
    localStorage.clear()
    console.log(localStorage.getItem('token'))
  }
}
