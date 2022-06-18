import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor
} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor() {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    console.log("Request Intercepted")
    if(localStorage.getItem("token")){
      let token:any = localStorage.getItem('token')
      request=request.clone({
        setHeaders:{
          Authorization:token
        }
      });
    }
    else{
      console.log("You must login first")
    }
    return next.handle(request);

  }
}
