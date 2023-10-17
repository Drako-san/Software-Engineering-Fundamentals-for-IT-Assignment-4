import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;
import java.util.ArrayList;
public class Artist {
    private String ID;
    private String Name;
    private String Address;
    private String Birthdate;
    private String Bio;
    private ArrayList <String> Occupations;
    private ArrayList <String> Genres;
    private ArrayList <String> Awards;
    public Artist(String id, String name, String address, String birthdate, String bio, ArrayList <String> occupations, ArrayList <String> genres, ArrayList <String> awards)
    {
        ID=id;
        Name=name;
        Address=address;
        Birthdate=birthdate;
        Bio=bio;
        Occupations=occupations;
        Genres=genres;
        Awards=awards;
    }
    public boolean addArtist()
    {
        if (isValidArtistID(ID) && isValidBirthdate(Birthdate) && isValidAddress(Address)
                && isValidBio(Bio) && isValidOccupations(Occupations) && isValidGenres(Genres)
                && isValidAwards(Awards)) {
            // If all conditions are met, you can save the artist's information to a TXT file here
            // and return true to indicate success.
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("artists.txt", true));
                writer.write(ID + "|" + Name + "|" + Address + "|" + Birthdate + "|" + Bio + "|" +
                        String.join(",", Occupations) + "|" + String.join(",", Genres) + "|" +
                        String.join(",", Awards));
                writer.newLine();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
        return false;
    }

    private boolean isValidArtistID(String id) {
        return id.matches("[5-9]{3}[A-Z]{5}[!@#$%^&*()_+~`]{2}");
    }

    private boolean isValidBirthdate(String birthdate) {
        return birthdate.matches("^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-(19|20)\\d\\d$");
    }

    private boolean isValidAddress(String address) {
        return address.matches("[A-Za-z]+\\|[A-Za-z]+\\|[A-Za-z]+");
    }

    private boolean isValidBio(String bio) {
        int wordCount = bio.split("\\s+").length;
        return wordCount >= 10 && wordCount <= 30;
    }

    private boolean isValidOccupations(ArrayList<String> occupations) {
        return occupations.size() >= 1 && occupations.size() <= 5;
    }

    private boolean isValidGenres(ArrayList<String> genres) {
        return genres.size() >= 2 && genres.size() <= 5 && !genres.contains("pop") || !genres.contains("rock");
    }

    private boolean isValidAwards(ArrayList<String> awards) {
        for (String award : awards) {
            String[] parts = award.split(",");
            if (parts.length != 2) {
                return false;
            }
            String year = parts[0].trim();
            String title = parts[1].trim();
            if (!year.matches("\\d{4}") || title.length() < 4 || title.length() > 10) {
                return false;
            }
        }
        return awards.size() <= 3;
    }
    public boolean updateArtist(String newID, String newName, String newAddress, String newBirthdate,
                                String newBio, ArrayList<String> newOccupations, ArrayList<String> newGenres,
                                ArrayList<String> newAwards) {
        // Check if the new artist information meets all the conditions
        if (isValidArtistID(newID) && isValidBirthdate(newBirthdate) && isValidAddress(newAddress)
                && isValidBio(newBio) && isValidOccupations(newOccupations) && isValidGenres(newGenres)
                && isValidAwards(newAwards)) {

            // Check Condition 2: If an artist was born before 2000, their occupation cannot be changed
            if (isBornBefore2000() && !Occupations.equals(newOccupations)) {
                return false;
            }

            // Check Condition 3: Awards that were given before 2000 cannot be changed
            if (awardsGivenBefore2000Changed(newAwards)) {
                return false;
            }

            // Update artist information
            ID = newID;
            Name = newName;
            Address = newAddress;
            Birthdate = newBirthdate;
            Bio = newBio;
            Occupations = newOccupations;
            Genres = newGenres;
            Awards = newAwards;

            // Update the artist's information in the TXT file here
            try {
                // Read all artists from the file, update the information for this artist, and write back to the file
                BufferedReader reader = new BufferedReader(new FileReader("artists.txt"));
                ArrayList<String> lines = new ArrayList<>();
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("\\|");
                    if (parts.length == 8 && parts[0].equals(ID)) {
                        line = ID + "|" + Name + "|" + Address + "|" + Birthdate + "|" + Bio + "|" +
                                String.join(",", Occupations) + "|" + String.join(",", Genres) + "|" +
                                String.join(",", Awards);
                    }
                    lines.add(line);
                }
                reader.close();

                BufferedWriter writer = new BufferedWriter(new FileWriter("artists.txt"));
                for (String updatedLine : lines) {
                    writer.write(updatedLine);
                    writer.newLine();
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }

            return true;
        }

        return false;
    }

    // ... other helper methods ...

    private boolean isBornBefore2000() {
        String[] birthdateParts = Birthdate.split("-");
        int birthYear = Integer.parseInt(birthdateParts[2]);
        return birthYear < 2000;
    }

    private boolean awardsGivenBefore2000Changed(ArrayList<String> newAwards) {
        for (String award : Awards) {
            String[] awardParts = award.split(",");
            int awardYear = Integer.parseInt(awardParts[0].trim());
            if (awardYear < 2000 && !newAwards.contains(award)) {
                return true;
            }
        }
        return false;
    }
}
