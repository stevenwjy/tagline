package tagline.model.tag;

import tagline.model.contact.ContactId;

public class ContactTag extends Tag {
    public static final String TAG_PREFIX = "@";

    ContactId contactId;

    public ContactTag(ContactId contactId) {
        super(ContactTag.TAG_PREFIX + contactId.toString());
        this.contactId = contactId;
    }
}
