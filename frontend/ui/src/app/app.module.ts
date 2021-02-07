import { BrowserModule } from '@angular/platform-browser';
import {Injectable, NgModule} from '@angular/core';

import { AppComponent } from './app.component';
import { ManageProjectComponent } from './manage-project/manage-project.component';
import {Route, Router, RouterModule} from '@angular/router';
import { NZ_I18N } from 'ng-zorro-antd/i18n';
import { en_US } from 'ng-zorro-antd/i18n';
import { registerLocaleData } from '@angular/common';
import en from '@angular/common/locales/en';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {NzButtonModule} from 'ng-zorro-antd/button';
import {NzListModule} from 'ng-zorro-antd/list';
import {NzIconModule} from 'ng-zorro-antd/icon';
import { HomeComponent } from './home/home.component';
import { EditorComponent } from './editor/editor.component';
import { MonacoEditorModule } from 'ngx-monaco-editor';
import { NzLayoutModule } from 'ng-zorro-antd/layout';
import { NzTableModule } from 'ng-zorro-antd/table';
import { NzDividerModule } from 'ng-zorro-antd/divider';
import { NzInputModule } from 'ng-zorro-antd/input';
import { ManageFilesComponent } from './manage-files/manage-files.component';
import {AuthGuard} from "./auth.guard";

registerLocaleData(en);

const routes: Route[] = [
  {path: 'home', component: HomeComponent},
  {path: 'projects', component: ManageProjectComponent, canActivate: [AuthGuard]},
  {path: 'projects/:projectId', component: ManageFilesComponent, canActivate: [AuthGuard]},
  {path: 'editor/:projectId/:fileName', component: EditorComponent, canActivate: [AuthGuard]},
  {path: '**', redirectTo: 'home'},
];

@NgModule({
  declarations: [
    AppComponent,
    ManageProjectComponent,
    HomeComponent,
    EditorComponent,
    ManageFilesComponent
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
    MonacoEditorModule.forRoot(),
    NzLayoutModule,
    NzTableModule,
    NzDividerModule,
    NzInputModule
  ],
  providers: [{ provide: NZ_I18N, useValue: en_US }],
  bootstrap: [AppComponent]
})

export class AppModule {}
