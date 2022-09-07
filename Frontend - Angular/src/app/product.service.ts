import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, Subject, tap } from 'rxjs';
import { environment } from 'src/environments/environment';
import { CustomResponse } from './interface/CustomResponse';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  private _url = environment.apiBaseUrl;
  private _refreshNeeded$ = new Subject<void>();

  get refreshNeeded$() {
    return this._refreshNeeded$;
  }

  constructor(private http: HttpClient) {}

  mainSearch(pageSize: number, pageNumber: number, data: any = null) {
    var obj: any = {};
    obj.body = data;
    obj.pageSize = pageSize;
    obj.pageNumber = pageNumber;
    return this.http.post<any>(`${this._url}/product/search/page`, obj);
  }

  getProductsHis(pageSize: number, pageNumber: number) {
    var obj: any = {};
    obj.pageSize = pageSize;
    obj.pageNumber = pageNumber;
    return this.http.post<any>(`${this._url}/history/page`, obj);
  }

  addProduct(data: any, bywho: string): Observable<CustomResponse> {
    var obj: any = {};
    obj.requestHeader = { createdBy: bywho };
    obj.body = data;
    return this.http.post<any>(`${this._url}/product/add`, obj).pipe(
      tap(() => {
        this._refreshNeeded$.next();
      })
    );
  }

  deleteProduct(id: number): Observable<CustomResponse> {
    return this.http.delete<any>(`${this._url}/product/delete/${id}`).pipe(
      tap(() => {
        this._refreshNeeded$.next();
      })
    );
  }

  updateProduct(data: any, bywho: string): Observable<CustomResponse> {
    var obj: any = {};
    obj.requestHeader = { createdBy: bywho };
    obj.body = data;
    console.log(obj);
    return this.http.put<any>(`${this._url}/product/update`, obj).pipe(
      tap(() => {
        this._refreshNeeded$.next();
      })
    );
  }
}
