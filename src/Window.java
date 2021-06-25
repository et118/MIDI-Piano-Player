import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window {

    private PianoPlayer player;
    private JFrame frame;
    private FileDropArea dropPane;
    private JLabel dropTextLabel;

    public JButton playPauseButton;
    private JButton resetButton;
    public Window(PianoPlayer player) {
        this.player = player;
    }

    public void init() {
        frame = new JFrame("Piano Player");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);

        playPauseButton = new JButton("Play");
        playPauseButton.setBounds(0,0,100,25);

        resetButton = new JButton("Reset");
        resetButton.setBounds(100,0,100,25);

        dropPane = new FileDropArea(player,this);
        dropPane.setBounds(200,0,200,50);

        dropTextLabel = new JLabel("Drop File Above");
        dropTextLabel.setBounds(250,40,200,50);

        frame.add(playPauseButton);
        frame.add(resetButton);
        frame.add(dropPane);
        frame.add(dropTextLabel);

        frame.setSize(new Dimension(500,400));
        frame.setLayout(null);
        frame.setVisible(true);

        playPauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(playPauseButton.getText() == "Play") {
                    player.start();
                } else {
                    player.stop();
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.reset();
            }
        });
    }
    public void changeSelectedFile() {
        dropTextLabel.setText(player.getSong().getName());
    }
}
