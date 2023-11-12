import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { ContactGroup } from 'src/app/models/ContactGroup';
import { ContactGroupService } from 'src/app/services/contact-group.service';
import { Location } from '@angular/common';
import { Router } from '@angular/router';


@Component({
  selector: 'app-add-group',
  templateUrl: './add-group.component.html',
  styleUrls: ['./add-group.component.css']
})
export class AddGroupComponent {
  newGroupName: string = '';

  constructor(public dialogRef: MatDialogRef<AddGroupComponent>, private groupService: ContactGroupService,
    private router: Router) {}

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
         // Navigate to the current route to trigger a reload
      this.router.routeReuseStrategy.shouldReuseRoute = () => false;
      const currentUrl = this.router.url + '?';
      this.router.navigateByUrl(currentUrl).then(() => {
        this.router.navigated = false;
        this.router.navigate([this.router.url]);});
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