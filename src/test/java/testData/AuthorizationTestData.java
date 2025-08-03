package testData;

import com.github.javafaker.Faker;

public class AuthorizationTestData {

    static Faker faker = new Faker();
    public String
            userWrongEmail = faker.internet().emailAddress(),
            userWrongPassword = faker.internet().password(),
            userEmail = "vip.persik@internet.ru",
            userPassword = "@nYQ46Af-hb^M24";

}
