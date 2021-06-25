import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileDropArea extends JPanel {
    private PianoPlayer player;
    private Window window;
    public FileDropArea(PianoPlayer player, Window window) {
        super();
        this.player = player;
        this.window = window;
        enableDragAndDrop();
        setBackground(Color.BLACK);
    }

    private void enableDragAndDrop() {
        DropTarget target = new DropTarget(this, new DropTargetListener() {
            @Override
            public void dragEnter(DropTargetDragEvent dtde) {

            }

            @Override
            public void dragOver(DropTargetDragEvent dtde) {

            }

            @Override
            public void dropActionChanged(DropTargetDragEvent dtde) {

            }

            @Override
            public void dragExit(DropTargetEvent dte) {

            }

            @Override
            public void drop(DropTargetDropEvent e) {

                try {
                    e.acceptDrop(DnDConstants.ACTION_COPY);

                    List list = (List) e.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
                    File file = (File) list.get(0);
                    player.setSong(file);
                    window.changeSelectedFile();
                } catch (UnsupportedFlavorException unsupportedFlavorException) {
                    unsupportedFlavorException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }
}