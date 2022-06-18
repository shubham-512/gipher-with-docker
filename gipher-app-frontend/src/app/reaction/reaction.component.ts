import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { GiphyService } from '../giphy.service';
import { WishlistService } from '../wishlist.service';

@Component({
  selector: 'app-reaction',
  templateUrl: './reaction.component.html',
  styleUrls: ['./reaction.component.css']
})
export class ReactionComponent implements OnInit,OnDestroy {

  gifs :any [] = [];
  subscription !: Subscription;
  userId =localStorage.getItem("email") ??  ''

  constructor(private giphyService:GiphyService,private wishlistService:WishlistService) { }

  ngOnInit(): void {
    this.giphyService.getReactionGifs();
    this.subscription=  this.giphyService.getGifs()
    .subscribe((response:any)=>{
      this.gifs = response
    });
  }
  addToWishlist(gif:any){

    let wishlist = {
      userId : this.userId,
      gifId : gif.id,
      title : gif.title,
      name : gif.username,
      imgUrl : gif.images.downsized.url
    }
    
    
    this.wishlistService.addToWishlist(wishlist)
    .subscribe((respose:any)=>{
      console.log(respose)
     
    })
    window.alert("gif added to wishlist")
  }
  ngOnDestroy():void{
    this.subscription.unsubscribe()
  }

}
