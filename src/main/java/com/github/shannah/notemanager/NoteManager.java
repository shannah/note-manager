package com.github.shannah.notemanager;

import ca.weblite.jdeploy.app.hive.filewatcher.FileWatcherHiveDriver;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class NoteManager {

    public static final String MODE_GUI = "gui";
    public static final String MODE_COMMAND = "command";
    public static final String PROP_MODE = "jdeploy.mode";

    private static final String APP_NAME = "note-manager";

    public static void main(String... args) {
        // Ensure jdeploy.app.name is set for Hive IPC (required by FileWatcherHiveDriver)
        if (System.getProperty("jdeploy.app.name") == null) {
            System.setProperty("jdeploy.app.name", APP_NAME);
        }

        // Install Hive IPC driver for background messaging between instances
        FileWatcherHiveDriver.install();

        String mode = System.getProperty(PROP_MODE, MODE_GUI);

        if (MODE_COMMAND.equalsIgnoreCase(mode)) {
            // MCP server mode: Quarkus handles stdin/stdout MCP protocol
            Quarkus.run(args);
        } else {
            // GUI mode: launch the JavaFX application (with AWT handler registered first)
            NoteManagerApp.main(args);
        }
    }
}
