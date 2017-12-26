package Test;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Farid on 2017-07-31.
 */
public class Game {

    public static ArrayList<Question> questions = new ArrayList<>();
    public static int score;
    private static Connection connection;

    public static void main(String[] args) {
        initBySQLDB();
        int questionSize = questions.size();
        while (questions.size() > 0) {
            System.out.println(questions.get(0).getText());
            for (int i = 0; i < questions.get(0).getAnswers().length; i++) {
                System.out.println("- " + questions.get(0).getAnswers()[i]);
            }
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nWhich answer is true ?");
            String answer = scanner.nextLine();
            if (answer.equalsIgnoreCase(questions.get(0).getRightAnswer())) {
                System.out.println("TRUE =============");
                score++;
            } else System.out.println("!!!!!!!!!!!!!!!!!! FALSE");
            questions.remove(0);
        }
        System.out.printf("You have answered true : %d of %d questions.", score, questionSize);
    }

    public static void initBySQLDB() {
            try (Connection cn = connection =
                    DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306?useSSL=true",
                            "root",
                            "password"))
            {
                Statement statement = connection.createStatement();
                ResultSet rsToGetSize = statement.executeQuery("SELECT COUNT(*) FROM questiongame.question");
                rsToGetSize.next();
                int tableSize = rsToGetSize.getInt(1);
                int counter = 1;
                while(counter!=tableSize+1){
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM questiongame.question WHERE Question_ID= " + counter);
                    while (resultSet.next()) {
                        String question_text = resultSet.getString("Question_Text");
                        String trueAnswer = resultSet.getString("True_Answer");
                        String falseAnswer1 = resultSet.getString("FalseAnswer1");
                        String falseAnswer2 = resultSet.getString("FalseAnswer2");
                        String falseAnswer3 = resultSet.getString("FalseAnswer3");
                        Question question = new Question(question_text, trueAnswer, falseAnswer1, falseAnswer2, falseAnswer3);
                        questions.add(question);
                    }
                    counter++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public static void initByFileScanner() {
        try {
            File file = new File("db.txt");
            Scanner fileScanner = new Scanner(file);
            List<String> lines = new ArrayList<>();
            while (fileScanner.hasNext()) {
                lines.add(fileScanner.nextLine());
            }
            for (int i = 0; i < lines.size(); i += 5) {
                Question question = new Question(lines.get(i), lines.get(i + 1), lines.get(i + 2), lines.get(i + 3), lines.get(i + 4));
                questions.add(question);
            }
        } catch (Exception e) {
            System.out.println("File Couldn`t be read.");
        }
    }
}