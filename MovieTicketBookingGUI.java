
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

class Movie {

    String name;
    int availableSeats;

    Movie(String name, int seats) {
        this.name = name;
        this.availableSeats = seats;
    }
}

public class MovieTicketBookingGUI extends JFrame {

    private final JComboBox<String> movieDropdown;
    private final JTextField ticketField;
    private final JTextArea bookingDetails;
    private final ArrayList<Movie> movies = new ArrayList<>();

    public MovieTicketBookingGUI() {
        setTitle("Movie Ticket Booking System");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        // Add movies
        movies.add(new Movie("Avengers: Endgame", 50));
        movies.add(new Movie("Inception", 100));
        movies.add(new Movie("The Dark Knight", 200));

        // Movie Selection Dropdown
        JLabel selectMovieLabel = new JLabel("Select Movie:");
        movieDropdown = new JComboBox<>();
        for (Movie movie : movies) {
            movieDropdown.addItem(movie.name + " (" + movie.availableSeats + " seats)");
        }

        // Ticket Input
        JLabel ticketLabel = new JLabel("Enter Number of Tickets:");
        ticketField = new JTextField();

        // Book Button
        JButton bookButton = new JButton("Book Ticket");
        bookButton.addActionListener(e -> bookTicket());

        // Booking Details Area
        bookingDetails = new JTextArea();
        bookingDetails.setEditable(false);

        // Add components to frame
        add(selectMovieLabel);
        add(movieDropdown);
        add(ticketLabel);
        add(ticketField);
        add(bookButton);
        add(new JScrollPane(bookingDetails));

        setVisible(true);
    }

    private void bookTicket() {
        int selectedIndex = movieDropdown.getSelectedIndex();
        if (selectedIndex < 0) {
            JOptionPane.showMessageDialog(this, "Please select a movie!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String ticketText = ticketField.getText();

        try {
            int tickets = Integer.parseInt(ticketText);
            Movie selectedMovie = movies.get(selectedIndex);

            if (tickets <= 0 || tickets > selectedMovie.availableSeats) {
                JOptionPane.showMessageDialog(this, "Invalid number of tickets!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Update seat availability
            selectedMovie.availableSeats -= tickets;

            // Update dropdown
            movieDropdown.removeAllItems();
            for (Movie movie : movies) {
                movieDropdown.addItem(movie.name + " (" + movie.availableSeats + " seats)");
            }

            // Display booking confirmation
            bookingDetails.append("Movie: " + selectedMovie.name + "\nTickets: " + tickets + "\n\n");
            JOptionPane.showMessageDialog(this, "Booking Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            ticketField.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        MovieTicketBookingGUI gui = new MovieTicketBookingGUI();
        gui.setVisible(true);
    }
}
