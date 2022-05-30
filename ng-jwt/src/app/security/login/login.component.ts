import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {AuthService} from '../../service/auth.service';
import {TokenStorageService} from '../../service/token-storage.service';
import {Router} from '@angular/router';
import {ShareService} from '../../service/share.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup = new FormGroup({
    username: new FormControl(),
    password: new FormControl(),
    remember_me: new FormControl()
  });
  roles: string[] = [];
  message: string = "";
  username: string;


  constructor(private authService: AuthService,
              private tokenStorageService: TokenStorageService,
              private shareService: ShareService,
              private router: Router) {
  }

  ngOnInit(): void {
    if (this.tokenStorageService.getToken()) {
      const user = this.tokenStorageService.getUser();
      this.authService.isLoggedIn = true;
      this.roles = this.tokenStorageService.getUser().roles;
      this.username = this.tokenStorageService.getUser().username;
    }
  }

  submit() {
    this.authService.login(this.loginForm.value).subscribe(data => {
      if (this.loginForm.value.remember_me) {
        this.tokenStorageService.saveTokenLocal(data.accessToken);
        this.tokenStorageService.saveUserLocal(data);
        console.log("hihi");
      } else {
        this.tokenStorageService.saveTokenSession(data.accessToken);
        this.tokenStorageService.saveUserLocal(data);
        console.log("hihi");
      }
      this.authService.isLoggedIn = true;
      this.username = this.tokenStorageService.getUser().username;
      this.roles = this.tokenStorageService.getUser().roles;
      this.loginForm.reset();
      console.log(this.username);
      console.log(this.roles);
      this.shareService.sendClickEvent();

      if (this.roles[0] == "ROLE_ADMIN") {
        this.router.navigate(['security/admin']);
      } else if (this.roles[0] == "ROLE_SELLER") {
        this.router.navigate(['security/seller']);
      }
    }, error => {
      this.message = "Sai tên đăng nhập hoặc mật khẩu.";
      this.authService.isLoggedIn = false;
    })
  }
}
