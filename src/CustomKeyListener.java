import lc.kra.system.keyboard.event.GlobalKeyAdapter;
import lc.kra.system.keyboard.event.GlobalKeyEvent;

public class CustomKeyListener extends GlobalKeyAdapter {
    private PianoPlayer player;
    public CustomKeyListener(PianoPlayer player) {
        this.player = player;
    }
    @Override
    public void keyPressed(GlobalKeyEvent event) {
        if (event.getVirtualKeyCode() == GlobalKeyEvent.VK_DELETE) {
            player.toggle();
        }
        if(event.getVirtualKeyCode() == GlobalKeyEvent.VK_END) {
            player.reset();
        }
    }

    @Override
    public void keyReleased(GlobalKeyEvent event) {

    }
}
