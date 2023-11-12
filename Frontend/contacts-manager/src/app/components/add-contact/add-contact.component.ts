import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { ContactService } from 'src/app/services/contact.service';
import { Router } from '@angular/router';
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
    private contactService: ContactService,
    private router: Router

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

        // Navigate to the current route to trigger a reload
      this.router.routeReuseStrategy.shouldReuseRoute = () => false;
      const currentUrl = this.router.url + '?';
      this.router.navigateByUrl(currentUrl).then(() => {
        this.router.navigated = false;
        this.router.navigate([this.router.url]);});
      });
    }
  }

  onClose() {
    this.dialogRef.close();
  }
}