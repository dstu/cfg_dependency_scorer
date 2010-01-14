/**
 * 
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import ling.*;
import scorer.TreeEvaluator;
import ling.PennTreeReader;

/**
 * @author Deepak Santhanam
 * deepak@cs.brown.edu
 */
public class Tester {

	public static void main(String[] args) {
            String goldFileName = "22.gold";
            String evalFileName = "22p.txt";
            if (args.length == 2) {
                goldFileName = args[0];
                evalFileName = args[1];
            } else if (args.length != 0) {
                System.err.println("Usage: java Tester [goldfile] [testfile]");
                System.err.println("Default goldfile: " + goldFileName);
                System.err.println("Default testfile: " + evalFileName);
                System.exit(-1);
            }

	    PennTreeReader _goldReader = null;
            try {
                _goldReader = new PennTreeReader(new FileReader(goldFileName));
            } catch (FileNotFoundException fnfe) {
                System.err.println("File " + goldFileName + " does not exist.");
                System.exit(-1);
            }

	    PennTreeReader _evalReader = null;
            try {
                _evalReader = new PennTreeReader(new FileReader(evalFileName));
            } catch (FileNotFoundException fnfe) {
                System.err.println("File " + evalFileName + " does not exist.");
                System.exit(-1);
            }

            TreeEvaluator _tEval = new TreeEvaluator();

	    double _sum=0; int _nos=0;
	    
	    ArrayList<Double> _scores = new ArrayList<Double>();
	    
	   while(_goldReader.hasNext() && _evalReader.hasNext()) {
	    	Double _pval = new Double(_tEval.EvaluateTree(_goldReader.next(),_evalReader.next()));
	    	_scores.add(_pval);
	    	_sum=_sum+_pval.doubleValue();
	    	_nos++;
	    	System.out.println(_nos +" : "+ _pval.doubleValue());
	   }
	}	
}
