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
//        Sport sport = new Sport();
//            sport.setId(1);
//            sport.setName("Tenis");
//            sport.setCity("Hebron");
//        System.out.println(addSport(sport).toString());
//        System.out.println("Creating athletes Table");
//        createTables();
//        Athlete athlete1 = new Athlete();
//        athlete1.setId(7);
//        athlete1.setName("Eial");
//        athlete1.setCountry(null);
//        athlete1.setIsActive(false);
//        Athlete athlete2 = new Athlete();
//        athlete2.setId(2);
//        athlete2.setName("Alex");
//        athlete2.setCountry("UK");
//        athlete2.setIsActive(true);
//        System.out.println("adding athlete1");
//        System.out.println(addAthlete(athlete1).toString());
//        System.out.println("adding athlete2");
//        addAthlete(athlete2);
//        clearTables();
//        dropTables();
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
        dropParticipants();
        //more drops
    }

    private static void dropParticipants() {
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
            DBConnector.printResults(results); //TODO create Athelete object to return - check
            //if exists
            Athlete athlete = new Athlete();
//            athlete.setId(athleteId);
//            athlete.setName();
//            athlete.setCountry();
//            athlete.setIsActive();
            results.close();
            return athlete;

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
            //check if coutner is zero
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
            DBConnector.printResults(results); //TODO create Sports object to return - check
            //if exists
            Sport sport = new Sport();
//            sport.setId(athleteId);
//            sport.setName();
//            sport.setCity();
//            sport.setAthletesCount();
            results.close();
            return sport;
            //if doesnt exists return badSport here!

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

