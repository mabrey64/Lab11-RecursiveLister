import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * RecursiveGui is a simple GUI application that allows users to select a directory,
 * start a recursive process, and quit the application.
 * It uses Swing components to create the GUI layout and handle user interactions.
 */

public class RecursiveGui extends JFrame
{
    JPanel mainPanel;
    JLabel titleLabel;
    JTextPane textPane; // Changed to JTextPane
    JFileChooser fileChooser;
    JScrollPane scrollPane;
    JButton selectFileButton;
    JButton startButton;
    JButton quitButton;

    /**
     * Constructor to initialize the GUI components and layout.
     * Sets the title, size, and default close operation for the JFrame.
     * Initializes the main panel, title label, text pane, file chooser,
     * scroll pane, and buttons.
     */
    public RecursiveGui ()
    {
        setTitle("Recursive GUI: A Simple Example");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setLayout(null);

        titleLabel = new JLabel("Select a directory to display its files and subdirectories:");
        titleLabel.setBounds(175, 10, 500, 30);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        mainPanel.add(titleLabel);

        textPane = new JTextPane(); // Initialize JTextPane
        textPane.setFont(new Font("Monospaced", Font.PLAIN, 12));
        textPane.setEditable(false);

        scrollPane = new JScrollPane(textPane);
        scrollPane.setBounds(10, 50, 760, 400);
        mainPanel.add(scrollPane);

        selectFileButton = new JButton("Select File");
        selectFileButton.setBounds(100, 470, 150, 30);
        selectFileButton.addActionListener(e -> {
            fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int returnValue = fileChooser.showOpenDialog(this);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                String selectedDirectory = fileChooser.getSelectedFile().getName();
                textPane.setText("Selected Directory: " + selectedDirectory);
            }
            else {
                textPane.setText("No directory selected.");
            }
        });
        mainPanel.add(selectFileButton);

        startButton = new JButton("Start");
        startButton.setBounds(325, 470, 150, 30);
        startButton.addActionListener(e -> {
            textPane.setText("Starting the recursive process...\n");
            Path directoryPath = fileChooser.getSelectedFile().toPath();
            List<Path> fileList = new ArrayList<>();
            ListAllFiles(directoryPath, fileList);
        });
        mainPanel.add(startButton);

        quitButton = new JButton("Quit");
        quitButton.setBounds(550, 470, 150, 30);
        quitButton.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
        mainPanel.add(quitButton);

        add(mainPanel);
    }

    /**
     * Recursively lists all files in the given directory and its subdirectories,
     * coloring directories blue and files green without using HTML.
     * @param path The path to the directory.
     * @param fileList The list to store the file paths.
     */
    public void ListAllFiles(Path path, List<Path> fileList) {
        StyledDocument doc = textPane.getStyledDocument();
        Style styleBlue = textPane.addStyle("blue", null);
        StyleConstants.setForeground(styleBlue, Color.BLUE);

        Style styleGreen = textPane.addStyle("green", null);
        StyleConstants.setForeground(styleGreen, Color.GREEN);

        // Recursively list files and directories
        if (path.toFile().isDirectory()) {
            File[] files = path.toFile().listFiles();
            if (files != null) {
                for (File file : files) {
                    String displayName = file.getName();
                    if (file.isDirectory()) {
                        try {
                            doc.insertString(doc.getLength(), displayName + " (Sub-Directory)\n", styleBlue);
                        } catch (BadLocationException e) {
                            e.printStackTrace();
                        }
                        ListAllFiles(file.toPath(), fileList);
                    } else {
                        fileList.add(file.toPath());
                        try {
                            doc.insertString(doc.getLength(), displayName + " (File)\n", styleGreen);
                        } catch (BadLocationException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RecursiveGui gui = new RecursiveGui();
            gui.setVisible(true);
        });
    }
}