import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Contact } from 'src/app/models/Contact';
import { ContactService } from 'src/app/services/contact.service';
import { MatDialog } from '@angular/material/dialog';
import { AddContactComponent } from '../add-contact/add-contact.component';


@Component({
  selector: 'app-contactslist',
  templateUrl: './contactslist.component.html',
  styleUrls: ['./contactslist.component.css']
})
export class ContactslistComponent implements OnInit {
  contacts: Contact[] = [];

  constructor(private contactService: ContactService, private router: Router,private dialog: MatDialog) {}

  ngOnInit(): void {
    this.contactService.getContacts().subscribe((contacts) => {
      this.contacts = contacts;
    });
  }


  navigateToContact(contactId: number) {
    this.router.navigate(['/contact', contactId]);
  }

  navigateToAddContact() {
    const dialogRef = this.dialog.open(AddContactComponent, {
      width: '400px', // Adjust the width as needed
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        // Handle the data you received from the dialog (e.g., add the contact).
        // You can use the result object to access the form values.
        console.log(result);
      }
    });
}
}