import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { ContactGroup } from 'src/app/models/ContactGroup';
import { ContactGroupService } from 'src/app/services/contact-group.service';


@Component({
  selector: 'app-add-group',
  templateUrl: './add-group.component.html',
  styleUrls: ['./add-group.component.css']
})
export class AddGroupComponent {
  newGroupName: string = '';

  constructor(public dialogRef: MatDialogRef<AddGroupComponent>, private groupService: ContactGroupService) {}

  onSaveClick(): void {
    if (this.newGroupName.trim() !== '') {
      const newGroup: ContactGroup = {
        groupId: 0, // You may need to set an appropriate ID value
        groupName: this.newGroupName.trim()
      };

      this.groupService.createContactGroup(newGroup).subscribe(createdGroup => {
        // Handle the response as needed (e.g., add the group to your group list).
        console.log('Group created:', createdGroup);
        this.dialogRef.close(createdGroup);
      }, error => {
        // Handle the error here if the group creation fails.
        console.error('Error creating group:', error);
      });
    }
  }

  onCancelClick(): void {
    this.dialogRef.close();
  }
}