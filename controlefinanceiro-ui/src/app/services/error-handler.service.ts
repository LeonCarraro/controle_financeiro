import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { ToastaService } from 'ngx-toasta';
import { NotAuthenticatedError } from './auth-http-interceptor';

@Injectable({
  providedIn: 'root'
})
export class ErrorHandlerService {

  constructor(
    private toasta: ToastaService,
    private router: Router
  ) {}

  handle(errorResponse: any) {
    let msg: string;

    if (typeof errorResponse === 'string') {
      msg = errorResponse;
    } else if (errorResponse instanceof NotAuthenticatedError) {
      msg = 'Sua sessão expirou!';
      this.router.navigate(['/login']);
    } else {
      msg = 'Erro ao processar serviço remoto!';
    }

    this.toasta.error(msg);
  }

}
