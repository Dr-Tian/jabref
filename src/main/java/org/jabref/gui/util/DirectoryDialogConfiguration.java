package org.jabref.gui.util;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javafx.stage.FileChooser;

import org.jabref.logic.util.FileExtensions;

public class DirectoryDialogConfiguration {

    private final Path initialDirectory;

    public Optional<Path> getInitialDirectory() {
        return Optional.ofNullable(initialDirectory);
    }

    private DirectoryDialogConfiguration(Path initialDirectory) {
        this.initialDirectory = initialDirectory;
    }

    public static class Builder {

        private Path initialDirectory;

        public DirectoryDialogConfiguration build() {
            return new DirectoryDialogConfiguration(initialDirectory);
        }

        public Builder withInitialDirectory(Path directory) {

            //Dir must be a folder, not a file
            if (!Files.isDirectory(directory)) {
                directory = directory.getParent();
            }
            //The lines above work also if the dir does not exist at all!
            //NULL is accepted by the filechooser as no inital path
            if (!Files.exists(directory)) {
                directory = null;
            }
            initialDirectory = directory;
            return this;
        }
    }
}
