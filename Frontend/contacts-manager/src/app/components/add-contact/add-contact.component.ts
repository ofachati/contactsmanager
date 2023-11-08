import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { ContactService } from 'src/app/services/contact.service';
@Component({
  selector: 'app-add-contact',
  templateUrl: './add-contact.component.html',
  styleUrls: ['./add-contact.component.css']
})
export class AddContactComponent {

  contactForm: FormGroup;

  constructor(
    private dialogRef: MatDialogRef<AddContactComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private fb: FormBuilder,
    private contactService: ContactService

  ) {
    this.contactForm = this.fb.group({
      firstName: ['', [Validators.required]],
      lastName: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
    });
  }

  onSave() {
    if (this.contactForm.valid) {
      const newContact = this.contactForm.value;
      this.contactService.createContact(newContact).subscribe((createdContact) => {
        // Handle the response as needed (e.g., add the contact to your contact list).
        console.log('Contact created:', createdContact);
  
        this.dialogRef.close(createdContact);
      });
    }
  }

  onClose() {
    this.dialogRef.close();
  }
}
