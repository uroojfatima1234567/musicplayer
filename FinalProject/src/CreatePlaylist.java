import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Stack;

public class CreatePlaylist extends JFrame {

    private JPanel contentPane;
    private Stack<String> obj = new Stack<>();
    private JList<String> list;
    private DefaultListModel<String> listModel;
    private Music musicInstance;

    // Adding songs from here
    static String song1 = "Music/Bhool Jaa Arijit Singh.wav";
    static String song2 = "Music/Raabta Jubin Nautiyal.wav";
    static String song3 = "Music/Mahol Be Maza Hai by Pankaj Udhas.wav";
    // Add more as needed

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Music music = new Music();
                CreatePlaylist frame = new CreatePlaylist(music);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public CreatePlaylist(Music musicInstance) {
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 190, 650, 509);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(SystemColor.control);
        panel.setBounds(0, 0, 650, 509);
        contentPane.add(panel);

        JLabel lblNewLabel = new JLabel("Close");
        lblNewLabel.setBounds(595, 10, 45, 17);
        lblNewLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
            }
        });
        panel.setLayout(null);
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(lblNewLabel);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(0, 474, 650, 35);
        panel_1.setBackground(Color.DARK_GRAY);
        panel.add(panel_1);

        JButton btnAdd = new JButton("Add");
        btnAdd.setBounds(229, 415, 85, 25);
        btnAdd.addActionListener(e -> {
            // Get the selected song from the JList
            String selectedSong = list.getSelectedValue();

            if (selectedSong != null) {
                // Add the selected song to the custom stack
                obj.push(selectedSong);
                System.out.println(obj); // Print the stack after adding a song
                
                JOptionPane.showMessageDialog(null, "Song Added");
            }
        });

        btnAdd.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(btnAdd);

        // Your actual songs for the JList
        String[] songs = {song1, song2, song3};

        // Create the JList with a DefaultListModel
        listModel = new DefaultListModel<>();
        for (String song : songs) {
            listModel.addElement(song);
        }

        list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBounds(200, 80, 266, 320);
        panel.add(scrollPane);

        JButton btnNewButton = new JButton("Save");
        btnNewButton.addActionListener(e -> {
            // Add all songs from the stack to the JList
        	while (!obj.isEmpty()) {
                String song = obj.pop();
                musicInstance.addToSongList(song);
            }
        	JOptionPane.showMessageDialog(null, "Successful");
        });

        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnNewButton.setBounds(349, 415, 85, 25);
        panel.add(btnNewButton);

        // Store the instance of Music
        this.musicInstance = musicInstance;
    }

}
