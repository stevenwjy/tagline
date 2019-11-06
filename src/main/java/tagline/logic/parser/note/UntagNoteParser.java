package tagline.logic.parser.note;

import static tagline.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tagline.logic.parser.ParserUtil.allPrefixesPresent;
import static tagline.logic.parser.note.NoteCliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.List;

import tagline.logic.commands.note.TagNoteCommand;
import tagline.logic.commands.note.UntagNoteCommand;
import tagline.logic.parser.ArgumentMultimap;
import tagline.logic.parser.ArgumentTokenizer;
import tagline.logic.parser.Parser;
import tagline.logic.parser.Prompt;
import tagline.logic.parser.exceptions.ParseException;
import tagline.logic.parser.exceptions.PromptRequestException;
import tagline.logic.parser.tag.TagParserUtil;
import tagline.model.note.NoteId;
import tagline.model.tag.Tag;

/**
 * Parses input arguments and creates a new UntagNoteCommand Object
 */
public class UntagNoteParser implements Parser<UntagNoteCommand> {
    public static final String UNTAG_NOTE_MISSING_ID_PROMPT_STRING = "Please enter the ID of the note to untag.";
    public static final String UNTAG_NOTE_MISSING_TAGS_PROMPT_STRING = "Please enter a tag to remove.";
    public static final String MESSAGE_NO_TAG = "At least one tag must be provided.";

    /**
     * Parses the given {@code String} of arguments in the context of the UntagNoteCommand
     * and returns an UntagNoteCommand object for execution.
     *
     * @throws tagline.logic.parser.exceptions.ParseException if the user input does not conform the expected format
     */
    public UntagNoteCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TAG);
        checkCompulsoryFields(argMultimap);

        NoteId noteId;
        try {
            noteId = NoteParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TagNoteCommand.MESSAGE_USAGE), pe);
        }

        List<String> tags = argMultimap.getAllValues(PREFIX_TAG);
        if (tags.isEmpty()) {
            throw new ParseException(MESSAGE_NO_TAG);
        }

        List<Tag> tagList = new ArrayList<>();
        for (String tagString : tags) {
            Tag tag = TagParserUtil.parseTag(tagString);
            tagList.add(tag);
        }

        return new UntagNoteCommand(noteId, tagList);
    }

    //@@author tanlk99
    /**
     * Checks the compulsory fields of the command (i.e. note ID, tag name).
     * @throws PromptRequestException if a compulsory field is missing
     */
    private static void checkCompulsoryFields(ArgumentMultimap argMultimap) throws PromptRequestException {
        List<Prompt> promptList = new ArrayList<>();
        if (argMultimap.getPreamble().isEmpty()) {
            promptList.add(new Prompt("", UNTAG_NOTE_MISSING_ID_PROMPT_STRING));
        }

        if (!allPrefixesPresent(argMultimap, PREFIX_TAG)) {
            promptList.add(new Prompt(PREFIX_TAG.getPrefix(), UNTAG_NOTE_MISSING_TAGS_PROMPT_STRING));
        }

        if (!promptList.isEmpty()) {
            throw new PromptRequestException(promptList);
        }
    }
}
