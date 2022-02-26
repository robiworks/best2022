import java.util.*;


public class Alert {

    public static void main(String [] args)
    {
        String to = "best@gmail.com";
        String from = "from@gmail.com";
        String host = "localhost";
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);

        Session session = Session.getDefaultInstance(properties);

        try
        {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("alert");
            message.setText("neki si naredu");

           //kako zdj poslat email hehe 
            System.out.println("Mail successfully sent");
        }
        catch (Exception message)
        {
            message.printStackTrace();
        }
    }
}