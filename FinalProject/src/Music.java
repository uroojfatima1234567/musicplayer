import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class SongNode {
    String data;
    SongNode next;
    SongNode prev;

    public SongNode(String data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

public class Music extends JFrame {
    private SongNode head;
    private SongNode tail;
    private SongNode currentSongNode;
    private Clip clip;
    private JProgressBar progressBar;
    private JLabel currentSongLabel;
    private JLabel upcomingSongsLabel;
    

    // Adding songs from here
    static String song1 = "Music/Bhool Jaa Arijit Singh.wav";
    static String song2 = "Music/Raabta Jubin Nautiyal.wav";
    static String song3 = "Music/Mahol Be Maza Hai by Pankaj Udhas.wav";
    // Add more as needed
    
    //private JList<String> songList;
    private JList<String> songList;
    
    private DefaultListModel<String> songListModel1;
    //private JList<String> songList;
    private JList<String> songList1;
    
	private DefaultListModel<String> songListModel;

    public Music() {
        // Set up the JFrame
        setTitle("Music Player");
        setBounds(300, 110, 950, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create buttons
        JButton playButton = new JButton("Play");
        playButton.setBounds(418, 531, 67, 21);
        JButton stopButton = new JButton("Stop");
        stopButton.setBounds(490, 531, 61, 21);
        JButton nextButton = new JButton("Next");
        nextButton.setBounds(561, 531, 67, 21);
        JButton previousButton = new JButton("Previous");
        previousButton.setBounds(321, 531, 87, 21);
        

        // Add action listeners to buttons
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // playSelectedSong();
            	playSong();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopSong();
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextSong();
            }
        });
       

        previousButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                previousSong();
            }
        });

        // Set up the layout
        getContentPane().setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(255, 255, 255));
        buttonPanel.setLayout(null);
        buttonPanel.add(playButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(nextButton);
        buttonPanel.add(previousButton);

        getContentPane().add(buttonPanel, BorderLayout.CENTER);

        // Create a progress bar
        progressBar = new JProgressBar(0, 100);
        progressBar.setBounds(297, 492, 360, 16);
        buttonPanel.add(progressBar);
        progressBar.setStringPainted(true);

        // Create a label for displaying the current song
        currentSongLabel = new JLabel("Now Playing: ");
        currentSongLabel.setBounds(0, 10, 350, 13);

        // Create a label for displaying upcoming songs
        upcomingSongsLabel = new JLabel("Upcoming Songs: ");
        upcomingSongsLabel.setBounds(0, 43, 350, 13);

        // Create a panel for labels
        JPanel labelPanel = new JPanel();
        labelPanel.setBounds(297, 403, 360, 66);
        buttonPanel.add(labelPanel);
        labelPanel.setLayout(null);
        labelPanel.add(currentSongLabel);
        labelPanel.add(upcomingSongsLabel);

        JPanel panel = new JPanel();
        panel.setBounds(0, 572, 936, 41);
        panel.setBackground(Color.DARK_GRAY);
        buttonPanel.add(panel);
        panel.setLayout(null);

        JSeparator separator = new JSeparator();
        separator.setOrientation(SwingConstants.VERTICAL);
        separator.setBackground(new Color(255, 255, 255));
        separator.setBounds(458, 10, 41, 25);
        panel.add(separator);

        JLabel lblNewLabel_5 = new JLabel("The sound of the future");
        lblNewLabel_5.setFont(new Font("Segoe Script", Font.BOLD, 18));
        lblNewLabel_5.setForeground(new Color(255, 255, 255));
        lblNewLabel_5.setBounds(163, 5, 236, 30);
        panel.add(lblNewLabel_5);

        JLabel lblNewLabel_5_1 = new JLabel("Where the music never ends !");
        lblNewLabel_5_1.setForeground(Color.WHITE);
        lblNewLabel_5_1.setFont(new Font("Segoe Script", Font.BOLD, 18));
        lblNewLabel_5_1.setBounds(536, 5, 290, 30);
        panel.add(lblNewLabel_5_1);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setBounds(44, 10, 93, 90);
        ImageIcon img2 = new ImageIcon(this.getClass().getResource("/Logo2.png"));
        lblNewLabel.setIcon(img2);
        buttonPanel.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("MelodyMatrix");
        lblNewLabel_1.setBounds(20, 94, 144, 28);
        lblNewLabel_1.setFont(new Font("Segoe Script", Font.BOLD, 17));
        buttonPanel.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Music Player");
        lblNewLabel_2.setBounds(341, 10, 329, 56);
        lblNewLabel_2.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 40));
        buttonPanel.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("");
        lblNewLabel_3.setBounds(300, 94, 370, 310);
        ImageIcon img3 = new ImageIcon(this.getClass().getResource("/Disk.png"));
        lblNewLabel_3.setIcon(img3);
        buttonPanel.add(lblNewLabel_3);
        
        
        // Create Playlist Button
        JButton btnCreatePlaylist = new JButton("Create Playlist");
        btnCreatePlaylist.setBounds(797, 92, 129, 35);
        btnCreatePlaylist.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		CreatePlaylist obj = new CreatePlaylist(null);
				obj.main(null);
        		dispose();
        	}
        });
        buttonPanel.add(btnCreatePlaylist);
        
        
        
        

        // Initialize the playlist
        initializePlaylist();
        
        
        
        // Create the list of songs
        DefaultListModel<String> songListModel = new DefaultListModel<>();
        SongNode temp = head;
        do {
            songListModel.addElement(new File(temp.data).getName());
            temp = temp.next;
        } while (temp != head);

        // Create the JList
        songList = new JList<>(songListModel);

        // Add the list to a scroll pane
        JScrollPane scrollPane = new JScrollPane(songList);
        scrollPane.setBounds(10, 207, 231, 301);
        buttonPanel.add(scrollPane);
        
     
        
        
    //////////////////////////////////////////////////////////////////////// 
        
     // Create the list of songs
        songListModel1 = new DefaultListModel<>();
        
        songList1 = new JList<>(songListModel1);

        
        JScrollPane scrollPane_1 = new JScrollPane(songList1);
        scrollPane_1.setBounds(695, 207, 231, 301);
        buttonPanel.add(scrollPane_1);
        
        
        
        JLabel lblNewLabel_4 = new JLabel("Library");
        lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 22));
        lblNewLabel_4.setBounds(77, 169, 87, 28);
        buttonPanel.add(lblNewLabel_4);
        
        JLabel lblNewLabel_4_1 = new JLabel("Playlist");
        lblNewLabel_4_1.setFont(new Font("Times New Roman", Font.BOLD, 22));
        lblNewLabel_4_1.setBounds(772, 169, 87, 28);
        buttonPanel.add(lblNewLabel_4_1);

        // Add a listener to handle song selection
        songList.addListSelectionListener(e -> playSelectedSong(songList));
        songList1.addListSelectionListener(e -> playSelectedSong1(songList1));


        // ...

        // Show the JFrame
        setVisible(true);
    }

    // Add the playSelectedSong method
    private void playSelectedSong(JList<String> selectedList) {
        stopSong();

        int selectedIndex = selectedList.getSelectedIndex();
        if (selectedIndex >= 0 && selectedIndex < selectedList.getModel().getSize()) {
            SongNode selectedNode = getNodeAtPosition(selectedIndex, selectedList);
            if (selectedNode != null) {
                currentSongNode = selectedNode;
                playSong();
            } else {
                System.out.println("Selected node is null!");
            }
        }
    }



    private void playSelectedSong1(JList<String> selectedList) {
        stopSong();

        int selectedIndex = selectedList.getSelectedIndex();
        if (selectedIndex >= 0 && selectedIndex < selectedList.getModel().getSize()) {
            DefaultListModel<String> listModel = (DefaultListModel<String>) selectedList.getModel();
            String filePath = listModel.getElementAt(selectedIndex);

            // Find the corresponding node in the playlist
            SongNode selectedNode = findNodeByData(filePath);

            if (selectedNode != null) {
                currentSongNode = selectedNode;
                playSong();
            } else {
                System.out.println("Selected node is null!");
            }
        }
    }

    // Helper method to find a node by its data (file path)
    private SongNode findNodeByData(String data) {
        SongNode temp = head;
        do {
            if (temp.data.equals(data)) {
                return temp;
            }
            temp = temp.next;
        } while (temp != head);
        return null; // Node not found
    }


    
    
    private void initializePlaylist() 
    {
        // Manually add songs to the playlist
        addSong(song1);
        addSong(song2);
        addSong(song3);
        // Add more songs as needed

        // Ensure currentSongNode starts at the head
        currentSongNode = head;
    }



    private SongNode getNodeAtPosition(int position, JList<String> selectedList) {
        DefaultListModel<String> listModel = (DefaultListModel<String>) selectedList.getModel();
        if (position < 0 || position >= listModel.getSize()) {
            return null;
        }

        SongNode temp = head;
        for (int i = 0; i < position; i++) {
            if (temp != null) {
                temp = temp.next;
            } else {
                // Handle the case where the position is out of bounds
                return null;
            }
        }

        return temp;
    }
    //Doubly Circular LinkedList
    private void addSong(String data) 
    {
        SongNode newNode = new SongNode(data);
        if (head == null) 
        {
            head = newNode;
            tail = newNode;
        } 
        else 
        {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
            tail.next = head; // Make it circular
            head.prev = tail; // Make it circular
        }
    }
    

    private void playSong() 
    {
        try 
        {
            if (clip != null && clip.isRunning()) 
            {
                clip.stop();
            }

            if (currentSongNode == null) 
            {
                currentSongNode = head;
            }

            String filePath = currentSongNode.data;

            // Get an audio input stream from the file
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));

            // Get a clip to play the audio
            clip = AudioSystem.getClip();

            // Open the audio input stream and start playing
            clip.open(audioInputStream);
            clip.start();

            // Set the current song label
            currentSongLabel.setText("Now Playing: " + new File(filePath).getName());

            // Set the upcoming songs label
            upcomingSongsLabel.setText("Upcoming Songs: " + getUpcomingSongs());

            // Set up a timer to update the progress bar
            Timer timer = new Timer(100, new ActionListener() 
            {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    int currentMicrosecond = (int) clip.getMicrosecondPosition();
                    int totalMicrosecond = (int) clip.getMicrosecondLength();
                    int progress = (int) ((double) currentMicrosecond / totalMicrosecond * 100);
                    progressBar.setValue(progress);

                    if (!clip.isRunning()) 
                    {
                        ((Timer) e.getSource()).stop();
                    }
                }
            });
            timer.start();

            // Move to the next song in the playlist
            currentSongNode = currentSongNode.next;

        } 
        catch (Exception ex) 
        {
            ex.printStackTrace();
        }
    }

    private void stopSong() 
    {
        if (clip != null && clip.isRunning()) 
        {
            clip.stop();
          //  progressBar.setValue(0);
        }
    }

    private void nextSong() 
    {
        stopSong();
        playSong(); // Plays the next song in the playlist
    }

    private void previousSong() {
        stopSong();

        try {
            if (clip != null && clip.isRunning()) {
                clip.stop();
            }

            // Move to the previous song
            if (currentSongNode != null) {
                currentSongNode = currentSongNode.prev;
            } else {
                // Handle the case where the currentSongNode is null (initial state)
                currentSongNode = tail;
            }

            String filePath = currentSongNode.data;

            // Get an audio input stream from the file
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));

            // Get a clip to play the audio
            clip = AudioSystem.getClip();

            // Open the audio input stream and start playing
            clip.open(audioInputStream);
            clip.start();

            // Set the current song label
            currentSongLabel.setText("Now Playing: " + new File(filePath).getName());

            // Set the upcoming songs label
            upcomingSongsLabel.setText("Upcoming Songs: " + getUpcomingSongs());

            // Set up a timer to update the progress bar
            Timer timer = new Timer(100, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int currentMicrosecond = (int) clip.getMicrosecondPosition();
                    int totalMicrosecond = (int) clip.getMicrosecondLength();
                    int progress = (int) ((double) currentMicrosecond / totalMicrosecond * 100);
                    progressBar.setValue(progress);

                    if (!clip.isRunning()) {
                        ((Timer) e.getSource()).stop();
                    }
                }
            });
            timer.start();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private String getUpcomingSongs() 
    {
        StringBuilder upcomingSongs = new StringBuilder();
        SongNode temp = currentSongNode.next;
        upcomingSongs.append(new File(temp.data).getName());
        return upcomingSongs.toString();
    }
    
   
    public void setSongListModel(DefaultListModel<String> songListModel1) {
        // Set the model to the JList directly
        songList.setModel(songListModel1);
        // Ensure the JList is updated
        songList.repaint();
    }
    
    public void addToSongList(String song) {
        if (songListModel1 != null) {
            // Add the song to your JList in the Music class
            songListModel1.addElement(song);
            // Ensure the JList is updated
            songList.repaint();
        } else {
            System.out.println("Song list model is null!");
        }
    }

    
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Music();
            }
        });
    }
}
