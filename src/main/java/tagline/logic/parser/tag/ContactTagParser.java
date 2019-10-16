package tagline.logic.parser.tag;

import tagline.model.contact.ContactId;
import tagline.model.tag.ContactTag;

public class ContactTagParser {

    public ContactTag parse(String arg) {
        return new ContactTag(new ContactId(arg));
    }
}
