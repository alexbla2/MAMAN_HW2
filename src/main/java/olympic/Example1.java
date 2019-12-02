package olympic;

import olympic.business.Athlete;
import olympic.business.ReturnValue;
import olympic.business.Sport;

public class Example1 {

    public static void main(String[] args) {


        Solution.dropTables();
        Solution.createTables();
//        Test1();    // BASIC  API
        Solution.clearTables();
        Test2();    // BASIC  API
        Solution.dropTables();

    }

    public static void Test1() {
        Sport s = new Sport();
        s.setId(1);
        s.setName("football");
        s.setCity("TLV");

        Athlete a = new Athlete();
        a.setId(1);
        a.setCountry("Israel");
        a.setName("gadi");

        Solution.addAthlete(a);
        Solution.addSport(s);

        if(Solution.getSport(s.getId()).equals(s) == Boolean.TRUE)
            System.out.println("1.1 OK");
        if(Solution.getAthleteProfile(a.getId()).equals(a) == Boolean.TRUE)
            System.out.println("1.2 OK");
        if(Solution.getSport(2).equals(Sport.badSport()) == Boolean.TRUE)
            System.out.println("1.3 OK");

        Solution.athleteJoinSport(s.getId(),a.getId());

        if(Solution.confirmStanding(s.getId(),a.getId(),5) == ReturnValue.NOT_EXISTS)
            System.out.println("1.4 OK");
        if (Solution.confirmStanding(2,a.getId(),5) == ReturnValue.NOT_EXISTS)
            System.out.println("1.5 OK");
        a.setId(2);
        a.setIsActive(true);
        Solution.addAthlete(a);
        Solution.athleteJoinSport(s.getId(),a.getId());
        if (Solution.confirmStanding(s.getId(),a.getId(),3) == ReturnValue.OK)
            System.out.println("1.6 OK");

        if(Solution.athleteLeftSport(s.getId(),3) == ReturnValue.NOT_EXISTS)
            System.out.println("1.7 OK");
        if(Solution.athleteLeftSport(3,a.getId()) == ReturnValue.NOT_EXISTS)
            System.out.println("1.8 OK");
        if(Solution.athleteLeftSport(s.getId(),a.getId()) == ReturnValue.OK)
            System.out.println("1.9 OK");

        Athlete b = new Athlete();
        b.setId(11);
        b.setCountry("Israel");
        b.setName("g");
        b.setIsActive(true);

        Solution.addAthlete(b);
        Solution.athleteJoinSport(s.getId(),b.getId());
        if(Solution.makeFriends(a.getId(),b.getId()) == ReturnValue.OK)
            System.out.println("1.10 OK");
        if(Solution.makeFriends(a.getId(),b.getId()) == ReturnValue.ALREADY_EXISTS)
            System.out.println("1.11 OK");
        if(Solution.makeFriends(b.getId(),a.getId()) == ReturnValue.ALREADY_EXISTS)
            System.out.println("1.12 OK");
        if(Solution.makeFriends(a.getId(),a.getId()) == ReturnValue.BAD_PARAMS)
            System.out.println("1.13 OK");

        if(Solution.removeFriendship(a.getId(),a.getId()) == ReturnValue.NOT_EXISTS)
            System.out.println("1.14 OK");
        if(Solution.removeFriendship(b.getId(),a.getId()) == ReturnValue.OK)
            System.out.println("1.15 OK");

        if(Solution.changePayment(b.getId(),s.getId(),200) == ReturnValue.NOT_EXISTS)
            System.out.println("1.16 OK");

        Athlete c = new Athlete();
        c.setId(22);
        c.setCountry("Canada");
        c.setName("g");

        Solution.addAthlete(c);
        // a && c not in Joined, b in Joined => a not popular
        if(Solution.makeFriends(b.getId(),a.getId()) == ReturnValue.OK)
            System.out.println("1.17 OK");
        if(Solution.makeFriends(a.getId(),c.getId()) == ReturnValue.OK)
            System.out.println("1.18 OK");
        if(!Solution.isAthletePopular(a.getId()))
            System.out.println("1.19 OK");

        if(Solution.athleteLeftSport(s.getId(),b.getId()) == ReturnValue.OK)
            System.out.println("1.20 OK");
        if(Solution.isAthletePopular(a.getId()))
            System.out.println("1.21 OK");
        Solution.clearTables();

        Solution.addAthlete(a);
        Solution.addAthlete(b);
        Solution.addAthlete(c);
        Solution.addSport(s);
        Solution.athleteJoinSport(s.getId(),a.getId());
        Solution.athleteJoinSport(s.getId(),b.getId());
        Solution.athleteJoinSport(s.getId(),c.getId());

        Solution.confirmStanding(s.getId(),a.getId(),1);
        Solution.confirmStanding(s.getId(),b.getId(),2);
        Solution.confirmStanding(s.getId(),c.getId(),2);

        if(Solution.getTotalNumberOfMedalsFromCountry("Israel") == 2)
            System.out.println("1.22 OK");
        if(Solution.getTotalNumberOfMedalsFromCountry("Canada") == 1)
            System.out.println("1.23 OK");
        Solution.athleteDisqualified(s.getId(),a.getId());
        if(Solution.getTotalNumberOfMedalsFromCountry("Israel") == 1)
            System.out.println("1.24 OK");
        Solution.changePayment(a.getId(),s.getId(),100);
        Solution.changePayment(b.getId(),s.getId(),100);
        Solution.changePayment(c.getId(),s.getId(),100);

        if(Solution.getIncomeFromSport(s.getId()) == 100)
            System.out.println("1.25 OK");

        Athlete d = new Athlete();
        d.setId(22);
        d.setCountry("France");
        d.setName("g");
        d.setIsActive(true);
        Solution.addAthlete(d);
        Solution.athleteJoinSport(s.getId(),d.getId());
        Solution.changePayment(d.getId(),s.getId(),300);
        if(Solution.getIncomeFromSport(s.getId()) == 100)
            System.out.println("1.26 OK");
        Solution.athleteLeftSport(s.getId(),a.getId());
        Solution.athleteJoinSport(s.getId(),a.getId());
        Solution.confirmStanding(s.getId(),d.getId(),2);
        Solution.confirmStanding(s.getId(),a.getId(),3);
        if(Solution.getBestCountry().contains("Israel"))  //  should by Israel
            System.out.println("1.27 OK");
    }

