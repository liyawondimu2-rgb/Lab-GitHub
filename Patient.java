
/**
 * Patient class stores personal and emergency contact information.
 * Course: CMSC 203
 * Platform: Eclipse IDE / Java
 */
public class Patient
{
    private String firstName;
    private String middleName;
    private String lastName;
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
    private String phoneNumber;
    private String emergencyContactName;
    private String emergencyContactPhone;

    /** Creates a patient with empty information. */
    public Patient()
    {
        this("", "", "", "", "", "", "", "", "", "");
    }

    /** Creates a patient using the three name fields. */
    public Patient(String firstName, String middleName, String lastName)
    {
        this(firstName, middleName, lastName,
             "", "", "", "", "", "", "");
    }

    /** Creates a patient using all ten attributes. */
    public Patient(String firstName, String middleName, String lastName,
                   String streetAddress, String city, String state,
                   String zipCode, String phoneNumber,
                   String emergencyContactName, String emergencyContactPhone)
    {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
        this.emergencyContactName = emergencyContactName;
        this.emergencyContactPhone = emergencyContactPhone;
    }

    /** Returns the patient's first name. */
    public String getFirstName()
    {
        return firstName;
    }

    /** Updates the patient's first name. */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    /** Returns the patient's middle name. */
    public String getMiddleName()
    {
        return middleName;
    }

    /** Updates the patient's middle name. */
    public void setMiddleName(String middleName)
    {
        this.middleName = middleName;
    }

    /** Returns the patient's last name. */
    public String getLastName()
    {
        return lastName;
    }

    /** Updates the patient's last name. */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    /** Returns the street address. */
    public String getStreetAddress()
    {
        return streetAddress;
    }

    /** Updates the street address. */
    public void setStreetAddress(String streetAddress)
    {
        this.streetAddress = streetAddress;
    }

    /** Returns the city. */
    public String getCity()
    {
        return city;
    }

    /** Updates the city. */
    public void setCity(String city)
    {
        this.city = city;
    }

    /** Returns the state. */
    public String getState()
    {
        return state;
    }

    /** Updates the state. */
    public void setState(String state)
    {
        this.state = state;
    }

    /** Returns the ZIP code. */
    public String getZipCode()
    {
        return zipCode;
    }

    /** Updates the ZIP code. */
    public void setZipCode(String zipCode)
    {
        this.zipCode = zipCode;
    }

    /** Returns the patient's phone number. */
    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    /** Updates the patient's phone number. */
    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    /** Returns the emergency contact name. */
    public String getEmergencyContactName()
    {
        return emergencyContactName;
    }

    /** Updates the emergency contact name. */
    public void setEmergencyContactName(String emergencyContactName)
    {
        this.emergencyContactName = emergencyContactName;
    }

    /** Returns the emergency contact phone number. */
    public String getEmergencyContactPhone()
    {
        return emergencyContactPhone;
    }

    /** Updates the emergency contact phone number. */
    public void setEmergencyContactPhone(String emergencyContactPhone)
    {
        this.emergencyContactPhone = emergencyContactPhone;
    }

    /** Combines the patient's first, middle, and last names. */
    public String buildFullName()
    {
        return firstName + " " + middleName + " " + lastName;
    }

    /** Combines all parts of the patient's address. */
    public String buildAddress()
    {
        return streetAddress + " " + city + " " + state + " " + zipCode;
    }

    /** Combines the emergency contact name and phone number. */
    public String buildEmergencyContact()
    {
        return emergencyContactName + " " + emergencyContactPhone;
    }

    /** Checks the required phone number format. */
    public boolean isValidPhoneNumber()
    {
        return phoneNumber != null &&
               phoneNumber.matches("\\d{3}-\\d{3}-\\d{4}");
    }

    /** Checks the emergency phone number format. */
    public boolean isValidEmergencyPhoneNumber()
    {
        return emergencyContactPhone != null &&
               emergencyContactPhone.matches("\\d{3}-\\d{3}-\\d{4}");
    }

    /** Returns the patient's name in last-first-middle order. */
    public String getLastFirstMiddle()
    {
        return lastName + ", " + firstName + " " + middleName;
    }

    /** Checks whether the patient lives in a given city and state. */
    public boolean hasSameCityState(String city, String state)
    {
        return this.city != null && this.state != null &&
               this.city.equalsIgnoreCase(city) &&
               this.state.equalsIgnoreCase(state);
    }

    /** Updates all four parts of the address. */
    public void updateAddress(String street, String city,
                              String state, String zip)
    {
        this.streetAddress = street;
        this.city = city;
        this.state = state;
        this.zipCode = zip;
    }

    /** Returns the patient and emergency contact phone details. */
    public String getContactSummary()
    {
        return "Phone Number: " + phoneNumber +
               "\nEmergency Contact: " + buildEmergencyContact();
    }

    /** Returns all patient information in readable form. */
    @Override
    public String toString()
    {
        return "Name: " + buildFullName() +
               "\nAddress: " + buildAddress() +
               "\nPhone Number: " + phoneNumber +
               "\nEmergency Contact: " + buildEmergencyContact() +
               "\nPhone Valid: " + isValidPhoneNumber() +
               "\nEmergency Phone Valid: " +
               isValidEmergencyPhoneNumber();
    }
}
