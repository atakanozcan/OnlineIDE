import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { ManageProjectComponent } from './manage-project/manage-project.component';
import {Route, Router, RouterModule} from "@angular/router";
import { NZ_I18N } from 'ng-zorro-antd/i18n';
import { en_US } from 'ng-zorro-antd/i18n';
import { registerLocaleData } from '@angular/common';
import en from '@angular/common/locales/en';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {NzButtonModule} from "ng-zorro-antd/button";
import {NzListModule} from "ng-zorro-antd/list";
import {NzIconModule} from "ng-zorro-antd/icon";
//import {NzMenuModule} from "ng-zorro-antd/menu";
import { HomeComponent } from './home/home.component';
import { EditorComponent } from './editor/editor.component';
import { MonacoEditorModule } from 'ngx-monaco-editor';

registerLocaleData(en);

const routes: Route[] = [
  {path: '', component: HomeComponent},
  {path: 'projects', component: ManageProjectComponent},
  {path: 'editor', component: EditorComponent}
  ];

@NgModule({
  declarations: [
    AppComponent,
    ManageProjectComponent,
    HomeComponent,
    EditorComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes),
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    NzButtonModule,
    NzListModule,
    NzIconModule,
    MonacoEditorModule.forRoot()
  ],
  providers: [{ provide: NZ_I18N, useValue: en_US }],
  bootstrap: [AppComponent]
})
export class AppModule {}
