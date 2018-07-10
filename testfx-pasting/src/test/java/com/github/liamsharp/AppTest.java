package com.github.liamsharp;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeoutException;

import org.apache.commons.lang3.SystemUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;

import javafx.application.Application;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

public class AppTest extends FxRobot
{
    private static final Logger LOGGER = LogManager.getLogger(AppTest.class);
    
    @BeforeClass
    public static void beforeClass()
    {
        if (Boolean.getBoolean("headless"))
        {
            System.setProperty("testfx.robot", "glass");
            System.setProperty("testfx.headless", "true");
            System.setProperty("prism.order", "sw");
            System.setProperty("prism.text", "t2k");
            System.setProperty("java.awt.headless", "true");
        }
    }
    
    @Test
    public void testStuff() throws TimeoutException, InterruptedException
    {
        FxToolkit.registerPrimaryStage();
        final Application app = FxToolkit.setupApplication(App.class);
        
        clickOn("#text_field");
        
        //write("hello");
        pasteText("hello");
        
        assertEquals("hello", App.getTextFieldText());
        
        FxToolkit.cleanupApplication(app);
    }

    private void pasteText(
        final String text)
    {
        copyToClipboard(text);
        pasteFromClipboard();
    }

    public void copyToClipboard(
        final String text)
    {
        interact(() -> writeToClipboard(text));
    }

    private void pasteFromClipboard()
    {
        robotContext().getTypeRobot().push(getPaste());
    }
    
    private KeyCodeCombination getPaste()
    {
        return new KeyCodeCombination(KeyCode.V, getModifier());
    }

    private KeyCombination.Modifier getModifier()
    {
        return SystemUtils.IS_OS_MAC ? KeyCombination.META_DOWN : KeyCombination.CONTROL_DOWN;
    }
    
    public void writeToClipboard(
        final String data)
    {
        LOGGER.info("Copying to clipboard: '" + data + "'");
        final ClipboardContent clipboardContent = new ClipboardContent();
        clipboardContent.putString(data);
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        clipboard.setContent(clipboardContent);
    }

}
