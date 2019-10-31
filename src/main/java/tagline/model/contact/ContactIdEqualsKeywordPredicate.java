package tagline.model.contact;

import java.util.function.Predicate;

public class ContactIdEqualsKeywordPredicate implements Predicate<Contact> {

    private final ContactId contactId;

    public ContactIdEqualsKeywordPredicate(ContactId contactId) {
        this.contactId = contactId;
    }

    @Override
    public boolean test(Contact contact) {
        return contact.getContactId().equals(contactId);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ContactIdEqualsKeywordPredicate // instanceof handles nulls
            && contactId.equals(((ContactIdEqualsKeywordPredicate) other).contactId)); // state check
    }
}
