package com.beust.jcommander;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by jeremysolarz on 12/27/16.
 */
public class MainParameterValidator {

    AtomicInteger counter = new AtomicInteger(0);

    @Parameter(description = "A path", validateWith = DirectoryNameValidator.class,
            validateValueWith = DirectoryListValidator.class)
    List<String> files = new ArrayList<>(Collections.singletonList("./"));

    public static class DirectoryNameValidator implements IParameterValidator {
        @Override
        public void validate(String name, String value) throws ParameterException {
            System.out.println("Validating directory: " + value);
            checkIsExistingDirectory(value);
        }

    }
    public static class DirectoryListValidator implements IValueValidator<List<String>> {
        @Override
        public void validate(String name, List<String> value) throws ParameterException {
            System.out.println("Validating parameters list: " + value);
            for (String directoryName : value) {
                checkIsExistingDirectory(directoryName);
            }
        }
    }

    private static void checkIsExistingDirectory(String directoryName) {
        if (!Paths.get(directoryName).toFile().isDirectory()) {
            throw new ParameterException(directoryName + " is not a directory");
        }
    }
}
