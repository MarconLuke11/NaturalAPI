package com.hexaTech.frameworks;

import com.hexaTech.interactor.frameworksInterface.WordParsingInterface;
import net.didion.jwnl.JWNL;
import net.didion.jwnl.JWNLException;
import net.didion.jwnl.data.*;
import net.didion.jwnl.dictionary.Dictionary;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Component
public class WordNet implements WordParsingInterface {

    public boolean thisNounIsASynonymOf(String word, String target) throws FileNotFoundException, JWNLException {
        boolean found=false;
        JWNL.initialize(new FileInputStream(".\\src\\main\\resources\\properties.xml"));
        final Dictionary dictionary = Dictionary.getInstance();
        final IndexWord indexWord = dictionary.lookupIndexWord(POS.NOUN, target);
        if(indexWord==null)
            return found;
        final Synset[] senses = indexWord.getSenses();
        for (Synset synset: senses) {
            Word[] words = synset.getWords();
            for (Word w: words) {
                if (word.equalsIgnoreCase(w.getLemma())) {
                    found = true;
                    break;
                }
            }
        }
        return found;
    }

    public boolean thisVerbIsASynonymOf(String word, String target) throws FileNotFoundException, JWNLException {
        boolean found=false;
        JWNL.initialize(new FileInputStream(".\\src\\main\\resources\\properties.xml"));
        final Dictionary dictionary = Dictionary.getInstance();
        final IndexWord indexWord = dictionary.lookupIndexWord(POS.VERB, target);
        if(indexWord==null)
            return false;
        final Synset[] senses = indexWord.getSenses();
        for (Synset synset: senses) {
            Word[] words = synset.getWords();
            for (Word w: words) {
                if (word.equalsIgnoreCase(w.getLemma())) {
                    found = true;
                    break;
                }
            }
        }
        return found;
    }
}
