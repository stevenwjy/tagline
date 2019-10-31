package tagline.logic.commands.contact;

import static java.util.Objects.requireNonNull;

import java.util.Collections;

import tagline.commons.core.Messages;
import tagline.logic.commands.CommandResult;
import tagline.logic.commands.CommandResult.ViewType;
import tagline.model.Model;
import tagline.model.contact.ContactId;
import tagline.model.contact.ContactIdEqualsKeywordPredicate;
import tagline.model.note.NoteContainsTagsPredicate;
import tagline.model.tag.ContactTag;

/**
 * Shows a contact in address book whose id matches the query.
 */
public class ShowContactCommand extends ContactCommand {

    public static final String COMMAND_WORD = "show";

    public static final String MESSAGE_SUCCESS = "Listed notes for tags: %1$s";

    public static final String MESSAGE_USAGE = COMMAND_KEY + " " + COMMAND_WORD
        + ": Shows a contact profile whose id matches the id given in the query\n"
        + "Parameters: CONTACT_ID (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " 1";

    private final ContactIdEqualsKeywordPredicate predicateContact;
    private final NoteContainsTagsPredicate predicateNote;

    public ShowContactCommand(ContactId contactId) {
        this.predicateContact = new ContactIdEqualsKeywordPredicate(contactId);
        this.predicateNote = new NoteContainsTagsPredicate(Collections.singletonList(new ContactTag(contactId)));
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredContactList(predicateContact);
        model.updateFilteredNoteList(predicateNote);
        return new CommandResult(
            String.format(MESSAGE_SUCCESS, model.getFilteredNoteList().size()),
            ViewType.CONTACT_PROFILE);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof ShowContactCommand // instanceof handles nulls
            && predicateContact.equals(((ShowContactCommand) other).predicateContact) // state check
            && predicateNote.equals(((ShowContactCommand) other).predicateNote)); // state check
    }
}
