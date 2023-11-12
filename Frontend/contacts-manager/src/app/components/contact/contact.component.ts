import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Address } from 'src/app/models/Address';
import { Contact } from 'src/app/models/Contact';
import { PhoneNumber } from 'src/app/models/PhoneNumber';
import { ContactService } from 'src/app/services/contact.service';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.css']
})
export class ContactComponent implements OnInit {

  contact!: Contact;
  editMode: boolean = false;

  constructor(
    private route: ActivatedRoute,
    private contactService: ContactService,
    private router: Router

  ) {}

  ngOnInit(): void {
    const contactId = this.route.snapshot.paramMap.get('id');
    if (contactId) {
      this.contactService.getContactById(+contactId).subscribe((contact) => {
        this.contact = contact || {}; // If contact is null or undefined, set it to an empty object
  
        // Provide default values for the empty fields
        this.contact.firstName = this.contact.firstName || '';
        this.contact.lastName = this.contact.lastName || '';
        this.contact.email = this.contact.email || '';
        
        // Check and provide default values for address
        if (!this.contact.address) {
          this.contact.address = {
            street: '',
            city: '',
            zip: '',
            country: ''
          } as Address;
        }
  
        // Check and provide default values for phones
        if (!this.contact.phones) {
          this.contact.phones = [];
        }
  
        // Assuming each phone has phoneKind and phoneNumber fields
        for (const phone of this.contact.phones) {
          phone.phoneKind = phone.phoneKind || '';
          phone.phoneNumber = phone.phoneNumber || '';
        }
      });
    }
  }
  

  toggleEditMode(): void {
    if (this.editMode) {
      // Save logic here
      if (this.contact) {
        this.contactService.updateContact(this.contact).subscribe((response) => {
          console.log('Contact updated:', response);
        });
      }
    }

    // Toggle edit mode regardless of whether it was initially on or off
    this.editMode = !this.editMode;
  }

  onSaveClick(): void {
    if (this.contact) {
      this.contactService.updateContact(this.contact).subscribe((response) => {
        console.log('Contact updated:', response);
        this.toggleEditMode(); // Switch back to read-only mode after saving
      });
    }
  }

  // contact.component.ts
addPhoneNumber(): void {
  if (this.contact) {
    if (!this.contact.phones) {
      this.contact.phones = [];
    }

    this.contact.phones.push({
      phoneKind: '',
      phoneNumber: ''
    } as PhoneNumber);
  }
}


  removePhoneNumber(index: number): void {
    this.contact!.phones?.splice(index, 1);
  }

  deleteContact(): void {
    if (this.contact && this.contact.idContact) {
      this.contactService.deleteContact(this.contact.idContact).subscribe(() => {
        // Redirect to the contact list component after deletion
        this.router.navigate(['/contact']);
      });
    }
  }
}
