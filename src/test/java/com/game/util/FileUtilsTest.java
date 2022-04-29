package com.game.util;

import com.game.config.GameConfig;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileUtilsTest {

    @ParameterizedTest
    @MethodSource("goodFileNames")
    void testBuildFilePath_goodFileNames(String expectedFileName, String actualFileName) {
        var expectedFilePath = Paths.get(expectedFileName);
        var actualFilePath = FileUtils.buildFilePath(actualFileName);

        assertEquals(expectedFilePath, actualFilePath);
    }

    private static Stream<Arguments> goodFileNames() {
        return Stream.of(
                Arguments.of("score.txt", "score"),
                Arguments.of("file_name.txt", "file_name"),
                Arguments.of("my_File.txt", "my_File.txt"),
                Arguments.of("file123.txt", "file123.png"),
                Arguments.of("file123.txt", "file123.")
        );
    }

    @ParameterizedTest
    @MethodSource("badFileNames")
    void testBuildFilePath_badFileNames(String expectedFileName, String actualFileName) {
        var expectedFilePath = Paths.get(expectedFileName).getFileName();
        var actualFilePath = FileUtils.buildFilePath(actualFileName);

        assertEquals(expectedFilePath, actualFilePath);
    }

    private static Stream<Arguments> badFileNames() {
        var defaultFileName = GameConfig.DEFAULT_FILE_NAME + GameConfig.DEFAULT_FILE_EXTENSION;

        return Stream.of(
                Arguments.of(defaultFileName, null),
                Arguments.of(defaultFileName, ""),
                Arguments.of(defaultFileName, " "),
                Arguments.of(defaultFileName, "a b.txt"),
                Arguments.of(defaultFileName, " .txt")
        );
    }
}