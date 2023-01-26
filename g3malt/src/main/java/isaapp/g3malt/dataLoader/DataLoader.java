package isaapp.g3malt.dataLoader;

import isaapp.g3malt.model.*;
import isaapp.g3malt.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserCredentialsRepository userCredentialsRepository;

    @Autowired
    UserTypeRepository userTypeRepository;

    @Autowired
    QuestionnaireRepository questionnaireRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    BloodBankRepository bloodBankRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    BloodBankStorageRepository bloodBankStorageRepository;

    @Override
    public void run(String... args) throws Exception {
        loadUserData();
    }

    private void loadUserData() {
        UserType ut3 = new UserType(3, "ADMIN");
        UserType ut1 = new UserType(1, "STAFF");
        UserType ut2 = new UserType(2, "CUSTOMER");

        List<UserType> userTypes1 = new ArrayList<>();
        List<UserType> userTypes2 = new ArrayList<>();
        List<UserType> userTypes3 = new ArrayList<>();
        userTypes1.add(ut1);
        userTypes2.add(ut2);
        userTypes3.add(ut3);

        GenderType male = GenderType.male;
        GenderType female = GenderType.female;

        Date date1 = new Date(123,11,23,13,00,00);
        Date date2 = new Date(123,11,23,14,00,00);
        Date date3 = new Date(123,11,23,16,00,00);
        Date date4 = new Date(123,11,24,13,00,00);
        Date date5 = new Date(123,11,25,18,00,00);
        Date date6 = new Date(123,11,28,15,00,00);

        User user1 = new User(null, "Aleksa", "Colovic", "Vojvode Sindjelica 13", "Novi Sad", "Srbija", "060165356789", "9876543219876", male, "student", "FTN", userTypes3, "");
        MedicalStaff user2 = new MedicalStaff(null, "Luka", "Mandic", "Rumenacki put 1", "Novi Sad", "Srbija", "06012345235", "0192837465893", male, "student", "FTN", userTypes1, "",null);
        MedicalStaff user3 = new MedicalStaff(null, "Aleksandar", "Stojanovic", "Kamenica", "Novi Sad", "Srbija", "06012347777", "8496572839476", male, "student", "FTN", userTypes1, "",null);

        Customer customer1 = new Customer(null, "Milana", "Dokic", "Nusiceva", "Novi Sad", "Srbija", "060123456789", "1234567891234", female, "student", "FTN", userTypes2, "", 0, LoyaltyType.bronze, 0, null);
        Customer customer2 = new Customer(null, "Tatjana", "Gemovic", "Telep", "Novi Sad", "Srbija", "060123487347", "7584957836578", female, "student", "FTN", userTypes2, "", 0, LoyaltyType.bronze, 0, null);

        UserCredentials userCredentials1 = new UserCredentials(null, "dokic@gmail.com", "123456", customer1, true, null);
        UserCredentials userCredentials2 = new UserCredentials(null, "colovic@gmail.com", "123456", user1, true, null);
        UserCredentials userCredentials3 = new UserCredentials(null, "mandic@gmail.com", "123456", user2, true, null);
        UserCredentials userCredentials4 = new UserCredentials(null, "gemovic@gmail.com", "123456", customer2, true, null);
        UserCredentials userCredentials5 = new UserCredentials(null, "stojanovic@gmail.com", "123456", user3, true, null);

        Questionnaire questionnaire1 = new Questionnaire(null, 0, 3, false, false, false, false, true, false, false, false, false, true, false, true, false, false, false, false);

        BloodBankStorage bloodBankStorage1 = new BloodBankStorage(null, 5, 6, 3, 9, 5, 8, 4, 7);
        BloodBankStorage bloodBankStorage2 = new BloodBankStorage(null, 5, 6, 3, 9, 5, 8, 4, 7);
        BloodBankStorage bloodBankStorage3 = new BloodBankStorage(null, 5, 6, 3, 9, 5, 8, 4, 7);
        BloodBankStorage bloodBankStorage4 = new BloodBankStorage(null, 5, 6, 3, 9, 5, 8, 4, 7);
        BloodBankStorage bloodBankStorage5 = new BloodBankStorage(null, 5, 6, 3, 9, 5, 8, 4, 7);
        BloodBankStorage bloodBankStorage6 = new BloodBankStorage(null, 5, 6, 3, 9, 5, 8, 4, 7);

        BloodBank bloodBank1 = new BloodBank(null, "Bankrvica", "Kosovska 5", "Novi Sad", "Srbija", "Mnogo smo strucni i uvek dostupni", 4.1, null, null, "08:00-20:00", bloodBankStorage1, null);
        BloodBank bloodBank2 = new BloodBank(null, "NewBloodNow", "Pasterova 5", "Novi Sad", "Srbija", "Mnogo smo strucni i uvek dostupni", 3.6, null, null, "08:00-20:00", bloodBankStorage2, null);
        BloodBank bloodBank3 = new BloodBank(null, "WhiteBlood", "Njegoseva 5", "Beograd", "Srbija", "Mnogo smo strucni i uvek dostupni", 4.8, null, null, "08:00-20:00", bloodBankStorage3, null);
        BloodBank bloodBank4 = new BloodBank(null, "Bloody", "Zlatne grede 5", "Beograd", "Srbija", "Mnogo smo strucni i uvek dostupni", 3.9, null, null, "08:00-20:00", bloodBankStorage4, null);
        BloodBank bloodBank5 = new BloodBank(null, "Bloodbankic", "Gagarinova 5", "Pancevo", "Srbija", "Mnogo smo strucni i uvek dostupni", 4.8, null, null, "08:00-20:00", bloodBankStorage5, null);
        BloodBank bloodBank6 = new BloodBank(null, "BloodStore", "Rumenacka 5", "Novi Sad", "Srbija", "Mnogo smo strucni i uvek dostupni", 4.4, null, null, "08:00-20:00", bloodBankStorage6, null);

        Appointment appointment1 = new Appointment(1, null, date1, 30, 1500, null, true);
        Appointment appointment2 = new Appointment(1, null, date2, 30, 1500, null, true);
        Appointment appointment3 = new Appointment(1, null, date3, 30, 1500, null, true);
        Appointment appointment4 = new Appointment(1, null, date4, 30, 1500, null, true);
        Appointment appointment5 = new Appointment(1, null, date5, 30, 1500, null, true);
        Appointment appointment6 = new Appointment(1, null, date6, 30, 1500, null, true);

        if (userTypeRepository.count() == 0) {
            userTypeRepository.save(ut1);
            userTypeRepository.save(ut2);
            userTypeRepository.save(ut3);
        }
        if (userRepository.count() == 0) {
            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);
        }
        if (customerRepository.count() == 0) {
            customerRepository.save(customer1);
            customerRepository.save(customer2);
        }
        if (userCredentialsRepository.count() == 0) {
            userCredentialsRepository.save(userCredentials1);
            userCredentialsRepository.save(userCredentials2);
            userCredentialsRepository.save(userCredentials3);
            userCredentialsRepository.save(userCredentials4);
            userCredentialsRepository.save(userCredentials5);
        }
        if (questionnaireRepository.count() == 0) {
            questionnaireRepository.save(questionnaire1);
        }
        if (bloodBankStorageRepository.count() == 0) {
            bloodBankStorageRepository.save(bloodBankStorage1);
            bloodBankStorageRepository.save(bloodBankStorage2);
            bloodBankStorageRepository.save(bloodBankStorage3);
            bloodBankStorageRepository.save(bloodBankStorage4);
            bloodBankStorageRepository.save(bloodBankStorage5);
            bloodBankStorageRepository.save(bloodBankStorage6);
        }
        if (bloodBankRepository.count() == 0) {
            bloodBankRepository.save(bloodBank1);
            bloodBankRepository.save(bloodBank2);
            bloodBankRepository.save(bloodBank3);
            bloodBankRepository.save(bloodBank4);
            bloodBankRepository.save(bloodBank5);
            bloodBankRepository.save(bloodBank6);
        }
        if (appointmentRepository.count() == 0) {
            appointmentRepository.save(appointment1);
            appointmentRepository.save(appointment2);
            appointmentRepository.save(appointment3);
            appointmentRepository.save(appointment4);
            appointmentRepository.save(appointment5);
            appointmentRepository.save(appointment6);
        }

        System.out.println("Users: " + userRepository.count());
        System.out.println("UserCredentials: " + userCredentialsRepository.count());
    }

}
