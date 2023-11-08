import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Contact } from '../models/Contact';

@Injectable({
  providedIn: 'root',
})
export class ContactService {
  private contactsUrl = '/CarnetContact/ContactServlet'; 

  constructor(private http: HttpClient) {}

  getContacts(): Observable<Contact[]> {
    return this.http.get<Contact[]>(this.contactsUrl);
  }

  getContactById(id: number): Observable<Contact> {
    return this.http.get<Contact>(`${this.contactsUrl}?id=${id}`);
  }

  createContact(contact: Contact): Observable<string> {
    return this.http.post<string>(this.contactsUrl, contact);
  }

  updateContact(contact: Contact): Observable<string> {
    return this.http.put<string>(this.contactsUrl, contact);
  }

  deleteContact(id: number): Observable<string> {
    return this.http.delete<string>(`${this.contactsUrl}?id=${id}`);
  }
}
