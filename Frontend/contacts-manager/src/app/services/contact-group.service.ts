import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ContactGroup } from '../models/ContactGroup';

@Injectable({
  providedIn: 'root'
})
export class ContactGroupService {
  private contactGroupUrl = '/CarnetContact/ContactGroupServlet';

  constructor(private http: HttpClient) { }

  getAllContactGroups(): Observable<ContactGroup[]> {
    return this.http.get<ContactGroup[]>(this.contactGroupUrl);
  }

  getContactGroupById(id: number): Observable<ContactGroup> {
    return this.http.get<ContactGroup>(`${this.contactGroupUrl}?id=${id}`);
  }

  createContactGroup(contactGroup: ContactGroup): Observable<any> {
    return this.http.post(this.contactGroupUrl, contactGroup);
  }

  updateContactGroup(contactGroup: ContactGroup): Observable<any> {
    return this.http.put(this.contactGroupUrl, contactGroup);
  }

  deleteContactGroup(id: number): Observable<any> {
    return this.http.delete(`${this.contactGroupUrl}?id=${id}`);
  }
}
