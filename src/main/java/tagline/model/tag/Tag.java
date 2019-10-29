package tagline.model.tag;

/**
 * Represents a tag in tagline.
 */
public abstract class Tag {
    public final TagId tagId;

    /**
     * Constructs a {@code Tag}.
     */
    Tag() {
        this.tagId = new TagId();
    }

    /**
     * Constructs a {@code Tag} for data from storage.
     *
     * @param tagId A valid tag id.
     */
    public Tag(TagId tagId) {
        this.tagId = tagId;
    }

    public TagId getTagId() {
        return tagId;
    }

    /**
     * Returns true if {@code other} has the same data and ID as this object.
     * This defines a stronger notion of equality between two tags.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof Tag) // instanceof handles nulls
            && tagId.equals(((Tag) other).tagId);
    }

    @Override
    public abstract String toString();

    public abstract boolean isSameContent(Tag other);
}
