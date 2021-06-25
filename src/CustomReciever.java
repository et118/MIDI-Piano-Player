import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

public class CustomReciever implements Receiver {
    private PianoPlayer player;
    public CustomReciever(PianoPlayer player) {
        this.player = player;
    }

    @Override
    public void send(MidiMessage message, long timeStamp) {
        ShortMessage sm = (ShortMessage) message;
        if(sm.getCommand() == ShortMessage.NOTE_ON) {
            Note note = new Note(sm.getData1());
            player.keyPress(note.getChar(),note.getShift());
        } else if(sm.getCommand() == ShortMessage.NOTE_OFF) {
            Note note = new Note(sm.getData1());
            player.keyRelease(note.getChar(),note.getShift());
        }
    }

    @Override
    public void close() {

    }
}
