// group.component.ts
import { Component, Input, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ContactGroup } from 'src/app/models/ContactGroup';
import { ContactGroupService } from 'src/app/services/contact-group.service';
import { AddGroupComponent } from '../add-group/add-group.component';
import { ContactService } from 'src/app/services/contact.service';
import { Contact } from 'src/app/models/Contact';

@Component({
  selector: 'app-group',
  templateUrl: './group.component.html',
  styleUrls: ['./group.component.css'],
})
export class GroupComponent implements OnInit {
  @Input() group!: ContactGroup;
  editMode = false;
  selectedContactId: number | undefined;
  allContacts: Contact[] = []; // Replace 'any' with your actual Contact model

  constructor(
    private groupService: ContactGroupService,
    private contactService: ContactService,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    // Fetch all contacts when the component initializes
    this.contactService.getContacts().subscribe((contacts) => {
      this.allContacts = contacts;
    });
  }

  toggleEditMode(): void {
    if (this.editMode) {
      // Save logic here
      if (this.group) {
        this.groupService.updateContactGroup(this.group).subscribe((response) => {
          // Handle the response as needed, e.g., show a success message
          console.log('Group updated:', response);
        });
      }
    }

    this.editMode = !this.editMode;
  }

  deleteContact(index: number): void {
    // Implement delete contact logic
    this.group.contacts!.splice(index, 1);
  }

  deleteGroup(): void {
    // Implement delete group logic
    const groupId = this.group.groupId; // Assuming groupId is part of the ContactGroup model
    this.groupService.deleteContactGroup(groupId).subscribe((response) => {
      // Handle the response as needed, e.g., show a success message
      console.log('Group deleted:', response);
      // Notify the parent component about the deletion
    });
  }

  addContactToGroup(): void {
    // Check if a contact is selected
    if (this.selectedContactId !== undefined) {
      // Find the selected contact in the allContacts array
      const selectedContact = this.allContacts.find(
        (contact) => contact.idContact === this.selectedContactId
      );

      // Add the selected contact to the group
      if (selectedContact) {
        this.group.contacts!.push(selectedContact);
      }

      // Reset the selected contact
      this.selectedContactId = undefined;
    }
  }

 

  openAddContactDialog(): void {
    const dialogRef = this.dialog.open(AddGroupComponent, {
      width: '400px', // Adjust the width to fit your design
    });

    dialogRef.afterClosed().subscribe((newGroup: ContactGroup) => {
      if (newGroup) {
        // Handle the new group as needed, e.g., save it to your data
        // Notify the parent component about the new group
      }
    });
  }
}
