import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Wishlist } from './model/wishlist.model';

const URL = 'http://localhost:8888/api/v1/wishlist';
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
};
const deleteHttpOption = {
  headers: new HttpHeaders({ 'Content-Type': '	text/plain;charset=UTF-8' }),
};



@Injectable({
  providedIn: 'root'
})
export class WishlistService {
  
  
  constructor(private http:HttpClient) { }


  addToWishlist(wishlist:Wishlist):Observable<any>{
    return this.http.post<Wishlist>(URL,wishlist,httpOptions);
   
  }

  getGifWishlist(userId:string):Observable<any>{
    return this.http.get<Wishlist[]>(URL+`/${userId}`);
  }

  removeGifWishlist(userId:String,gifId:string):Observable<any>{
    
   const  deleteWishlistItem ={
     userId :userId,
     gifId : gifId
   } 
    
    return this.http.post(URL+"/deltewishitem",deleteWishlistItem,httpOptions)
  }

  // clearWishlist(){
  //   this.gifs = []
  // }
}
