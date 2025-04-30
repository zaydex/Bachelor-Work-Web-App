import {Component} from '@angular/core';
import {RouterLink, RouterOutlet} from '@angular/router';
import {MatIcon} from '@angular/material/icon';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink, MatIcon],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'bachelor-ui';

  menuOpen: boolean = false;

  toggleMenu(){
    this.menuOpen = !this.menuOpen;
  }
}
