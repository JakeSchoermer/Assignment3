package au.edu.uq.itee.csse2002.sem12011.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import au.edu.uq.itee.csse2002.sem12011.BadCardException;
import au.edu.uq.itee.csse2002.sem12011.BadDeckException;
import au.edu.uq.itee.csse2002.sem12011.BadFormatException;
import au.edu.uq.itee.csse2002.sem12011.BonusCard;
import au.edu.uq.itee.csse2002.sem12011.Card;
import au.edu.uq.itee.csse2002.sem12011.Deck;
import au.edu.uq.itee.csse2002.sem12011.FreezePlayerCard;
import au.edu.uq.itee.csse2002.sem12011.FreezeSubjectCard;
import au.edu.uq.itee.csse2002.sem12011.SubjectCard;

/**
 * The DeckLoader class is responsible for reading and writing 
 * decks of cards.
 * 
 * <h3>File Format</h3>
 * The file format for a deck of cards consists of zero or more 
 * <u>card description</u> lines terminated with line separators 
 * (as described below).  A <u>card description</u> line consists
 * of 3 or 4 fields separated by a comma (",") character, where the
 * fields are as follows
 * <ul>
 *   <li>Field 1 is the card type, and is one of the values listed in
 *       the next section.</li>
 *   <li>Field 2 is a card count.  It is a non-negative integer.</li>
 *   <li>Field 3 is the card name.  It is a non-empty String.</li>
 *   <li>Field 4 is the card id.  If present, it is an integer.</li>
 * </ul>
 * If the card id field is present, the card count should be 1, and
 * the card description specifies that a single card should be created with 
 * the given id.  If the card field is absent, the card count specifies
 * the number of cards to be created.
 * <p>
 * There should be no leading or trailing spaces on any of the fields.
 * <h3>Card types</h3>
 * The following card type strings should be supported:
 * <ul>
 * <li>"subject" - denotes a SubjectCard instance.</li>
 * <li>"freeze_player" - denotes a FreezePlayerCard instance.</li>
 * <li>"freeze_subject" - denotes a FreezeSubjectCard instance with type NORMAL.</li>
 * <li>"grade" - denotes a FreezeSubjectCard instance with type GRADE.</li>
 * <li>"unfreeze_player" - denotes a BonusCard with type UNFREEZE_PLAYER.</li>
 * <li>"unfreeze_subject" - denotes a BonusCard with type UNFREEZE_SUBJECT.</li>
 * <li>"steal_one_player" - denotes a BonusCard with type STEAL_ONE_PLAYER.</li>
 * <li>"steal_all_players" - denotes a BonusCard with type STEAL_ALL_PLAYERS.</li>
 * </ul>
 * <p>
 * 
 * <h3>Line Separators</h3>
 * When the file is written, the current platform's line separator
 * shall be used.  When the file is read, any of the following shall
 * be accepted as a line separator:
 * <ul>
 * <li>CR (i.e. "\r") - Mac style.</li> 
 * <li>NL (i.e. "\n") - UNIX / Linux style.</li> 
 * <li>CR NL (i.e. "\r\n") - DOS / Windows style. </li> 
 * </ul>
 * <h3>Examples</h3>
 * Here is a "file" consisting of 3-field card descriptors; e.g.
 * for initializing a fresh deck of cards.
 * <pre>
 * subject,30,Subject Card
 * grade,20,Grade Card
 * freeze_player,10,Computer Virus
 * freeze_subject,10,Food Poisoning
 * unfreeze_player,6,Anti-Virus
 * unfreeze_subject,6,Medical Certificate
 * steal_one_player,3,Computer Hack
 * steal_all_players,2,Mind Control
 * </pre>
 * Here is a "file" of 4-field card descriptors; e.g.
 * representing a saved deck of cards.
 * <pre>
 * subject,1,Subject Card,21
 * grade,1,Grade Card,1
 * grade,1,Grade Card,15
 * freeze_player,1,Computer Virus,26
 * subject,1,Subject Card,8
 * subject,1,Subject Card,13
 * </pre>
 */
public class DeckLoader {
    
    //
    // INVARIANTS:
    // 'INSTANCE' contains the singleton instance.
    //

    /**
     * The singleton instance.
     */
    private static final DeckLoader INSTANCE = new DeckLoader();

    /**
     * Private constructor for creating the singleton instance.
     */
    private DeckLoader() {
    }

