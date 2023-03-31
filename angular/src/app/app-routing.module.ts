import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { ResetComponent } from './reset/reset.component';
import { IndexComponent } from './index/index.component';
import { CalendarComponent } from './calendar/calendar.component';
import { SubjectComponent } from './subject/subject.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'reset', component: ResetComponent },
  { path: 'index', component: IndexComponent },
  { path: 'calendar', component: CalendarComponent },
  { path: 'subject', component: SubjectComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
