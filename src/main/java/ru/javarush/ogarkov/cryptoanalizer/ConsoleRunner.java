package ru.javarush.ogarkov.cryptoanalizer;

import ru.javarush.ogarkov.cryptoanalizer.entity.Result;
import ru.javarush.ogarkov.cryptoanalizer.exceptions.AppException;

import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Scanner;

import static ru.javarush.ogarkov.cryptoanalizer.constants.Constants.TXT_FOLDER;

public class ConsoleRunner {
    public static void main(String[] args) {
        Application application = new Application();
            args = getArgs(args);
            Result result = application.run(args);
            System.out.println(result);
    }

    public static final String MESSAGE_SELECT_MODE = """
            Plese select mode OR Enter to Exit:
            _______________________________________________________
            1. Encode
            2. Decode
            3. Brute force
            4. Analyze""";

    public static final String MESSAGE_SELECT_ALPHABET = """
                        
            Plese select encryption difficulty OR Enter to default:
            _______________________________________________________
            1. Full character set (default)
            2. Simple character set (Russian lower case)""";

    public static final String MESSAGE_ENTER_FILENAME_TO_ENCODE = """
                        
            Enter source (full path OR only filename OR Enter for text.txt)
            _______________________________________________________""";

    public static final String MESSAGE_ENTER_FILENAME_TO_ENCODED = """
                        
            Enter destination (full path OR only filename OR Enter for encoded.txt)
            _______________________________________________________""";

    public static final String MESSAGE_ENTER_FILENAME_TO_DECODE = """
                        
            Enter source (full path OR only filename OR Enter for encoded.txt)
            _______________________________________________________""";


    public static final String MESSAGE_ENTER_FILENAME_TO_DECODED = """
                        
            Enter destination (full path OR only filename OR Enter for decoded.txt)
            _______________________________________________________""";


    public static final String MESSAGE_ENTER_FILENAME_TO_ANALYZE = """
                        
            Enter source (full path OR only filename OR Enter for encoded.txt)
            For correct analysis, the file must be encrypted by the Simple character set (Russian lower case)
            _______________________________________________________""";

    public static final String MESSAGE_ENTER_FILENAME_TO_ANALYZED = """
                        
            Enter destination (full path OR only filename OR Enter for analyzed.txt)
            _______________________________________________________""";

    public static final String MESSAGE_ENTER_FILENAME_TO_DICTIONARY = """
                        
            Enter dictionary (full path OR only filename OR Enter for dict.txt)
            _______________________________________________________""";

    public static final String MESSAGE_ENTER_KEY = """
                        
            Enter Key (positive number OR Enter for Key = 1)
            _______________________________________________________""";



    public static final String INCORRECT_SELECTION = "Incorrect selection";
    public static final String DEFAULT_ORIGINAL = "text.txt";
    public static final String DEFAULT_ENCODED = "encoded.txt";
    public static final String DEFAULT_DECODED = "decoded.txt";
    public static final String DEFAULT_BRUTEFORCED = "bruteforced.txt";
    public static final String DEFAULT_DICTIONARY = "dict.txt";
    public static final String DEFAULT_ANALYZED = "analyzed.txt";
    public static final String WARNING_NUMBER = "Only positive numbers are supported";
    public static final String WARNING_FILE_NAME = "Only text files with the correct name are supported";

    static String action;
    static String fileSource;
    static String fileDestination;
    static String fileExample;
    static String key;


    private static String[] getArgs(String[] args) {
        if (args.length == 0) {
            try (Scanner scanner = new Scanner(System.in)) {
                int mode = getMode(scanner);
                if (mode == 404) {
                    return args;
                }
                switch (mode) {
                    case 1 -> args = getEncoderParameters(scanner);
                    case 2 -> args = getDecoderParameters(scanner);
                    case 3 -> args = getBruteForcerParameters(scanner);
                    case 4 -> args = getAnalyzerParameters(scanner);
                }
            }
        } else if (args.length > 2) {
            args[1] = checkFileName(args[1]);
            args[2] = checkFileName(args[2]);
            if (args[0].equals("analyze") && args.length > 3) {
                args[3] = checkFileName(args[3]);
            }
        }
        return args;
    }


