import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-reset-pass',
  templateUrl: './reset-pass.component.html',
  styleUrls: ['./reset-pass.component.css']
})
export class ResetPassComponent implements OnInit {
  resetPasswordForm: FormGroup = new FormGroup({
    newPassword: new FormControl(),
    comfirmNewPassword: new FormControl()
  });

  constructor() { }

  ngOnInit(): void {
  }

  submit() {

  }
}
