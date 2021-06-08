import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { ErrorHandlerService } from '../../services/error-handler.service';

@Component({
  selector: 'app-menubar',
  templateUrl: './menubar.component.html',
  styleUrls: ['./menubar.component.css']
})
export class MenubarComponent implements OnInit {

  showUserMenu = false;
  isMobile: boolean;

  constructor(
    public authService: AuthService,
    private errorHandler: ErrorHandlerService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.isMobile = this.getIsMobile();
    window.onresize = () => {
      this.isMobile = this.getIsMobile();
    };
  }

  logout() {
    this.authService.logout()
      .then(() => {
        this.router.navigate(['/login']);
      }).catch(error => {
        this.errorHandler.handle(error);
      });
  }

  getIsMobile(): boolean {
    const width = document.documentElement.clientWidth;
    const breakpoint = 725;
    if (width < breakpoint) {
      return true;
    } else {
      return false;
    }
  }

  closeUserMenu() {
      if (this.showUserMenu) {
        this.showUserMenu = false;
      }
  }

}
