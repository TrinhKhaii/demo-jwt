import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {SecurityComponent} from './security.component';
import {LoginComponent} from './login/login.component';
import {InforComponent} from './infor/infor.component';
import {AdminComponent} from './admin/admin.component';
import {SellerComponent} from './seller/seller.component';
import {AuthGuard} from './auth.guard';
import {ErrorPageComponent} from '../error-page/error-page.component';
import {ResetPasswordComponent} from './reset-password/reset-password.component';
import {ResetPassComponent} from './reset-pass/reset-pass.component';


const routes: Routes = [
  {path: 'security', component: SecurityComponent, children: [
      {path: 'login', component: LoginComponent},
      {path: 'error-page', component: ErrorPageComponent},
      {path: 'infor', component: InforComponent},
      {path: 'admin', component: AdminComponent, canActivate: [AuthGuard],
      data: {
        roles: ['ROLE_ADMIN']
      }},
      {path: 'seller', component: SellerComponent, canActivate: [AuthGuard],
      data: {
        roles: ['ROLE_ADMIN', 'ROLE_SELLER']
      }},
      {path: "reset-password", component: ResetPasswordComponent},
      {path: "reset-pass", component: ResetPassComponent}
    ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SecurityRoutingModule { }
