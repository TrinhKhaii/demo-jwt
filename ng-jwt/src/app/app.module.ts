import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { SecurityComponent } from './security/security.component';
import {AppRoutingModule} from './app-routing.module';
import {SecurityModule} from './security/security.module';
import {RouterModule} from '@angular/router';
import { AdminComponent } from './security/admin/admin.component';
import { SellerComponent } from './security/seller/seller.component';
import { HeaderComponent } from './component/header/header.component';
import { FooterComponent } from './component/footer/footer.component';
import { ErrorPageComponent } from './error-page/error-page.component';

@NgModule({
  declarations: [
    AppComponent,
    SecurityComponent,
    AdminComponent,
    SellerComponent,
    HeaderComponent,
    FooterComponent,
    ErrorPageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    SecurityModule,
    RouterModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
