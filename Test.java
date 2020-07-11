package temapoo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

public class Test {
    public static void main(String[] args) {
        String delims = "[;]";
        BufferedReader fileCampaign;
        VMS voucherManagement = VMS.getInstance();
        try {
            // System.out.println("SUNTEM LA CAMPANII");
            fileCampaign = new BufferedReader((new FileReader("C:\\Users\\Alice\\Desktop\\temapoo\\test02\\input\\campaigns.txt")));
            String line = fileCampaign.readLine();
            int nrCampaigns = Integer.parseInt(line);
            //System.out.println(nrCampaigns);
            String currentDate = fileCampaign.readLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(currentDate, formatter);
            //System.out.println(dateTime);
            line = fileCampaign.readLine();
            while (line != null) {

                String[] tokens = line.split(delims);
                Integer id = Integer.valueOf(tokens[0]);
                String name = tokens[1];
                String description = tokens[2];
                LocalDateTime startDate = LocalDateTime.parse(tokens[3], formatter);
                LocalDateTime endDate = LocalDateTime.parse(tokens[4], formatter);
                int budget = Integer.parseInt(tokens[5]);
                String strategyType = tokens[6];
                Campaign newCampaign = new Campaign(id, name, description, startDate, endDate, budget, strategyType, dateTime);
                voucherManagement.addCampaign(newCampaign);
                //  System.out.println(newCampaign.getName());

                //System.out.println(line);
                //read next line
                line = fileCampaign.readLine();
            }
            Vector<Campaign> Campaigns = voucherManagement.getCampaigns();
            for (int i = 0; i < Campaigns.size(); i++) {
                Campaign thisCampaign = Campaigns.get(i);
                //System.out.println(thisCampaign.getName());
            }
            fileCampaign.close();
            //System.out.println("\n");

        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            //   System.out.println("SUNTEM LA USERI");
            BufferedReader secondReader;
            secondReader = new BufferedReader(new FileReader("C:\\Users\\Alice\\Desktop\\temapoo\\test02\\input\\users.txt"));
            String lineUsers = secondReader.readLine();
            int nrUsers = Integer.parseInt(lineUsers);
            //System.out.println(nrUsers);
            lineUsers = secondReader.readLine();
            while (lineUsers != null) {
                String[] tokens = lineUsers.split(delims);
                Integer id = Integer.valueOf(tokens[0]);
                String name = tokens[1];
                String password = tokens[2];
                String email = tokens[3];
                String userT = tokens[4];
                User newUser = new User(id, name, email, password, userT);
                // System.out.println(lineUsers);
                voucherManagement.addUser(newUser);
                lineUsers = secondReader.readLine();
            }
            Vector<User> Users = voucherManagement.getUsers();
            for (int i = 0; i < Users.size(); i++) {
                User thisUser = Users.get(i);
                //  System.out.println(thisUser.getName());
                //System.out.println(thisUser.getEmail());
            }
            //System.out.println("\n");
            secondReader.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            // System.out.println("AM AJUNS LA EVENTS");
            BufferedReader thirdReader;
            thirdReader = new BufferedReader((new FileReader("C:\\Users\\Alice\\Desktop\\temapoo\\test02\\input\\events.txt")));
            String eventsLine = thirdReader.readLine();
            String currentDate = eventsLine;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(currentDate, formatter);
            //System.out.println(dateTime);
            eventsLine = thirdReader.readLine();
            int nrEvents = Integer.parseInt(eventsLine);
            //System.out.println(nrEvents);
            eventsLine = thirdReader.readLine();
            while (eventsLine != null) {
                String[] tokens = eventsLine.split(delims);
                Integer userId = Integer.valueOf(tokens[0]);
                if (tokens[1].equals("addCampaign")) {
                    if (voucherManagement.isAdmin(userId)) {
                        Integer id = Integer.valueOf(tokens[2]);
                        String name = tokens[3];
                        String description = tokens[4];
                        LocalDateTime startDate = LocalDateTime.parse(tokens[5], formatter);
                        LocalDateTime endDate = LocalDateTime.parse(tokens[6], formatter);
                        int budget = Integer.parseInt(tokens[7]);
                        String strategyType = tokens[8];
                        Campaign newCampaign = new Campaign(id, name, description, startDate, endDate, budget, strategyType, dateTime);
                        voucherManagement.addCampaign(newCampaign);
                    }
                }
                if (tokens[1].equals("editCampaign")) {
                    if (voucherManagement.isAdmin(userId)) {
                        Integer id2 = Integer.valueOf(tokens[2]);
                        String name2 = tokens[3];
                        String description2 = tokens[4];
                        LocalDateTime startDate2 = LocalDateTime.parse(tokens[5], formatter);
                        LocalDateTime endDate2 = LocalDateTime.parse(tokens[6], formatter);
                        int budget2 = Integer.parseInt(tokens[7]);
                        String strategyType2 = null;
                        Campaign newCampaign2 = new Campaign(id2, name2, description2, startDate2, endDate2, budget2, strategyType2, dateTime);
                        voucherManagement.updateCampaign(id2, newCampaign2);
                    }
                }
                if (tokens[1].equals("cancelCampaign")) {
                    if (voucherManagement.isAdmin(userId)) {
                        Integer id = Integer.valueOf(tokens[2]);
                        voucherManagement.cancelCampaign(id);
                    }
                    else System.out.println("[]");
                }
                if (tokens[1].equals("generateVoucher")) {
                    if (voucherManagement.isAdmin(userId)) {
                        Integer id = Integer.valueOf(tokens[2]);
                        String email = tokens[3];
                        User user = voucherManagement.getUser(email);
                        String Type = tokens[4];
                        float value = Float.parseFloat(tokens[5]);
                        Campaign campaign = voucherManagement.getCampaign(id);
                        if(campaign.getObservers()!=null)
                        {
                            boolean found = false;
                            for(User u : campaign.getObservers()) {
                                if (u.getId().equals(userId)) {
                                    found = true;
                                    break;
                                }
                                }
                            if(found)
                            {
                                campaign.removeObserver(user);
                            }

                        }
                        if(campaign.getEndDate().compareTo(dateTime)>0)
                        {
                            campaign.addObserver(user);
                            campaign.generateVoucher(email, Type, value);
                        }

                    }
                }
                if (tokens[1].equals("redeemVoucher")) {
                    if (voucherManagement.isAdmin(userId)) {
                        Integer idCampaign = Integer.valueOf(tokens[2]);
                        Campaign campaign = voucherManagement.getCampaign(idCampaign);
                        String idVoucher = tokens[3];
                        LocalDateTime localDate = LocalDateTime.parse(tokens[4], formatter);
                        User user = voucherManagement.getUser(userId);
                        campaign.redeemVoucher(idVoucher, localDate);
                    }
                }
                if (tokens[1].equals("getVouchers")) {
                    if(!voucherManagement.isAdmin(userId))
                    {
                        User user = voucherManagement.getUser(userId);
                        UserVoucherMap vouchers = user.getVouchers();
                        System.out.println(vouchers.toString());
                    }
                }

                if (tokens[1].equals("getObservers")) {
                    if (voucherManagement.isAdmin(userId)) {
                        Integer idCampaign = Integer.valueOf(tokens[2]);
                        Campaign campaign = voucherManagement.getCampaign(idCampaign);
                        System.out.println(campaign.getObservers());
                    }
                }

                if (tokens[1].equals("getNotifications")) {
                    if(!voucherManagement.isAdmin(userId))
                    {
                        User user = voucherManagement.getUser(userId);
                        for(Notification n:user.getNotifications())
                        System.out.println("["+n.campaignId+";["+userId+"];"+dateTime+";"+n.type+"]");

                    }

                }

            eventsLine = thirdReader.readLine();

        }

            thirdReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
