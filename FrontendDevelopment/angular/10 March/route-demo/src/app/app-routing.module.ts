import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { ServicesComponent } from './services/services.component';
import { AboutUsComponent } from './about-us/about-us.component';
import { ContactUsComponent } from './contact-us/contact-us.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { WelcomeGuard } from './guards/welcome.guard';


const routes: Routes = [
  { path: '', 
    component: HomeComponent
  }
  ,
  {
    path: 'netbanking',
    component:LoginComponent

  },
  {
    path:'services',
    component: ServicesComponent
  },
  {
    path: 'about-us',
    component: AboutUsComponent

  },
  {
    path: 'home',
    component: HomeComponent
  },
  {
    path: 'contact-us',
    component: ContactUsComponent
  },
  {
    path:'welcome',
    component:WelcomeComponent,
    canActivate: [WelcomeGuard],
    data: ['MANAGER']
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
