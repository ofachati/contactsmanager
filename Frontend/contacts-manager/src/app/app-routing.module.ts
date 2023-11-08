import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './components/home/home.component';
import { ContactComponent } from './components/contact/contact.component';
import { GroupComponent } from './components/group/group.component';
import { ContactslistComponent } from './components/contactslist/contactslist.component';
import { GroupslistComponent } from './components/groupslist/groupslist.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  {
    path: 'home',
component: HomeComponent
  
  },
  { path: 'contact', component: ContactslistComponent },
      { path: 'group', component: GroupslistComponent },
  { path: 'contact/:id', component: ContactComponent },

  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: '**', redirectTo: '/login' }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }