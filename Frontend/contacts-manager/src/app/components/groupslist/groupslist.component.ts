import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ContactGroup } from 'src/app/models/ContactGroup';
import { AddGroupComponent } from '../add-group/add-group.component';
import { ContactGroupService } from 'src/app/services/contact-group.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-groupslist',
  templateUrl: './groupslist.component.html',
  styleUrls: ['./groupslist.component.css']
})
export class GroupslistComponent  implements OnInit {
  contactGroups: ContactGroup[] = []; // Initialize with your contact groups


 

  constructor(private groupService: ContactGroupService, private router: Router,private dialog: MatDialog) {}
  ngOnInit(): void {
    this.groupService.getAllContactGroups().subscribe((contactGroups) => {
      this.contactGroups = contactGroups;
    });
  }

  editGroup(group: ContactGroup): void {
    // Implement edit group logic
    this.groupService.updateContactGroup(group).subscribe(response => {
      // Handle the response as needed, e.g., show a success message
      console.log('Group updated:', response);
    });
  }
  

  deleteGroup(group: ContactGroup): void {
    // Implement delete group logic
    const groupId = group.groupId; // Assuming groupId is part of the ContactGroup model
    this.groupService.deleteContactGroup(groupId).subscribe(response => {
      // Handle the response as needed, e.g., show a success message
      console.log('Group deleted:', response);
      // Remove the group from the contactGroups array or refresh the list
      this.contactGroups = this.contactGroups.filter(g => g !== group);
    });
  }
  

  addContactToGroup(group: ContactGroup): void {
    // Implement adding a new contact to the group logic
  }

  openAddGroupDialog(): void {
    const dialogRef = this.dialog.open(AddGroupComponent, {
      width: '400px', // Adjust the width to fit your design
    });
  
    dialogRef.afterClosed().subscribe((newGroup: ContactGroup) => {
      if (newGroup) {
        // Handle the new group as needed, e.g., save it to your data
        this.contactGroups.push(newGroup);
      }
    });
  }
  
}