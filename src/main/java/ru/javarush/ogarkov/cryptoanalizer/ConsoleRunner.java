package ru.javarush.ogarkov.cryptoanalizer;

import ru.javarush.ogarkov.cryptoanalizer.constants.Constants;
import ru.javarush.ogarkov.cryptoanalizer.entity.Result;

import java.util.Arrays;

public class ConsoleRunner {
    public static void main(String[] args) {
        Application application = new Application();
        Result result = application.run(args);//закинул args в метод run класса Application
        System.out.println(result);
    }
}
/*
encode
Enter source (full path OR only filename OR Enter for )
Enter destination (full path OR only filentve OR Enter
Enter key (int number OR Enter for key=1)

decode
Enter source (full path OR only filename OR Enter for )
Enter destination (full path OR only filentve OR Enter
Enter key (int number OR Enter for key=1)


bruteforce
Enter source (full path OR only filename OR Enter for )
Enter destination (full path OR only filentve OR Enter

analyze
Enter source (full path OR only filename OR Enter for )
Enter dictionary (full path OR only filentve OR Enter
Enter destination (full path OR only filentve OR Enter


Plese select mode:
1. Encrypt
2. Decrypt
3. Brute force
4. Analyze
*/