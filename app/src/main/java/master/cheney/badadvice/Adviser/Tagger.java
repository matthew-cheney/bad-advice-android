package master.cheney.badadvice.Adviser;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

public class Tagger {

    MaxentTagger tagger;

    public Tagger(String filePath) {

        // File file = new File("english-bidirectional-distsim.tagger");
        // String filepath = file.getPath();

        /*ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL url = classLoader.getResource(filePath);
        String newFilePath = url.toString();

        System.out.println("Current working directory:");
        System.out.println(System.getProperty(newFilePath));
        System.out.println("End directory");
         */

        File file = new File("test_file.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("created test_file.txt");
        System.out.println(System.getProperty("user.dir"));

        tagger = new MaxentTagger();
    }

    public ArrayList<Word> tag(String sent) {

        sent = capitalizeI(sent);

        String tagged = this.tagger.tagString(sent);

        String[] tagged_split_by_spaces = tagged.split(" ");

        ArrayList<Word> tagged_words = new ArrayList<Word>();

        for (String word : tagged_split_by_spaces) {
            String[] parts = word.split("_");
            tagged_words.add(new Word(parts[0], parts[1]));
        }

        if (tagged_words.get(tagged_words.size() - 1).getPos().equals(".")) {
            tagged_words.remove(tagged_words.size() - 1);
        }

        return tagged_words;

    }

    private String capitalizeI(String sent) {
        return sent.replaceAll("\\bi\\b", "I");
    }

}