    private static String[] getEncoderParameters(Scanner scanner) {
        action = "encode" + getAlphabet(scanner);
        fileSource = getFileName(scanner, MESSAGE_ENTER_FILENAME_TO_ENCODE, DEFAULT_ORIGINAL);
        fileDestination = getFileName(scanner, MESSAGE_ENTER_FILENAME_TO_ENCODED, DEFAULT_ENCODED);
        key = getKey(scanner);
        return new String[]{action, fileSource, fileDestination, key};
    }

    private static String[] getDecoderParameters(Scanner scanner) {
        action = "decode" + getAlphabet(scanner);
        fileSource = getFileName(scanner, MESSAGE_ENTER_FILENAME_TO_DECODE, DEFAULT_ENCODED);
        fileDestination = getFileName(scanner, MESSAGE_ENTER_FILENAME_TO_DECODED, DEFAULT_DECODED);
        key = getKey(scanner);
        return new String[]{action, fileSource, fileDestination, key};
    }

    private static String[] getBruteForcerParameters(Scanner scanner) {
        action = "bruteforce" + getAlphabet(scanner);
        fileSource = getFileName(scanner, MESSAGE_ENTER_FILENAME_TO_DECODE, DEFAULT_ENCODED);
        fileDestination = getFileName(scanner, MESSAGE_ENTER_FILENAME_TO_DECODED, DEFAULT_BRUTEFORCED);
        return new String[]{action, fileSource, fileDestination};
    }

    private static String[] getAnalyzerParameters(Scanner scanner) {
        action = "analyze";
        fileSource = getFileName(scanner, MESSAGE_ENTER_FILENAME_TO_ANALYZE, DEFAULT_ENCODED);
        fileExample = getFileName(scanner, MESSAGE_ENTER_FILENAME_TO_DICTIONARY, DEFAULT_DICTIONARY);
        fileDestination = getFileName(scanner, MESSAGE_ENTER_FILENAME_TO_ANALYZED, DEFAULT_ANALYZED);
        return new String[]{action, fileSource, fileExample, fileDestination};
    }


    private static String getKey(Scanner scanner) {
        Integer key = null;
        do {
            System.out.println(MESSAGE_ENTER_KEY);
            String input = scanner.nextLine();
            if (input.isEmpty()) {
                key = 1;
            } else {
                try {
                    key = Integer.parseInt(input);
                    if (key < 0) {
                        System.out.println(WARNING_NUMBER);
                        key = null;
                    }
                } catch (NumberFormatException exception) {
                    System.out.println(WARNING_NUMBER);
                }
            }
        } while (key == null);
        return "" + key;
    }

    private static String getFileName(Scanner scanner, String message, String defaultFileName) {
        String fileName;
        do {
            System.out.println(message);
            String input = scanner.nextLine();
            if (input.isEmpty()) {
                fileName = TXT_FOLDER + defaultFileName;
            } else {
                fileName = getFullFileName(input);
            }
        } while (fileName == null);
        return fileName;
    }

    private static String getFullFileName(String input) {
        String fileName = null;
        if (!input.endsWith(".txt") || input.endsWith("\\.txt")) {
            System.out.println(WARNING_FILE_NAME);
            return fileName;
        }
        try {
            Path path = Path.of(input);
            if (path.isAbsolute()) {
                fileName = input;
            } else fileName = TXT_FOLDER + input;
        } catch (InvalidPathException pathException) {
            System.out.printf("File name \"%s\" is invalid\n", input);
        }
        return fileName;
    }

    private static String checkFileName(String fileName) {
        String checkedFileName = getFullFileName(fileName);
        if (Objects.isNull(checkedFileName)) {
            throw new AppException("Invalid file Name");
        }
        return checkedFileName;
    }

    private static String getAlphabet(Scanner scanner) {
        String alphabet = null;
        do {
            System.out.println(MESSAGE_SELECT_ALPHABET);
            String in = scanner.nextLine();
            switch (in) {
                case "", "1" -> alphabet = "";
                case "2" -> alphabet = "_lc";
                default -> System.out.println(INCORRECT_SELECTION);
            }
        } while (alphabet == null);
        return alphabet;
    }

    public static int getMode(Scanner scanner) {
        int mode = -1;
        do {
            System.out.println(MESSAGE_SELECT_MODE);
            String in = scanner.nextLine();
            switch (in) {
                case "" -> mode = 404;
                case "1" -> mode = 1;
                case "2" -> mode = 2;
                case "3" -> mode = 3;
                case "4" -> mode = 4;
                default -> System.out.println(INCORRECT_SELECTION);
            }
        } while (mode < 0);
        return mode;
    }
}
