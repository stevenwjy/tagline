package tagline.model.tag;

import tagline.model.contact.ContactId;

/**
 * Tag a contact.
 */
public class ContactTag extends Tag {
    public static final String TAG_PREFIX = "@";

    private ContactId contactId;

    /**
     * Constructs a {@code ContactTag}.
     *
     * @param contactId A valid contactId
     */
    public ContactTag(ContactId contactId) {
        super();
        this.contactId = contactId;
    }

    /**
     * Constructs a {@code ContactTag} from storage.
     *
     * @param tagId     A valid tag id
     * @param contactId A valid contactId
     */
    public ContactTag(TagId tagId, ContactId contactId) {
        super(tagId);
        this.contactId = contactId;
    }

    public ContactId getContactId() {
        return contactId;
    }

    /**
     * Checks whether a contact tag has the same content with another tag.
     *
     * @param other Other tag
     */
    public boolean isSameContent(Tag other) {
        return (other instanceof ContactTag)
            && contactId.equals(((ContactTag) other).contactId);
    }

    @Override
    public String toString() {
        return TAG_PREFIX + contactId.toString();
    }
}