    /**
     * Get the singleton instance.
     * 
     * @return the singleton DeckLoader instance.
     */
    public static DeckLoader getInstance() {
        return INSTANCE;
    }

    /**
     * Load a deck of cards from a file.  The format of the file is as
     * specified in the class-level javadoc.  Both 3 and 4 field card
     * descriptions are supported.
     * 
     * @param file the file containing the serialized deck of cards.
     * @return the deck of cards.
     * @throws IOException if there is a problem opening or reading the file.
     * @throws BadFormatException if there is a problem with the format
     *         of the input file / stream, or if the resulting cards or
     *         deck violate their respective invariants. 
     */
    public Deck load(File file) throws IOException, BadFormatException {
        Reader reader = new FileReader(file);
        try {
            return load(reader);
        } finally {
            reader.close();
        }
    }

    /**
     * Load a deck of cards from a Reader.  The format of the character
     * stream is as specified in the class-level javadoc.  Both 3 and 4 
     * field card descriptions are supported.
     * 
     * @param reader the reader containing the serialized deck of cards.
     * @return the deck of cards.
     * @throws IOException if there is a problem reading the reader.
     * @throws BadFormatException if there is a problem with the format
     *         of the input file / stream, or if the resulting cards or
     *         deck violate their respective invariants. 
     */
    public Deck load(Reader reader) throws IOException, BadFormatException {
        // Insert a buffered reader into the stack if there isn't one 
        // there already.
        BufferedReader br = reader instanceof BufferedReader ?
                (BufferedReader) reader : new BufferedReader(reader);
        List<Card> cards = new ArrayList<Card>();
        // Use readLine to split into lines, then a Scanner to 
        // split each line into fields. 
        String line;
        while ((line = br.readLine()) != null) {
            Scanner scanner = new Scanner(line);
            scanner.useDelimiter(",");
            try {
                String type = scanner.next();
                int count = scanner.nextInt();
                String name = scanner.next();
                boolean hasId = false;
                int id = 0;
                if (scanner.hasNextInt()) {
                    hasId = true;
                    id = scanner.nextInt();
                    if (scanner.hasNext()) {
                        throw new BadFormatException("too many fields");
                    }
                }
                if (hasId) {
                    if (count != 1) {
                        throw new BadFormatException(
                                "count field must be '1' in 4 field records");
                    }
                    cards.add(recreateCard(name, type, id));
                } else {
                    if (count < 0) {
                        throw new BadFormatException("count field is negative");
                    }
                    for (int i = 0; i < count; i++) {
                        cards.add(createCard(name, type));
                    }
                }
            } catch (BadCardException ex) {
                throw new BadFormatException("invalid card", ex);
            } catch (NoSuchElementException ex) {
                throw new BadFormatException("badly formated line", ex);
            } 
            if (scanner.hasNext()) {
                scanner.nextLine();
            }
        }
        try {
            return new DeckImpl(cards.toArray(new Card[cards.size()]));
        } catch (BadDeckException ex) {
            throw new BadFormatException("invalid deck", ex);
        }
    }

    /**
     * Create a Card object of the appropriate class with the
     * supplied name and type.  The type determines the card 
     * class, and (in some cases) the card's type field.  The
     * card will have a new 'id' allocated, though this may not
     * be unique with respect to cards that are recreated.
     * 
     * @param name the supplied card name
     * @param type the supplied card type.
     * @return the card that was created.
     * @throws BadCardException if the card fields are invalid
     * @throws BadFormatException if we don't recognize the type.
     */
    private Card createCard(String name, String type) 
    throws BadCardException, BadFormatException {
        // Ugly 'if' switch.  (In Java 7 we'll be able to use a
        // switch statement instead.
        if (type.equals("subject")) {
            return new SubjectCardImpl(name);
        } else if (type.equals("freeze_player")) {
            return new FreezePlayerCardImpl(name);
        } else if (type.equals("freeze_subject")) {
            return new FreezeSubjectCardImpl(name, 
                    FreezeSubjectCard.FreezeSubjectCardType.NORMAL);
        } else if (type.equals("grade")) {
            return new FreezeSubjectCardImpl(name, 
                    FreezeSubjectCard.FreezeSubjectCardType.GRADE);
        } else if (type.equals("unfreeze_player")) {
            return new BonusCardImpl(name, 
                    BonusCardImpl.BonusCardType.UNFREEZE_PLAYER);
        } else if (type.equals("unfreeze_subject")) {
            return new BonusCardImpl(name, 
                    BonusCardImpl.BonusCardType.UNFREEZE_SUBJECT);
        } else if (type.equals("steal_one_player")) {
            return new BonusCardImpl(name, 
                    BonusCardImpl.BonusCardType.STEAL_ONE_PLAYER);
        } else if (type.equals("steal_all_players")) {
            return new BonusCardImpl(name, 
                    BonusCardImpl.BonusCardType.STEAL_ALL_PLAYERS);
        } else {
            throw new BadFormatException("Unknown type '" + type + "'");
        }
    }

