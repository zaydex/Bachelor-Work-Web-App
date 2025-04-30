import {Routes} from '@angular/router';
import {LandingPageComponent} from './landing-page.component';

const HOME_ROUTES: Routes = [
  {
    path: '',
    component: LandingPageComponent,
    providers: []
  }
]

export default HOME_ROUTES;
