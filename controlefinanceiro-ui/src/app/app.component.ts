import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ToastaConfig } from 'ngx-toasta';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  constructor(
    private router: Router,
    private toastaConfig: ToastaConfig
  ) {
    this.toastaConfig.theme = 'bootstrap';
  }

  showMenubar(): boolean {
    return this.router.url !== '/login' && this.router.url !== '/signup' && this.router.url !== '/not-found';
  }

}
