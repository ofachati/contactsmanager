import { Address } from "./Address";
import { ContactGroup } from "./ContactGroup";
import { PhoneNumber } from "./PhoneNumber";

export interface Contact {
    idContact: number;
    firstName: string;
    lastName: string;
    email: string;
    address?: Address;
    phones?: PhoneNumber[];
    //contactGroups?: ContactGroup[];
  }
  