    /**
     * Recreate a Card object of the appropriate class with the
     * supplied name, type and id.  The type determines the card 
     * class, and (in some cases) the card's type field.  We don't
     * check the uniqueness of the card id.
     * 
     * @param name the supplied card name
     * @param type the supplied card type.
     * @param id the to be used for the recreated card
     * @return the card that was created.
     * @throws BadCardException if the card fields are invalid
     * @throws BadFormatException if we don't recognize the type.
     */
    private Card recreateCard(String name, String type, int id) 
    throws BadCardException, BadFormatException {
        // This is the analog of the 'if' switch in the previous method
        if (type.equals("subject")) {
            return new SubjectCardImpl(name, id);
        } else if (type.equals("freeze_player")) {
            return new FreezePlayerCardImpl(name, id);
        } else if (type.equals("freeze_subject")) {
            return new FreezeSubjectCardImpl(name, id,
                    FreezeSubjectCard.FreezeSubjectCardType.NORMAL);
        } else if (type.equals("grade")) {
            return new FreezeSubjectCardImpl(name, id,
                    FreezeSubjectCard.FreezeSubjectCardType.GRADE);
        } else if (type.equals("unfreeze_player")) {
            return new BonusCardImpl(name, id,
                    BonusCardImpl.BonusCardType.UNFREEZE_PLAYER);
        } else if (type.equals("unfreeze_subject")) {
            return new BonusCardImpl(name, id,
                    BonusCardImpl.BonusCardType.UNFREEZE_SUBJECT);
        } else if (type.equals("steal_one_player")) {
            return new BonusCardImpl(name, id,
                    BonusCardImpl.BonusCardType.STEAL_ONE_PLAYER);
        } else if (type.equals("steal_all_players")) {
            return new BonusCardImpl(name, id,
                    BonusCardImpl.BonusCardType.STEAL_ALL_PLAYERS);
        } else {
            throw new BadFormatException("Unknown type '" + type + "'");
        }
    }

    /**
     * Save a deck of cards to a file using 4 field card descriptors 
     * as specified in the class-level javadoc.
     * 
     * @param deck the Deck to be saved.
     * @param file the File to save the cards to.
     * @throws IOException if there is a problem opening or writing the file.
     */
    public void save(Deck deck, File file) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter(file));
        try {
            for (Card card : deck) {
                String serialType = getSerialType(card);
                pw.print(serialType);
                pw.print(',');
                pw.print(1);
                pw.print(',');
                pw.print(card.getName());
                pw.print(',');
                pw.print(card.getId());
                pw.println();
            }
        } finally {
            pw.close();
        }
    }

    /**
     * Map a card to the appropriate card type string as specified
     * in the serial format spec.
     * 
     * @param card the card to be mapped
     * @return the corresponding type string.
     */
    private String getSerialType(Card card) {
        if (card instanceof SubjectCard) {
            return "subject";
        } else if (card instanceof FreezePlayerCard) {
            return "freeze_player";
        } else if (card instanceof FreezeSubjectCard) {
            FreezeSubjectCard.FreezeSubjectCardType type = 
                ((FreezeSubjectCard) card).getType();
            switch (type) {
            case NORMAL: 
                return "freeze_subject";
            case GRADE: 
                return "grade";
            default: 
                throw new AssertionError(
                        "Unknown FreezeSubjectCard type " + type);
            }
        } else if (card instanceof BonusCard) {
            BonusCard.BonusCardType type = ((BonusCard) card).getType();
            switch (type) {
            case UNFREEZE_PLAYER: 
                return "unfreeze_player";
            case UNFREEZE_SUBJECT: 
                return "unfreeze_subject";
            case STEAL_ONE_PLAYER:
                return "steal_one_player";
            case STEAL_ALL_PLAYERS:
                return "steal_all_players";
            default: 
                throw new AssertionError("Unknown BonusCard type " + type);
            }
        } else {
            throw new AssertionError("Unknown Card class: " + card.getClass());
        }
    }
}
