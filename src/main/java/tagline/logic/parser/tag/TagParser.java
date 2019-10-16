package tagline.logic.parser.tag;

import static tagline.commons.core.Messages.MESSAGE_INVALID_TAG_FORMAT;
import static tagline.commons.core.Messages.MESSAGE_UNKNOWN_TAG;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tagline.logic.parser.exceptions.ParseException;
import tagline.model.tag.ContactTag;
import tagline.model.tag.Tag;

public class TagParser {
    private static final Pattern BASIC_TAG_FORMAT = Pattern.compile("(?<tagKey>[%#@])(?<tagValue>.*)");
    private static final String TAG_USAGE = "tag starts with '@' for contact, '%' for group, and '#' for hash tag";

    public Tag parseTag(String arg) throws ParseException {
        final Matcher matcher = BASIC_TAG_FORMAT.matcher(arg.stripLeading());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_TAG_FORMAT, TAG_USAGE));
        }

        final String tagKey = matcher.group("tagKey");
        final String tagValue = matcher.group("tagValue");

        switch (tagKey) {
        case ContactTag.TAG_PREFIX:
            return new ContactTagParser().parse(tagValue);
        default:
            throw new ParseException(MESSAGE_UNKNOWN_TAG);
        }
    }
}
