import java.util.Properties;
import java.util.Scanner;

public class CustomerUtil {

    PropteriesUtil pu = new PropteriesUtil();
    CustomerRepo cr = new CustomerRepo();
    Scanner scanner = new Scanner(System.in);
    String firstName, familyName, emailAddress, password, streetAddress, postalAddress;
    int phoneNumber, postalCode;

    public void registerNewCustomer() {

        System.out.println("Du får nu registrera dig.");
        System.out.println("Ange förnamn: ");
        firstName = scanner.nextLine();

        System.out.println("Ange efternamn: ");
        familyName = scanner.nextLine();

        System.out.println("Ange email: "); //Lägga in kontroll?
        emailAddress = scanner.nextLine();

        System.out.println("Ange önskat lösenord: "); //Lägga in kontroll? Minst x antal tecken...
        password = scanner.nextLine();

        System.out.println("Ange telefonnummer: ");
        phoneNumber = Integer.parseInt(scanner.nextLine());

        System.out.println("Ange gatuadress: ");
        streetAddress = scanner.nextLine();

        System.out.println("Ange postnummer: ");
        postalCode = Integer.parseInt(scanner.nextLine());

        System.out.println("Ange postadress: ");
        postalAddress = scanner.nextLine();

        cr.addNewCustomer(firstName,familyName,emailAddress, password,
                phoneNumber, streetAddress, postalCode, postalAddress );

        System.out.println("Du är nu registrerad och kommer att få logga in.");
    }

    public Customer checkIfEmailBelongsToCustomer() {

        Customer temp = null;
        System.out.println("Ange din email: ");
        emailAddress = scanner.nextLine();

        if (cr.checkIfRegisteredCustomerByEmail(emailAddress)) {
            temp = cr.getCustomerByEmail(emailAddress);
        }
        return  temp;
    }

    public Boolean askUserIfRegisteredCustomer() {
        System.out.println("Är du en befintlig kund? (Ja/nej)");
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("Ja")) {
            return true;
        }
        else return false;
    }
    public String promptForCorrectPassword() {

        System.out.println("Ange ditt lösenord: ");
        return scanner.nextLine();
    }
    public Boolean logInFailedRetryOrRegister() {
        System.out.println("Du har angett felaktig email och/eller lösenord. " +
                "Försök igen eller registrera dig som ny kund.");
        System.out.println("Försök igen (Ja). Eller registrera dig som ny kund (Nej)");
        String input = scanner.nextLine();
        if(input.equalsIgnoreCase("Ja")) {return true;}
        else {return false;}

    }

        //System.out.println("Ange ditt lösenord: ");
    //        password = scanner.nextLine();
}
