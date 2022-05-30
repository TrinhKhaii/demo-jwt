import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  constructor(private http: HttpClient) {}

  findAccountByUserName(username: string): Observable<any> {
    return this.http.get<any>("http://localhost:8080/api/public/find-by-username?username=" + username);
  }

  checkCode(username: string, code: String) {
    return this.http.get<any>("http://localhost:8080/api/public/check-code?username=" + username + "&code=" + code);
  }
}
