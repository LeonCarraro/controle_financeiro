import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Transaction } from './models';
import { environment } from '../../environments/environment';

export class TransactionFilter {
  page = 0;
  size = 6;
  description: string;
  wallets: string;
}

@Injectable({
  providedIn: 'root'
})
export class TransactionsService {

  baseUrl = environment.apiUrl;

  constructor(
    private http: HttpClient
  ) { }

  getAll(filter: TransactionFilter): Promise<any> {
    let urlParams = new HttpParams();
    urlParams = urlParams.append('page', filter.page.toString());
    urlParams = urlParams.append('size', filter.size.toString());

    if (filter.description) {
      urlParams = urlParams.append('name', filter.description);
    }

    if (filter.wallets) {
      const wallets = filter.wallets.toString();
      urlParams = urlParams.append('wallets', wallets);
    }

    return this.http.get(this.baseUrl + '/api/transactions', { params: urlParams })
      .toPromise()
      .then(res => {
        return res;
      });
  }

  save(transaction: Transaction): Promise<any> {
    return this.http.post(this.baseUrl + '/api/transactions', transaction)
      .toPromise()
      .then(res => {
        return res;
      });
  }

}
