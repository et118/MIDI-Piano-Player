import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

public class NoteReciever implements Receiver {
    @Override
    public void send(MidiMessage message, long timeStamp) {
        ShortMessage sm = (ShortMessage) message;
        if(sm.getCommand() == ShortMessage.NOTE_ON) {

        } else if(sm.getCommand() == ShortMessage.NOTE_OFF) {

        }
    }

    @Override
    public void close() {

    }
}
