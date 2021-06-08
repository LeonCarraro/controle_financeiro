import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { SignupRequest } from './models';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  baseUrl = environment.apiUrl;

  jwtPayload: any;

  constructor(
    private http: HttpClient,
    private jwtHelper: JwtHelperService
  ) {
    this.loadToken();
  }

  login(username: string, password: string): Promise<void> {
    const body: string = 'username=' + username + '&password=' + password + '&grant_type=password';

    let httpHeaders = new HttpHeaders();
    httpHeaders = httpHeaders.append('Authorization', 'Basic QW5ndWxhcjphbmd1bGFy');
    httpHeaders = httpHeaders.append('Content-Type', 'application/x-www-form-urlencoded');

    return this.http.post(this.baseUrl + '/oauth/token', body, { headers: httpHeaders, withCredentials: true })
      .toPromise()
      .then(res => {
        this.saveToken(res['access_token']);
      }).catch(res => {
        if (res.status === 400 && res.error['error'] === 'invalid_grant') {
          return Promise.reject('Usuário ou senha inválido!');
        }

        return Promise.reject(res);
      });
  }

  refreshToken(): Promise<void> {
    const body: string = 'grant_type=refresh_token';

    let httpHeaders = new HttpHeaders();
    httpHeaders = httpHeaders.append('Authorization', 'Basic QW5ndWxhcjphbmd1bGFy');
    httpHeaders = httpHeaders.append('Content-Type', 'application/x-www-form-urlencoded');

    return this.http.post(this.baseUrl + '/oauth/token', body, { headers: httpHeaders, withCredentials: true })
      .toPromise()
      .then(res => {
        this.saveToken(res['access_token']);
        return Promise.resolve(null);
      }).catch(res => {
        return Promise.resolve(null);
      });
  }

  logout() {
    return this.http.delete(this.baseUrl + '/tokens/revoke', { withCredentials: true })
      .toPromise()
      .then(() => {
        this.deleteToken();
      });
  }

  signup(username: string, password: string): Promise<void> {
    const body = new SignupRequest();
    body.username = username;
    body.password = password;

    return this.http.post(this.baseUrl + '/api/auth/signup', body)
      .toPromise()
      .then(res => {
        return Promise.resolve(null);
      }).catch(error => {
        if (error.status === 409) {
          return Promise.reject(error.error.errors[0]);
        }

        return Promise.reject(error);
      });
  }

  isTokenInvalid() {
    const token = localStorage.getItem('token');

    return !token || this.jwtHelper.isTokenExpired(token);
  }

  deleteToken() {
    localStorage.removeItem('token');
    this.jwtPayload = null;
  }

  private saveToken(token: string) {
    this.jwtPayload = this.jwtHelper.decodeToken(token);
    localStorage.setItem('token', token);
  }

  private loadToken() {
    const token = localStorage.getItem('token');

    if (token) {
      this.saveToken(token);
    }
  }

}
