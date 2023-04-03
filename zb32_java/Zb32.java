import java.util.Random;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;


public class Zb32
{
    public static final String ALPHABET = "ybndrfg8ejkmcpqxot1uwisza345h769";

    public static void main(String[] args)
    {
        ArgumentParser parser = ArgumentParsers.newFor("zb32").build().description("Z-Base-32 random string generator");
        parser.addArgument("-s", "--seed").type(Integer.class).help("Use a specific random seed");
        parser.addArgument("-l", "--length").type(Integer.class).setDefault(16).help("Length of each generated string");
        parser.addArgument("-c", "--count").type(Integer.class).setDefault(1).help("Count of strings to generate");
        try
        {
            Namespace parsedArgs = parser.parseArgs(args);
            Integer seed = parsedArgs.get("seed");
            int length = parsedArgs.get("length");
            int count = parsedArgs.get("count");

            Random rng = (seed != null) ? new Random(seed) : new Random();

            for(int i = 0; i < count; i++)
            {
                System.out.println(generateZb32String(rng, length));
            }           
        }
        catch(ArgumentParserException e)
        {
            parser.handleError(e);
        }
    }

    private static String generateZb32String(Random rng, int length)
    {
        StringBuilder sb = new StringBuilder(length);

        for(int i = 0; i < length; i++)
        {
            sb.append(ALPHABET.charAt(rng.nextInt(32)));
        }
        
        return sb.toString();
    }
}
