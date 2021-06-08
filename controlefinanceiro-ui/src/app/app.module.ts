import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { ButtonModule } from 'primeng/button';
import { TableModule } from 'primeng/table';
import { DialogModule } from 'primeng/dialog';
import { InputTextModule } from 'primeng/inputtext';
import { InputNumberModule } from 'primeng/inputnumber';
import { FormsModule } from '@angular/forms';
import { DropdownModule } from 'primeng/dropdown';
import { CardModule } from 'primeng/card';
import { NgxCurrencyModule } from 'ngx-currency';
import { ClickOutsideModule } from 'ng-click-outside';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { ToastaModule } from 'ngx-toasta';
import { MultiSelectModule } from 'primeng/multiselect';
import { CalendarModule } from 'primeng/calendar';
import { ChartModule } from 'primeng/chart';

import { AppComponent } from './app.component';
import { TransactionsComponent } from './components/transactions/transactions.component';
import { MenubarComponent } from './components/menubar/menubar.component';
import { ErrorMessageComponent } from './components/error-message/error-message.component';
import { LoginComponent } from './components/login/login.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { HomeComponent } from './components/home/home.component';
import { SignupComponent } from './components/signup/signup.component';

import { JwtHelperService, JwtModule } from '@auth0/angular-jwt';
import { AuthHttpInterceptor } from './services/auth-http-interceptor';
import { AuthGuard } from './services/auth.guard';
import { environment } from '../environments/environment';

export function tokenGetter(): string {
  return localStorage.getItem('token');
}

@NgModule({
  declarations: [
    AppComponent,
    TransactionsComponent,
    MenubarComponent,
    ErrorMessageComponent,
    LoginComponent,
    NotFoundComponent,
    HomeComponent,
    SignupComponent
  ],
  imports: [
    JwtModule.forRoot({
      config: {
        tokenGetter,
        allowedDomains: environment.tokenAllowedDomains,
        disallowedRoutes: environment.tokenDisallowedRoutes
      }
    }),
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    ButtonModule,
    TableModule,
    DialogModule,
    InputTextModule,
    InputNumberModule,
    FormsModule,
    DropdownModule,
    CardModule,
    NgxCurrencyModule,
    ClickOutsideModule,
    HttpClientModule,
    ToastaModule.forRoot(),
    MultiSelectModule,
    CalendarModule,
    ChartModule
  ],
  providers: [
    JwtHelperService, {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthHttpInterceptor,
      multi: true
    },
    AuthGuard
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
