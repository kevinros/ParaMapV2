package General_Tools;
import java.io.FileInputStream;
import java.io.InputStream;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.WhitespaceTokenizer;
//The following is based on OpenNLP online tutorial
// for tagging parts of speech
public class Tagger {

    private POSModel model;
    private POSTaggerME tag;

    public Tagger() throws Exception {


        InputStream inputStream = new
            FileInputStream("src/models/en-pos-maxent.bin");
        POSModel model = new POSModel(inputStream);
        POSTaggerME tag = new POSTaggerME(model);

    }

    //Returned a tagged array from a given sentence
    public String[]tagSentence(String sentence){
        //Tokenizing the sentence using WhitespaceTokenizer class
        WhitespaceTokenizer whitespaceTokenizer=WhitespaceTokenizer.INSTANCE;
        String[]tokens=whitespaceTokenizer.tokenize(sentence);

        //Generating tags
        String[]tags = tag.tag(tokens);
        return tags;
    }






    }
