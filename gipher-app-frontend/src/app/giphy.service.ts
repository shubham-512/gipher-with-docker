import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { environment } from 'src/environments/environment';
import { BehaviorSubject } from 'rxjs';
const giphyApiKey = 'cbNIs8U9Uw0r9KuHqqWOU39vK5Ms7b3y'

@Injectable({
  providedIn: 'root'
})
export class GiphyService {

  gifs = new BehaviorSubject<any>([]);

  constructor(private http:HttpClient) { }

  getTrendingGiphs(){
    return this.http.get(`http://34.125.128.88:8888/v1/gifs/trending?api_key=${giphyApiKey}&limit=25&rating=g`)
    .subscribe((response : any)=>{
      this.gifs.next(response.data)
    });
  }

  searchGifs(gifName:string){
    return this.http.get(`http://34.125.128.88:8888/v1/gifs/search?q=${gifName}&api_key=${giphyApiKey}&limit=25&rating=g`)
    .subscribe((response : any)=>{
      this.gifs.next(response.data)
    });
  }

  getEntertainmentGifs(){
    return this.http.get(`http://34.125.128.88:8888/v1/gifs/search?q=entertainment&api_key=${giphyApiKey}&limit=25&rating=g`)
    .subscribe((response : any)=>{
      this.gifs.next(response.data)
    });
  
  }

  getSportsGifs(){
    return this.http.get(`http://34.125.128.88:8888/v1/gifs/search?q=sports&api_key=${giphyApiKey}&limit=25&rating=g`)
    .subscribe((response : any)=>{
      this.gifs.next(response.data)
    });
  
  }
  getReactionGifs(){
    return this.http.get(`http://34.125.128.88:8888/v1/gifs/search?q=reaction&api_key=${giphyApiKey}&limit=25&rating=g`)
    .subscribe((response : any)=>{
      this.gifs.next(response.data)
    });
  
  }

  getStickerGifs(){
    return this.http.get(`http://34.125.128.88:8888/v1/gifs/search?q=sticker&api_key=${giphyApiKey}&limit=25&rating=g`)
    .subscribe((response : any)=>{
      this.gifs.next(response.data)
    });
  
  }

  getGifs(){
    return this.gifs.asObservable();
  }
}
