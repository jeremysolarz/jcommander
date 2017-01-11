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

    @Parameter(
        description = "A path",
        validateValueWith = DirectoryListValidator.class,
        validateWith = DirectoryNameValidator.class
    )
    List<String> files = new ArrayList<>(Collections.singletonList("./"));

    public static class DirectoryListValidator implements IValueValidator<List<String>> {
        @Override
        public void validate(String name, List<String> value) throws ParameterException {
            System.out.println("Validating value: " + value);
            for (String directoryName : value) {
                checkIsExistingDirectory(directoryName);
            }
        }
    }

    public static class DirectoryNameValidator implements IParameterValidator {
        @Override
        public void validate(String name, String value) throws ParameterException {
            System.out.println("Validating parameter: " + value);
            checkIsExistingDirectory(value);
        }

    }

    private static void checkIsExistingDirectory(String directoryName) {
        if (!Paths.get(directoryName).toFile().isDirectory()) {
            throw new ParameterException(directoryName + " is not a directory");
        }
    }
}
