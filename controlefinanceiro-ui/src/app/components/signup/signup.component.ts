import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastaService } from 'ngx-toasta';
import { AuthService } from 'src/app/services/auth.service';
import { ErrorHandlerService } from 'src/app/services/error-handler.service';
import { LoginComponent } from '../login/login.component';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  constructor(
    private authService: AuthService,
    private errorHandler: ErrorHandlerService,
    private router: Router,
    private toasta: ToastaService
  ) {}

  ngOnInit(): void {
  }

  signup(username: string, password: string) {
    this.authService.signup(username, password)
      .then(() => {
        this.toasta.success('Conta criada com sucesso!');
        this.authService.login(username, password)
          .then(() => {
            this.router.navigate(['/inicio']);
          }).catch(error => {
            this.errorHandler.handle(error);
        });
      }).catch(error => {
        this.errorHandler.handle(error);
      });
  }

}
