package olympic;

import olympic.business.Athlete;
import olympic.business.ReturnValue;
import olympic.business.Sport;
import olympic.data.DBConnector;
import olympic.data.PostgreSQLErrorCodes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static olympic.business.ReturnValue.*;

public class Solution {

    public static void main(String[] args) {
        dropTables();
        createTables();
        Sport sport = new Sport();
            sport.setId(1);
            sport.setName("Tenis");
            sport.setCity("Hebron");
        System.out.println(addSport(sport).toString());
//        System.out.println("Creating athletes Table");
//        createTables();
        Athlete athlete1 = new Athlete();
        athlete1.setId(7);
        athlete1.setName("Eial");
        athlete1.setCountry("USA");
        athlete1.setIsActive(false);
        Athlete athlete2 = new Athlete();
        athlete2.setId(2);
        athlete2.setName("Alex");
        athlete2.setCountry("UK");
        athlete2.setIsActive(true);
//        System.out.println("adding athlete1");
        System.out.println(addAthlete(athlete1).toString());
        System.out.println(addAthlete(athlete2).toString());
//        System.out.println("adding athlete2");
//        clearTables();
//        dropTables();
        System.out.println(getAthleteProfile(1).toString());
        System.out.println(getAthleteProfile(2).toString());
        System.out.println(getAthleteProfile(7).toString());
        System.out.println(getSport(1).toString());
        System.out.println(getSport(2).toString());
    }


    public static void createTables() {

        createAthletesTable();
        createSportsTable();
        createParticipantsTable();
        //create more tables
    }

