import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  baseUrl = environment.apiUrl;

  constructor(
    private http: HttpClient
  ) { }

  getGeneralStatistics(): Promise<any> {
    return this.http.get(this.baseUrl + '/api/transactions/statistics/general')
      .toPromise()
      .then(res => {
        return res;
      });
  }

}
