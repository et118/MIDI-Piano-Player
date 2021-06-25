import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Note {
    private final String[] NAMES = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
    private final Character[] specialCharArray = new Character[]{'!','@','$','%','^','*','('};
    private final Character[] specialNumberArray = new Character[]{'1','2','4','5','6','8','9'};

    private List<Character> specialCharList = new ArrayList<>(Arrays.asList(specialCharArray));
    private List<Character> specialNumberList = new ArrayList<>(Arrays.asList(specialNumberArray));


    private ArrayList<char[]> keys = new ArrayList<char[]>();

    public int key;
    public int octave;
    public String name;
    public int note;

    public Note(int key) {
        this.key = key;
        this.octave = (key / 12) - 1;
        this.note = key % 12;
        this.name = NAMES[this.note];

        keys.add(new char[]{'1', '!', '2', '@', '3', '4', '$', '5', '%', '6', '^', '7'});
        keys.add(new char[]{'8', '*', '9', '(', '0', 'q', 'Q', 'w', 'W', 'e', 'E', 'r'});
        keys.add(new char[]{'t', 'T', 'y', 'Y', 'u', 'i', 'I', 'o', 'O', 'p', 'P', 'a'});
        keys.add(new char[]{'s', 'S', 'd', 'D', 'f', 'g', 'G', 'h', 'H', 'j', 'J', 'k'});
        keys.add(new char[]{'l', 'L', 'z', 'Z', 'x', 'c', 'C', 'v', 'V', 'b', 'B', 'n'});

    }

    public char getChar() {
        char key = 'c';
        try {
            key = keys.get(octave - 1)[note];
        } catch (IndexOutOfBoundsException e) {
            if(octave - 1 < 1) {
                key = keys.get(0)[note];
            }else if(octave - 1 > 4) {
                key = keys.get(4)[note];
            }
        }
        if(specialCharList.contains(key)) {
            key = specialNumberList.get(specialCharList.indexOf(key));
        }
        return key;
    }
    public boolean getShift() {
        char c = getChar();

        if(!specialCharList.contains(c)) {
            if(!Character.isUpperCase(c)) {
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    @Override
    public String toString() {
        return "Note -> " + this.name + this.octave + " key=" + this.key;
    }
}
