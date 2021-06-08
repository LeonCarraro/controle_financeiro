import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Wallet } from './models';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class WalletsService {

  baseUrl = environment.apiUrl;

  constructor(
    private http: HttpClient
  ) { }

  getAll(): Promise<any> {
    return this.http.get(this.baseUrl + '/api/wallets')
      .toPromise()
      .then(res => {
        return res;
      });
  }

  save(wallet: Wallet): Promise<any> {
    return this.http.post(this.baseUrl + '/api/wallets', wallet)
      .toPromise()
      .then(res => {
        return res;
      });
  }

}
