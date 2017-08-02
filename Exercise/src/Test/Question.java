package Test;

import java.util.Random;

/**
 * Created by Farid on 2017-07-31.
 */
public class Question {

    private String text;
    private String[] answers;
    private String rightAnswer;

    public Question(String text, String... answers) {
        this.text = text;
        this.answers = answers;
        this.rightAnswer = answers[0];

        for (int i = 0; i < answers.length; i++) {
            Random random = new Random();
            int tmpInt = random.nextInt(answers.length);
            String tmpText = answers[i];
            answers[i] = answers[tmpInt];
            answers[tmpInt] = tmpText;
        }
    }

    public String getText() {
        return text;
    }

    public String[] getAnswers() {
        return answers;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }
}
