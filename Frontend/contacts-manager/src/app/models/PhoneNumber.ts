import { Contact } from "./Contact";

export interface PhoneNumber {
    idPhoneNumber: number;
    phoneKind: string;
    phoneNumber: string;
    //contact ?: Contact; // Assuming it's a required field, remove if optional
  }
  