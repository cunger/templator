package org.okbqa.tripletempeh.interpreter;

import org.okbqa.tripletempeh.interpreter.grammar.DependenciesParser;
import org.okbqa.tripletempeh.interpreter.grammar.DependenciesLexer;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.okbqa.tripletempeh.graph.Graph;

/**
 *
 * @author cunger
 */
public class Interpreter {
           
    public Interpreter() {
    }
    
    public Graph interpret(String dependency) {
        
        try {
            // Parse dependency string
            
            InputStream stream = new ByteArrayInputStream(dependency.getBytes(StandardCharsets.UTF_8));
            ANTLRInputStream input = new ANTLRInputStream(stream);
            
            DependenciesLexer  lexer  = new DependenciesLexer(input);
            CommonTokenStream  tokens = new CommonTokenStream(lexer);
            DependenciesParser parser = new DependenciesParser(tokens);
            
            ParseTree tree = parser.graph(); 
            
            // Constructor graph from parse tree 
            
            ParseTreeWalker walker = new ParseTreeWalker();
            GraphConstructor constructor = new GraphConstructor();
            walker.walk(constructor,tree);
            
            return(constructor.graph);

        } catch (IOException ex) {
            Logger.getLogger(Interpreter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
}
