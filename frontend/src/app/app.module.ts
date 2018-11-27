import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TaskmanagerComponent } from './taskmanager/taskmanager.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';
import { AddtaskComponent } from './addtask/addtask.component';
import { MomentModule } from 'angular2-moment';
import { EdittaskComponent } from './edittask/edittask.component';
import { DatePipe } from '@angular/common';
import { ViewtaskComponent } from './viewtask/viewtask.component';



const appRoutes: Routes = [
{
	path: 'taskmanager',
	component:TaskmanagerComponent,
	data: {title: 'Task Manager'}
},
{
	path: 'addtask',
	component: AddtaskComponent,
	data : {title: 'Add Task'}
},
{
	path: 'edittask/:id',
	component : EdittaskComponent,
	data : {title: 'Edit Task'}
},
{
	path: 'viewtask/:id',
	component: ViewtaskComponent,
	data : {title: 'View Task'}
},
{
	path:'',
	redirectTo: '/taskmanager',
	pathMatch :'full'
}
];

@NgModule({
  declarations: [
    AppComponent,
    TaskmanagerComponent,
    AddtaskComponent,
    EdittaskComponent,
    ViewtaskComponent
  ],
  imports: [
    BrowserModule,
	FormsModule,
	HttpClientModule,
	MomentModule,
    AppRoutingModule,
	RouterModule.forRoot(
	appRoutes,
	{enableTracing: true}
	)
  ],
  providers: [ DatePipe ],
  bootstrap: [AppComponent]
})
export class AppModule { }
