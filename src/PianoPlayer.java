import lc.kra.system.keyboard.GlobalKeyboardHook;

import javax.sound.midi.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class PianoPlayer {
    public boolean playing = false;
    private File currentSong = null;
    private Window window;

    private Sequencer sequencer;
    private Sequence sequence = null;
    private Transmitter transmitter;
    private CustomReciever reciever;
    private Robot robot;
    private GlobalKeyboardHook keyboardHook;
    private CustomKeyListener listener;
    public PianoPlayer() {
        window = new Window(this);
        window.init();

        try {
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            transmitter = sequencer.getTransmitter();
            reciever = new CustomReciever(this);
            robot = new Robot();
            keyboardHook = new GlobalKeyboardHook();
            listener = new CustomKeyListener(this);
            keyboardHook.addKeyListener(listener);
            transmitter.setReceiver(reciever);
            sequencer.getTransmitters().iterator().next().close();
        } catch (MidiUnavailableException | AWTException e) {
            e.printStackTrace();
        }

    }

    public void keyPress(char key, boolean shift) {
        if(shift) {
            robot.keyPress(KeyEvent.VK_SHIFT);
        }
        robot.keyPress(KeyEvent.getExtendedKeyCodeForChar(key));
        if(shift) {
            robot.keyRelease(KeyEvent.VK_SHIFT);
        }
    }
    public void keyRelease(char key, boolean shift) {
        if(shift) {
            robot.keyPress(KeyEvent.VK_SHIFT);
        }
        robot.keyRelease(KeyEvent.getExtendedKeyCodeForChar(key));
        if(shift) {
            robot.keyRelease(KeyEvent.VK_SHIFT);
        }
    }

    public void toggle() {
        if(sequence != null) {
            if(sequencer.isRunning()) {
                stop();
                window.playPauseButton.setText("Play");
            } else {
                start();
                window.playPauseButton.setText("Pause");
            }
        }
    }

    public void setSong(File midi) {
        currentSong = midi;
        try {
            sequence = MidiSystem.getSequence(currentSong);
            sequencer.setSequence(sequence);
        } catch (InvalidMidiDataException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public File getSong() {
        return currentSong;
    }

    public void start() {
        if(sequence != null) {
            sequencer.start();
            window.playPauseButton.setText("Pause");
        }
    }

    public void stop() {
        if(sequence != null) {
            sequencer.stop();
            window.playPauseButton.setText("Play");
        }
    }

    public void reset() {
        stop();
        window.playPauseButton.setText("Play");
        if(sequence != null) {
            try {
                sequencer.setSequence(MidiSystem.getSequence(currentSong));
            } catch (InvalidMidiDataException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
