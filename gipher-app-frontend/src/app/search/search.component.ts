import { Component, OnInit } from '@angular/core';
import { GiphyService } from '../giphy.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  gifs :any [] = [];

  constructor(private giphyService:GiphyService) { }

  ngOnInit(): void {
  }
  search(searchTerm:string){
    if(searchTerm != ''){
      this.giphyService.searchGifs(searchTerm);
    }
  }

}
