import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EntertainmentComponent } from './entertainment/entertainment.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { ReactionComponent } from './reaction/reaction.component';
import { SearchComponent } from './search/search.component';
import { SportsComponent } from './sports/sports.component';
import { StickerComponent } from './sticker/sticker.component';
import { ProfileComponent } from './profile/profile.component';
import { RegisterComponent } from './register/register.component';
import { WishlistComponent } from './wishlist/wishlist.component';
import { AuthGuard } from './auth.guard';

const routes: Routes = [
  {
    path:'',component:HomeComponent , canActivate : [AuthGuard]
  },
  {
    path:'login', component:LoginComponent
  },
  {
    path:'register', component:RegisterComponent
  },
  {
    path:'entertainment', component:EntertainmentComponent ,canActivate : [AuthGuard]
  },
  {
    path:'sports', component:SportsComponent , canActivate : [AuthGuard]
  },
  {
    path:'reaction', component:ReactionComponent , canActivate : [AuthGuard]
  },
  {
    path:'sticker', component:StickerComponent , canActivate : [AuthGuard]
  },
  // {
  //   path:'search', component:SearchComponent
  // },
  {
    path:'profile', component:ProfileComponent ,canActivate : [AuthGuard]
  },
  {
    path:'wishlist', component:WishlistComponent , canActivate : [AuthGuard]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
