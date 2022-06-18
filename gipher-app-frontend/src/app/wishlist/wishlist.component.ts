import { Component, OnInit } from '@angular/core';
import { Wishlist } from '../model/wishlist.model';
import { WishlistService } from '../wishlist.service';

@Component({
  selector: 'app-wishlist',
  templateUrl: './wishlist.component.html',
  styleUrls: ['./wishlist.component.css']
})
export class WishlistComponent implements OnInit {
  userId = localStorage.getItem("email") ??  ''
   wishlist :Wishlist[] = [];
   wishlistLen ?:number
  

  

   deleteWishlistItem(wishlistItem:Wishlist){
     this.wishlistService.removeGifWishlist(this.userId,wishlistItem.gifId)
     .subscribe((response:any)=>{
       console.log(response)
       this.wishlistService.getGifWishlist(this.userId)
       .subscribe((response:any)=>{
         this.wishlist = response;
         this.wishlistLen = this.wishlist.length})
       
       
     
     })
     
     

   }

  constructor(private wishlistService:WishlistService ) { }

  ngOnInit(): void {
    this.wishlistService.getGifWishlist(this.userId)
    .subscribe((response:any)=>{
      this.wishlist = response;
      this.wishlistLen = this.wishlist.length

    })
  }

}
