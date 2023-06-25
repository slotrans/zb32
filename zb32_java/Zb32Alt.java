import java.util.Random;

import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;


public class Zb32Alt
{
    public static final String ALPHABET = "ybndrfg8ejkmcpqxot1uwisza345h769";

    private final Random rng;
    private final int length;
    private final int count;

    private Zb32Alt(Integer seed, int length, int count)
    {
        this.rng = (seed != null) ? new Random(seed) : new Random();
        this.length = length;
        this.count = count;
    }

    public static void main(String[] args)
    {
        ArgumentParser parser = ArgumentParsers.newFor("zb32").build().description("Z-Base-32 random string generator");
        parser.addArgument("-s", "--seed").type(Integer.class).help("Use a specific random seed");
        parser.addArgument("-l", "--length").type(Integer.class).setDefault(16).help("Length of each generated string");
        parser.addArgument("-c", "--count").type(Integer.class).setDefault(1).help("Count of strings to generate");
        try
        {
            Namespace parsedArgs = parser.parseArgs(args);
            Zb32Alt program = new Zb32Alt(parsedArgs.get("seed"), parsedArgs.get("length"), parsedArgs.get("count"));
            program.run();
        }
        catch(ArgumentParserException e)
        {
            parser.handleError(e);
        }
    }

    private void run()
    {
        for(int i = 0; i < count; i++)
        {
            System.out.println(generateZb32String());
        }
    }

    private String generateZb32String()
    {
        StringBuilder sb = new StringBuilder(length);

        for(int i = 0; i < length; i++)
        {
            sb.append(ALPHABET.charAt(rng.nextInt(32)));
        }

        return sb.toString();
    }
}
