package tagline.storage.tag;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import tagline.commons.exceptions.IllegalValueException;
import tagline.model.contact.ContactId;
import tagline.model.tag.ContactTag;
import tagline.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Tag}.
 */
public class JsonAdaptedTag {

    private final String tagType;
    private final int tagId;

    /**
     * Constructs a {@code JsonAdaptedTag} with the given {@code tagName}.
     */
    @JsonCreator
    public JsonAdaptedTag(@JsonProperty("tagType") String tagType, @JsonProperty("tagId") int tagId) {
        this.tagType = tagType;
        this.tagId = tagId;
    }

    /**
     * Converts a given {@code Tag} into this class for Jackson use.
     */
    public JsonAdaptedTag(Tag source) {
        tagType = source.tagType.name();
        tagId = source.tagId;
    }

    @JsonValue
    public String getTagType() {
        return tagType;
    }

    @JsonValue
    public int getTagId() {
        return tagId;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Tag toModelType() throws IllegalValueException {
        return new ContactTag(new ContactId("123"));
    }
}
