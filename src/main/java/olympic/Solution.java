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
        clearTables();

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

        Athlete athlete3 = new Athlete();
        athlete3.setId(10);
        athlete3.setName("or");
        athlete3.setCountry("Brazil");
        athlete3.setIsActive(true);



        System.out.println("adding athlete1");
        System.out.println(addAthlete(athlete1).toString());
        System.out.println("adding athlete2");
        System.out.println(addAthlete(athlete2).toString());
        System.out.println("adding athlete3");
        System.out.println(addAthlete(athlete3).toString());

        Sport sport =new Sport();
        sport.setId(50);
        sport.setCity("haifa");
        sport.setName("football");
        //System.out.println("adding sport");


        System.out.println(makeFriends(2,7).toString());
        System.out.println(makeFriends(2,7).toString());
        System.out.println(makeFriends(7,2).toString());
        System.out.println(makeFriends(10,7).toString());

        System.out.println(removeFriendship(10,8).toString());
        System.out.println(removeFriendship(7,10).toString());


//        System.out.println(addSport(sport).toString());
//        System.out.println(athleteJoinSport(50,2).toString());
//
//        System.out.println(confirmStanding(50,2,2).toString());
//        System.out.println(athleteDisqualified(50,5).toString());
    }


    public static void createTables() {

        createAthletesTable();
        createSportsTable();
        createParticipantsTable();
        createWinnersTable();
        createFriendsTable();
        //create more tables
    }

    private static void createFriendsTable() {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {

            pstmt = connection.prepareStatement("CREATE TABLE Friends\n" +
                    "(\n" +
                    "    Aid1 INTEGER ,\n" +
                    "    Aid2 INTEGER ,\n" +
                    "    PRIMARY KEY (Aid1,Aid2),\n" +
                    "    FOREIGN KEY (Aid1) REFERENCES Athletes (id) ON DELETE CASCADE,\n" +
                    "    FOREIGN KEY (Aid2) REFERENCES Athletes (id) ON DELETE CASCADE\n" +
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

    private static void createWinnersTable() {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {

            pstmt = connection.prepareStatement("CREATE TABLE Winners\n" +
                    "(\n" +
                    "    Aid INTEGER ,\n" +
                    "    Sid INTEGER ,\n" +
                    "    Place INTEGER ,\n" +
                    "    PRIMARY KEY (Aid,Sid),\n" +
                    "    FOREIGN KEY (Aid) REFERENCES Athletes (id) ON DELETE CASCADE,\n" +
                    "    FOREIGN KEY (Sid) REFERENCES Sports (id) ON DELETE CASCADE,\n" +
                    "    CHECK (Place > 0 AND Place < 4 )\n" +
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
                    "    Counter INTEGER,\n" +
                    "    PRIMARY KEY (Id),\n" +
                    "    CHECK (Id > 0),\n" +
                    "    CHECK (Counter > -1)\n" +
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
        clearWinnersTables();
        clearFriendsTable();
        //clear other tables
    }

    private static void clearFriendsTable() {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("DELETE FROM Friends");
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

    private static void clearWinnersTables() {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("DELETE FROM Winners");
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
        dropWinnersTable();
        dropFriendsTable();
        //more drops
    }

    private static void dropFriendsTable() {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("DROP TABLE IF EXISTS Friends");
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

    private static void dropWinnersTable() {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            pstmt = connection.prepareStatement("DROP TABLE IF EXISTS Winners");
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
                    " VALUES (?, ?, ?,?)");
            pstmt.setInt(1,sport.getId());
            pstmt.setString(2, sport.getName());
            pstmt.setString(3, sport.getCity());
            pstmt.setInt(4, 0); //coutner set to 0
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
        PreparedStatement pstmt1 = null;
        try {
            Athlete athlete = getAthleteProfile(athleteId);
            Sport sport = getSport(sportId);
            if(athlete.equals(Athlete.badAthlete()) || sport.equals(Sport.badSport())){ //no such athlete or sport
                return NOT_EXISTS;
            }else{
                pstmt = connection.prepareStatement("SELECT Aid,Sid,Payment FROM Participants" +
                        " WHERE Aid =? AND Sid=?");
                pstmt.setInt(1,athleteId);
                pstmt.setInt(2,sportId);
                ResultSet results = pstmt.executeQuery();
                if(results.next()){ //already exists in paricipants
                    results.close();
                    return ALREADY_EXISTS;
                }else{ //all good - lets add!
                    pstmt = connection.prepareStatement("INSERT INTO Participants" +
                            " VALUES (?, ?, ?)");
                    pstmt.setInt(1,athleteId);
                    pstmt.setInt(2,sportId);
                    if(athlete.getIsActive()){ // no payment needed
                        pstmt.setInt(3,0);
                        //update counter
                        pstmt1 = connection.prepareStatement(
                                "UPDATE Sports " +
                                        "SET counter = counter + ? " +
                                        "where id = ?");
                        pstmt1.setInt(1,1);
                        pstmt1.setInt(2, sport.getId());
                        pstmt1.executeUpdate();
                    }else{ //observer - need to pay 100$
                        pstmt.setInt(3,100);
                    }
                    pstmt.execute();
                }
                results.close();
            }
        } catch (SQLException e) {
            return ERROR;
            //e.printStackTrace()();
        }
        finally {
            try {
                if(pstmt != null){
                    pstmt.close();
                }
                if(pstmt1 != null){
                    pstmt1.close();
                }
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

    public static ReturnValue athleteLeftSport(Integer sportId, Integer athleteId) {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            Athlete athlete = getAthleteProfile(athleteId);
            pstmt = connection.prepareStatement(
                    "DELETE FROM Participants " +
                            "where Aid = ? AND Sid= ?");
            pstmt.setInt(1,athleteId);
            pstmt.setInt(2,sportId);
            int affectedRows = pstmt.executeUpdate();
            if(affectedRows == 0){
                return NOT_EXISTS;
            }else{
                //update counter
                if(athlete.getIsActive()){
                    pstmt = connection.prepareStatement(
                            "UPDATE Sports " +
                                    "SET counter = counter - ? " +
                                    "where id = ?");
                    pstmt.setInt(1,1);
                    pstmt.setInt(2, sportId);
                    pstmt.executeUpdate();
                }
                return OK;
            }
        } catch (SQLException e) {
            System.out.println("Delete Participant exception detected!");
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

    public static ReturnValue confirmStanding(Integer sportId, Integer athleteId, Integer place) {
        if(place>3 || place <1)
            return BAD_PARAMS;

        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        PreparedStatement pstmt1 = null;

        try {
            Athlete athlete = getAthleteProfile(athleteId);
            Sport sport = getSport(sportId);

            pstmt = connection.prepareStatement("SELECT Aid,Sid,Payment FROM Participants" +
                    " WHERE Aid =? AND Sid=?");
            pstmt.setInt(1,athleteId);
            pstmt.setInt(2,sportId);
            ResultSet results = pstmt.executeQuery();


            if(!athlete.getIsActive() || !results.next() || athlete.equals(Athlete.badAthlete()) || sport.equals(Sport.badSport())){ //no such athlete or sport or the athlete is not participating in the sport
                results.close();
                return NOT_EXISTS;
            }else {
                results.close();

                //check if athlete already got a medal
                pstmt1 = connection.prepareStatement("SELECT Aid,Sid,Place FROM Winners" +
                        " WHERE Aid =? AND Sid=?");
                pstmt1.setInt(1,athleteId);
                pstmt1.setInt(2,sportId);
                results = pstmt1.executeQuery();

                if(results.next()) {//athlete already got medal, time to update it!
                    pstmt = connection.prepareStatement("UPDATE Winners" +
                            " SET Place = ?" +
                            " WHERE Aid =? AND Sid=?");
                    pstmt.setInt(1, place);
                    pstmt.setInt(2, athleteId);
                    pstmt.setInt(3, sportId);
                    pstmt.executeUpdate();
                }else {

                    pstmt = connection.prepareStatement("INSERT INTO Winners" +
                            " VALUES (?, ?, ?)");
                    pstmt.setInt(1, athleteId);
                    pstmt.setInt(2, sportId);
                    pstmt.setInt(3, place);
                    pstmt.execute();
                }
            }
        } catch (SQLException e) {
            return ERROR;
            //e.printStackTrace()();
        }
        finally {
            try {
                if(pstmt != null){
                    pstmt.close();
                }
                if(pstmt1 != null){
                    pstmt1.close();
                }
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

    public static ReturnValue athleteDisqualified(Integer sportId, Integer athleteId) {
        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        try {
            Athlete athlete = getAthleteProfile(athleteId);
            pstmt = connection.prepareStatement(
                    "DELETE FROM Winners " +
                            "where Aid = ? AND Sid= ?");
            pstmt.setInt(1,athleteId);
            pstmt.setInt(2,sportId);
            int affectedRows = pstmt.executeUpdate();
            if(affectedRows == 0){
                return NOT_EXISTS;
            }else{
                //update counter
                return OK;
            }
        } catch (SQLException e) {
            System.out.println("Delete winner exception detected!");
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

    public static ReturnValue makeFriends(Integer athleteId1, Integer athleteId2) {

        if(athleteId1 == athleteId2) //check if bad parameters
            return BAD_PARAMS;

        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        PreparedStatement pstmt1 = null;
        ResultSet results;
        ResultSet results1;
        try {
            pstmt = connection.prepareStatement("SELECT Id,Name,Country,Active From Athletes" +
                    " WHERE Id =?");
            //check if athletes exists
            pstmt = connection.prepareStatement("SELECT Id From Athletes" +
                    " Where Id = ?");
            pstmt.setInt(1,athleteId1);
            pstmt1 = connection.prepareStatement("SELECT Id From Athletes" +
                    " Where Id = ?");
            pstmt1.setInt(1,athleteId2);
            results=pstmt.executeQuery();
            results1=pstmt1.executeQuery();
            if(!results1.next() || !results.next())
                return NOT_EXISTS;
            //end check if athletes exists

            //check if already friends
            pstmt = connection.prepareStatement("SELECT Aid1,Aid2 FROM Friends" +
                    " WHERE Aid1 = ? AND  Aid2= ?" +
                    " UNION " +
                    " SELECT Aid1,Aid2 FROM Friends" +
                    " WHERE Aid1 = ? AND  Aid2= ?");

            pstmt.setInt(1,athleteId1);
            pstmt.setInt(2, athleteId2);
            pstmt.setInt(3, athleteId2);
            pstmt.setInt(4, athleteId1);
            results = pstmt.executeQuery();
            if(results.next()) {
                results.close();
                return ALREADY_EXISTS;
            }
            //end check if already friends
            else { //everything good! lets make new friends!
                pstmt = connection.prepareStatement("INSERT INTO Friends" +
                        " VALUES ( ? , ? )");
                pstmt.setInt(1 , athleteId1);
                pstmt.setInt(2 , athleteId2);
                pstmt.execute();
            }

        } catch (SQLException e) {
            return ERROR;
            //e.printStackTrace()();
        }
        finally {
            try {
                if(pstmt != null){
                    pstmt.close();
                }
                if(pstmt1 != null){
                    pstmt1.close();
                }
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

    public static ReturnValue removeFriendship(Integer athleteId1, Integer athleteId2) {



        Connection connection = DBConnector.getConnection();
        PreparedStatement pstmt = null;
        PreparedStatement pstmt1 = null;
        PreparedStatement pstmt2 = null;
        ResultSet results;
        ResultSet results1;
        ResultSet results2;
        try {
            //check if athletes exists and if they are friends
            pstmt = connection.prepareStatement("SELECT Id FROM Athletes" +
                    " WHERE Id = ?");
            pstmt.setInt(1,athleteId1);
            pstmt1 = connection.prepareStatement("SELECT Id From Athletes" +
                    " WHERE Id = ?");
            pstmt1.setInt(1,athleteId2);
            pstmt2 = connection.prepareStatement("SELECT Aid1,Aid2 From Friends" +
                    " WHERE Aid1 = ? And Aid2 = ?" +
                    " UNION " +
                    " SELECT Aid1,Aid2 From Friends " +
                    " WHERE Aid1 = ? And Aid2 = ?");
            pstmt2.setInt(1,athleteId1);
            pstmt2.setInt(2, athleteId2);
            pstmt2.setInt(3, athleteId2);
            pstmt2.setInt(4, athleteId1);


            results=pstmt.executeQuery();
            results1=pstmt1.executeQuery();
            results2=pstmt2.executeQuery();
            if(!results2.next() || !results1.next() || !results.next())
                return NOT_EXISTS;
            //end check if athletes exists

            //check if already friends
            pstmt = connection.prepareStatement("DELETE FROM Friends " +
                    " WHERE Aid1 = ? AND Aid2 = ? " +
                    " OR Aid1 = ? AND Aid2 = ?");

            pstmt.setInt(1,athleteId1);
            pstmt.setInt(2, athleteId2);
            pstmt.setInt(3, athleteId2);
            pstmt.setInt(4, athleteId1);
            pstmt.execute();

        } catch (SQLException e) {
            return ERROR;
            //e.printStackTrace()();
        }
        finally {
            try {
                if(pstmt != null){
                    pstmt.close();
                }
                if(pstmt1 != null){
                    pstmt1.close();
                }
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