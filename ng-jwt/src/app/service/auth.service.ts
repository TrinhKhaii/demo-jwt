import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  httpOptions: any;
  isLoggedIn: boolean;

  constructor(private http: HttpClient) { }

  login(obj: any): Observable<any> {
    return this.http.post("http://localhost:8080/api/public/login", obj)
  }


}