    private static void createParticipantsTable() {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {

            pstmt = connection.prepareStatement("CREATE TABLE Participants\n" +
                    "(\n" +
                    "    Aid INTEGER ,\n" +
                    "    Sid INTEGER ,\n" +
                    "    Payment INTEGER ,\n" +
                    "    PRIMARY KEY (Aid,Sid),\n" +
                    "    FOREIGN KEY (Aid) REFERENCES Athletes (id) ON DELETE CASCADE,\n" +
                    "    FOREIGN KEY (Sid) REFERENCES Sports (id) ON DELETE CASCADE\n" +
                    ")");
            pstmt.execute();
        } catch (SQLException e) {
            //e.printStackTrace()();
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
    }

    private static void createAthletesTable(){
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {

            pstmt = connection.prepareStatement("CREATE TABLE Athletes\n" +
                    "(\n" +
                    "    Id INTEGER ,\n" +
                    "    Name TEXT NOT NULL ,\n" +
                    "    Country TEXT NOT NULL ,\n" +
                    "    Active BOOLEAN NOT NULL ,\n" +
                    "    PRIMARY KEY (Id),\n" +
                    "    CHECK (Id > 0)\n" +
                    ")");
            pstmt.execute();
        } catch (SQLException e) {
            //e.printStackTrace()();
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
    }

    private static void createSportsTable(){
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {

            pstmt = connection.prepareStatement("CREATE TABLE Sports\n" +
                    "(\n" +
                    "    Id INTEGER ,\n" +
                    "    Name TEXT NOT NULL ,\n" +
                    "    City TEXT NOT NULL ,\n" +
                    "    Counter INTEGER DEFAULT 0,\n" +
                    "    PRIMARY KEY (Id),\n" +
                    "    CHECK (Id > 0)\n" +
                    ")");
            pstmt.execute();
        } catch (SQLException e) {
            //e.printStackTrace()();
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
    }

    public static void clearTables() {
        clearAthletesTable();
        clearSportsTable();
        clearParticipantsTable();
        //clear other tables
    }

    private static void clearParticipantsTable() {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("DELETE FROM Participants");
            pstmt.execute();
        } catch (SQLException e) {
            //e.printStackTrace()();
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
    }

    private static void clearAthletesTable() {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("DELETE FROM Athletes");
            pstmt.execute();
        } catch (SQLException e) {
            //e.printStackTrace()();
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
    }

    private static void clearSportsTable() {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("DELETE FROM Sports");
            pstmt.execute();
        } catch (SQLException e) {
            //e.printStackTrace()();
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
    }

    public static void dropTables() {
        dropAthletesTable();
        dropSportsTable();
        dropParticipantsTable();
        //more drops
    }

    private static void dropParticipantsTable() {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("DROP TABLE IF EXISTS Participants");
            pstmt.execute();
        } catch (SQLException e) {
            //e.printStackTrace()();
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
    }

    private static void dropAthletesTable() {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("DROP TABLE IF EXISTS Athletes");
            pstmt.execute();
        } catch (SQLException e) {
            //e.printStackTrace()();
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
    }

    private static void dropSportsTable() {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("DROP TABLE IF EXISTS Sports");
            pstmt.execute();
        } catch (SQLException e) {
            //e.printStackTrace()();
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
    }


    public static ReturnValue addAthlete(Athlete athlete)
    {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("INSERT INTO Athletes" +
                    " VALUES (?, ?, ?, ?)");
            pstmt.setInt(1,athlete.getId());
            pstmt.setString(2, athlete.getName());
            pstmt.setString(3, athlete.getCountry());
            pstmt.setBoolean(4,athlete.getIsActive());
            pstmt.execute();

        } catch (SQLException e) {
            if(Integer.valueOf(e.getSQLState()) ==
                    PostgreSQLErrorCodes.CHECK_VIOLATION.getValue()
                ||  Integer.valueOf(e.getSQLState()) ==
                        PostgreSQLErrorCodes.NOT_NULL_VIOLATION.getValue()){
                    return BAD_PARAMS;
            }
            if(Integer.valueOf(e.getSQLState()) ==
                    PostgreSQLErrorCodes.UNIQUE_VIOLATION.getValue()){
                return ALREADY_EXISTS;
            }

            //e.printStackTrace()();
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                return ERROR;
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                return ERROR;
                //e.printStackTrace()();
            }
        }
        return OK;
    }

    public static Athlete getAthleteProfile(Integer athleteId) {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("SELECT Id,Name,Country,Active From Athletes" +
                    " WHERE Id =?");
            pstmt.setInt(1,athleteId);
            ResultSet results = pstmt.executeQuery();
            if(results.next() == false){ //no such athlete
                results.close();
                return Athlete.badAthlete();
            }else{
                Athlete athlete = new Athlete();
                athlete.setId(results.getInt(1));
                athlete.setName(results.getString(2));
                athlete.setCountry(results.getString(3));
                athlete.setIsActive(results.getBoolean(4));
                results.close();
                return athlete;
            }
        } catch (SQLException e) {
            return Athlete.badAthlete();
            //e.printStackTrace()();
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                return Athlete.badAthlete();
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                return Athlete.badAthlete();
                //e.printStackTrace()();
            }
        }
    }

    public static ReturnValue deleteAthlete(Athlete athlete) {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(
                    "DELETE FROM Athletes " +

                            "where id = ?");
            pstmt.setInt(1,athlete.getId());
            int affectedRows = pstmt.executeUpdate();
            if(affectedRows == 0){
                return NOT_EXISTS;
            }else{
                return OK;
            }
        } catch (SQLException e) {
            System.out.println("Delete athlete exception detected!");
            return ERROR;
            //e.printStackTrace()();
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                return ERROR;
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                return ERROR;
                //e.printStackTrace()();
            }
        }
    }

    public static ReturnValue addSport(Sport sport) {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("INSERT INTO Sports" +
                    " VALUES (?, ?, ?)");
            pstmt.setInt(1,sport.getId());
            pstmt.setString(2, sport.getName());
            pstmt.setString(3, sport.getCity());
            pstmt.execute();

        } catch (SQLException e) {
            if(Integer.valueOf(e.getSQLState()) ==
                    PostgreSQLErrorCodes.CHECK_VIOLATION.getValue()
                    ||  Integer.valueOf(e.getSQLState()) ==
                    PostgreSQLErrorCodes.NOT_NULL_VIOLATION.getValue()){
                return BAD_PARAMS;
            }
            if(Integer.valueOf(e.getSQLState()) ==
                    PostgreSQLErrorCodes.UNIQUE_VIOLATION.getValue()){
                return ALREADY_EXISTS;
            }

            //e.printStackTrace()();
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                return ERROR;
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                return ERROR;
                //e.printStackTrace()();
            }
        }
        return OK;
    }

    public static Sport getSport(Integer sportId) {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("SELECT Id,Name,City,Counter From Sports" +
                    " WHERE Id =?");
            pstmt.setInt(1,sportId);
            ResultSet results = pstmt.executeQuery();
            if(results.next() == false){ //no such sport
                results.close();
                return Sport.badSport();
            }else{
                Sport sport = new Sport();
                sport.setId(results.getInt(1));
                sport.setName(results.getString(2));
                sport.setCity(results.getString(3));
                sport.setAthletesCount(results.getInt(4));
                results.close();
                return sport;
            }
        } catch (SQLException e) {
            return Sport.badSport();
            //e.printStackTrace()();
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                return Sport.badSport();
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                return Sport.badSport();
                //e.printStackTrace()();
            }
        }
    }

    public static ReturnValue deleteSport(Sport sport) {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement(
                    "DELETE FROM Sports " +
                            "where Id = ?");
            pstmt.setInt(1,sport.getId());
            int affectedRows = pstmt.executeUpdate();
            if(affectedRows == 0){
                return NOT_EXISTS;
            }else{
                return OK;
            }
        } catch (SQLException e) {
            System.out.println("Delete Sport exception detected!");
            return ERROR;
            //e.printStackTrace()();
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                return ERROR;
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                return ERROR;
                //e.printStackTrace()();
            }
        }
    }

    public static ReturnValue athleteJoinSport(Integer sportId, Integer athleteId) {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            //TODO check if sport and athelete exists
            //then get the isActive accord - change payment accord.
            pstmt = connection.prepareStatement("INSERT INTO Participants" +
                    " VALUES (?, ?, ?)");
            pstmt.setInt(1,athleteId);
            pstmt.setInt(2,sportId);


            pstmt.execute();

        } catch (SQLException e) {
            //e.printStackTrace()();
        }
        finally {
            try {
                pstmt.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                //e.printStackTrace()();
            }
        }
        return OK;
    }

    public static ReturnValue athleteLeftSport(Integer sportId, Integer athleteId) {
        return OK;
    }

    public static ReturnValue confirmStanding(Integer sportId, Integer athleteId, Integer place) {
        return OK;
    }

    public static ReturnValue athleteDisqualified(Integer sportId, Integer athleteId) {
        return OK;
    }

    public static ReturnValue makeFriends(Integer athleteId1, Integer athleteId2) {
        return OK;
    }

    public static ReturnValue removeFriendship(Integer athleteId1, Integer athleteId2) {
        return OK;
    }

    public static ReturnValue changePayment(Integer athleteId, Integer sportId, Integer payment) {
        return OK;
    }

    public static Boolean isAthletePopular(Integer athleteId) {
        return true;
    }

    public static Integer getTotalNumberOfMedalsFromCountry(String country) {
        return 0;
    }

    public static Integer getIncomeFromSport(Integer sportId) {
        return 0;
    }

    public static String getBestCountry() {
        return "";
    }

    public static String getMostPopularCity() {
        return "";
    }

    public static ArrayList<Integer> getAthleteMedals(Integer athleteId) {
        return new ArrayList<>();
    }

    public static ArrayList<Integer> getMostRatedAthletes() {
        return new ArrayList<>();
    }

    public static ArrayList<Integer> getCloseAthletes(Integer athleteId) {
        return new ArrayList<>();
    }

    public static ArrayList<Integer> getSportsRecommendation(Integer athleteId) {
        return new ArrayList<>();
    }
}

