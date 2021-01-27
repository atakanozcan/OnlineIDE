import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {Route, RouterModule} from '@angular/router';
import {HttpClientModule} from '@angular/common/http';
import {EditorComponent} from './editor/editor.component';
import {ProjectListComponent} from './project-list/project-list.component';
import {AuthGuard} from "./auth.guard";

const routes: Route[] = [
  {path: 'editor', component: EditorComponent, canActivate: [AuthGuard]},
  {path: 'projects', component: ProjectListComponent, canActivate: [AuthGuard]},
  {path: '**', redirectTo: '/'}
];

@NgModule({
  declarations: [
    AppComponent,
    EditorComponent,
    ProjectListComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes),
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
