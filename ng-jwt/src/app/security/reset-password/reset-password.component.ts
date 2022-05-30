import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {AccountService} from '../../service/account.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {

  resetPasswordForm: FormGroup = new FormGroup({
    username: new FormControl()
  });
  confirmCode: string;
  errorMessage: string;


  constructor(private accountService: AccountService,
              private router: Router) { }

  ngOnInit(): void {
  }

  submit(confirmBtn: HTMLButtonElement) {
    this.accountService.findAccountByUserName(this.resetPasswordForm.value.username).subscribe(() => {
      console.log("Thanh cong");
      confirmBtn.click();
    }, error => {
      console.log("that bai");
    });
  }

  checkCode(closeModal: HTMLButtonElement) {
    this.accountService.checkCode(this.resetPasswordForm.value.username, this.confirmCode).subscribe(res => {
      closeModal.click();
      this.router.navigate(['security/reset-pass'])
    }, error => {
      this.errorMessage = "Nhập ngu"
    })
  }
}