    public static void Test2() {
        Solution.clearTables();
        Athlete a = new Athlete();
        Sport s = new Sport();
        a.setId(1);
        a.setName("yossi");
        a.setCountry("israel");
        a.setIsActive(true);
        Solution.addAthlete(a);
        a.setId(2);
        a.setName("gadi");
        a.setCountry("israel");
        a.setIsActive(false);
        Solution.addAthlete(a);
        a.setId(3);
        a.setName("moshe");
        a.setCountry("canada");
        a.setIsActive(true);
        Solution.addAthlete(a);
        s.setId(12);
        s.setName("tennis");
        s.setCity("tel-aviv");
        Solution.addSport(s);
        s.setId(1);
        s.setName("swim");
        s.setCity("tel-aviv");
        Solution.addSport(s);
        s.setId(3);
        s.setName("football");
        s.setCity("haifa");
        Solution.addSport(s);
        Solution.athleteJoinSport(12,1);
        Solution.athleteJoinSport(1,2);
        Solution.makeFriends(1,2);
        Solution.makeFriends(1,3);

        if(!Solution.isAthletePopular(1))
            System.out.println("2.1 failed");
        if(Solution.isAthletePopular(2))
            System.out.println("2.2 failed");
        if(Solution.getIncomeFromSport(1) != 100)
            System.out.println("2.3 failed");
        if(Solution.confirmStanding(12,1,1) != ReturnValue.OK)
            System.out.println("2.4 failed");
        if(Solution.confirmStanding(1,2,1) != ReturnValue.NOT_EXISTS)
            System.out.println("2.5 failed");
        if(Solution.getTotalNumberOfMedalsFromCountry("israel") != 1)
            System.out.println("2.6 failed");
        if(!Solution.getBestCountry().contains("israel"))
            System.out.println("2.7 failed");
        if(Solution.athleteDisqualified(1,2)!= ReturnValue.OK)
            System.out.println("2.8 failed");
        if(!Solution.getBestCountry().contains("israel"))
            System.out.println("2.9 failed");
        if(Solution.athleteDisqualified(12,1)!= ReturnValue.OK)
            System.out.println("2.10 failed");
        if(!Solution.getBestCountry().equals(""))
            System.out.println("2.11 failed");
        if(Solution.confirmStanding(12,1,1) != ReturnValue.OK)   // disqualified athletes can get medals
            System.out.println("2.12 failed");
        Solution.athleteJoinSport(12,3);
        Solution.athleteJoinSport(2,3);
        Solution.confirmStanding(12,3,1);
        Solution.confirmStanding(2,3,2);
        if(Solution.getTotalNumberOfMedalsFromCountry("canada") != 1)
            System.out.println("2.13 failed");
        if(!Solution.getBestCountry().contains("canada"))
            System.out.println("2.14 failed");
        if(!Solution.getMostPopularCity().contains("tel-aviv"))
            System.out.println("2.15 failed");
        if(Solution.deleteAthlete(Solution.getAthleteProfile(1)) != ReturnValue.OK)
            System.out.println("2.16 failed");
        if(Solution.athleteLeftSport(12,1) != ReturnValue.NOT_EXISTS) // should have been deleted on previous test
            System.out.println("2.17 failed");
        Solution.athleteLeftSport(12,3);
        Solution.athleteLeftSport(2,3);
        Solution.athleteLeftSport(1,2);
        if(!Solution.getBestCountry().equals(""))   // no athletes observing/participating in sports
            System.out.println("2.16 failed");
        if(!Solution.getMostPopularCity().equals(""))   // no athletes observing/participating in sports
            System.out.println("2.17 failed");
        Solution.deleteAthlete(Solution.getAthleteProfile(2));
        Solution.deleteAthlete(Solution.getAthleteProfile(3));
        if(!Solution.getBestCountry().isEmpty())   // no athletes in DB
            System.out.println("2.18 failed");
        if(!Solution.getMostPopularCity().isEmpty())   // no athletes in DB
            System.out.println("2.19 failed");
        Solution.deleteSport(Solution.getSport(12));
        Solution.deleteSport(Solution.getSport(1));
        Solution.deleteSport(Solution.getSport(3));

        a.setId(1);
        a.setName("A");
        a.setCountry("A");
        a.setIsActive(true);
        Solution.addAthlete(a);
        a.setId(2);
        Solution.addAthlete(a);
        s.setId(11);
        s.setName("S");
        s.setCity("SA");
        Solution.addSport(s);
        s.setId(22);
        s.setName("S");
        s.setCity("SB");
        Solution.addSport(s);
        Solution.athleteJoinSport(11,1);
        Solution.athleteJoinSport(11,2);
        Solution.athleteJoinSport(22,1);
        Solution.athleteJoinSport(22,2);
        if(!Solution.getMostPopularCity().contains("SA"))   // same AVG for SA and SB. should choose by LEX sort
            System.out.println("2.20 failed");
    }
}
