package utils;

import com.fasterxml.jackson.annotation.JsonProperty;


public final class TestAccount {
  @JsonProperty ("email")
  private String email = "";

  @JsonProperty ("password")
  private String password = "";

  @JsonProperty ("firstName")
  private String firstName = "";

  @JsonProperty ("lastName")
  private String lastName = "";


  /**
   * Default constructor.
   */
  public TestAccount() {

  }

  /**
   * Constructor of the TestAccount class. Test Account include the following information
   *
   * @param email email
   * @param password password
   * @param firstName first name
   * @param lastName last name
   */
  public TestAccount(String email, String password, String firstName, String lastName) {
    this.email = email;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public String email() {
    return this.email;
  }

  public String password() {
    return this.password;
  }

  public String firstName() {
    return this.firstName;
  }

  public String lastName() {
    return this.lastName;
  }


}
