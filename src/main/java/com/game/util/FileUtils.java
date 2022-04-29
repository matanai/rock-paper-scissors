package com.game.util;

import com.game.config.GameConfig;
import com.game.constant.Message;
import com.game.exception.GameException;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.nio.file.*;

/**
 * Utility class for handling file write operations, and basic check and generation of file names
 * @version 1.0.1
 */
@UtilityClass
public class FileUtils {

    /**
     * Build {@link Path} object from the user-defined file name or default file name if user has
     * not provided any file name or file name does not meet criteria. Create file if not present
     * and save message into the file. If the file is already present and contains messages, new
     * messages will be appended
     *
     * @param message to save into a file
     * @param fileName user-defined file name
     */
    public void saveToFile(String message, String fileName) {
        var filePath = buildFilePath(fileName);

        try {
            if (!Files.exists(filePath, LinkOption.NOFOLLOW_LINKS)) {
                Files.createFile(filePath);
            }

            Files.write(filePath,
                    message.getBytes(),
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.printf(Message.MSG_ERR_FILE_OUT, e);
            throw new GameException(e.getMessage());
        }
    }

    /**
     * Check if user-defined file name is not null, not empty, and does not contain whitespaces,
     * and append supported file extension and return it as a {@link Path} object
     *
     * @param fileName user-defined file path
     * @return user-defined file name as {@link Path} object
     */
    public Path buildFilePath(String fileName) {
        var defaultFileName = GameConfig.DEFAULT_FILE_NAME + GameConfig.DEFAULT_FILE_EXTENSION;

        if (fileName != null && fileName.length() > 0 && !fileName.contains(" ")) {
            return Paths.get(getFileNameWithExtension(fileName));
        }

        return Paths.get(defaultFileName);
    }

    /**
     * If present strip off unsupported file extension and append supported extension. If user
     * defined file name ends with supported file extension, return as it is
     *
     * @param fileName user-defined file name, which may or may not contain file extension
     * @return user-defined file name with supported extension
     */
    public String getFileNameWithExtension(String fileName) {
        if (!fileName.endsWith(GameConfig.DEFAULT_FILE_EXTENSION)) {
            return fileName.split("\\.")[0] + GameConfig.DEFAULT_FILE_EXTENSION;
        }

        return fileName;
    }
}
