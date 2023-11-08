import { Contact } from "./Contact";

export interface ContactGroup {
    groupId: number;
    groupName: string;
    contacts ?: Contact[]; // Assuming it's an array of contacts
  }
  