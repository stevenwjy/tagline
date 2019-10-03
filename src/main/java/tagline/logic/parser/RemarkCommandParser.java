package tagline.logic.parser;

import static java.util.Objects.requireNonNull;
import static tagline.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tagline.logic.parser.CliSyntax.PREFIX_REMARK;

import tagline.commons.core.index.Index;
import tagline.commons.exceptions.IllegalValueException;
import tagline.logic.commands.RemarkCommand;
import tagline.logic.parser.exceptions.ParseException;

public class RemarkCommandParser {

    public RemarkCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_REMARK);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    RemarkCommand.MESSAGE_USAGE), ive);
        }

        String remark = argMultimap.getValue(PREFIX_REMARK).orElse("");

        return new RemarkCommand(index, remark);
    }
}
