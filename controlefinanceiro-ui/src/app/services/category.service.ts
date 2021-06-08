import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Category } from './models';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  baseUrl = environment.apiUrl;

  constructor(
    private http: HttpClient
  ) { }

  getAll(): Promise<any> {
    return this.http.get(this.baseUrl + '/api/categories')
      .toPromise()
      .then(res => {
        return res;
      });
  }

  save(category: Category): Promise<any> {
    return this.http.post(this.baseUrl + '/api/categories', category)
      .toPromise()
      .then(res => {
        return res;
      });
  }

}
